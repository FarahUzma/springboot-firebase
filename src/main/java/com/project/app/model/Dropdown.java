package com.project.app.model;

import java.util.List;

public class Dropdown {

	private String key;
	private String value;
	private List<String> roomNos;
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the roomNos
	 */
	public List<String> getRoomNos() {
		return roomNos;
	}
	/**
	 * @param roomNos the roomNos to set
	 */
	public void setRoomNos(List<String> roomNos) {
		this.roomNos = roomNos;
	}
	
}
