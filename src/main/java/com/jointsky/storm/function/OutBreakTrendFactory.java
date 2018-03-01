package com.jointsky.storm.function;

import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;

import java.util.Map;

/**
 * Created by LiuZifan on 2018/1/31.
 */
public class OutBreakTrendFactory implements StateFactory{
    public State makeState(Map map, IMetricsContext iMetricsContext, int i, int i1) {
        return new OutbreakTrendState(new OutbreakTrendBackingMap());
    }
}

