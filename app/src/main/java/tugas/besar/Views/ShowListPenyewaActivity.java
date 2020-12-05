package tugas.besar.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import tugas.besar.API.PenyewaResponse;
import tugas.besar.Adapters.PenyewaRecyclerAdapter;
import tugas.besar.AdminActivity;
import tugas.besar.CreateMotorActivity;
import tugas.besar.CreatePenyewaActivity;
import tugas.besar.Models.PenyewaDAO;
import tugas.besar.R;

public class ShowListPenyewaActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private PenyewaRecyclerAdapter recyclerAdapter;
    private List<PenyewaDAO> penyewa = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_show_list_penyewa);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button btnTambahPenyewa = findViewById(R.id.btnTambahPenyewa);
        btnTambahPenyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowListPenyewaActivity.this, CreatePenyewaActivity.class);
                startActivity(i);
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
        Call<PenyewaResponse> call = apiService.getAllPenyewa("data");

        call.enqueue(new Callback<PenyewaResponse>() {
            @Override
            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response){
                generateDataList(response.body().getPenyewas());
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t){
                Toast.makeText(ShowListPenyewaActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<PenyewaDAO> penyewaList) {
        recyclerView = findViewById(R.id.penyewaRecyclerView);
        recyclerAdapter = new PenyewaRecyclerAdapter(this, penyewaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowListPenyewaActivity.this);
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