package net.devcode.ftsi_kcf.REPO;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface OnCompletedGetCollection {
	public void completedGetCollection(Task< QuerySnapshot > querySnapshotTask);
	
}
