package com.jointsky.Test;

import com.jointsky.storm.blot.ParseDataBlot;
import com.jointsky.util.PropertiesLoader;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by LiuZifan on 2018/2/6.
 */
public class testTopology {
    private static final PropertiesLoader loader = new PropertiesLoader("kafka.properties");
    private static final String zks = loader.getProperty("zookeeper.connect");                //设置zookeeper参数值
    private static final String topic = "zxjc_Gas_TenMinData";

    private static int SPOUT_PARALLELISM_HINT = 1;

    private static StormTopology buildTopology() {
        //KafkaSpout的创建
        BrokerHosts brokerHosts =new ZkHosts(zks);
        SpoutConfig kafkaSpoutConf = new SpoutConfig(brokerHosts,topic,"/" + topic, "esper-test");
        kafkaSpoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());   //MultiScheme用于指定如果处理从kafka中读取到的字节,StringScheme将byte[]转化为String,并指定stream输出一个名为str的Field

        TopologyBuilder builder = new TopologyBuilder();                       //topology的构建
        builder.setSpout("KafkaSpout",new KafkaSpout(kafkaSpoutConf),SPOUT_PARALLELISM_HINT);
        builder.setBolt("ParseData",new ParseDataBlot()).shuffleGrouping("KafkaSpout","GasFacTenMinData");
        return builder.createTopology();
    }

    public static void main(String[] args) {
        Config conf = new Config();
        conf.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("single-point-test1", conf, buildTopology());
    }

}
