package com.united.homebooklite.signActivity;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.united.homebooklite.R;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpFragment extends Fragment {

    TextInputEditText txtEmail, txtPassword, txtPasswordAgain, txtBirthday, txtFullname, txtMobilePhone;
    Button signup;
    TextView haveAccount, clickHere;
    RadioButton collaborate, member;
    CheckBox accept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        anhXa(view);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtEmail.getText().toString().trim().length() <= 0) {

                }
                if (txtPassword.getText().toString().trim().length() <= 0) {

                }
                if (txtPasswordAgain.getText().toString().trim().length() <= 0) {

                }
                if (txtFullname.getText().toString().trim().length() <= 0) {

                }
                if (txtMobilePhone.getText().toString().trim().length() <= 0) {

                }
                if (!collaborate.isChecked() && !member.isChecked()) {

                } else {
                    signUp();
                }
            }
        });

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        return view;
    }

    private void anhXa(View v) {
        txtEmail = v.findViewById(R.id.emailUp);
        txtPassword = v.findViewById(R.id.passUp);
        txtPasswordAgain = v.findViewById(R.id.passUpAgain);
        txtBirthday = v.findViewById(R.id.birthUp);
        txtFullname = v.findViewById(R.id.nameUp);
        txtMobilePhone = v.findViewById(R.id.phoneUp);

        signup = v.findViewById(R.id.signUp);
        haveAccount = v.findViewById(R.id.backToLogin);
        clickHere = v.findViewById(R.id.clickForDetails);

        collaborate = v.findViewById(R.id.collaborateUP);
        member = v.findViewById(R.id.memberUp);
        accept = v.findViewById(R.id.checkAccept);
    }

    private void logIn() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signLayout, new LogInFragment())
                .commit();
    }

    private void signUp() {
        Account acc = new Account();
        acc.setEmail(txtEmail.getText().toString());
        acc.setPassword(txtPassword.getText().toString());
        acc.setName(txtFullname.getText().toString());
        acc.setPhone(txtMobilePhone.getText().toString());
        if (collaborate.isChecked()) {
            acc.setRole("owner");
        }
        if (member.isChecked()) {
            acc.setRole("member");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfaceInsert = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call =
                interfaceInsert.insertAccount(acc.getEmail(), acc.getPassword(), acc.getRole(), acc.getName(), acc.getPhone());
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseInsert = response.body();
                Toast.makeText(getActivity(), "Thành công.", Toast.LENGTH_SHORT);
                Log.d("Message: ", svrResponseInsert.getMessage());
                rememberUser(acc.getEmail(),acc.getPassword());
                logIn();
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT);
                Log.d("Message: ", t.getMessage());
                Log.e("Message: ", t.getMessage());
            }
        });
    }

    private void rememberUser(String e, String p) {
        SharedPreferences pref = getActivity().getSharedPreferences("User_Remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("Email", e);
        editor.putString("Password", p);
        editor.commit();
    }

}