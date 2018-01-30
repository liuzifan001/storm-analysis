package jointsky.storm.blot;

import jointsky.util.ETLUtil;
import jointsky.vo.GasTenMinData;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * Created by LiuZifan on 2018/1/30.
 */
    public class ETLBlot extends BaseBasicBolt{
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String msg = tuple.getString(0);
        System.out.println("ETLBlot rev:" + msg);
        GasTenMinData data = ETLUtil.transData(msg);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("gasTenMinData"));
    }

    public void cleanup() {

    }

}
