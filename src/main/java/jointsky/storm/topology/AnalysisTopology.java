package jointsky.storm.topology;

import jointsky.storm.function.*;
import jointsky.util.PropertiesLoader;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.trident.OpaqueTridentKafkaSpout;
import org.apache.storm.kafka.trident.TridentKafkaConfig;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.trident.operation.builtin.Sum;
import org.apache.storm.tuple.Fields;


/**
 * 构建Kafka Trident Topology
 *
 */
public class AnalysisTopology {
    private static PropertiesLoader loader = new PropertiesLoader("kafka.properties");
    //设置zookeeper参数值
    private static String zks = loader.getProperty("zookeeper.connect");
    private static String topic = "zxjc_Gas_TenMinData";

    //构建topology
    public static StormTopology buildTopology() {
        TridentTopology topology = new TridentTopology();
        BrokerHosts brokerHosts =new ZkHosts(zks);
        TridentKafkaConfig spoutConf = new TridentKafkaConfig(brokerHosts,topic);

        //MultiScheme用于指定如果处理从kafka中读取到的字节,StringScheme将byte[]转化为String,并指定stream输出一个名为str的Field
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
        OpaqueTridentKafkaSpout spout = new OpaqueTridentKafkaSpout(spoutConf);
        Stream spoutStream = topology.newStream("kafka-stream" , spout);

        //采用ETLFilter对tuple进行过滤
        Stream filterStream = spoutStream.each(new Fields("str"),new ETLFilter())
                //定位省份
                .each(new Fields("str"),new PraseData(),new Fields("prasedData"))
                .each(new Fields("prasedData"),new ProvinceAssign(),new Fields("province"))
                //.each(new Fields("prasedData","province"),new HourAssign(),new Fields("key","value")).project(new Fields("key","value"))
                //groupBy强制数据按照key重新分片
                .groupBy(new Fields("key"))
                .persistentAggregate(new OutBreakTrendFactory(),new Fields("key","value"),new SumByKey(),new Fields("sum")).newValuesStream();


        return topology.build();
    }

    //提交运行topology
    public static void main(String[] args) {
        Config conf = new Config();
        conf.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("analysis-test",conf,buildTopology());
    }

}
