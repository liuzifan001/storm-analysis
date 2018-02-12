package jointsky.vo;

/**
 * Created by LiuZifan on 2018/2/6.
 */
public class GasZsTenMinData {
    private String updateDate;
    private String provinceCode;
    private String cityCode;
    private String psCode ;
    private String outputCode ;
    private String pollutantCode ;
    private String monitorTime ;
    private double zsStrength;
    private double zsDisCharge;
    private double standardValue;
    private int isException ;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getOutputCode() {
        return outputCode;
    }

    public void setOutputCode(String outputCode) {
        this.outputCode = outputCode;
    }

    public String getPollutantCode() {
        return pollutantCode;
    }

    public void setPollutantCode(String pollutantCode) {
        this.pollutantCode = pollutantCode;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public double getZsStrength() {
        return zsStrength;
    }

    public void setZsStrength(double zsStrength) {
        this.zsStrength = zsStrength;
    }

    public double getZsDisCharge() {
        return zsDisCharge;
    }

    public void setZsDisCharge(double zsDisCharge) {
        this.zsDisCharge = zsDisCharge;
    }

    public double getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(double standardValue) {
        this.standardValue = standardValue;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }
}
