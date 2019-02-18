package net.devcode.ftsi_kcf.DAO;

import net.devcode.ftsi_kcf.Models.PumpModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PumpDao {
	
	@Insert
	void insert( PumpModel pumpModel);
	
	@Update
	void update( PumpModel pumpModel);
	
	@Delete
	void delete( PumpModel pumpModel);
	
	@Query("DELETE FROM pump_table")
	void  deleteAllPumps();
	
	@Query("SELECT * FROM pump_table ORDER BY station ASC")
	LiveData<List< PumpModel >> getAllPumpsLive();
	
}
