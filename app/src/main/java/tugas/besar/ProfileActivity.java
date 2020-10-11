package tugas.besar;


import android.app.AlertDialog;

import android.content.DialogInterface;

import android.graphics.Color;

import android.os.AsyncTask;

import android.os.Bundle;

import android.provider.ContactsContract;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.Button;

import android.widget.LinearLayout;

import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatDelegate;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.textfield.TextInputEditText;


import com.google.android.material.textfield.TextInputLayout;




public class UpdateProfile extends Fragment {


    TextInputEditText nameText;

    TextInputLayout layoutName;

    Button saveBtn, deleteBtn, cancelBtn;


    ContactsContract.Profile profile;


    LinearLayout ll;

    int darkM = AppCompatDelegate.MODE_NIGHT_YES;
     Profile profile;


    public void UpdateProfil() {

        // Required empty public constructor

    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile container, false);

        Profile = (profile) getArguments().getSerializable("guru");

        nameText = view.findViewById(R.id.input_name);

        layoutName = view.findViewById(R.id.input_name_layout);
        ll = (LinearLayout) view.findViewById(R.id.linearLayout);


        if (AppCompatDelegate.getDefaultNightMode() == darkM)

        {

            ll.setBackgroundColor(Color.parseColor("#141414"));

        }

        else

        {

            ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }


        saveBtn = view.findViewById(R.id.btn_update);

        deleteBtn = view.findViewById(R.id.btn_delete);

        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {

            nameText.setText(profile.getFullName());

        } catch (Exception ex) {

            ex.printStackTrace();

        }
        return view;

    }


    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                if(nameText.getText().toString().isEmpty() ){
                    layoutName.setError("Please fill name correctly.");
                }

                else

                {

                    profile.setFullName(nameText.getText().toString());

                    update(profile);

                }


            }

        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Are you sure to delete?");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int id) {

                        delete(profile);

                    }

                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {


                    }

                })

                        .show();


            }

        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.hide(UpdateProfile.this).commit();

            }

        });

    }



    private void update(final Profile profile){

        class UpdateUser extends AsyncTask<Void, Void, Void> {


            @Override

            protected Void doInBackground(Void... voids) {


                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()

                        .guruDao()

                        .update(profile);

                return null;

            }


            @Override

            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                Toast.makeText(getActivity().getApplicationContext(), "Guru updated", Toast.LENGTH_SHORT).show();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.hide(UpdateProfile.this).commit();

            }

        }


        UpdateUser update = new UpdateUser();

        update.execute();

    }


    private void delete(final Profile profile){

        class DeleteUser extends AsyncTask<Void, Void, Void> {


            @Override

            protected Void doInBackground(Void... voids) {


                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()

                        .guruDao()

                        .delete(profile);

                return null;

            }


            @Override

            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                Toast.makeText(getActivity().getApplicationContext(), "Guru deleted", Toast.LENGTH_SHORT).show();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.hide(UpdateProfile.this).commit();

            }

        }


        DeleteUser delete = new DeleteUser();

        delete.execute();

    }
}