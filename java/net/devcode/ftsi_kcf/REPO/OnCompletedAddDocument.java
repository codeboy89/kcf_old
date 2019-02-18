package net.devcode.ftsi_kcf.REPO;

import com.google.firebase.firestore.DocumentReference;

public interface OnCompletedAddDocument {
	
	public void completedAddDocument(DocumentReference documentReference);
}
