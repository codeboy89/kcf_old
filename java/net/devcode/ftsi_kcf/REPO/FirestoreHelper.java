package net.devcode.ftsi_kcf.REPO;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

public class FirestoreHelper {
	private static final String TAG = "FireStoreHelper";
	private FirebaseFirestore db;
	
	public FirestoreHelper() {
	
		db = FirebaseFirestore.getInstance();
	}
	
	
	public void addDocument(String colPath, Object object, final OnCompletedAddDocument onCompletedAddDocument) {
		db = FirebaseFirestore.getInstance();
		db.collection( colPath ).add( object ).addOnSuccessListener( new OnSuccessListener < DocumentReference >() {
			@Override
			public void onSuccess(DocumentReference documentReference) {
				onCompletedAddDocument.completedAddDocument( documentReference );
				Log.d( TAG, "DocumentSnapshot added with ID: " + documentReference.getId() );
			}
		} ).addOnFailureListener( new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				onCompletedAddDocument.completedAddDocument( null );
				Log.w( TAG, "Error adding document", e );
			}
		} );
	
		
		
	}
	
	public void setDocument(final String colPath, final String doc, Object object, final OnCompletedSetDocument onCompletedSetDocument) {
		db = FirebaseFirestore.getInstance();
		db.collection( colPath ).document( doc ).set( object ).addOnCompleteListener( new OnCompleteListener < Void >() {
			@Override
			public void onComplete(@NonNull Task < Void > task) {
				
				if ( task.isSuccessful() ) {
					onCompletedSetDocument.completedSetDocument( true );
					Log.i( TAG, " setDocument successful: " + colPath + doc );
				}
				else {
					onCompletedSetDocument.completedSetDocument( false );
					Log.i( TAG, " setDocument failed: " + colPath + doc );
				}
			}
		} );
	}
	
	public void getDocument(String colPath, String document, final OnCompletedGetDocument onCompletedGetDocument) {
		db = FirebaseFirestore.getInstance();
		DocumentReference docRef = db.collection( colPath ).document( document );
		docRef.get().addOnCompleteListener( new OnCompleteListener < DocumentSnapshot >() {
			@Override
			public void onComplete(@NonNull Task < DocumentSnapshot > task) {
				
				if ( task.isSuccessful() ) {
					DocumentSnapshot document = task.getResult();
					if ( document.exists() ) {
						onCompletedGetDocument.completedGetDocument( document );
						Log.d( TAG, "DocumentSnapshot data: " + document.getData() );
					}
					else {
						onCompletedGetDocument.completedGetDocument( null );
						Log.d( TAG, "No such document" );
					}
				}
				else {
					Log.d( TAG, "get failed with ", task.getException() );
				}
			}
		} );
	}
	
	public void getCollection(String colPath, final OnCompletedGetCollection onCompletedGetCollection) {
		db = FirebaseFirestore.getInstance();
		db.collection( colPath ).get().addOnCompleteListener( new OnCompleteListener < QuerySnapshot >() {
			@Override
			public void onComplete(@NonNull Task < QuerySnapshot > task) {
				
				if ( task.isSuccessful() ) {
					onCompletedGetCollection.completedGetCollection( task );
					for ( QueryDocumentSnapshot document : task.getResult() ) {
						Log.d( TAG, document.getId() + " => " + document.getData() );
					}
				}
				else {
					onCompletedGetCollection.completedGetCollection(null);
					Log.d( TAG, "Error getting documents: ", task.getException() );
				}
			}
		} );
	}
	
	
}
