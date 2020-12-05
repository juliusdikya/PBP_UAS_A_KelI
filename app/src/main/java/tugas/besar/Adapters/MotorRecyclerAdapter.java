package tugas.besar.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import tugas.besar.DetailMotorFragment;
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

        final MotorDAO mtr = filteredDataList.get(position);
        holder.twNamaMotor.setText(mtr.getNama_motor());
        holder.twHargaSewa.setText(mtr.getHarga_sewa());
        Glide.with(context)
                .load(mtr.getImg_motor())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivUrl);

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailMotorFragment dialog = new DetailMotorFragment();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", mtr.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{
        private TextView twNamaMotor, twHargaSewa;
        private ImageView ivUrl;
        private LinearLayout mParent;

        public RoomViewHolder(@NonNull View itemView){
            super(itemView);
            twNamaMotor = itemView.findViewById(R.id.twNamaMotor);
            twHargaSewa = itemView.findViewById(R.id.twHargaSewa);
            ivUrl = itemView.findViewById(R.id.ivUrl);

            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }
}
