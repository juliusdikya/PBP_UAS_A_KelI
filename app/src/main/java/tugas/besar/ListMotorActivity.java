package tugas.besar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

import tugas.besar.databinding.ActivityListmotorBinding;

public class ListMotorActivity extends AppCompatActivity {

    ArrayList<Motor> ListMotor;
    private RecyclerView recyclerView;
    private RecyclerViewList adapter;
    private ActivityListmotorBinding binding;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listmotor);
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        recyclerView = binding.recyclerViewMotor;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListMotor = new DaftarMotor().MOTOR;
        adapter = new RecyclerViewList(ListMotor);
        recyclerView.setAdapter(adapter);

    }
}