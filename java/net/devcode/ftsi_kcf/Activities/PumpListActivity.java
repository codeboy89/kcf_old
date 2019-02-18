package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import net.devcode.ftsi_kcf.Adapters.PumpListAdapter;
import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.Models.FleetPumpList;
import net.devcode.ftsi_kcf.Models.PrevAuditModel;
import net.devcode.ftsi_kcf.Models.PumpModel;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.REPO.OnCompletedAddDocument;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetDocument;
import net.devcode.ftsi_kcf.REPO.OnCompletedSetDocument;
import net.devcode.ftsi_kcf.Utils.Utils;
import net.devcode.ftsi_kcf.VM.PumpViewModel;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FEH1;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FEH5;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_FLUIDENDSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PEH1;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PEH5;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_POWERENDSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PSI;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PSIGUAGE;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PUMPNAME;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_PUMPSERIAL;
import static net.devcode.ftsi_kcf.Utils.Utils.EXTRA_STATION;

public class PumpListActivity extends AppCompatActivity {
	
	FleetModel fleetModel = FleetModel.getInstance();
	private String TAG = "PumpListActivity";
	private String districtName;
	private String fleetName;
	private PumpViewModel pumpViewModel;
	private FloatingActionButton buttonAddPump;
	private List < PumpModel > pumpModelList;
	private PumpListAdapter pumpListAdapter;
	private RecyclerView recyclerView;
	private FleetPumpList fleetPumpList;
	private String pumpListId;
	private PrevAuditModel prevAuditModel;
	private String pumpListPath;
	private String auditsPath;
	private String prevAuditsPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_pump_list );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		setTitle( "Pumps" );
		
		recyclerView = findViewById( R.id.activity_pump_list_recyclerview );
		buttonAddPump = findViewById( R.id.activity_pump_list_button_add );
		
		pumpViewModel = ViewModelProviders.of( this ).get( PumpViewModel.class );
		
		
		init();
		
		if ( fleetModel != null ) {
			if ( fleetModel.getDistrictName() != null && fleetModel.getFleetName() != null && getIntent().getStringExtra( "ID" ) != null ) {
				Log.i( TAG, "OnCreate: FleetPumpList: FleetModel: " + fleetModel.toString() );
				Intent intent = getIntent();
				
				String prevPumpListId = intent.getStringExtra( "ID" );
				String prevPumpListPath = "District/" + fleetModel.getDistrictName() + "/Fleets/" + fleetModel.getFleetName() + "/Pumps";
				pumpViewModel.getFleetPumpList( prevPumpListPath, prevPumpListId, new OnCompletedGetDocument() {
					@Override
					public void completedGetDocument(DocumentSnapshot documentSnapshot) {
						
						if ( documentSnapshot != null ) {
							fleetPumpList = documentSnapshot.toObject( FleetPumpList.class );
							Log.i( TAG, "OnCreate: FleetPumpList: completedGetDocument: Successful" );
							Log.i( TAG, "OnCreate: FleetPumpList: completedGetDocument: Adding: " + fleetPumpList.getId() );
							pumpViewModel.deleteAllPumps();
							for ( PumpModel pumpModel : fleetPumpList.getPumpModelList() ) {
								Log.i( TAG, "OnCreate: FleetPumpList: completedGetDocument: Adding: " + pumpModel.toString() );
								pumpViewModel.insert( pumpModel );
							}
						}
						else {
							Log.i( TAG, "OnCreate: FleetPumpList: completedGetDocument: PrevFleetModel = Null" );
						}
					}
				} );
				
			}
			else {
				pumpViewModel.deleteAllPumps();
				Log.i( TAG, "OnCreate: FleetPumpList: Checking: pumpViewModel.getPrevFleetModel() != null: is Null" );
			}
		}
	}
	
	private void init() {
		
		recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
		recyclerView.setHasFixedSize( true );
		
		pumpListAdapter = new PumpListAdapter();
		recyclerView.setAdapter( pumpListAdapter );
		
		buttonAddPump.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent( PumpListActivity.this, AddEditPumpActivity.class );
				startActivityForResult( intent, Utils.ADD_PUMP_REQUEST );
				
			}
		} );
		
		
		pumpViewModel.getAllPumpsLive().observe( this, new Observer < List < PumpModel > >() {
			@Override
			public void onChanged(List < PumpModel > pumpModels) {
				
				pumpListAdapter.submitList( pumpModels );
				pumpModelList = pumpModels;
				
				for ( PumpModel p : pumpModelList ) {
					Log.i( TAG, "onChanged: " + p.toString() );
				}
			}
		} );
		
		new ItemTouchHelper( new ItemTouchHelper.SimpleCallback( 0, ItemTouchHelper.LEFT ) {
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
				
				return false;
			}
			
			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
				
				pumpViewModel.delete( pumpListAdapter.getPumpAt( viewHolder.getAdapterPosition() ) );
				Toast.makeText( PumpListActivity.this, "Deleted PumpModel: " + pumpListAdapter.getPumpAt( viewHolder.getAdapterPosition() ), Toast.LENGTH_SHORT ).show();
			}
		} ).attachToRecyclerView( recyclerView );
		
		pumpListAdapter.setOnItemClickListener( new PumpListAdapter.onItemClickListener() {
			@Override
			public void onItemClick(PumpModel pumpModel) {
				
				Intent pumpData = new Intent( PumpListActivity.this, AddEditPumpActivity.class );
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
				pumpData.putExtra( Utils.EXTRA_ID, pumpModel.getId() );
				startActivityForResult( pumpData, Utils.EDIT_PUMP_REQUEST );
			}
		} );
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		
		super.onActivityResult( requestCode, resultCode, data );
		
		if ( resultCode == RESULT_OK && requestCode == Utils.ADD_PUMP_REQUEST ) {
			
			PumpModel pumpModel = new PumpModel( data.getStringExtra( EXTRA_PUMPNAME ), data.getStringExtra( EXTRA_PUMPSERIAL ), data.getStringExtra( EXTRA_POWERENDSERIAL ), data.getStringExtra( EXTRA_FLUIDENDSERIAL ),
			                                     data.getStringExtra( EXTRA_FEH1 ), data.getStringExtra( EXTRA_FEH5 ), data.getStringExtra( EXTRA_PEH1 ), data.getStringExtra( EXTRA_PEH5 ),
			                                     data.getStringExtra( EXTRA_STATION ), data.getStringExtra( EXTRA_PSI ), data.getStringExtra( EXTRA_PSIGUAGE ) );
			
			pumpViewModel.insert( pumpModel );
			
			Toast.makeText( this, "ADDING: " + pumpModel.toString(), Toast.LENGTH_SHORT ).show();
		}
		else if ( resultCode == RESULT_OK && requestCode == Utils.EDIT_PUMP_REQUEST ) {
			int id = 0;
			if ( data != null ) {
				id = data.getIntExtra( Utils.EXTRA_ID, - 1 );
			}
			if ( id == - 1 ) {
				Toast.makeText( this, "PumpModel can't br updated", Toast.LENGTH_SHORT ).show();
				return;
			}
			
			PumpModel pumpModel = new PumpModel( data.getStringExtra( EXTRA_PUMPNAME ), data.getStringExtra( EXTRA_PUMPSERIAL ), data.getStringExtra( EXTRA_POWERENDSERIAL ), data.getStringExtra( EXTRA_FLUIDENDSERIAL ),
			                                     data.getStringExtra( EXTRA_FEH1 ), data.getStringExtra( EXTRA_FEH5 ), data.getStringExtra( EXTRA_PEH1 ), data.getStringExtra( EXTRA_PEH5 ),
			                                     data.getStringExtra( EXTRA_STATION ), data.getStringExtra( EXTRA_PSI ), data.getStringExtra( EXTRA_PSIGUAGE ) );
			pumpModel.setId( id );
			pumpViewModel.update( pumpModel );
			
			Toast.makeText( this, "PumpModel: " + pumpModel.getPumpName() + " updated", Toast.LENGTH_SHORT ).show();
		}
		else {
			Toast.makeText( this, "NO PUMPS ADDED", Toast.LENGTH_SHORT ).show();
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
				undo();
				return true;
			
			case R.id.redo_pump_list:
				redo();
				return true;
			
			case R.id.restore_pump_list:
				restore();
				return true;
			
			case R.id.clear_pump_list:
				clear();
				return true;
			
			case R.id.save_pump_list:
				save();
				return true;
			
			default:
				return super.onOptionsItemSelected( item );
			
		}
	}
	
	private void undo() {
		
		Toast.makeText( this, "undo", Toast.LENGTH_SHORT ).show();
		
	}
	
	private void redo() {
		
		Toast.makeText( this, "redo", Toast.LENGTH_SHORT ).show();
		
	}
	
	private void restore() {
		
		Toast.makeText( this, "restore", Toast.LENGTH_SHORT ).show();
	}
	
	private void clear() {
		
		for ( PumpModel p : pumpModelList ) {
			
			Log.i( TAG, "Clearing: " + p.toString() );
			
			pumpViewModel.deleteAllPumps();
		}
		
	}
	
	private void save() {
		
		fleetModel = FleetModel.getInstance();
		districtName = fleetModel.getDistrictName();
		fleetName = fleetModel.getFleetName();
		
		pumpListPath = "District/" + districtName + "/Fleets/" + fleetName + "/Pumps";
		auditsPath = "District/" + districtName + "/Fleets/" + fleetName + "/Audits";
		prevAuditsPath = "District/" + districtName + "/Fleets/" + fleetName + "/PreviousAudits";
		
		fleetModel.setDate( new SimpleDateFormat( "E, MMM dd yyyy" ).format( new Date() ) );
		fleetModel.setTime( new SimpleDateFormat( "HH:mm:ss.SSS z" ).format( Calendar.getInstance().getTime() ) );
		
		
		fleetPumpList = new FleetPumpList( fleetModel.getDistrictName(), fleetModel.getFleetName(), fleetModel.getUser(), fleetModel.getDate(), fleetModel.getTime(), pumpViewModel.getAllPumpsLive().getValue() );
		
		insertPumpLists();
		
		try {
			Thread.sleep( 1000 );
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		
	}
	
	private void insertPumpLists() {
		
		Log.i( TAG, "Inserting PumpList: " + fleetPumpList.toString() );
		pumpViewModel.insertPumpList( pumpListPath, fleetPumpList, new OnCompletedAddDocument() {
			@Override
			public void completedAddDocument(DocumentReference documentReference) {
				
				if ( documentReference != null ) {
					Log.i( TAG, "Insert PumpList: completedAddDocument: " + documentReference.getId() );
					Toast.makeText( getApplicationContext(), "Success inserting pumplist! " + documentReference.getId(), Toast.LENGTH_SHORT ).show();
					pumpListId = documentReference.getId();
					fleetPumpList.setId( documentReference.getId() );
					pumpViewModel.updatePumpList( pumpListPath, documentReference.getId(), fleetPumpList, new OnCompletedSetDocument() {
						@Override
						public void completedSetDocument(Boolean isSuccesful) {
							
							if ( isSuccesful ) {
								Log.i( TAG, "Insert PumpList: updatePumpList: completedSetDocument: Successful" );
								Toast.makeText( getApplicationContext(), "updatePumpList ID update Successful!", Toast.LENGTH_SHORT ).show();
							}
							else {
								Log.i( TAG, "Insert PumpList: updatePumpList: completedSetDocument: Failed" );
								Toast.makeText( getApplicationContext(), "updatePumpList ID update Failed!", Toast.LENGTH_SHORT ).show();
							}
						}
					} );
					insertAuditItem();
				}
				else {
					Log.i( TAG, "Insert PumpList: completedAddDocument: Failed" );
					Toast.makeText( getApplicationContext(), "Error inserting pumplist!", Toast.LENGTH_SHORT ).show();
				}
			}
		} );
	}
	
	public void insertAuditItem() {
		
		fleetModel.setPumpListId( pumpListId );
		Log.i( TAG, "Inserting AuditItem: " + fleetModel.toString() );
		pumpViewModel.insertAuditItem( auditsPath, fleetModel, new OnCompletedAddDocument() {
			@Override
			public void completedAddDocument(DocumentReference documentReference) {
				
				if ( documentReference != null ) {
					Log.i( TAG, "Insert AuditItem: completedAddDocument: " + documentReference.getId() );
					Toast.makeText( getApplicationContext(), "Success inserting AuditItem! " + documentReference.getId(), Toast.LENGTH_SHORT ).show();
					fleetModel.setId( documentReference.getId() );
					pumpViewModel.updateAuditItem( auditsPath, documentReference.getId(), fleetModel, new OnCompletedSetDocument() {
						@Override
						public void completedSetDocument(Boolean isSuccesful) {
							
							if ( isSuccesful ) {
								Log.i( TAG, "Insert PumpList: updatePumpList: completedSetDocument: Successful" );
								Toast.makeText( getApplicationContext(), "updatePumpList ID update Successful!", Toast.LENGTH_SHORT ).show();
							}
							else {
								Log.i( TAG, "Insert PumpList: updatePumpList: completedSetDocument: Failed" );
								Toast.makeText( getApplicationContext(), "updatePumpList ID update Failed!", Toast.LENGTH_SHORT ).show();
							}
						}
					} );
					insertPreviousAuditItem( fleetModel.getId() );
				}
				else {
					Log.i( TAG, "Insert AuditItem: completedAddDocument: Failed" );
					Toast.makeText( getApplicationContext(), "Error inserting AuditItem!", Toast.LENGTH_SHORT ).show();
					
				}
			}
		} );
	}
	
	public void insertPreviousAuditItem(String auditId) {
		
		prevAuditModel = new PrevAuditModel( fleetModel.getFleetName(), fleetModel.getLocation(), fleetModel.getDate(), fleetModel.getTime(), pumpListId, auditId, "" );
		Log.i( TAG, "Inserting PrevAuditItem: " + prevAuditModel.toString() );
		pumpViewModel.insertPrevAuditItem( prevAuditsPath, prevAuditModel, new OnCompletedAddDocument() {
			@Override
			public void completedAddDocument(DocumentReference documentReference) {
				
				if ( documentReference != null ) {
					
					Log.i( TAG, "Insert PrevAuditItem: completedAddDocument: " + documentReference.getId() );
					Toast.makeText( getApplicationContext(), "Success inserting PrevAuditItem! " + documentReference.getId(), Toast.LENGTH_SHORT ).show();
					prevAuditModel.setId( documentReference.getId() );
					
					pumpViewModel.updatePrevAuditItem( prevAuditsPath, documentReference.getId(), prevAuditModel, new OnCompletedSetDocument() {
						@Override
						public void completedSetDocument(Boolean isSuccesful) {
							
							if ( isSuccesful ) {
								Log.i( TAG, "Insert PrevAuditItem: updatePrevAuditItem: completedSetDocument: Successful" );
								Toast.makeText( getApplicationContext(), "PrevAuditItem ID update Successful!", Toast.LENGTH_SHORT ).show();
							}
							else {
								Log.i( TAG, "Insert PrevAuditItem: updatePrevAuditItem: completedSetDocument: Failed" );
								Toast.makeText( getApplicationContext(), "PrevAuditItem ID update Failed!", Toast.LENGTH_SHORT ).show();
							}
						}
					} );
				}
				else {
					Log.i( TAG, "Insert PrevAuditItem: completedAddDocument: Failed" );
					Toast.makeText( getApplicationContext(), "Error inserting PrevAuditItem!", Toast.LENGTH_SHORT ).show();
				}
			}
		} );
		
	}
	
	
}
