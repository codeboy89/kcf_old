package net.devcode.ftsi_kcf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devcode.ftsi_kcf.Models.FleetModel;
import net.devcode.ftsi_kcf.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FleetAdapter extends ListAdapter< FleetModel, FleetAdapter.NoteHolder > {
	
	private onItemClickListener listener;
	
	public FleetAdapter( ) {
		
		super( DIFF_CALLBACK );
	}
	
	private  static  final  DiffUtil.ItemCallback< FleetModel > DIFF_CALLBACK = new DiffUtil.ItemCallback < FleetModel >() {
		@Override
		public boolean areItemsTheSame(@NonNull FleetModel oldItem, @NonNull FleetModel newItem ) {
			
			//return oldItem.getId() == newItem.getId();
			return false;
		}
		
		@Override
		public boolean areContentsTheSame(@NonNull FleetModel oldItem, @NonNull FleetModel newItem ) {
			
			return oldItem.toString() == newItem.toString();
		}
	};
	@NonNull
	@Override
	public NoteHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		
		LayoutInflater layoutInflater;
		View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.fleet_item, parent, false );
		return new NoteHolder( itemView );
	}
	
	@Override
	public void onBindViewHolder( @NonNull NoteHolder holder, int position ) {
		
		FleetModel currentFleetModel = getItem( position );
		holder.textViewFleetName.setText( currentFleetModel.getFleetName() );
		holder.textViewDate.setText( currentFleetModel.getDate().toString() );
	}
	

	
	public FleetModel getFleetAt(int position ) {
		
		return getItem( position );
	}
	
	public void setOnItemClickListener( onItemClickListener listener ) {
		
		this.listener = listener;
	}
	
	public interface onItemClickListener {
		
		void onItemClick(FleetModel FleetModel);
	}
	
	class NoteHolder extends RecyclerView.ViewHolder {
		
		private TextView textViewFleetName;
		private TextView textViewDate;
		
		public NoteHolder( @NonNull View itemView ) {
			
			super( itemView );
			textViewFleetName = itemView.findViewById( R.id.fleet_item_textview_fleetName );
			textViewDate = itemView.findViewById( R.id.fleet_item_textview_date );
			
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
