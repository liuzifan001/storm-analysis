package jointsky.storm.function;

import jointsky.vo.GasTenMinData;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuZifan on 2018/1/30.
 */
public class ProvinceAssign extends BaseFunction{
    private static Map<String,String> PROVINCES = new HashMap<String, String>();
    {//初始化省份矩阵
        PROVINCES.put("11","北京市");
        PROVINCES.put("12","天津市");
        PROVINCES.put("13","河北省");
    }
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        GasTenMinData gtmData = (GasTenMinData) tridentTuple.getValue(0);
        
    }

}
