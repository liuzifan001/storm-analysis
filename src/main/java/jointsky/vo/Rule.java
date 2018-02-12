package jointsky.vo;

import java.sql.Timestamp;

/**
 * Created by LiuZifan on 2018/2/10.
 */
public class Rule {
    private long id;
    private String type;
    private String epl;
    private String description;
    private Timestamp createTime;
    private String createBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpl() {
        return epl;
    }

    public void setEpl(String epl) {
        this.epl = epl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
