package jointsky.storm.function;

import org.apache.storm.shade.org.jboss.netty.util.internal.ConcurrentHashMap;
import org.apache.storm.trident.state.map.IBackingMap;
import org.apache.storm.trident.state.map.NonTransactionalMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuZifan on 2018/1/31.
 */
public class OutbreakTrendState extends NonTransactionalMap<Double>{
    protected OutbreakTrendState(OutbreakTrendBackingMap outbreakTrendBackingMap){
        super(outbreakTrendBackingMap);
    }
}

class OutbreakTrendBackingMap implements IBackingMap<Double> {
    Map<String,Double> storage = new ConcurrentHashMap<String,Double>();

    public List<Double> multiGet(final List<List<Object>> keys) {
        List<Double> values = new ArrayList<Double>();

        for (List<Object> key:keys){
            Double value = storage.get(key.get(0));
            if(value==null) {
                values.add(new Double(0));
            }else {
                values.add(value);
            }
        }
        return values;
    }

    public void multiPut(List<List<Object>> keys,List<Double> vals) {
        for(int i=0; i<keys.size();i++) {
            System.out.println("已持久化： ["+ keys.get(i).get(0) +" : " + vals.get(i) + "]");
            storage.put((String) keys.get(i).get(0),vals.get(i));
        }
    }
}

