package net.devcode.ftsi_kcf.VM;

import android.app.Application;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.REPO.DataRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class FleetViewModel extends AndroidViewModel {
	
	private String TAG = "FleetViewModel";
	private DataRepository repository;
	
	public FleetViewModel(@NonNull Application application) {
		
		super( application );
		repository = new DataRepository( application );
	
	}
	
	public void updateFleetModel(FleetModel fleetModel){
		repository.updateFleetModel( fleetModel );
	}
	
	public FleetModel getFleetModel(){
		return repository.getFleetModel();
	}


}
