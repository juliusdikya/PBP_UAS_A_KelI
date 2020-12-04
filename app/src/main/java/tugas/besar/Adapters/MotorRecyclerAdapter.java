package tugas.besar.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tugas.besar.Models.MotorDAO;
import tugas.besar.R;

public class MotorRecyclerAdapter extends RecyclerView.Adapter<MotorRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<MotorDAO> dataList;
    private List<MotorDAO> filteredDataList;
    private Context context;

    public MotorRecyclerAdapter(Context context, List<MotorDAO> dataList){
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_adapter_motor, parent, false);
        return  new RoomViewHolder(view);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()){
                    filteredDataList = dataList;
                } else{
                    List<MotorDAO> filteredList = new ArrayList<>();
                    for (MotorDAO MotorDAO : dataList){
                        if (MotorDAO.getNama_motor().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(MotorDAO);
                        }
                        filteredDataList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredDataList = (List<MotorDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onBindViewHolder(@NonNull MotorRecyclerAdapter.RoomViewHolder holder, int position) {

        final MotorDAO brg = filteredDataList.get(position);
        holder.twNamaMotor.setText(brg.getNama_motor());
        holder.twHargaSewa.setText(brg.getHarga_sewa());

//        holder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
//                DetailUserFragment dialog = new DetailUserFragment();
//                dialog.show(manager, "dialog");
//
//                Bundle args = new Bundle();
//                args.putString("id", brg.getId());
//                dialog.setArguments(args);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twNamaMotor, twHargaSewa;
//        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twNamaMotor = itemView.findViewById(R.id.twNamaMotor);
            twHargaSewa = itemView.findViewById(R.id.twHargaSewa);
//            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
