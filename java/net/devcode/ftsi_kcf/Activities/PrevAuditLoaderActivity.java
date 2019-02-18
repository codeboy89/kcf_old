package net.devcode.ftsi_kcf.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.devcode.ftsi_kcf.Adapters.PrevAuditListAdapter;
import net.devcode.ftsi_kcf.Adapters.PrevAuditListAdapter.onItemClickListener;
import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.Models.PrevAuditModel;
import net.devcode.ftsi_kcf.R;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetCollection;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetDocument;
import net.devcode.ftsi_kcf.VM.PrevAuditViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrevAuditLoaderActivity extends AppCompatActivity {
	private ProgressBar progressBar;
	private String TAG = "PrevAuditLoaderActivity";
	private String auditsPath;
	private String document;
	private String selectedPrevAuditId;
	private List < PrevAuditModel > prevAuditModels;
	private PrevAuditListAdapter prevAuditListAdapter;
	private String user;
	private String fleet;
	private String district;
	private PrevAuditViewModel prevAuditViewModel;
	private FleetModel fleetModel = FleetModel.getInstance();
	//District/District$/Fleets/Fleet$/Audits
	private String prevAuditPath;
	private RecyclerView recyclerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		prevAuditModels = new ArrayList <>();
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_prev_audit_loader );
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		
		getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close );
		setTitle( "Audit Loader" );
		
		user = fleetModel.getUser();
		fleet = fleetModel.getFleetName();
		district = fleetModel.getDistrictName();
		auditsPath = "District/" + district + "/Fleets/" + fleet + "/Audits";
		prevAuditPath = "District/" + district + "/Fleets/" + fleet + "/PreviousAudits";
		
		Log.i( TAG, "onCreate: prevAuditPath: " + prevAuditPath );
		
		recyclerView = findViewById( R.id.activity_prev_audit_loader_prev_audit_recycler_view );
		progressBar = findViewById( R.id.activity_prev_audit_loader_activity_pre_audit_loader_progressBar );
		
		recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
		recyclerView.setHasFixedSize( true );
		
		prevAuditListAdapter = new PrevAuditListAdapter();
		recyclerView.setAdapter( prevAuditListAdapter );
		
		///////view model////////////////////////////////////////////////////////////////////
		prevAuditViewModel = ViewModelProviders.of( this ).get( PrevAuditViewModel.class );
		
		
		new ItemTouchHelper( new ItemTouchHelper.SimpleCallback( 0, ItemTouchHelper.LEFT ) {
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
				
				return false;
			}
			
			@Override
			public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
				
				//pumpViewModel.delete( pumpAdapter.getPumpAt( viewHolder.getAdapterPosition() ) );
				//Toast.makeText( PumpListActivity.this,
				//                "Deleted PumpModel: " + pumpAdapter.getPumpAt( viewHolder.getAdapterPosition() ),
				//                Toast.LENGTH_SHORT ).show();
			}
		} ).attachToRecyclerView( recyclerView );
		
		prevAuditListAdapter.setOnItemClickListener( new onItemClickListener() {
			@Override
			public void onItemClick(PrevAuditModel prevAuditModel) {
				
				document = prevAuditModel.getId();
				getPrevAuditItem();
			}
		} );
		
		progressBar.setVisibility( View.VISIBLE );
		getPrevAudits();
	}
	
	private void getPrevAuditItem() {
		
		prevAuditViewModel.getPrevAuditItem( prevAuditPath , document, new OnCompletedGetDocument() {
			@Override
			public void completedGetDocument(DocumentSnapshot documentSnapshot) {
				
				if ( documentSnapshot != null ) {
					progressBar.setVisibility( View.GONE );
					selectedPrevAuditId = documentSnapshot.getId();
					Log.i( TAG, "GetPrevAuditItem: OnCompletedGetDocument: Successful: " + documentSnapshot.getId());
					Toast.makeText( getApplicationContext(),documentSnapshot.toObject( PrevAuditModel.class ).toString(), Toast.LENGTH_LONG ).show();
					prevAuditViewModel.getPrevAudit( auditsPath, documentSnapshot.toObject( PrevAuditModel.class ).getAuditId(  ), new OnCompletedGetDocument() {
						@Override
						public void completedGetDocument(DocumentSnapshot documentSnapshot) {
							if ( documentSnapshot!= null ){
								
								fleetModel = documentSnapshot.toObject( FleetModel.class );
								Intent myIntent = new Intent( PrevAuditLoaderActivity.this, PumpListActivity.class );
								myIntent.putExtra( "ID", fleetModel.getPumpListId() );
								startActivity( myIntent );
								Log.i( TAG, "GetPrevAudit: OnCompletedGetDocument: GetPrevAudit: Successful: Starting PumpListActivity " + fleetModel.getId());
								Log.i( TAG, "GetPrevAudit: OnCompletedGetDocument: GetPrevAudit: Successful: Starting PumpListActivity " + fleetModel.toString());
								
								
							}else{
								Log.i( TAG, "GetPrevAuditItem: OnCompletedGetDocument: GetPrevAudit: Failure: ");
								Toast.makeText( getApplicationContext(),"GetPrevAuditItem: OnCompletedGetDocument: GetPrevAudit: Failure: ", Toast.LENGTH_LONG ).show();
								
							}
						}
					} );
				}
				else {
					progressBar.setVisibility( View.GONE );
					Toast.makeText( getApplicationContext(), "Loading PrevAuditItem Failed!", Toast.LENGTH_SHORT ).show();
					Log.i( TAG, "GetPrevAuditItem: OnCompletedGetDocument: Failed: " );
				}
			}
		} );
		
	}
	
	private void getPrevAudits() {
		
		prevAuditModels.clear();
		prevAuditViewModel.getPrevAudits( prevAuditPath, new OnCompletedGetCollection() {
			@Override
			public void completedGetCollection(Task < QuerySnapshot > querySnapshotTask) {
				
				if ( querySnapshotTask != null ) {
					progressBar.setVisibility( View.GONE );
					
					List < DocumentSnapshot > documents = querySnapshotTask.getResult().getDocuments();
					for ( DocumentSnapshot documentSnapshot : documents ) {
						prevAuditModels.add( documentSnapshot.toObject( PrevAuditModel.class ) );
						
						Log.i( TAG, "GetPrevAudits: OnCompletedGetCollection: Adding: " + documentSnapshot.toObject( PrevAuditModel.class ) );
					}
					
					sort();
					prevAuditListAdapter.submitList( prevAuditModels );
					
					Log.i( TAG, "GetPrevAudits: OnCompletedGetCollection: Successful: " );
				}
				else {
					progressBar.setVisibility( View.GONE );
					Toast.makeText( getApplicationContext(), "GetPrevAudits: OnCompletedGetCollections: Failed!", Toast.LENGTH_SHORT ).show();
					Log.i( TAG, "GetPrevAudits: OnCompletedGetCollections: Failed: " );
				}
			}
			
			
		} );
		recyclerView.setVisibility( View.VISIBLE );
	}
	
	public void sort() {
		
		
		Collections.sort( prevAuditModels, new Comparator < PrevAuditModel >() {
			@Override
			public int compare(PrevAuditModel o1, PrevAuditModel o2) {
				
				// Sorting implementation here
				return Integer.parseInt( String.valueOf( String.valueOf( o2.getTime() ).compareTo( String.valueOf( o1.getTime() ) ) ) );
				
			}
		} );
		
		
		for ( int i = 0; i < prevAuditModels.size(); i++ ) {
			prevAuditModels.get( i ).setEntryCount( i + 1 );
			Log.i( TAG, "call: Sorted: " + prevAuditModels.get( i ).getTime() + "-" + prevAuditModels.get( i ).getEntryCount() );
		}
		
		if ( prevAuditModels.isEmpty() ) {
			
			Toast.makeText( this, "No previous Audits availible!", Toast.LENGTH_LONG ).show();
			setContentView( R.layout.error );
			try {
				Thread.sleep( 2000 );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
			setContentView( R.layout.activity_prev_audit_loader );
		}
		else {
			prevAuditListAdapter.submitList( prevAuditModels );
			
		}
		
	}
	
}

