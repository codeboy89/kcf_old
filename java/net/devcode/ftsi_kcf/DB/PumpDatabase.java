package net.devcode.ftsi_kcf.DB;

import android.content.Context;
import android.os.AsyncTask;

import net.devcode.ftsi_kcf.DAO.PumpDao;
import net.devcode.ftsi_kcf.Models.PumpModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PumpModel.class}, version = 1, exportSchema = false)
public abstract class PumpDatabase extends RoomDatabase {
	
	private static PumpDatabase instance;
	
	public abstract PumpDao pumpDao();
	
	public static synchronized  PumpDatabase getInstance(Context context)
	{
		if(instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(),
					PumpDatabase.class, "pump_database")
					.fallbackToDestructiveMigration()
					.addCallback(pumpRoomCallback)
					.build();
		}
		return instance;
	}
	
	private static RoomDatabase.Callback pumpRoomCallback = new RoomDatabase.Callback(){
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulatePumpDBAsyncTask(instance).execute();
		}
	};
	private static class PopulatePumpDBAsyncTask extends AsyncTask<Void,Void,Void>
	{
		private PumpDao pumpDao;
		
		private PopulatePumpDBAsyncTask(PumpDatabase db)
		{
			pumpDao = db.pumpDao();
		}
		@Override
		protected Void doInBackground(Void... voids) {
			
			pumpDao.insert(new PumpModel( "53Q-11482", "testSN", "testPESN", "TestFESN", "testFEH1",
			                              "TestFEH5", "TestPEH1", "TestPEH5", "1", Boolean.toString(true), Boolean.toString(true)));
			
			pumpDao.insert(new PumpModel( "53Q-10345", "testSN", "testPESN", "TestFESN", "testFEH1",
			                              "TestFEH5", "TestPEH1", "TestPEH5", "2", Boolean.toString(true), Boolean.toString(true)));
			
			pumpDao.insert(new PumpModel( "53Q-11688", "testSN", "testPESN", "TestFESN", "testFEH1",
			                              "TestFEH5", "TestPEH1", "TestPEH5", "3", Boolean.toString(true), Boolean.toString(true)));
			
			return null;
		}
	}
}
