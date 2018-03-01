package com.jointsky.storm.blot;

import com.jointsky.dao.AlarmRecordDao;
import com.jointsky.dao.RuleSubscribeDao;
import com.jointsky.dao.impl.AlarmRecordDaoImpl;
import com.jointsky.dao.impl.RuleSubscribeDaoImpl;
import com.jointsky.util.SendMailUtil;
import com.jointsky.vo.AlarmRecord;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuZifan on 2018/2/4.
 */
public class OutputBlot extends BaseBasicBolt{
    SendMailUtil sendMailUtil = new SendMailUtil();
    RuleSubscribeDao ruleSubscribeDao = new RuleSubscribeDaoImpl();
    AlarmRecordDao alarmRecordDao = new AlarmRecordDaoImpl();
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {

        if(tuple.getString(0).equals("alarm")) {
            //预警邮件发送处理
            String ruleId = tuple.getStringByField("ruleId");
            String contend = tuple.getStringByField("description");
            List<String> userEmailList = ruleSubscribeDao.getEmailListByRuleId(ruleId);
            if(userEmailList.size()!=0){
                String userAddrs = toEmailAddrs(userEmailList);
                String subject = "环境实时预警消息：" + ruleId;

                sendMailUtil.doSendHtmlEmail(subject,contend,userAddrs);
            }
            //将预警事件写入mysql的AlarmRecord
            alarmRecordDao.add(toAlarmRecord(ruleId,contend));
        }

/*
        TenMinData result = (TenMinData) tuple.getValue(0);
        System.out.println("OutputBlot recv: [" + result.getPsCode() + "," + result.getOutputCode() + "," + result.getPollutantCode() + "," + result.getDisCharge() + "]");
        Fields fields = tuple.getFields();
        String data ="";
        for (String f:fields.toList()) {
             data += f + ":" + tuple.getValueByField(f);
        }
        //sendEmail

*/

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    public AlarmRecord toAlarmRecord(String ruleId, String description) {
        AlarmRecord alarmRecord = new AlarmRecord();
        Timestamp ts = new Timestamp(new Date().getTime());
        alarmRecord.setAlarmTime(ts);
        alarmRecord.setRuleId(ruleId);
        alarmRecord.setContend(description);
        return alarmRecord;
    }

    public String toEmailAddrs(List<String> emailList) {
        String emailAddrs ="";
        for (String addr:emailList) {
            emailAddrs += addr + ",";
        }
        return emailAddrs.substring(0,emailAddrs.length()-1);
    }
}
