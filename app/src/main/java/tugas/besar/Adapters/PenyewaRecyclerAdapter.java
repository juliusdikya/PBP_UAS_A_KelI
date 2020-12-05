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

import tugas.besar.DetailPenyewaFragment;
import tugas.besar.Models.PenyewaDAO;
import tugas.besar.R;

public class  PenyewaRecyclerAdapter extends RecyclerView.Adapter<PenyewaRecyclerAdapter.RoomViewHolder> implements Filterable {
    private List<PenyewaDAO> dataList;
    private List<PenyewaDAO> filteredDataList;
    private Context context;

    public PenyewaRecyclerAdapter(Context context, List<PenyewaDAO> dataList){
        this.context = context;
        this.dataList = dataList;
        this.filteredDataList = dataList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_adapter_penyewa, parent, false);
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
                    List<PenyewaDAO> filteredList = new ArrayList<>();
                    for (PenyewaDAO PenyewaDAO : dataList){
                        if (PenyewaDAO.getNama_penyewa().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(PenyewaDAO);
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
                filteredDataList = (List<PenyewaDAO>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onBindViewHolder(@NonNull PenyewaRecyclerAdapter.RoomViewHolder holder, int position) {

        final PenyewaDAO pyw = filteredDataList.get(position);
        holder.twNamaPenyewa.setText(pyw.getNama_penyewa());
        holder.twNamaMotor.setText(pyw.getNama_motor());
        holder.twDurasi.setText(pyw.getDurasi_sewa());
//        holder.twPembayaran.setText(pyw.getHarga());

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailPenyewaFragment dialog = new DetailPenyewaFragment();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", pyw.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twNamaPenyewa, twDurasi, twNamaMotor, twPembayaran;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twNamaPenyewa = itemView.findViewById(R.id.twNamaPenyewa);
            twNamaMotor = itemView.findViewById(R.id.twNamaMotor);
            twDurasi = itemView.findViewById(R.id.twDurasi);
//            twPembayaran = itemView.findViewById(R.id.twPembayaran);

            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
