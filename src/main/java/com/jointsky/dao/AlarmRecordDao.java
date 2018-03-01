package com.jointsky.dao;


import com.jointsky.vo.AlarmRecord;

import java.util.List;

/**
 * Created by hasee on 2018/2/17.
 */
public interface AlarmRecordDao {
    void add(AlarmRecord alarmRecord);
    void deleteById(int id);
    void update(AlarmRecord alarmRecord);
    List<AlarmRecord> findAll();
}
