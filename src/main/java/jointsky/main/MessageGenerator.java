package jointsky.main;

import jointsky.kafka.service.KafkaService;
import jointsky.kafka.service.KafkaServiceImpl;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * 在线监测数据发生器，用时打开
 */
public class MessageGenerator {

    public static void main(String[] args) {
        KafkaService service = new KafkaServiceImpl();

        String topic = "zxjc_Gas_TenMinData";
        //PsCode列表、outputCode列表、pollutantCode列表
        String[] psCodes ={"110000000002","110000001022","120000001027","120000001031","120000001032","110000001044","110000001050","110000001071","110000001076","110000001081","110105000015","130105003021","130105003113","130105013318","110105013319","110106001013","110106003781","110106003874","110106024759","130107020143","110109010376","130111000001","130111000010","130111000527","110111120302","110112030076","110117020123","110118000298","110118000380","110118000489","120228000218","120229000143","120229000268"};
        String[] outputCodes = {"1","2","3"};
        String[] pollutantCodes = {"001","002","003"};

        /* 消息字段
        String updateDate;
        String psCode ;
        String outputCode ;
        String pollutantCode ;
        String monitorTime ;
        String revisedStrength ;
        String revisedFlow ;
        String isException ;*/

        //原始Message为csv格式，字段间以逗号隔开
        //循环发送msg到Kafka集群
        try {
            service.establishConnect();
            while (true) {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String updateDate = sdf.format(now);
                String monitorTime = sdf.format(now).substring(0,15) + "0:00";

                for(String psc:psCodes) {
                    for (String outpc:outputCodes) {
                        for (String polltc:pollutantCodes) {
                            double rs = new Random().nextInt(200) + new Random().nextDouble();
                            double rf = new Random().nextInt(20) + new Random().nextDouble();
                            String msg = generateMessage(updateDate,psc,outpc,polltc,monitorTime,rs,rf,"0");
                            ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,null,msg);
                            service.send(record);
                        }
                    }
                }
                //休眠10分钟
                Thread.sleep(600000);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String generateMessage(String updateDate,String psCode,String outputCode,String pollutantCode,String monitorTime,double revisedStrength,double revisedFlow,String isException){
        String msg = updateDate + "," + psCode + "," + outputCode + "," + pollutantCode + "," + monitorTime + "," + String.format("%.3f",revisedStrength) + "," + String.format("%.3f",revisedFlow) + "," + isException;
        return msg;
    }
}
