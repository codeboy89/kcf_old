package net.devcode.ftsi_kcf.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;
@IgnoreExtraProperties
public class FleetPumpList {
	
	private String id;
	private String districtName;
	private String fleetName;
	private String user;
	private String date;
	private String time;
	private List < PumpModel > pumpModelList;
	
	public FleetPumpList() {
	
	}
	
	public FleetPumpList(String districtName, String fleetName, String user, String date, String time, List < PumpModel > pumpModelList) {
		
		
		this.districtName = districtName;
		this.fleetName = fleetName;
		this.user = user;
		this.date = date;
		this.time = time;
		this.pumpModelList = pumpModelList;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getDistrictName() {
		
		return districtName;
	}
	
	public void setDistrictName(String districtName) {
		
		this.districtName = districtName;
	}
	
	public String getFleetName() {
		
		return fleetName;
	}
	
	public void setFleetName(String fleetName) {
		
		this.fleetName = fleetName;
	}
	
	public String getUser() {
		
		return user;
	}
	
	public void setUser(String user) {
		
		this.user = user;
	}
	
	public String getDate() {
		
		return date;
	}
	
	public void setDate(String date) {
		
		this.date = date;
	}
	
	public String getTime() {
		
		return time;
	}
	
	public void setTime(String time) {
		
		this.time = time;
	}
	
	public List < PumpModel > getPumpModelList() {
		
		return pumpModelList;
	}
	
	public void setPumpModelList(List < PumpModel > pumpModelList) {
		
		this.pumpModelList = pumpModelList;
	}
	
	@Override
	public String toString() {
		
		return "FleetPumpList{" + "id='" + id + '\'' + ", districtName='" + districtName + '\'' + ", fleetName='" + fleetName + '\'' + ", user='" + user + '\'' + ", date='" + date + '\'' + ", time='" + time + '\'' + ", pumpModelList=" + pumpModelList + '}';
	}
}
