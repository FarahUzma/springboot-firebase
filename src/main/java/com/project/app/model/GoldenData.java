package com.project.app.model;

public class GoldenData {
	private String buildingKey;
	private String roomNo;
	private String vcNo;
	private String stbNo;
	private int sNo;
	
	public String getBuildingKey() {
		return buildingKey;
	}
	public void setBuildingKey(String buildingKey) {
		this.buildingKey = buildingKey;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getVcNo() {
		return vcNo;
	}
	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}
	public String getStbNo() {
		return stbNo;
	}
	public void setStbNo(String stbNo) {
		this.stbNo = stbNo;
	}
	/**
	 * @return the sNo
	 */
	public int getsNo() {
		return sNo;
	}
	/**
	 * @param sNo the sNo to set
	 */
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	
}
