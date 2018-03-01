package com.jointsky.dao;

import com.jointsky.vo.RuleSubscribe;

import java.util.List;

/**
 * Created by hasee on 2018/2/22.
 */
public interface RuleSubscribeDao {

    void add(RuleSubscribe ruleSubscribe);
    void deleteById(int id);
    void update(RuleSubscribe ruleSubscribe);
    List<RuleSubscribe> findAll();

    //查询该userId所有订阅
    List<RuleSubscribe> findByUserId(String userId);
    //根据ruleId获取订阅此规则的所有用户Email
    List<String> getEmailListByRuleId(String ruleId);
    //转化为逗号分隔的待发送列表并发送邮件


}
