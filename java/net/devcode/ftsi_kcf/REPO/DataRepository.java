package net.devcode.ftsi_kcf.REPO;

import android.app.Application;
import android.os.AsyncTask;

import net.devcode.ftsi_kcf.DAO.PumpDao;
import net.devcode.ftsi_kcf.DB.PumpDatabase;
import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.Models.PumpModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DataRepository {
	
	public static DataRepository getInstance() {
		
		return instance;
	}
	
	private static DataRepository instance;
	FirestoreHelper firestoreHelper;
	
	private String TAG = "DataRepository";
	private PumpDao pumpDao;
	private LiveData < List < PumpModel > > allPumpsLive;
	private Object object;
	private FleetModel prevFleetModel;
	private FleetModel fleetModelCache = FleetModel.getInstance();
	
	public DataRepository(Application application) {
		
		firestoreHelper = new FirestoreHelper();
		PumpDatabase pumpDatabase = PumpDatabase.getInstance( application );
		pumpDao = pumpDatabase.pumpDao();
		allPumpsLive = pumpDao.getAllPumpsLive();
	}
	
	public FleetModel getPrevFleetModel() {
		
		return prevFleetModel;
	}
	
	public void setPrevFleetModel(FleetModel prevFleetModel) {
		
		this.prevFleetModel = prevFleetModel;
	}
	
	public void insertPump(PumpModel pumpModel) {
		
		new InsertPumpAsyncTask( pumpDao ).execute( pumpModel );
	}
	
	public void updatePump(PumpModel pumpModel) {
		
		new UpdatePumpAsyncTask( pumpDao ).execute( pumpModel );
	}
	
	public void deletePump(PumpModel pumpModel) {
		
		new DeletePumpAsyncTask( pumpDao ).execute( pumpModel );
	}
	
	public void deleteAllPumps() {
		
		new DeleteAllPumpAsyncTask( pumpDao ).execute();
	}
	
	public LiveData < List < PumpModel > > getAllPumpsLive() {
		
		return allPumpsLive;
	}
	
	public void updateFleetModel(FleetModel fleetModel) {
		
		fleetModelCache = fleetModel;
	}
	
	public FleetModel getFleetModel() {
		
		return fleetModelCache;
	}
	///////////////Firestore/////////////////////////////////////////////////////////////////
	
	public void firestoreAddDocument(String colPath, Object object, OnCompletedAddDocument onCompletedAddDocument) {
		
		firestoreHelper.addDocument( colPath, object, onCompletedAddDocument );
	}
	
	public void firestoreSetDocument(String colPath, String doc, Object object, OnCompletedSetDocument onCompletedSetDocument) {
		
		firestoreHelper.setDocument( colPath, doc, object, onCompletedSetDocument );
	}
	
	public void firestoreGetDocument(String colPath, String document, OnCompletedGetDocument onCompletedGetDocument) {
		
		firestoreHelper.getDocument( colPath, document, onCompletedGetDocument );
	}
	
	public void firestoreGetCollection(String colPath, OnCompletedGetCollection onCompletedGetCollection) {
		
		firestoreHelper.getCollection( colPath, onCompletedGetCollection );
		
	}
	
	
	/////////////////////Classes//////////////////////////////////////////////////////////////
	
	private static class InsertPumpAsyncTask extends AsyncTask < PumpModel, Void, Void > {
		
		private PumpDao pumpDao;
		
		private InsertPumpAsyncTask(PumpDao pumpDao) {
			
			this.pumpDao = pumpDao;
		}
		
		@Override
		protected Void doInBackground(PumpModel... pumpModels) {
			
			pumpDao.insert( pumpModels[0] );
			return null;
		}
	}
	
	private static class UpdatePumpAsyncTask extends AsyncTask < PumpModel, Void, Void > {
		
		private PumpDao pumpDao;
		
		private UpdatePumpAsyncTask(PumpDao pumpDao) {
			
			this.pumpDao = pumpDao;
		}
		
		@Override
		protected Void doInBackground(PumpModel... pumpModels) {
			
			pumpDao.update( pumpModels[0] );
			return null;
		}
	}
	
	
	private static class DeletePumpAsyncTask extends AsyncTask < PumpModel, Void, Void > {
		
		private PumpDao pumpDao;
		
		private DeletePumpAsyncTask(PumpDao pumpDao) {
			
			this.pumpDao = pumpDao;
		}
		
		@Override
		protected Void doInBackground(PumpModel... pumpModels) {
			
			pumpDao.delete( pumpModels[0] );
			return null;
		}
	}
	
	
	private static class DeleteAllPumpAsyncTask extends AsyncTask < Void, Void, Void > {
		
		private PumpDao pumpDao;
		
		private DeleteAllPumpAsyncTask(PumpDao pumpDao) {
			
			this.pumpDao = pumpDao;
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			
			pumpDao.deleteAllPumps();
			return null;
		}
	}
	
	
}
