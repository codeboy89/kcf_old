package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.Utils.Utils;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.VM.FleetViewModel;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class PreAuditActivity extends AppCompatActivity implements View.OnClickListener {
	
	private String TAG = "PreAuditActivity";
	private Button newButton, updateButton;
	private Spinner districtsSpinner, fleetSpinner;
	private List < String > districtsArrayList = Arrays.asList( Utils.districtsArray );
	private List < String > fleetsArrayList = Arrays.asList( Utils.fleetArray );
	private FleetViewModel fleetViewModel;
	FleetModel fleetModel = FleetModel.getInstance();
	private ArrayAdapter < String > districtsSpinnerArrayAdapter;
	private ArrayAdapter < String > fleetSpinnerArrayAdapter;
	
	private String user;
	private String district;
	private String fleet;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_pre_audit );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		setTitle( "Pre Audit Setup" );
		districtsSpinner = findViewById( R.id.activity_pre_audit_spinner_fleet );
		fleetSpinner = findViewById( R.id.activity_pre_audit_spinner_district );
		newButton = findViewById( R.id.activity_pre_audit_button_new );
		updateButton = findViewById( R.id.activity_pre_audit_button_update );
		
		fleetViewModel = ViewModelProviders.of( this ).get( FleetViewModel.class );
		
		Intent intent = getIntent();
		this.user = intent.getStringExtra( Utils.EXTRA_USER );
		Log.i( TAG, "onCreate: " + user );
		
		///////Populating Array Adapters/////////////////////////////////////////////////////////////
		newButton.setOnClickListener( this );
		updateButton.setOnClickListener( this );
		
		districtsSpinnerArrayAdapter = new ArrayAdapter <>( this, android.R.layout.simple_spinner_item, districtsArrayList );
		districtsSpinnerArrayAdapter.setDropDownViewResource( R.layout.spinner );
		districtsSpinner.setAdapter( districtsSpinnerArrayAdapter );
		
		
		fleetSpinnerArrayAdapter = new ArrayAdapter <>( this, android.R.layout.simple_spinner_item, fleetsArrayList );
		fleetSpinnerArrayAdapter.setDropDownViewResource( R.layout.spinner );
		fleetSpinner.setAdapter( fleetSpinnerArrayAdapter );
		
	}
	
	@Override
	public void onClick(View v) {
		
		
		switch ( v.getId() ) {
			
			case R.id.activity_pre_audit_button_new:
				Intent intent_new = new Intent( PreAuditActivity.this, FleetDetailsActivity.class );
				fleetModel.setAuditType( "NEW" );
				save();
				startActivity( intent_new );
				break;
			
			case R.id.activity_pre_audit_button_update:
				Intent intent_update = new Intent( PreAuditActivity.this, PrevAuditLoaderActivity.class );
				fleetModel.setAuditType( "UPDATE");
				save();
				startActivity( intent_update );
				break;
			
			default:
				break;
		}
	}
	
	private void save() {
		
		district = districtsSpinner.getSelectedItem().toString();
		fleet = fleetSpinner.getSelectedItem().toString();
		
		fleetModel.setUser( user );
		fleetModel.setDistrictName( district );
		fleetModel.setFleetName( fleet );
		
		Log.i( TAG, "save: " + FleetModel.getInstance().toString());
	}
}
