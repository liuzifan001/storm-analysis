package com.jointsky.vo;

import java.sql.Timestamp;

/**
 * Created by hasee on 2018/2/22.
 */
public class AlarmRecord {
    private String id;
    private Timestamp alarmTime;
    private String ruleId;
    private String contend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getContend() {
        return contend;
    }

    public void setContend(String contend) {
        this.contend = contend;
    }
}
