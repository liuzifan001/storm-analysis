package com.jointsky.storm.function;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Created by LiuZifan on 2018/1/29.
 */
public class resloveFunction extends BaseFunction {
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String[] msg = tridentTuple.getString(0).split(",");
        Values values = new Values();
        for(int i=0;i<=1;i++) {
            values.add(msg[i]);
        }
        System.out.println(values.toString());
        tridentCollector.emit(values);
    }
}
