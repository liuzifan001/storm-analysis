package jointsky.storm.topology;

import jointsky.esper.EsperBolt;
import jointsky.storm.blot.EsperOutputBlot;
import jointsky.storm.blot.OutputBlot;
import jointsky.storm.blot.ParseDataBlot;
import jointsky.util.PropertiesLoader;
import jointsky.vo.GasTenMinData;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by LiuZifan on 2018/2/3.
 */
public class EsperTestTopology {
    private static final PropertiesLoader loader = new PropertiesLoader("kafka.properties");
    private static final String zks = loader.getProperty("zookeeper.connect");                //设置zookeeper参数值
    private static final String topic = "zxjc_Gas_TenMinData";

    private static int SPOUT_PARALLELISM_HINT = 1;

    private static StormTopology buildTopology() {
        //KafkaSpout的创建
        BrokerHosts brokerHosts =new ZkHosts(zks);
        SpoutConfig kafkaSpoutConf = new SpoutConfig(brokerHosts,topic,"/" + topic, "esper-test");
        kafkaSpoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());   //MultiScheme用于指定如果处理从kafka中读取到的字节,StringScheme将byte[]转化为String,并指定stream输出一个名为str的Field

        //定义EsperBlot
        EsperBolt esperBolt = new EsperBolt.Builder()
                .inputs()                                       //定义输入流
                    .aliasComponent("ParseData")              //定义来自的组件名称
                    .withField("GasFacTenMin")                //定义来自的Field
                    .ofType(GasTenMinData.class)               //数据类型的Class
                    .toEventType("GasFacTenMinEvent")         //转化为的event名称
                .outputs()
                    .onDefaultStream()
                    //.onStream("PsSumFlow").fromEventType("PsFlowSum")          //定义输出流名称与来自的Event
                    .emit("psCode","pollutantCode","monitorTime","movingAvgStrength","stddevStrength","sumFlow")      //发射的字段
                    //.emit("psCode","count")
                .statements()
                    .add(  //"insert into PsFlowSum " +
                            "select GasFacTenMin.psCode as psCode,GasFacTenMin.pollutantCode as pollutantCode,last(GasFacTenMin.monitorTime) as monitorTime," +
                            "avg(GasFacTenMin.revisedStrength) as movingAvgStrength, stddev(GasFacTenMin.revisedStrength) as stddevStrength, sum(GasFacTenMin.revisedFlow) as sumFlow " +
                            "from GasFacTenMinEvent.std:groupwin(GasFacTenMin.psCode,GasFacTenMin.pollutantCode).win:length_batch(144) " +
                            "group by GasFacTenMin.psCode,GasFacTenMin.pollutantCode "
                    )
                    //.add("select GasFacTenMin.psCode as psCode,count(*) as count from GasFacTenMinEvent.win:length_batch(12) group by GasFacTenMin.psCode")
                .build();



        TopologyBuilder builder = new TopologyBuilder();                       //topology的构建
        builder.setSpout("KafkaSpout",new KafkaSpout(kafkaSpoutConf),SPOUT_PARALLELISM_HINT);
        builder.setBolt("ParseData",new ParseDataBlot()).shuffleGrouping("KafkaSpout");
        builder.setBolt("esper-blot",esperBolt).shuffleGrouping("ParseData");
        builder.setBolt("esper-output", new EsperOutputBlot(),2).shuffleGrouping("esper-blot");
        builder.setBolt("outputBlot", new OutputBlot()).fieldsGrouping("ParseData",new Fields("GasFacTenMin"));
        return builder.createTopology();
    }

    public static void main(String[] args) {
        Config conf = new Config();
        conf.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("single-point-test", conf, buildTopology());
    }


}
