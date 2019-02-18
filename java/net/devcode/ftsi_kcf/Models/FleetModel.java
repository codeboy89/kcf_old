package net.devcode.ftsi_kcf.Models;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FleetModel {
	
	private static FleetModel instance = new FleetModel();
	private String location = "";
	private String districtName = "";
	private String fleetName = "";
	private String rotation = "";
	private String shift = "";
	private String auditType = "";
	private String user = "";
	private String date = "";
	private String time = "";
	private String datavan = "";
	private String baseStation = "";
	private String repeater = "";
	private String spareBatteries = "";
	private String pumpListId;
	private String id;
	
	
	private FleetModel() {
	
	
	}
	
	
	public FleetModel(String location, String districtName, String fleetName, String rotation, String shift, String auditType, String user, String date, String time, String datavan, String baseStation, String repeater,
	                  String spareBatteries, String pumpListId, String id) {
		
		this.location = location;
		this.districtName = districtName;
		this.fleetName = fleetName;
		this.rotation = rotation;
		this.shift = shift;
		this.auditType = auditType;
		this.user = user;
		this.date = date;
		this.time = time;
		this.datavan = datavan;
		this.baseStation = baseStation;
		this.repeater = repeater;
		this.spareBatteries = spareBatteries;
		this.pumpListId = pumpListId;
		this.id = id;
		
	}
	
	public static FleetModel getInstance() {
		
		return instance;
	}
	
	public String getLocation() {
		
		return location;
	}
	
	public void setLocation(String location) {
		
		this.location = location;
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
	
	public String getRotation() {
		
		return rotation;
	}
	
	public void setRotation(String rotation) {
		
		this.rotation = rotation;
	}
	
	public String getShift() {
		
		return shift;
	}
	
	public void setShift(String shift) {
		
		this.shift = shift;
	}
	
	public String getAuditType() {
		
		return auditType;
	}
	
	public void setAuditType(String auditType) {
		
		this.auditType = auditType;
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
	
	public String getDatavan() {
		
		return datavan;
	}
	
	public void setDatavan(String datavan) {
		
		this.datavan = datavan;
	}
	
	public String getBaseStation() {
		
		return baseStation;
	}
	
	public void setBaseStation(String baseStation) {
		
		this.baseStation = baseStation;
	}
	
	public String getRepeater() {
		
		return repeater;
	}
	
	public void setRepeater(String repeater) {
		
		this.repeater = repeater;
	}
	
	public String getSpareBatteries() {
		
		return spareBatteries;
	}
	
	public void setSpareBatteries(String spareBatteries) {
		
		this.spareBatteries = spareBatteries;
	}
	
	public String getPumpListId() {
		
		return pumpListId;
	}
	
	public void setPumpListId(String pumpListId) {
		
		this.pumpListId = pumpListId;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	@Override
	public String toString() {
		
		return "FleetModel{" + "location='" + location + '\'' + ", districtName='" + districtName + '\'' + ", fleetName='" + fleetName + '\'' + ", rotation='" + rotation + '\'' + ", shift='" + shift + '\'' + ", auditType='" + auditType + '\'' + ", user='" + user + '\'' + ", date='" + date + '\'' + ", time='" + time + '\'' + ", datavan='" + datavan + '\'' + ", baseStation='" + baseStation + '\'' + ", repeater='" + repeater + '\'' + ", spareBatteries='" + spareBatteries + '\'' + ", pumpListId='" + pumpListId + '\'' + ", id='" + id + '\'' + '}';
	}
}
