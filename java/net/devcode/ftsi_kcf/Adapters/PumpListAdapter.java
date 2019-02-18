package net.devcode.ftsi_kcf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devcode.ftsi_kcf.Models.PumpModel;
import net.devcode.ftsi_kcf.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PumpListAdapter extends ListAdapter< PumpModel, PumpListAdapter.NoteHolder > {
	
	private onItemClickListener listener;
	
	public PumpListAdapter( ) {
		
		super( DIFF_CALLBACK );
	}
	
	private  static  final  DiffUtil.ItemCallback< PumpModel > DIFF_CALLBACK = new DiffUtil.ItemCallback < PumpModel >() {
		@Override
		public boolean areItemsTheSame(@NonNull PumpModel oldItem, @NonNull PumpModel newItem ) {
			
			return oldItem.getId() == newItem.getId();
		}
		
		@Override
		public boolean areContentsTheSame(@NonNull PumpModel oldItem, @NonNull PumpModel newItem ) {
			
			return oldItem.toString() == newItem.toString();
		}
	};
	@NonNull
	@Override
	public NoteHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		
		LayoutInflater layoutInflater;
		View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.pump_list_item, parent, false );
		return new NoteHolder( itemView );
	}
	
	@Override
	public void onBindViewHolder( @NonNull NoteHolder holder, int position ) {
		
		PumpModel currentPumpModel = getItem( position );
		holder.textViewPumpName.setText( currentPumpModel.getPumpName() );
		holder.textViewStation.setText( currentPumpModel.getStation() );
	}
	

	
	public PumpModel getPumpAt(int position ) {
		
		return getItem( position );
	}
	
	public void setOnItemClickListener( onItemClickListener listener ) {
		
		this.listener = listener;
	}
	
	public interface onItemClickListener {
		
		void onItemClick( PumpModel pumpModel);
	}
	
	class NoteHolder extends RecyclerView.ViewHolder {
		
		private TextView textViewPumpName;
		private TextView textViewStation;
		
		public NoteHolder( @NonNull View itemView ) {
			
			super( itemView );
			textViewPumpName = itemView.findViewById( R.id.pump_list_item_textview_pumpname );
			textViewStation = itemView.findViewById( R.id.pump_list_item_textview_station );
			
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
