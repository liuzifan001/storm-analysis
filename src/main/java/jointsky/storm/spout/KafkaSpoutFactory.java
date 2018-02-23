package jointsky.storm.spout;

import jointsky.util.PropertiesLoader;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;

/**
 * Created by hasee on 2018/2/15.
 */
public class KafkaSpoutFactory {
    private static final PropertiesLoader loader = new PropertiesLoader("kafka.properties");
    private static final BrokerHosts brokerHosts =new ZkHosts(loader.getProperty("zookeeper.connect"));
    private SpoutConfig kafkaSpoutConf;
    private static KafkaSpoutFactory kafkaSpoutFactory = null;

    private KafkaSpoutFactory() {

    }
    public static synchronized KafkaSpoutFactory createFactory() {
        if(null == kafkaSpoutFactory) {
            kafkaSpoutFactory = new KafkaSpoutFactory();
        }
        return kafkaSpoutFactory;
    }
    //按照topic创建KafkaSpout
    public KafkaSpout createKafkaSpout(String topic) {
        kafkaSpoutConf = new SpoutConfig(brokerHosts,topic,"/" + topic,topic + "Client");
        kafkaSpoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());   //MultiScheme用于指定如果处理从kafka中读取到的字节,StringScheme将byte[]转化为String,并指定stream输出一个名为str的Field
        return new KafkaSpout(kafkaSpoutConf);
    }



}
