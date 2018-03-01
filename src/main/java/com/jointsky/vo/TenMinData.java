package com.jointsky.vo;

import java.io.Serializable;

/**
 * Created by LiuZifan on 2018/1/29.
 * vo类，用于封装采集的数据
 */
public class TenMinData implements Serializable{
    private String updateDate;
    private String provinceCode;
    private String cityCode;
    private String psCode ;
    private String outputCode ;
    private String pollutantCode ;
    private String monitorTime ;
    private double strength;
    private double disCharge;
    private double standardValue;
    private int isException ;

    public TenMinData(String[] message){
        this.setUpdateDate(message[0]);
        this.setPsCode(message[1]);
        this.setOutputCode(message[2]);
        this.setPollutantCode(message[3]);
        this.setMonitorTime(message[4]);
        this.setStrength(Double.parseDouble(message[5]));
        this.setDisCharge(Double.parseDouble(message[6]));
        this.setIsException(Integer.parseInt(message[7]));
    }

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

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getDisCharge() {
        return disCharge;
    }

    public void setDisCharge(double disCharge) {
        this.disCharge = disCharge;
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
