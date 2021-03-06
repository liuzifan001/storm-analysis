package com.jointsky.storm.function;

import com.jointsky.vo.TenMinData;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuZifan on 2018/1/30.
 * 将数据序列化为vo对象GasTenMinData
 */
public class PraseData extends BaseFunction{
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String[] msg = tridentTuple.getValue(0).toString().split(",");
        TenMinData gtmData = new TenMinData(msg);
        List<Object> values = new ArrayList<Object>();
        values.add(gtmData);
        tridentCollector.emit(values);
    }

}
