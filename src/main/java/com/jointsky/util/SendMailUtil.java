package com.jointsky.util;

/**
 * 用来发送邮件的工具类
 * Created by hasee on 2018/2/17.
 */
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;

public class SendMailUtil {

    private static final PropertiesLoader loader = new PropertiesLoader("mailServer.properties");
    private MimeMessage message;
    private Session session;
    private Transport transport;
    private String mailHost ;
    private String sender_username ;
    private String sender_password ;
    private String contentPart_Type ;

    /*
     * 初始化方法
     */
    public SendMailUtil() {

            this.mailHost = loader.getProperty("mail.smtp.host");
            this.sender_username = loader.getProperty("mail.sender.username");
            this.sender_password = loader.getProperty("mail.sender.password");
            this.contentPart_Type = loader.getProperty("mail.contentPart.type");

        session = Session.getInstance(loader.getProperties());
        session.setDebug(true); //开启后有调试信息,发布时关闭
        message = new MimeMessage(session);
    }

    public static void main(String[] args) {
        SendMailUtil sendMailUtil = new SendMailUtil();
        String subject = "污染源在线监控数据实时报警";
        String bcc = "1009255421@qq.com,2794545732@qq.com";
        String html = "test send Mail";
        sendMailUtil.doSendHtmlEmail(subject,html,bcc);
    }


    /**
     * 发送邮件
     * @param subject
     *               邮件主题
     * @param sendHtml
     *               邮件内容
     * @param bccUser
     *               密送人   同上

     */
    public void doSendHtmlEmail(String subject, String sendHtml, String bccUser){
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sender_username,"环境实时复杂事件预警系统","UTF-8");
            message.setFrom(from);

/*            // 设置多个收件人地址
            if(null != toUser && !toUser.isEmpty()){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);
                message.setRecipients(Message.RecipientType.TO, internetAddressTo);
            }

            // 设置多个抄送地址
            if(null != ccUser && !ccUser.isEmpty()){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }*/

            // 设置多个密送地址
            if(null != bccUser && bccUser.length()!= 0){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
            }

            // 发送日期
            message.setSentDate(new Date());

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, contentPart_Type);
            multipart.addBodyPart(contentPart);

            BodyPart attachmentBodyPart = null;

/*            // 添加附件的内容
            if (null != attachment && attachment.length != 0) {
                for (File file : attachment) {
                    attachmentBodyPart = new MimeBodyPart();

                    DataSource source = new FileDataSource(file);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    //MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }*/

            // 将multipart对象放到message中
            message.setContent(multipart);

            // 保存邮件
            message.saveChanges();

            // smtp验证
            transport = session.getTransport("smtp");
            transport.connect(mailHost, sender_username, sender_password);

            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}