package tugas.besar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tugas.besar.API.ClientAPI;
import tugas.besar.API.InterfaceAPI;
import tugas.besar.API.MotorResponse;
import tugas.besar.API.PenyewaResponse;
import tugas.besar.Views.EditMotorActivity;
import tugas.besar.Views.EditPenyewaActivity;

public class DetailPenyewaFragment extends DialogFragment {
    private TextView twNamaMotor, twNamaPenyewa, twDurasi, twPembayaran;
    private String sIdPenyewa, sNamaMotor, sNamaPenyewa, sDurasi, sPembayaran;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button deleteBtn, editBtn;

    public static DetailPenyewaFragment newInstance() {return new DetailPenyewaFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View v = inflater.inflate(R.layout.detail_penyewa_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNamaPenyewa = v.findViewById(R.id.twNamaPenyewa);
        twNamaMotor = v.findViewById(R.id.twNamaMotor);
        twDurasi = v.findViewById(R.id.twDurasi);
        twPembayaran = v.findViewById(R.id.twPembayaran);

        deleteBtn = v.findViewById(R.id.btnDelete);
        editBtn = v.findViewById(R.id.btnEdit);

        sIdPenyewa = getArguments().getString("id", "");
        loadUserById(sIdPenyewa);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdPenyewa);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditPenyewaActivity.class);
                i.putExtra("id", sIdPenyewa);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }

    private void loadUserById(String id) {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<PenyewaResponse> add = apiService.getPenyewaById(id, "data");

        add.enqueue(new Callback<PenyewaResponse>() {
            @Override
            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response) {
                sNamaPenyewa = response.body().getPenyewas().get(0).getNama_penyewa();
                sNamaMotor = response.body().getPenyewas().get(0).getNama_motor();
                sDurasi = response.body().getPenyewas().get(0).getDurasi_sewa();
                sPembayaran = response.body().getPenyewas().get(0).getHarga();

                twNamaPenyewa.setText(sNamaPenyewa);
                twNamaMotor.setText(sNamaMotor);
                twDurasi.setText(sDurasi);
                twPembayaran.setText(sPembayaran);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


    private void delete(String id) {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        final Call<PenyewaResponse> req = apiService.deletePenyewa(id, "", "", "", "");


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        req.enqueue(new Callback<PenyewaResponse>() {
                            @Override
                            public void onResponse(Call<PenyewaResponse> call, Response<PenyewaResponse> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", response.body().getMessage());

                                dismiss();
                            }
                            @Override
                            public void onFailure(Call<PenyewaResponse> call, Throwable t) {
                                Toast.makeText(getContext(), "Gagal Delete", Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", "Msg: " + t.getMessage());
                            }
                        });
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Yakin hapus?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}