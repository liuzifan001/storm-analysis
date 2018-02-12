package jointsky.storm.blot;

import jointsky.vo.TenMinData;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * Created by LiuZifan on 2018/2/4.
 */
public class OutputBlot extends BaseBasicBolt{
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        TenMinData result = (TenMinData) tuple.getValue(0);
        System.out.println("OutputBlot recv: [" + result.getPsCode() + "," + result.getOutputCode() + "," + result.getPollutantCode() + "," + result.getDisCharge() + "]");
        Fields fields = tuple.getFields();
        String data ="";
        for (String f:fields.toList()) {
             data += f + ":" + tuple.getValueByField(f);
        }
        //sendEmail


    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
