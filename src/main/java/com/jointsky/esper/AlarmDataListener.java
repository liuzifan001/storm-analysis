package com.jointsky.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.jointsky.vo.Rule;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuZifan on 2018/2/28.
 */
public class AlarmDataListener implements UpdateListener{
    private OutputCollector collector;
    private Rule rule;

    public AlarmDataListener(Rule rule,OutputCollector collector) {
        this.collector = collector;
        this.rule = rule;
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            for (EventBean newEvent : newEvents) {
                List<Object> tuple = new ArrayList<Object>();
                tuple.add("alarm");
                tuple.add(rule.getId());
                tuple.add(toDescription(newEvent));
                collector.emit("AlarmDataStream",tuple);
            }
        }
    }

    private String toDescription(EventBean eventBean) {
        String[] outputFields = rule.getOutputFields().split(",");
        String description = rule.getDescription();
        for (String field:outputFields) {
            //替换描述中所有关键值
            description.replaceAll(field,(String) eventBean.get(field));
        }
        return null;
    }
    //替换大括号中的内容，直接输出报警信息的String


    //转化为tuple
    private List<Object> toTuple(EventBean event, Fields fields)
    {
        int numFields = fields.size();
        List<Object> tuple = new ArrayList<Object>(numFields);
        tuple.add(rule);
        for (int idx = 0; idx < numFields; idx++) {
            tuple.add(event.get(fields.get(idx)));
        }
        return tuple;
    }
}
