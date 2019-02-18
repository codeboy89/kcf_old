package net.devcode.ftsi_kcf.VM;

import android.app.Application;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.Models.FleetPumpList;
import net.devcode.ftsi_kcf.Models.PrevAuditModel;
import net.devcode.ftsi_kcf.Models.PumpModel;
import net.devcode.ftsi_kcf.REPO.DataRepository;
import net.devcode.ftsi_kcf.REPO.OnCompletedAddDocument;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetCollection;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetDocument;
import net.devcode.ftsi_kcf.REPO.OnCompletedSetDocument;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PumpViewModel extends AndroidViewModel {
	
	private String TAG = "PumpViewModel";
	private DataRepository repository;
	private LiveData < List < PumpModel > > allPumpsLive;
	private List < PumpModel > restorePoint;
	
	public PumpViewModel(@NonNull Application application) {
		
		super( application );
			repository = new DataRepository( application );
		allPumpsLive = repository.getAllPumpsLive();
		
		
	}
	
	public FleetModel getFleetModel() {
		
		return repository.getFleetModel();
	}
	
	/////////PUMPS///////////////////////////////////////////////////
	public void insert(PumpModel pumpModel) {
		
		repository.insertPump( pumpModel );
	}
	
	public void update(PumpModel pumpModel) {
		
		repository.updatePump( pumpModel );
	}
	
	public void delete(PumpModel pumpModel) {
		
		repository.deletePump( pumpModel );
	}
	
	public void deleteAllPumps() {
		
		repository.deleteAllPumps();
	}
	
	public LiveData < List < PumpModel > > getAllPumpsLive() {
		
		return allPumpsLive;
	}
	
	
	public void insertPumpList(String collectionPath, FleetPumpList fleetPumpList, OnCompletedAddDocument onCompletedAddDocument) {
		
		repository.firestoreAddDocument( collectionPath, fleetPumpList, onCompletedAddDocument );
	}
	
	public void insertPrevAuditItem(String collectionPath, PrevAuditModel prevAudit, OnCompletedAddDocument onCompletedAddDocument) {
		
		repository.firestoreAddDocument( collectionPath, prevAudit, onCompletedAddDocument );
	}
	
	public void insertAuditItem(String collectionPath, FleetModel fleetModel, OnCompletedAddDocument onCompletedAddDocument) {
		
		repository.firestoreAddDocument( collectionPath, fleetModel, onCompletedAddDocument);
	}
	
	public void getAuditList(String collectionPath, OnCompletedGetCollection onCompletedGetCollection) {
		
		repository.firestoreGetCollection( collectionPath, onCompletedGetCollection );
	}
	
	public void getFleetPumpList(String collectionPath, String document, OnCompletedGetDocument onCompletedGetDocument) {
		
		repository.firestoreGetDocument( collectionPath, document, onCompletedGetDocument );
	}
	
	public void getPrevAuditList(String collectionPath, OnCompletedGetCollection onCompletedGetCollection) {
		
		repository.firestoreGetCollection( collectionPath, onCompletedGetCollection );
	}
	
	public void updatePrevAuditItem(String collectionPath, String document, PrevAuditModel prevAuditModel, OnCompletedSetDocument onCompletedSetDocument) {
		repository.firestoreSetDocument( collectionPath, document, prevAuditModel, onCompletedSetDocument);
	}
	
	public void updatePumpList(String collectionPath, String document, FleetPumpList fleetPumpList, OnCompletedSetDocument onCompletedSetDocument) {
		repository.firestoreSetDocument( collectionPath, document, fleetPumpList, onCompletedSetDocument);
	}
	
	public void updateAuditItem(String collectionPath, String document, FleetModel fleetModel, OnCompletedSetDocument onCompletedSetDocument) {
		repository.firestoreSetDocument( collectionPath, document, fleetModel, onCompletedSetDocument);
	}
	
	public FleetModel getPrevFleetModel() {
		return repository.getPrevFleetModel();
	}
}
