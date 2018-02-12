package jointsky.dao;

import jointsky.vo.Rule;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LiuZifan on 2018/2/11.
 */
public interface RuleDao {
    public List<Rule> getAllRules() throws SQLException;
}
