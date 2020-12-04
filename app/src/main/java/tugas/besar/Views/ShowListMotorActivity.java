package tugas.besar.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;
import tugas.besar.Adapters.MotorRecyclerAdapter;
import tugas.besar.Models.MotorDAO;
import tugas.besar.R;

public class ShowListMotorActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private MotorRecyclerAdapter recyclerAdapter;
    private List<MotorDAO> motor = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_motor);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        searchView = findViewById(R.id.searchUser);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        swipeRefresh.setRefreshing(true);
        loadUser();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUser();
            }
        });

    }

    private void loadUser() {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<MotorResponse> call = apiService.getAllMotor("data");

        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response){
                generateDataList(response.body().getMotors());
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t){
                Toast.makeText(ShowListMotorActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<MotorDAO> motorList) {
        recyclerView = findViewById(R.id.motorRecyclerView);
        recyclerAdapter = new MotorRecyclerAdapter(this, motorList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowListMotorActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryString) {
                recyclerAdapter.getFilter().filter(queryString);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String queryString) {
                recyclerAdapter.getFilter().filter(queryString);
                return false;
            }
        });
    }
}