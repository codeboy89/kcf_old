package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.devcode.ftsi_kcf.Utils.Utils;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.REPO.DataRepository;
import net.devcode.ftsi_kcf.VM.FleetViewModel;
import net.devcode.ftsi_kcf.VM.PumpViewModel;


import androidx.appcompat.app.AppCompatActivity;

import static net.devcode.ftsi_kcf.Utils.Utils.passwordString;
import static net.devcode.ftsi_kcf.Utils.Utils.usernameString;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener {
	
	private String TAG = "StartUpActivity";
	private DataRepository dataRepository;
	private FleetViewModel fleetViewModel;
	private PumpViewModel pumpViewModel;
	EditText username;
	EditText password;
	Button login;
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		/////Activity Setup////////////////////////////////////////////////////////////
		getSupportActionBar().hide();
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_start_up );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		username = findViewById( R.id.activity_start_up_edittext_username );
		password = findViewById( R.id.activity_start_up_edittext_password );
		login = findViewById( R.id.activity_start_up_button_login );
		login.setOnClickListener( this );
		
	}
	
	@Override
	public void onClick( View v ) {
		/////Correct Login//////////////////////////////////////////////////////////////
		String user = username.getText().toString().toLowerCase();
		if ( username.getText().toString().toLowerCase().equals( usernameString ) ) {
			if ( password.getText().toString().toLowerCase().equals( passwordString ) ) {
				Intent myIntent = new Intent( StartUpActivity.this, PreAuditActivity.class );
				myIntent.putExtra( Utils.EXTRA_USER, user );
				startActivity( myIntent );
			}
		}
		/////Incorrect Password//////////////////////////////////////////////////////////////
		
		if ( username.getText().toString().toLowerCase().equals( usernameString ) ) {
			if ( ! (password.getText().toString().toLowerCase().equals( passwordString )) ) {
				Toast.makeText( this, "Incorrect Password", Integer.parseInt( "1000" ) ).show();
				password.setText( "" );
			}
		}
		/////Incorrect Username//////////////////////////////////////////////////////////////
		
		else if ( ! username.getText().toString().toLowerCase().equals( usernameString ) ) {
			Toast.makeText( this, "Incorrect Username", Integer.parseInt( "1000" ) ).show();
			password.setText( "" );
			username.setText( "" );
		}
		
	}
	
}

