package jointsky.dao.impl;

import jointsky.dao.RuleDao;
import jointsky.util.ConnectionPool;
import jointsky.vo.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuZifan on 2018/2/11.
 */
public class RuleDaoImpl implements RuleDao {
    public List<Rule> getAllRules() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Rule> list = new ArrayList<Rule>();
        String sql = "select * from envdata.rule";
        try{
            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Rule rule = new Rule();
                rule.setId(rs.getInt(1));
                rule.setType(rs.getString(2));
                rule.setEpl(rs.getString(3));
                rule.setDescription(rs.getString(4));
                rule.setCreateTime(rs.getTimestamp(5));
                rule.setCreateBy(rs.getString(6));
                list.add(rule);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("查询所有数据失败");
        }finally{
            ps.close();
            rs.close();
            ConnectionPool.releaseConnection(conn);
        }
        return list;
    }
}
