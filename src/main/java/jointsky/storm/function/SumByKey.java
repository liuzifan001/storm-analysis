package jointsky.storm.function;

import clojure.lang.Numbers;
import org.apache.storm.trident.operation.CombinerAggregator;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by LiuZifan on 2018/1/31.
 */
public class SumByKey implements CombinerAggregator<Number> {
    public SumByKey(){

    }
    public Number init(TridentTuple tuple) {

        System.out.println("SumByKey" + tuple.toString());
        return (Number)tuple.getValue(1);
    }

    public Number combine(Number val1, Number val2) {
        return Numbers.add(val1, val2);
    }

    public Number zero() {
        return Integer.valueOf(0);
    }


}
