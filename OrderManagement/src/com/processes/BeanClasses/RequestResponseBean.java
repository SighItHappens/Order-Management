package com.processes.BeanClasses;

public class RequestResponseBean extends Bean{
	
	int requestID;
	String sourc;
	String sync;
	String threadObject;
	String status;
	int OrderID;
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getSourc() {
		return sourc;
	}
	public void setSourc(String sourc) {
		this.sourc = sourc;
	}
	public String getSync() {
		return sync;
	}
	public void setSync(String sync) {
		this.sync = sync;
	}
	public String getThreadObject() {
		return threadObject;
	}
	public void setThreadObject(String threadObject) {
		this.threadObject = threadObject;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	
	@Override
	public String toString() {
		return "RequestResponseBean [requestID=" + requestID + ", sourc="
				+ sourc + ", sync=" + sync + ", threadObject=" + threadObject
				+ ", status=" + status + ", OrderID=" + OrderID + "]";
	}


}
