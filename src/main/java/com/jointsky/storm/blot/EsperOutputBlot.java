package com.jointsky.storm.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

/**
 * Created by LiuZifan on 2018/2/3.
 */
public class EsperOutputBlot implements IRichBolt {
    FileOutputStream outSTr = null;
    BufferedOutputStream buff = null;
    private static Logger LOG = LoggerFactory.getLogger(EsperOutputBlot.class);

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        try {
            outSTr = new FileOutputStream(new File("F:\\out.txt"),true);
            buff =   new BufferedOutputStream(outSTr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void execute(Tuple tuple) {
       System.out.println("result: [" + tuple.getValueByField("psCode") +"," +tuple.getValueByField("pollutantCode") +
                tuple.getValueByField("monitorTime") +"," +tuple.getValueByField("movingAvgStrength") +
                tuple.getValueByField("stddevStrength") +"," +tuple.getValueByField("sumFlow") + "]");
/*
        LOG.info("result: [" + tuple.getValueByField("psCode") +"," +tuple.getValueByField("pollutantCode") +
                tuple.getValueByField("monitorTime") +"," +tuple.getValueByField("MovingAvgStrength") +
                tuple.getValueByField("stddevStrength") +"," +tuple.getValueByField("sumFlow") + "]"
        );*/

        try {
            buff.write(("result: [" + tuple.getValueByField("psCode") +"," +tuple.getValueByField("pollutantCode") + tuple.getValueByField("monitorTime") +"," +tuple.getValueByField("movingAvgStrength") + tuple.getValueByField("stddevStrength") +"," +tuple.getValueByField("sumFlow") + "]" +"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cleanup() {
        try {
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("result"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
