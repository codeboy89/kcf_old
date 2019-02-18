package net.devcode.ftsi_kcf.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class PrevAuditModel {
	
	private String time;
	private String fleet;
	private String location;
	private String date;
	private String pumpListId;
	private String auditId;
	private String id;
	
	@Exclude
	private int entryCount;
	
	public PrevAuditModel() {
	
	}
	
	public PrevAuditModel(String fleet, String location, String date, String time, String pumplistId, String auditId, String id) {
		
		this.fleet = fleet;
		this.location = location;
		this.date = date;
		this.time = time;
		this.pumpListId = pumplistId;
		this.auditId = auditId;
		this.id = id;
	}
	
	
	public String getTime() {
		
		return time;
	}
	
	public void setTime(String time) {
		
		this.time = time;
	}
	
	public String getFleet() {
		
		return fleet;
	}
	
	public void setFleet(String fleet) {
		
		this.fleet = fleet;
	}
	
	public String getLocation() {
		
		return location;
	}
	
	public void setLocation(String location) {
		
		this.location = location;
	}
	
	public String getDate() {
		
		return date;
	}
	
	public void setDate(String date) {
		
		this.date = date;
	}
	
	public String getPumpListId() {
		
		return pumpListId;
	}
	
	public void setPumpListId(String pumpListId) {
		
		this.pumpListId = pumpListId;
	}
	
	public String getAuditId() {
		
		return auditId;
	}
	
	public void setAuditId(String auditId) {
		
		this.auditId = auditId;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public int getEntryCount() {
		
		return entryCount;
	}
	
	public void setEntryCount(int entryCount) {
		
		this.entryCount = entryCount;
	}
	
	@Override
	public String toString() {
		
		return "PrevAuditModel{" + "time='" + time + '\'' + ", fleet='" + fleet + '\'' + ", location='" + location + '\'' + ", date='" + date + '\'' + ", pumpListId='" + pumpListId + '\'' + ", auditId='" + auditId + '\'' + ", id='" + id + '\'' + ", entryCount=" + entryCount + '}';
	}
}
