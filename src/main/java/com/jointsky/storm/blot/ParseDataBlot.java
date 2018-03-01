package com.jointsky.storm.blot;

import com.jointsky.util.ETLUtil;
import com.jointsky.vo.TenMinData;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息转化Blot，将来改为Builder模式
 * Created by LiuZifan on 2018/2/4.
 */
public class ParseDataBlot extends BaseBasicBolt{
    private static Logger LOG = LoggerFactory.getLogger(ParseDataBlot.class);

    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        //获取kafka消息字符串转化为String数组
/*        System.out.println("getSourceComponent :" + tuple.getSourceComponent());
        System.out.println("getSourceStreamId: " + tuple.getSourceStreamId());*/

        String str= tuple.getString(0);
        String[] originalData = str.split(",");
        //过滤非法消息,将合法消息转化为VO类,并发送
        if(ETLUtil.isLegalTenMinData(originalData)) {
          //  LOG.info("[legalData" + str + "]");
            TenMinData gasTenMinData = new TenMinData(originalData);
            basicOutputCollector.emit(new Values(gasTenMinData));
        }else {
          //  LOG.info("[ Drop ilegalData :" + str + "]");
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //发射blot，Field与输入的StreamId相同
        outputFieldsDeclarer.declare(new Fields("GasFacTenMin"));
        //outputFieldsDeclarer.declareStream(streamId,new Fields(streamId));
    }


}
