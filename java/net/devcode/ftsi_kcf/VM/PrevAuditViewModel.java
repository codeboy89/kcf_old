package net.devcode.ftsi_kcf.VM;

import android.app.Application;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.REPO.DataRepository;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetCollection;
import net.devcode.ftsi_kcf.REPO.OnCompletedGetDocument;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class PrevAuditViewModel extends AndroidViewModel {
	
	private String TAG = "PrevAuditViewModel";
	private FleetModel fleetModel;
	private DataRepository repository;
	
	public PrevAuditViewModel(@NonNull Application application) {
		
		super( application );
		repository = new DataRepository( application );
		fleetModel = FleetModel.getInstance();
	}
	public void setFleetModel(FleetModel fleetModel){
		repository.setPrevFleetModel( fleetModel );
	}
	
	public FleetModel getPrevFleetModel(){
		return repository.getPrevFleetModel();
	}
	public void getPrevAuditItem(String collectionPath, String document, OnCompletedGetDocument onCompletedGetDocument) {
		
		repository.firestoreGetDocument( collectionPath, document, onCompletedGetDocument );
	}
	
	public void getPrevAudits(String collectionPath, OnCompletedGetCollection onCompletedGetCollection) {
		repository.firestoreGetCollection( collectionPath,onCompletedGetCollection );
	}
	
	public void getPrevAudit(String collectionPath, String document, OnCompletedGetDocument onCompletedGetDocument) {
		repository.firestoreGetDocument( collectionPath, document, onCompletedGetDocument );
	}
}