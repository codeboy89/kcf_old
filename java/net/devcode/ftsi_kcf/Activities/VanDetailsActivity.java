package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.VM.FleetViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class VanDetailsActivity extends AppCompatActivity {
	
	static List < String > vans;
	private String TAG = "VanDetailsActivity";
	private Spinner vanSpinner;
	private TextView customer, baseStation, repeater, spareBatteries;
	private List < FleetModel > restorePoint;
	private FleetModel fleetModel;
	private String date;
	private String time;

	
	private FleetViewModel fleetViewModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_van );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		setTitle( "Van Setup" );
		
		
		
		restorePoint = new ArrayList <>();
		vanSpinner = findViewById( R.id.activity_van_spinner_van );
		customer = findViewById( R.id.activity_van_textview_customer );
		baseStation = findViewById( R.id.activity_van_textview_baseStation );
		repeater = findViewById( R.id.activity_van_textview_repeater );
		spareBatteries = findViewById( R.id.activity_van_textview_spareBatteries );
		
		fleetViewModel = ViewModelProviders.of( this ).get( FleetViewModel.class );
	
	}
	
	private void uiUpdate() {
		
		if ( restorePoint.isEmpty() ) {
			Log.i( TAG, "onCreate: RestorePoint empty" );
		}
		else {
			fleetModel = restorePoint.get( 0 );
			
			Log.i( TAG, "onCreate: RestorePoint loaded" );
			if ( vans == null ) {
				vans = Arrays.asList( (getResources().getStringArray( R.array.vans )) );
			}
			
			vanSpinner.setSelection( vans.indexOf( fleetModel.getDatavan() ) );
			customer.setText( fleetModel.getLocation() );
			baseStation.setText( fleetModel.getBaseStation() );
			repeater.setText( fleetModel.getRepeater() );
			spareBatteries.setText( fleetModel.getSpareBatteries() );
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
		Log.i( TAG, "save: getinstance" + fleetModel.toString() );
		
		fleetModel.setTime( time );
		fleetModel.setDate( date );
		fleetModel.setDatavan( vanSpinner.getSelectedItem().toString().toUpperCase() );
		fleetModel.setLocation( customer.getText().toString().trim().toUpperCase() );
		fleetModel.setBaseStation( baseStation.getText().toString().trim().toUpperCase() );
		fleetModel.setRepeater( repeater.getText().toString().trim().toUpperCase() );
		fleetModel.setSpareBatteries( spareBatteries.getText().toString().trim().toUpperCase() );
		fleetModel.setDate( date );
		fleetModel.setTime( time );

		
	fleetViewModel.updateFleetModel( fleetModel );
		Log.i( TAG, "save: " + fleetViewModel.getFleetModel().toString() );
		
		Intent intent = new Intent( VanDetailsActivity.this, PumpListActivity.class );
		
		startActivity( intent );
	}
	
}
