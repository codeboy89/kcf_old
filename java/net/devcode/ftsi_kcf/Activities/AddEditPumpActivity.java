package net.devcode.ftsi_kcf.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.devcode.ftsi_kcf.Models.FormData;
import net.devcode.ftsi_kcf.Models.PumpModel;
import net.devcode.ftsi_kcf.R;

import androidx.appcompat.app.AppCompatActivity;

import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FEH1;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FEH5;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FLUIDENDSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_ID;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PEH1;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PEH5;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_POWERENDSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PSI;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PSIGUAGE;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PUMPNAME;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PUMPSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_STATION;

public class AddEditPumpActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
	
	private String TAG = "AddEditPumpActivity";
	private TextView unitNumber, PESerialNumber, FESerialNumber, FEHole1Number, FEHole5Number, PEHole1Number, PEHole5Number, stationNumber;
	private RadioButton FEHole1Radio, FEHole5Radio, PEHole1Radio, PEHole5Radio;
	private ToggleButton psiGuage, psi;
	private PumpModel pumpModel;
	
	private IntentIntegrator qrScanUnit;
	private IntentIntegrator qrScanSensor;
	
	private boolean scanUnit = false;
	private boolean scanSensor = false;
	private FormData restoreFormData;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_add_edit_pump );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		
		init();
		Intent data = getIntent();
		
		if ( data.hasExtra( EXTRA_ID ) ) {
			setTitle( "Edit PumpModel" );
			
			PumpModel pumpModel = new PumpModel( data.getStringExtra( EXTRA_PUMPNAME ),
			                                     data.getStringExtra( EXTRA_PUMPSERIAL ),
			                                     data.getStringExtra( EXTRA_POWERENDSERIAL ),
			                                     data.getStringExtra( EXTRA_FLUIDENDSERIAL ),
			                                     data.getStringExtra( EXTRA_FEH1 ),
			                                     data.getStringExtra( EXTRA_FEH5 ),
			                                     data.getStringExtra( EXTRA_PEH1 ),
			                                     data.getStringExtra( EXTRA_PEH5 ),
			                                     data.getStringExtra( EXTRA_STATION ),
			                                     data.getStringExtra( EXTRA_PSI ),
			                                     data.getStringExtra( EXTRA_PSIGUAGE ) );
			
			Log.i( TAG, pumpModel.toString());
			
			unitNumber.setText( pumpModel.getPumpName() );
			PESerialNumber.setText( pumpModel.getPumpPeSerialNumber() );
			FESerialNumber.setText( pumpModel.getPumpFeSerialNumber() );
			PEHole1Number.setText( pumpModel.getPumpPeHoleNumber1() );
			PEHole5Number.setText( pumpModel.getPumpPeHoleNumber5() );
			FEHole1Number.setText( pumpModel.getPumpFeHoleNumber1() );
			FEHole5Number.setText( pumpModel.getPumpFeHoleNumber5() );
			stationNumber.setText( pumpModel.getStation() );
			psi.setChecked( Boolean.valueOf( pumpModel.getPsi().trim() ) );
			psiGuage.setChecked( Boolean.valueOf( pumpModel.getPsiguage().trim() ) );
			
		}
		else {
			setTitle( "Add PumpModel" );
		}
		
	}
	
	private void init() {
		
		unitNumber = findViewById( R.id.activity_add_edit_pump_unitNumber );
		PESerialNumber = findViewById( R.id.activity_add_edit_pump_PESerialNumber );
		FESerialNumber = findViewById( R.id.activity_add_edit_pump_FESerialNumber );
		FEHole1Number = findViewById( R.id.activity_add_edit_pump_FEHole1Number );
		FEHole5Number = findViewById( R.id.activity_add_edit_pump_FEHole5Number );
		PEHole1Number = findViewById( R.id.activity_add_edit_pump_PEHole1Number );
		PEHole5Number = findViewById( R.id.activity_add_edit_pump_PEHole5Number );
		stationNumber = findViewById( R.id.activity_add_edit_pump_stationNumber );
		FEHole1Radio = findViewById( R.id.activity_add_edit_pump_FEHole1Radio );
		FEHole5Radio = findViewById( R.id.activity_add_edit_pump_FEHole5Radio);
		PEHole1Radio = findViewById( R.id.activity_add_edit_pump_PEHole1Radio );
		PEHole5Radio = findViewById( R.id.activity_add_edit_pump_PEHole5Radio );
		psi = findViewById( R.id.activity_add_edit_pump_toggleButton_psi );
		psiGuage = findViewById( R.id.activity_add_edit_pump_toggleButton_psiGuage );
		
		///////Setting up QR Scanner/////////////////////////////////////////////////////////
		qrScanSensor = new IntentIntegrator( this );
		qrScanUnit = new IntentIntegrator( this );
		///////Setting OnClickListeners//////////////////////////////////////////////////////
		FEHole1Radio.setOnClickListener( this );
		FEHole5Radio.setOnClickListener( this );
		PEHole1Radio.setOnClickListener( this );
		PEHole5Radio.setOnClickListener( this );
		psiGuage.setOnClickListener( this );
		psi.setOnClickListener( this );
		unitNumber.setOnClickListener( this );
		PESerialNumber.setOnClickListener( this );
		FESerialNumber.setOnClickListener( this );
		FEHole1Number.setOnClickListener( this );
		FEHole5Number.setOnClickListener( this );
		PEHole1Number.setOnClickListener( this );
		PEHole5Number.setOnClickListener( this );
		stationNumber.setOnClickListener( this );
		unitNumber.setOnLongClickListener( this );
		PESerialNumber.setOnLongClickListener( this );
		FESerialNumber.setOnLongClickListener( this );
		FEHole1Number.setOnLongClickListener( this );
		FEHole5Number.setOnLongClickListener( this );
		PEHole1Number.setOnLongClickListener( this );
		PEHole5Number.setOnLongClickListener( this );
		
		restorePoint();
	}
	
	private void restorePoint() {
		
		restoreFormData = new FormData();
		restoreFormData.setUnit( unitNumber.getText().toString().toUpperCase().trim() );
		restoreFormData.setSerialNumber( "-1" );
		restoreFormData.setFluidendSerialNumber( PESerialNumber.getText().toString().toUpperCase().trim().trim() );
		restoreFormData.setPowerendSerialNumber( FESerialNumber.getText().toString().toUpperCase().trim() );
		restoreFormData.setFluidendHole1( FEHole1Number.getText().toString().toUpperCase().trim() );
		restoreFormData.setFluidendHole5( FEHole5Number.getText().toString().toUpperCase().trim() );
		restoreFormData.setPowerendHole1( PEHole1Number.getText().toString().toUpperCase().trim() );
		restoreFormData.setPowerendHole5( PEHole5Number.getText().toString().toUpperCase().trim() );
		restoreFormData.setStation( Integer.parseInt( stationNumber.getText().toString().trim()) );
		restoreFormData.setDampenerPressureGuage( psiGuage.isChecked() );
		restoreFormData.setDampenerPSI( psi.isChecked() );
	}
	
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate( R.menu.add_pump_menu, menu );
		return true;
		//super.onCreateOptionsMenu(menu)
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		
		switch ( item.getItemId() ) {
			case R.id.undo_pump:
				undo();
				return true;
			case R.id.redo_pump:
				redo();
				return true;
			case R.id.restore_pump:
				restore();
				return true;
			case R.id.clear_pump:
				clear();
				return true;
			case R.id.save_pump:
				save();
				return true;
			default:
				return super.onOptionsItemSelected( item );
		}
	}
	
	private void undo() {
		
		Toast.makeText( this, "UNDO", Toast.LENGTH_SHORT ).show();
	}
	
	private void redo() {
		
		Toast.makeText( this, "REDO", Toast.LENGTH_SHORT ).show();
	}
	
	private void restore() {
		
		unitNumber.setText( restoreFormData.getUnit() );
		PESerialNumber.setText( restoreFormData.getPowerendSerialNumber() );
		FESerialNumber.setText( restoreFormData.getFluidendSerialNumber() );
		FEHole1Number.setText( restoreFormData.getFluidendHole1() );
		FEHole5Number.setText( restoreFormData.getFluidendHole5() );
		PEHole1Number.setText( restoreFormData.getPowerendHole1() );
		PEHole5Number.setText( restoreFormData.getPowerendHole5() );
		stationNumber.setText( restoreFormData.getStation() );
		psi.setSelected( restoreFormData.isDampenerPSI() );
		psi.setSelected( restoreFormData.isDampenerPressureGuage() );
	}
	
	private void clear() {
		
		unitNumber.setText( "" );
		PESerialNumber.setText( "" );
		FESerialNumber.setText( "" );
		PEHole1Number.setText( "" );
		PEHole5Number.setText( "" );
		FEHole1Number.setText( "" );
		FEHole5Number.setText( "" );
		stationNumber.setText( "" );
		psi.setSelected( false );
		psi.setSelected( false );
	}
	
	private void save() {
		//////reading view and populating FormData////////////////////////////////////////
		if ( unitNumber.getText().toString().trim().isEmpty() || PESerialNumber.getText()
				.toString()
				.trim()
				.isEmpty() || FESerialNumber.getText().toString().trim().isEmpty() || FEHole1Number.getText()
				.toString()
				.trim()
				.isEmpty() || FEHole5Number.getText().toString().trim().isEmpty() || PEHole1Number.getText()
				.toString()
				.trim()
				.isEmpty() || PEHole5Number.getText().toString().trim().isEmpty() ) {
			
			Toast.makeText( this, "Please fill out fields completely", Toast.LENGTH_LONG ).show();
			return;
		}
		
		FormData formData = new FormData();
		formData.setUnit( unitNumber.getText().toString().toUpperCase().trim() );
		formData.setSerialNumber( "-1" );
		formData.setFluidendSerialNumber( PESerialNumber.getText().toString().toUpperCase().trim().trim() );
		formData.setPowerendSerialNumber( FESerialNumber.getText().toString().toUpperCase().trim() );
		formData.setFluidendHole1( FEHole1Number.getText().toString().toUpperCase().trim() );
		formData.setFluidendHole5( FEHole5Number.getText().toString().toUpperCase().trim() );
		formData.setPowerendHole1( PEHole1Number.getText().toString().toUpperCase().trim() );
		formData.setPowerendHole5( PEHole5Number.getText().toString().toUpperCase().trim() );
		formData.setStation( Integer.parseInt( stationNumber.getText().toString().trim()) );
		formData.setDampenerPressureGuage( psiGuage.isChecked() );
		formData.setDampenerPSI( psi.isChecked() );
		////////Transfer Formdata to current pump_audit Object///////////////////////////
		pumpModel = new PumpModel( formData.getUnit(),
		                           formData.getSerialNumber(),
		                           formData.getPowerendSerialNumber(),
		                           formData.getFluidendSerialNumber(),
		                           formData.getFluidendHole1(),
		                           formData.getFluidendHole5(),
		                           formData.getPowerendHole1(),
		                           formData.getPowerendHole5(),
		                           String.valueOf( formData.getStation() ),
		                           String.valueOf( formData.isDampenerPSI() ),
		                           String.valueOf( formData.isDampenerPressureGuage() ) );
		
		Intent pumpData = new Intent();
		pumpData.putExtra( EXTRA_PUMPNAME, pumpModel.getPumpName() );
		pumpData.putExtra( EXTRA_PUMPSERIAL, pumpModel.getPumpSerialNumber() );
		pumpData.putExtra( EXTRA_POWERENDSERIAL, pumpModel.getPumpPeSerialNumber() );
		pumpData.putExtra( EXTRA_FLUIDENDSERIAL, pumpModel.getPumpFeSerialNumber() );
		pumpData.putExtra( EXTRA_FEH1, pumpModel.getPumpFeHoleNumber1() );
		pumpData.putExtra( EXTRA_FEH5, pumpModel.getPumpFeHoleNumber5() );
		pumpData.putExtra( EXTRA_PEH1, pumpModel.getPumpPeHoleNumber1() );
		pumpData.putExtra( EXTRA_PEH5, pumpModel.getPumpPeHoleNumber5() );
		pumpData.putExtra( EXTRA_STATION, pumpModel.getStation() );
		pumpData.putExtra( EXTRA_PSI, pumpModel.getPsi() );
		pumpData.putExtra( EXTRA_PSIGUAGE, pumpModel.getPsiguage() );
		
		int id = getIntent().getIntExtra( EXTRA_ID, - 1 );
		
		if ( id != - 1 ) {
			pumpData.putExtra( EXTRA_ID, id );
		}
		
		setResult( RESULT_OK, pumpData );
		finish();
	}
	
	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
		//////Receiving QR Scanner results////////////////////////////////////////////////
		IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
		if ( result != null ) {
			//if qrcode has nothing in it
			//if qr contains data
			if ( result.getContents() == null ) {
				Toast.makeText( this, "Result Not Found", Toast.LENGTH_LONG ).show();
			}
			//////Assigning results to appropriate view////////////////////////////////////
			else {
				if ( FEHole1Radio.isChecked() & scanSensor ) {
					FEHole1Number.setText( result.getContents() );
				}
				if ( FEHole5Radio.isChecked() & scanSensor ) {
					FEHole5Number.setText( result.getContents() );
				}
				if ( PEHole1Radio.isChecked() & scanSensor ) {
					PEHole1Number.setText( result.getContents() );
				}
				if ( PEHole5Radio.isChecked() & scanSensor ) {
					PEHole5Number.setText( result.getContents() );
				}
				if ( scanUnit ) {
					unitNumber.setText( result.getContents() );
				}
				//////Clearing request identifiers///////////////////////////////////////////
				scanUnit = false;
				scanSensor = false;
			}
		}
		else {
			super.onActivityResult( requestCode, resultCode, data );
		}
	}
	
	@Override
	public void onClick( View v ) {
		
		int id = v.getId();
		Toast.makeText( this, "Clicked: " + id, Toast.LENGTH_SHORT ).show();
		if ( id == R.id.activity_add_edit_pump_FEHole1Number ) {
			Toast.makeText( this, "Scan FEH1", Toast.LENGTH_SHORT ).show();
			
			scanSensor = true;
			qrScanSensor.initiateScan();
		}
		if ( id == R.id.activity_add_edit_pump_FEHole5Number ) {
			Toast.makeText( this, "Scan FEH5", Toast.LENGTH_SHORT ).show();
			
			scanSensor = true;
			qrScanSensor.initiateScan();
		}
		if ( id == R.id.activity_add_edit_pump_PEHole1Number ) {
			Toast.makeText( this, "Scan PEH1", Toast.LENGTH_SHORT ).show();
			
			scanSensor = true;
			qrScanSensor.initiateScan();
		}
		if ( id == R.id.activity_add_edit_pump_PEHole5Number ) {
			Toast.makeText( this, "Scan PEH5", Toast.LENGTH_SHORT ).show();
			
			scanSensor = true;
			qrScanSensor.initiateScan();
		}
		if ( id == R.id.activity_add_edit_pump_unitNumber ) {
			Toast.makeText( this, "Scan Unit", Toast.LENGTH_SHORT ).show();
			scanUnit = true;
			qrScanUnit.initiateScan();
		}
	}
	
	@Override
	public boolean onLongClick( View v ) {
		
		int id = v.getId();
		if ( id == R.id.activity_add_edit_pump_FEHole1Number ) {
			manual( FEHole1Number );
		}
		if ( id == R.id.activity_add_edit_pump_FEHole5Number ) {
			manual( FEHole5Number );
		}
		if ( id == R.id.activity_add_edit_pump_PEHole1Number ) {
			manual( PEHole1Number );
		}
		if ( id == R.id.activity_add_edit_pump_PEHole5Number ) {
			manual( PEHole5Number );
		}
		if ( id == R.id.activity_add_edit_pump_unitNumber ) {
			manual( unitNumber );
		}
		if ( id == R.id.activity_add_edit_pump_PESerialNumber ) {
			manual( PESerialNumber );
		}
		if ( id == R.id.activity_add_edit_pump_FESerialNumber ) {
			manual( FESerialNumber );
		}
		if ( id == R.id.activity_add_edit_pump_stationNumber ) {
			manual( stationNumber );
		}
		
		return false;
	}
	
	public void manual( final TextView textView ) {
		////////Manual data entry////////////////////////////////////////////////////////////////
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder( AddEditPumpActivity.this );
		alertDialog.setTitle( "Manual Entry" );
		if ( textView == FEHole1Number ) {
			alertDialog.setMessage( "FE HOLE 1" );
		}
		if ( textView == FEHole5Number ) {
			alertDialog.setMessage( "FE HOLE 5" );
		}
		if ( textView == PEHole1Number ) {
			alertDialog.setMessage( "PE HOLE 1" );
		}
		if ( textView == PEHole5Number ) {
			alertDialog.setMessage( "PE HOLE 5" );
		}
		if ( textView == unitNumber ) {
			alertDialog.setMessage( "UNIT NUMBER" );
		}
		if ( textView == PESerialNumber ) {
			alertDialog.setMessage( "PE SERIAL#" );
		}
		if ( textView == FESerialNumber ) {
			alertDialog.setMessage( "Station#" );
		}
		///////receiving manual_entry_dialog entry//////////////////////////////////////////////
		final EditText input = new EditText( AddEditPumpActivity.this );
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
		                                                              LinearLayout.LayoutParams.MATCH_PARENT );
		input.setLayoutParams( lp );
		alertDialog.setView( input );
		/////////save///////////////////////////////////////////////////////////////
		alertDialog.setPositiveButton( "save", new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog, int which ) {
				
				if ( textView == FEHole1Number ) {
					alertDialog.setMessage( "FE HOLE 1" );
					FEHole1Number.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == FEHole5Number ) {
					alertDialog.setMessage( "FE HOLE 5" );
					FEHole5Number.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == PEHole1Number ) {
					alertDialog.setMessage( "PE HOLE 1" );
					PEHole1Number.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == PEHole5Number ) {
					alertDialog.setMessage( "PE HOLE 5" );
					PEHole5Number.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == unitNumber ) {
					alertDialog.setMessage( "Unit Number" );
					unitNumber.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == PESerialNumber ) {
					alertDialog.setMessage( "Powerend Serial Number" );
					PESerialNumber.setText( input.getText().toString().toUpperCase().trim() );
				}
				if ( textView == FESerialNumber ) {
					alertDialog.setMessage( "Fluidend Serial Number" );
					FESerialNumber.setText( input.getText().toString().toUpperCase().trim() );
				}
			}
			
		} );
		/////////Cancel//////////////////////////////////////////////////////////////////////
		alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog, int which ) {
				// Write your code here to execute after dialog
				dialog.cancel();
			}
		} );
		/////////Show Dialog/////////////////////////////////////////////////////////////////
		alertDialog.show();
	}
	
	
}
