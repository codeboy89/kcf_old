package net.devcode.ftsi_kcf.Models;

public class FormData {
	
	private String unit;
	private String serialNumber;
	private String powerendSerialNumber;
	private String fluidendSerialNumber;
	private String powerendHole1;
	private String powerendHole5;
	private String fluidendHole1;
	private String fluidendHole5;
	private int station;
	private boolean dampenerPSI;
	private boolean dampenerPressureGuage;
	
	public FormData() {
	
	}
	
	public FormData(String unit) {
		this.unit = unit;
	}
	
	public FormData(String unit, String serialNumber, String powerendSerialNumber, String fluidendSerialNumber, String powerendHole1, String powerendHole5, String fluidendHole1, String fluidendHole5,int station, boolean dampenerPSI, boolean dampenerPressureGuage) {
		this.unit = unit;
		this.serialNumber = serialNumber;
		this.powerendSerialNumber = powerendSerialNumber;
		this.fluidendSerialNumber = fluidendSerialNumber;
		this.powerendHole1 = powerendHole1;
		this.powerendHole5 = powerendHole5;
		this.fluidendHole1 = fluidendHole1;
		this.fluidendHole5 = fluidendHole5;
		this.station = station;
		this.dampenerPSI = dampenerPSI;
		this.dampenerPressureGuage = dampenerPressureGuage;
	}
	
	@Override
	public String toString() {
		return "FormData{" + "unit='" + unit + '\'' + ", serialNumber='" + serialNumber + '\'' + ", powerendSerialNumber='" + powerendSerialNumber + '\'' + ", fluidendSerialNumber='" + fluidendSerialNumber + '\'' + ", powerendHole1='" + powerendHole1 + '\'' + ", powerendHole5='" + powerendHole5 + '\'' + ", fluidendHole1='" + fluidendHole1 + '\'' + ", fluidendHole5='" + fluidendHole5 + '\'' + ", station=" + station + ", dampenerPSI=" + dampenerPSI + ", dampenerPressureGuage=" + dampenerPressureGuage + '}';
	}
	
	public int getStation() {
		return station;
	}
	
	public void setStation(int station) {
		this.station = station;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getPowerendSerialNumber() {
		return powerendSerialNumber;
	}
	
	public void setPowerendSerialNumber(String powerendSerialNumber) {
		this.powerendSerialNumber = powerendSerialNumber;
	}
	
	public String getFluidendSerialNumber() {
		return fluidendSerialNumber;
	}
	
	public void setFluidendSerialNumber(String fluidendSerialNumber) {
		this.fluidendSerialNumber = fluidendSerialNumber;
	}
	
	public String getPowerendHole1() {
		return powerendHole1;
	}
	
	public void setPowerendHole1(String powerendHole1) {
		this.powerendHole1 = powerendHole1;
	}
	
	public String getPowerendHole5() {
		return powerendHole5;
	}
	
	public void setPowerendHole5(String powerendHole5) {
		this.powerendHole5 = powerendHole5;
	}
	
	public String getFluidendHole1() {
		return fluidendHole1;
	}
	
	public void setFluidendHole1(String fluidendHole1) {
		this.fluidendHole1 = fluidendHole1;
	}
	
	public String getFluidendHole5() {
		return fluidendHole5;
	}
	
	public void setFluidendHole5(String fluidendHole5) {
		this.fluidendHole5 = fluidendHole5;
	}
	
	public boolean isDampenerPSI() {
		return dampenerPSI;
	}
	
	public void setDampenerPSI(boolean dampenerPSI) {
		this.dampenerPSI = dampenerPSI;
	}
	
	public boolean isDampenerPressureGuage() {
		return dampenerPressureGuage;
	}
	
	public void setDampenerPressureGuage(boolean dampenerPressureGuage) {
		this.dampenerPressureGuage = dampenerPressureGuage;
	}
	
	
}
