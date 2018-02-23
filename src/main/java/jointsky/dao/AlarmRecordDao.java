package jointsky.dao;


import jointsky.vo.AlarmRecord;

import java.util.List;

/**
 * Created by hasee on 2018/2/17.
 */
public interface AlarmRecordDao {
    public void add(AlarmRecord alarmRecord);
    public void deleteById(int id);
    public void update(AlarmRecord alarmRecord);
    public List<AlarmRecord> findAll();
}
