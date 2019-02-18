package net.devcode.ftsi_kcf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devcode.ftsi_kcf.Models.PrevAuditModel;
import net.devcode.ftsi_kcf.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PrevAuditListAdapter extends ListAdapter< PrevAuditModel, PrevAuditListAdapter.NoteHolder > {
	
	private onItemClickListener listener;
	
	public PrevAuditListAdapter( ) {
		
		super( DIFF_CALLBACK );
	}
	
	private  static  final  DiffUtil.ItemCallback< PrevAuditModel > DIFF_CALLBACK = new DiffUtil.ItemCallback < PrevAuditModel >() {
		@Override
		public boolean areItemsTheSame(@NonNull PrevAuditModel oldItem, @NonNull PrevAuditModel newItem ) {
			
			return oldItem.getDate() == newItem.getDate();
		}
		
		@Override
		public boolean areContentsTheSame(@NonNull PrevAuditModel oldItem, @NonNull PrevAuditModel newItem ) {
			
			return oldItem.toString() == newItem.toString();
		}
	};
	@NonNull
	@Override
	public NoteHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		
		LayoutInflater layoutInflater;
		View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.prev_audit_list_item, parent, false );
		return new NoteHolder( itemView );
	}
	
	@Override
	public void onBindViewHolder( @NonNull NoteHolder holder, int position ) {
		
		PrevAuditModel currentPrevAuditModel = getItem( position );
		holder.textViewFleetName.setText( currentPrevAuditModel.getFleet() );
		holder.textViewEntryDate.setText( currentPrevAuditModel.getDate() );
		holder.textViewLocation.setText( currentPrevAuditModel.getLocation() );
		holder.textViewEntryCount.setText( String.valueOf( currentPrevAuditModel.getEntryCount() ));
	}
	
	
	
	public PrevAuditModel getFleetAt(int position ) {
		
		return getItem( position );
	}
	
	public void setOnItemClickListener( onItemClickListener listener ) {
		
		this.listener = listener;
	}
	
	public interface onItemClickListener {
		
		void onItemClick( PrevAuditModel prevAuditModel);
	}
	
	class NoteHolder extends RecyclerView.ViewHolder {
		
		private TextView textViewFleetName;
		private TextView textViewEntryDate;
		private TextView textViewLocation;
		private TextView textViewEntryCount;
		
		public NoteHolder( @NonNull View itemView ) {
			
			super( itemView );
			textViewFleetName = itemView.findViewById( R.id.prev_audit_list_textview_fleet );
			textViewEntryDate = itemView.findViewById( R.id.prev_audit_list_textview__dateEntered );
			textViewLocation = itemView.findViewById( R.id.prev_audit_list_textview_location );
			textViewEntryCount = itemView.findViewById( R.id.prev_audit_list_textview__entryOrder );
			
			itemView.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					
					int position = getAdapterPosition();
					if ( listener != null && position != RecyclerView.NO_POSITION ) {
						listener.onItemClick( getItem( position ) );
					}
				}
			} );
			
		}
	}
}
