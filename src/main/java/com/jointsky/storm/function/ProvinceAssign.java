package com.jointsky.storm.function;

import com.jointsky.vo.TenMinData;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuZifan on 2018/1/30.
 */
public class ProvinceAssign extends BaseFunction{
    //
    private static Map<String,String> PROVINCES = new HashMap<String, String>();
    {//初始化省份矩阵
        PROVINCES.put("11","北京市");
        PROVINCES.put("12","天津市");
        PROVINCES.put("13","河北省");
    }
    public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector) {
        String province ="NONE";
        TenMinData gtmData = (TenMinData) tridentTuple.getValue(0);

        province = getProvince(gtmData.getPsCode().substring(0,2));
        List<Object> values = new ArrayList<Object>();
        values.add(province);
        tridentCollector.emit(values);
    }

    //将regionCode转化为省份名称
    public String getProvince(String regionCode) {
        return PROVINCES.get(regionCode);
    }
}
