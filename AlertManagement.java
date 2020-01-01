package com.scaalert.demo;

public class AlertManagement {
	private String rowKey;
	private String status;
	private String userId;
	public AlertManagement() {
		
	}
	public AlertManagement(String rowKey,String status,String userId) {
		this.rowKey = rowKey;
		this.status = status;
		this.userId = userId;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AlertManagement [rowKey=" + rowKey + ", status=" + status + ", userId=" + userId + "]";
	}
	
}
