package org.sidao.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class statisInfo {
	private Integer click=0;
	private Integer download=0;
	private Integer showTimes=0;
	private Integer user_quan=0;
	private Integer arouseTimes=0;
	private Integer apId=0;
	private Integer channelId=0;
	private Integer adId=0;
	private Integer province=0;
	private Integer operator=0;
	private String cdate;
	private String osType;
	public Integer getClick() {
		return click;
	}
	public void setClick() {
		this.click = 1;
	}
	public Integer getDownload() {
		return download;
	}
	public void setDownload() {
		this.download = 1;
	}
	public Integer getShowTimes() {
		return showTimes;
	}
	public void setShowTimes() {
		this.showTimes = 1;
	}
	public Integer getUser_quan() {
		return user_quan;
	}
	public void setUser_quan() {
		this.user_quan = 1;
	}
	public Integer getArouseTimes() {
		return arouseTimes;
	}
	public void setArouse() {
		this.arouseTimes = 1;
	}
	public Integer getApId() {
		return apId;
	}
	public void setApId(Integer apId) {
		this.apId = apId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public String getDate() {
		return cdate;
	}
	public void setDate() {
		Date date=new Date();
    	String dateFormat="yyyy-MM-dd";
    	SimpleDateFormat format = new SimpleDateFormat(dateFormat);
    	this.cdate=format.format(date);
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	
}
