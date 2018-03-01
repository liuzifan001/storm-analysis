package com.jointsky.main;


import com.jointsky.vo.Rule;
import com.jointsky.dao.RuleDao;
import com.jointsky.dao.impl.RuleDaoImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LiuZifan on 2018/1/29.
 */
public class Test {
    public static void main(String[] args) {
        RuleDao test = new RuleDaoImpl();
        try {
            List<Rule> lsit = test.getAllRules();
            for (Rule r:lsit) {
                System.out.println(r.getId() +" : " +r.getEpl());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
