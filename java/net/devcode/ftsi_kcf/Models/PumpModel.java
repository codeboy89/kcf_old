package net.devcode.ftsi_kcf.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@IgnoreExtraProperties
@Entity (tableName = "pump_table")
public class PumpModel {
	
	@PrimaryKey (autoGenerate = true)
	private int id;
	
	private String pumpName;
	private String pumpSerialNumber;
	private String pumpPeSerialNumber;
	private String pumpFeSerialNumber;
	private String pumpFeHoleNumber1;
	private String pumpFeHoleNumber5;
	private String pumpPeHoleNumber1;
	private String pumpPeHoleNumber5;
	private String station;
	private String psi;
	private String psiguage;
	
	public PumpModel(String pumpName, String pumpSerialNumber, String pumpPeSerialNumber, String pumpFeSerialNumber, String pumpFeHoleNumber1, String pumpFeHoleNumber5, String pumpPeHoleNumber1, String pumpPeHoleNumber5,
	                 String station, String psi, String psiguage) {
		
		this.pumpName = pumpName;
		this.pumpSerialNumber = pumpSerialNumber;
		this.pumpPeSerialNumber = pumpPeSerialNumber;
		this.pumpFeSerialNumber = pumpFeSerialNumber;
		this.pumpFeHoleNumber1 = pumpFeHoleNumber1;
		this.pumpFeHoleNumber5 = pumpFeHoleNumber5;
		this.pumpPeHoleNumber1 = pumpPeHoleNumber1;
		this.pumpPeHoleNumber5 = pumpPeHoleNumber5;
		this.station = station;
		this.psi = psi;
		this.psiguage = psiguage;
	}
	
	public PumpModel() {
	
	}
	
	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getPumpName() {
		
		return pumpName;
	}
	
	public void setPumpName(String pumpName) {
		
		this.pumpName = pumpName;
	}
	
	public String getPumpSerialNumber() {
		
		return pumpSerialNumber;
	}
	
	public void setPumpSerialNumber(String pumpSerialNumber) {
		
		this.pumpSerialNumber = pumpSerialNumber;
	}
	
	public String getPumpPeSerialNumber() {
		
		return pumpPeSerialNumber;
	}
	
	public void setPumpPeSerialNumber(String pumpPeSerialNumber) {
		
		this.pumpPeSerialNumber = pumpPeSerialNumber;
	}
	
	public String getPumpFeSerialNumber() {
		
		return pumpFeSerialNumber;
	}
	
	public void setPumpFeSerialNumber(String pumpFeSerialNumber) {
		
		this.pumpFeSerialNumber = pumpFeSerialNumber;
	}
	
	public String getPumpFeHoleNumber1() {
		
		return pumpFeHoleNumber1;
	}
	
	public void setPumpFeHoleNumber1(String pumpFeHoleNumber1) {
		
		this.pumpFeHoleNumber1 = pumpFeHoleNumber1;
	}
	
	public String getPumpFeHoleNumber5() {
		
		return pumpFeHoleNumber5;
	}
	
	public void setPumpFeHoleNumber5(String pumpFeHoleNumber5) {
		
		this.pumpFeHoleNumber5 = pumpFeHoleNumber5;
	}
	
	public String getPumpPeHoleNumber1() {
		
		return pumpPeHoleNumber1;
	}
	
	public void setPumpPeHoleNumber1(String pumpPeHoleNumber1) {
		
		this.pumpPeHoleNumber1 = pumpPeHoleNumber1;
	}
	
	public String getPumpPeHoleNumber5() {
		
		return pumpPeHoleNumber5;
	}
	
	public void setPumpPeHoleNumber5(String pumpPeHoleNumber5) {
		
		this.pumpPeHoleNumber5 = pumpPeHoleNumber5;
	}
	
	public String getStation() {
		
		return station;
	}
	
	public void setStation(String station) {
		
		this.station = station;
	}
	
	public String getPsi() {
		
		return psi;
	}
	
	public void setPsi(String psi) {
		
		this.psi = psi;
	}
	
	public String getPsiguage() {
		
		return psiguage;
	}
	
	public void setPsiguage(String psiguage) {
		
		this.psiguage = psiguage;
	}
	
	@Override
	public String toString() {
		
		return "PumpModel{" + "id=" + id + ", pumpName='" + pumpName + '\'' + ", pumpSerialNumber='" + pumpSerialNumber + '\'' + ", pumpPeSerialNumber='" + pumpPeSerialNumber + '\'' + ", pumpFeSerialNumber='" + pumpFeSerialNumber + '\'' + ", pumpFeHoleNumber1='" + pumpFeHoleNumber1 + '\'' + ", pumpFeHoleNumber5='" + pumpFeHoleNumber5 + '\'' + ", pumpPeHoleNumber1='" + pumpPeHoleNumber1 + '\'' + ", pumpPeHoleNumber5='" + pumpPeHoleNumber5 + '\'' + ", station='" + station + '\'' + ", psi='" + psi + '\'' + ", psiguage='" + psiguage + '\'' + '}';
	}
}
