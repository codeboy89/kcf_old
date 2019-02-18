package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.VM.FleetViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import static net.devcode.ftsi_kcf.Utils.Utils.rotationArray;
import static net.devcode.ftsi_kcf.Utils.Utils.shiftArray;


public class FleetDetailsActivity extends AppCompatActivity implements View.OnClickListener {
	


	private FleetViewModel fleetViewModel;
	private String TAG = "FleetDetailsActivity";
	private Spinner  RotationSpinner, ShiftSpinner;
	private FleetModel fleetModel;
	private List < FleetModel > restorePoint;
	private List < String > rotationArrayList = Arrays.asList( rotationArray );
	private List < String > shiftArrayList = Arrays.asList( shiftArray );
	private ArrayAdapter < String > rotationSpinnerArrayAdapter;
	private ArrayAdapter < String > shiftSpinnerArrayAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_fleet_details );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		setTitle( "Fleet Setup" );
		//////Setting up Interface//////////////////////////////////////////////////////////////////
		restorePoint = new ArrayList <>();
		RotationSpinner = findViewById( R.id.activity_fleet_details_RotationSpinner );
		ShiftSpinner = findViewById( R.id.activity_fleet_details_ShiftSpinner );
		
	
		//Log.i( TAG, "onCreate: " + fleetModel.toString() );
		///////Populating Array Adapters/////////////////////////////////////////////////////////////
		
		rotationSpinnerArrayAdapter = new ArrayAdapter <>( this,
		                                                   android.R.layout.simple_spinner_item,
		                                                   rotationArrayList );
		rotationSpinnerArrayAdapter.setDropDownViewResource( R.layout.spinner );
		RotationSpinner.setAdapter( rotationSpinnerArrayAdapter );
		
		shiftSpinnerArrayAdapter = new ArrayAdapter <>( this, android.R.layout.simple_spinner_item, shiftArrayList );
		shiftSpinnerArrayAdapter.setDropDownViewResource( R.layout.spinner );
		ShiftSpinner.setAdapter( shiftSpinnerArrayAdapter );
		
		fleetViewModel = ViewModelProviders.of( this ).get( FleetViewModel.class );
	
		
	}
	
	private void uiUpdate() {
		
		if ( restorePoint.isEmpty() ) {
			Log.i( TAG, "onCreate: RestorePoint empty" );
		}
		else {
			fleetModel = restorePoint.get( 0 );
			
			Log.i( TAG, "onCreate: RestorePoint loaded" );
			
			RotationSpinner.setSelection( rotationSpinnerArrayAdapter.getPosition( fleetModel.getRotation() ) );
			ShiftSpinner.setSelection( shiftSpinnerArrayAdapter.getPosition( fleetModel.getShift() ) );
			for ( FleetModel rp : restorePoint ) {
				Log.i( TAG, "onCreate: RestorePoint: " + rp.toString() );
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate( R.menu.pump_list_menu, menu );
		return true;
		//super.onCreateOptionsMenu(menu)
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch ( item.getItemId() ) {
			case R.id.undo_pump_list:
				//undo();
				return true;
			
			case R.id.redo_pump_list:
				//redo();
				return true;
			
			case R.id.restore_pump_list:
				//retore();
				return true;
			
			case R.id.clear_pump_list:
				//clear();
				return true;
			
			case R.id.save_pump_list:
				save();
				return true;
			
			default:
				return super.onOptionsItemSelected( item );
			
		}
	}
	
	private void save() {
		fleetModel = FleetModel.getInstance();
		fleetModel.setRotation( RotationSpinner.getSelectedItem().toString() );
		fleetModel.setShift( ShiftSpinner.getSelectedItem().toString() );
		fleetViewModel.updateFleetModel( fleetModel );
		Log.i( TAG, "save: " + fleetViewModel.getFleetModel().toString() );
		Intent intent = new Intent( FleetDetailsActivity.this, VanDetailsActivity.class );
		startActivity( intent );
	}
	
	
	@Override
	public void onClick(View v) {
	
	}
}
