package jointsky.vo;

import java.io.Serializable;

/**
 * Created by LiuZifan on 2018/1/29.
 * vo类，用于封装采集的数据
 */
public class GasTenMinData implements Serializable{
    private String updateDate;
    private String psCode ;
    private String outputCode ;
    private String pollutantCode ;
    private String monitorTime ;
    private double revisedStrength ;
    private double revisedFlow ;
    private int isException ;

    public GasTenMinData(String[] message){
        this.setUpdateDate(message[0]);
        this.setPsCode(message[1]);
        this.setOutputCode(message[2]);
        this.setPollutantCode(message[3]);
        this.setMonitorTime(message[4]);
        this.setRevisedStrength(Double.parseDouble(message[5]));
        this.setRevisedFlow(Double.parseDouble(message[6]));
        this.setIsException(Integer.parseInt(message[7]));
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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

    public double getRevisedStrength() {
        return revisedStrength;
    }

    public void setRevisedStrength(double revisedStrength) {
        this.revisedStrength = revisedStrength;
    }

    public double getRevisedFlow() {
        return revisedFlow;
    }

    public void setRevisedFlow(double revisedFlow) {
        this.revisedFlow = revisedFlow;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }
}
