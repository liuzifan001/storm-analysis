package jointsky.storm.function;

import jointsky.util.ETLUtil;
import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对接收的tuple进行过滤
 */
public class ETLFilter extends BaseFilter{
    private static Logger LOG = LoggerFactory.getLogger(ETLFilter.class);

    public boolean isKeep(TridentTuple tridentTuple) {
        String[] data = tridentTuple.getValue(0).toString().split(",");
        //判断字段数是否正确,数据是否合法
        if(data.length ==8 && isLegalData(data)) {
            System.out.println("Emitting data [" + tridentTuple.getValue(0).toString() +"]");
            return true;
        } else {
            System.out.println("drop data [" + tridentTuple.getValue(0).toString() +"]");
            return false;
        }
    }

    //用ETL工具判断数据是否合法
    private boolean isLegalData(String[] data){
        return ETLUtil.TimeFilter(data[0]) && ETLUtil.PsCodeFilter(data[1])
                && ETLUtil.outputCodeFilter(data[2]) && ETLUtil.pollutantCodeFilter(data[3])
                && ETLUtil.TimeFilter(data[4]) && ETLUtil.isDouble(data[5])
                && ETLUtil.isDouble(data[6]) && ETLUtil.isInt(data[7]);
    }
}
