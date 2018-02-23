package jointsky.dao;

import jointsky.vo.RuleSubscribe;

import java.util.List;

/**
 * Created by hasee on 2018/2/22.
 */
public interface RuleSubscribeDao {

    public void add(RuleSubscribe ruleSubscribe);
    public void deleteById(int id);
    public void update(RuleSubscribe ruleSubscribe);
    public List<RuleSubscribe> findAll();

    //查询该userId所有订阅
    public List<RuleSubscribe> findByUserId(String userId);
    //根据ruleId获取订阅此规则的所有用户Email
    public List<String> getEmailListByRuleId(long ruleId);

}
