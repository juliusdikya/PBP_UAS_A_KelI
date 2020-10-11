package tugas.besar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tugas.besar.databinding.RecyclerviewListBinding;
import java.util.ArrayList;

public class RecyclerViewList extends RecyclerView.Adapter<RecyclerViewList.mhsViewHolder> {
    private Context context;
    private ArrayList<Motor> ListMotor;
    private RecyclerviewListBinding binding;

    public RecyclerViewList(ArrayList<Motor> mahasiswaList) {
        this.ListMotor = mahasiswaList;
    }

    @NonNull
    @Override
    public mhsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = RecyclerviewListBinding.inflate(layoutInflater, parent, false);
        return new mhsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull mhsViewHolder holder, int position) {
        Motor motor = ListMotor.get(position);
        holder.bind(motor);
    }

    @Override
    public int getItemCount() {
        if(ListMotor != null){
            return ListMotor.size();
        }
        else {
            return 0;
        }
    }

    class mhsViewHolder extends RecyclerView.ViewHolder{
        private RecyclerviewListBinding binding;

        public mhsViewHolder(@NonNull RecyclerviewListBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Motor motor){
            binding.setMotor(motor);
            binding.executePendingBindings();
        }
    }

    public void onClick(View view) {
        Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
    }
}
