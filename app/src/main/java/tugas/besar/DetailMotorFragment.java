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
import tugas.besar.Views.EditMotorActivity;

public class DetailMotorFragment extends DialogFragment {
    private TextView twNamaMotor, twHargaSewa;
    private String sIdMotor, sNamaMotor, sHargaSewa;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;

    private Button deleteBtn, editBtn;

    public static DetailMotorFragment newInstance() {return new DetailMotorFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View v = inflater.inflate(R.layout.detail_motor_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNamaMotor = v.findViewById(R.id.twNamaMotor);
        twHargaSewa = v.findViewById(R.id.twHargaSewa);

        deleteBtn = v.findViewById(R.id.btnDelete);
        editBtn = v.findViewById(R.id.btnEdit);

        sIdMotor = getArguments().getString("id", "");
        loadUserById(sIdMotor);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(sIdMotor);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditMotorActivity.class);
                i.putExtra("id", sIdMotor);
                startActivity(i);
                dismiss();
            }
        });

        return v;
    }

    private void loadUserById(String id) {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        Call<MotorResponse> add = apiService.getMotorById(id, "data");

        add.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                sNamaMotor = response.body().getMotors().get(0).getNama_motor();
                sHargaSewa = response.body().getMotors().get(0).getHarga_sewa();

                twNamaMotor.setText(sNamaMotor);
                twHargaSewa.setText(sHargaSewa);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


    private void delete(String id) {
        InterfaceAPI apiService = ClientAPI.getClient().create(InterfaceAPI.class);
        final Call<MotorResponse> req = apiService.deleteMotor(id, "", "", "", "");


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        req.enqueue(new Callback<MotorResponse>() {
                            @Override
                            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("DELETE", response.body().getMessage());

                                dismiss();
                            }
                            @Override
                            public void onFailure(Call<MotorResponse> call, Throwable t) {
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