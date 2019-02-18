package net.devcode.ftsi_kcf.Utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
	
	public static final String usernameString = "admin";
	public static final String passwordString = "test";
	
	public static final String EXTRA_USER = "net.devcode.ftsi_kcf.EXTRA_USER";
	public static final String EXTRA_DATE = "net.devcode.ftsi_kcf.EXTRA_DATE";
	public static final String EXTRA_TIME = "net.devcode.ftsi_kcf.EXTRA_TIME";
	public static final String EXTRA_ID = "net.devcode.ftsi_kcf.EXTRA_ID";
	public static final String EXTRA_PUMPNAME = "net.devcode.ftsi_kcf.EXTRA_PUMPNAME";
	public static final String EXTRA_PUMPSERIAL = "net.devcode.ftsi_kcf.EXTRA_PUMPSERIAL";
	public static final String EXTRA_POWERENDSERIAL = "net.devcode.ftsi_kcf.EXTRA_POWERENDSERIAL";
	public static final String EXTRA_FLUIDENDSERIAL = "net.devcode.ftsi_kcf.EXTRA_FLUIDENDSERIAL";
	public static final String EXTRA_FEH1 = "net.devcode.ftsi_kcf.EXTRA_FEH1";
	public static final String EXTRA_FEH5 = "net.devcode.ftsi_kcf.EXTRA_FEH5";
	public static final String EXTRA_PEH1 = "net.devcode.ftsi_kcf.EXTRA_PEH1";
	public static final String EXTRA_PEH5 = "net.devcode.ftsi_kcf.EXTRA_PEH5";
	public static final String EXTRA_STATION = "net.devcode.ftsi_kcf.EXTRA_STATION";
	public static final String EXTRA_PSI = "net.devcode.ftsi_kcf.EXTRA_PSI";
	public static final String EXTRA_PSIGUAGE = "net.devcode.ftsi_kcf.EXTRA_PSIGUAGE";
	public static final String EXTRA_DISTRICT = "net.devcode.ftsi_kcf.EXTRA_DISTRICT";
	public static final String EXTRA_FLEET = "net.devcode.ftsi_kcf.EXTRA_FLEET";
	public static final String EXTRA_ROTATION = "net.devcode.ftsi_kcf.EXTRA_ROTATION";
	public static final String EXTRA_SHIFT = "net.devcode.ftsi_kcf.EXTRA_SHIFT";
	public static final String EXTRA_AUDIT_TYPE = "net.devcode.ftsi_kcf.EXTRA_AUDIT_TYPE";
	
	
	public static final String EXTRA_VAN = "net.devcode.ftsi_kcf.EXTRA_VAN";
	public static final String EXTRA_LOCATION = "net.devcode.ftsi_kcf.EXTRA_LOCATION";
	public static final String EXTRA_BASE_STATION = "net.devcode.ftsi_kcf.EXTRA_BASE_STATION";
	public static final String EXTRA_REPEATER = "net.devcode.ftsi_kcf.EXTRA_REPEATER";
	public static final String EXTRA_SPARE_BATTERIES = "net.devcode.ftsi_kcf.EXTRA_SPARE_BATTERIES";
	
	
	//root directory
	public static final String DISTRICT_PATH = "/District";
	//Districts/*
	
	public static final String ALEDO_PATH = "/District/Aledo";
	public static final String EIGHTYFOUR_PATH = "/District/EightyFour";
	public static final String ELKCITY_PATH = "/District/ElkCity";
	public static final String ODESSA_PATH = "/District/Odessa";
	public static final String PLEASANTON_PATH = "/District/Pleasanton";
	public static final String SHREVEPORT_PATH = "/District/Shreveport";
	public static final String LONGVIEW_PATH = "/District/Longview";
	public static final String HOBBS_PATH = "/District/Hobbs";
	
	//Districts/District/*
	public static final String PUMP_LIST_PATH = "/PumpList";
	public static final String SPARE_SENSORS_PATH = "/SpareSensors";
	public static final String DAMAGED_SENSORS_PATH = "/DamagedSensors";
	public static final String DISTRICT_SENSOR_LIST_PATH = "/MasterSensorList";
	public static final String VANS_PATH = "/Vans";
	public static final String FLEETS_PATH = "/Fleets";
	public static final String BASE_STATIONS_PATH = "/BaseStations";
	public static final String REPEATERS_PATH = "/Repeaters";
	public static final String YARD_PATH = "/Yard";
	
	//Districts/District/Fleets/*
	public static final String FLEET1_PATH = "/Fleet1";
	public static final String FLEET2_PATH = "/Fleet2";
	public static final String FLEET3_PATH = "/Fleet3";
	public static final String FLEET4_PATH = "/Fleet4";
	public static final String FLEET5_PATH = "/Fleet5";
	public static final String FLEET6_PATH = "/Fleet6";
	public static final String FLEET7_PATH = "/Fleet7";
	public static final String FLEET8_PATH = "/Fleet8";
	public static final String FLEET9_PATH = "/Fleet9";
	public static final String FLEET10_PATH = "/Fleet10";
	
	//Districts/District/Fleets/Fleet$/Audits
	public static final String AUDIT_PATH = "/Audits";
	
	public static final int ADD_PUMP_REQUEST = 1;
	public static final int EDIT_PUMP_REQUEST = 2;
	public static final int FLEETS_REQUEST = 3;
	
	public static final String[] districtsArray = {
			"ElkCity", "Shreveport", "Aledo", "Longview", "Pleasanton", "Odessa", "EightyFour", "Hobbs"};
	public static final String[] fleetArray = {
			"Fleet1", "Fleet2", "Fleet3", "Fleet4", "Fleet5", "Fleet6", "Fleet7", "Fleet8", "Fleet9", "Fleet10"};
	
	public static final String[] shiftArray = {"Days", "Nights"};
	
	public static final String[] rotationArray = {"Red Crew", "Green Crew", "Blue Crew"};
	
	public static void print(Context context, String message) {
		
		Toast.makeText( context, message, Toast.LENGTH_LONG ).show();
	}
}
