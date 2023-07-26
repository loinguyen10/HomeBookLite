package com.united.homebooklite.signActivity;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.united.homebooklite.MainActivity;
import com.united.homebooklite.R;
import com.united.homebooklite.api.InterfacePhp;
import com.united.homebooklite.api.SvrResponse;
import com.united.homebooklite.models.Account;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInFragment extends Fragment {

    EditText txtEmail,txtPassword;
    Button login;
    TextView forgetPassword,donthaveAccount;
    CheckBox remember;
    List<Account> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        anhXa(view);

        SharedPreferences sP = getActivity().getSharedPreferences("User_Remember", MODE_PRIVATE);
        String email = sP.getString("Email", "");
        String pass = sP.getString("Password", "");
        Boolean rem = sP.getBoolean("Remember", false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User_File", MODE_PRIVATE);

        txtEmail.setText(email);
        txtPassword.setText(pass);
        remember.setChecked(rem);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eI = txtEmail.getText().toString();
                String pI = txtPassword.getText().toString();

                Boolean check = true;

                if(eI.trim().length() <= 0){
                    check = false;
                    txtEmail.setError("Enter your email.");
                }
                if(pI.trim().length() <= 0){
                    check = false;
                    txtPassword.setError("Enter your password.");
                }
                if(check){
                    rememberUser(eI,pI,remember.isChecked());
                    loginSelect(eI,pI);
                }
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        donthaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        return view;
    }

    private void anhXa(View v){

        txtEmail = v.findViewById(R.id.emailIn);
        txtPassword = v.findViewById(R.id.passIn);

        remember = v.findViewById(R.id.rememberBox);
        forgetPassword = v.findViewById(R.id.forgetPassword);
        donthaveAccount = v.findViewById(R.id.nextToSignup);

        login = v.findViewById(R.id.logIn);
    }

    private void signUp(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signLayout,new SignUpFragment())
                .commit();
    }

    private void loginSelect(String email,String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lmatmet1234.000webhostapp.com/homebook/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfacePhp interfacex = retrofit.create(InterfacePhp.class);
        Call<SvrResponse> call = interfacex.getAccounts(email,pass);
        call.enqueue(new Callback<SvrResponse>() {
            @Override
            public void onResponse(Call<SvrResponse> call, Response<SvrResponse> response) {
                SvrResponse svrResponseSelect = response.body();
                if(svrResponseSelect.getAccounts() != null){
                    setLogin(Arrays.asList(svrResponseSelect.getAccounts()).get(0));
                    Log.d("ok","ok");

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }else{
                    Log.d("ko","ko");
                }
            }

            @Override
            public void onFailure(Call<SvrResponse> call, Throwable t) {
                Log.d("Message: ", t.getMessage());
                System.out.println("Message: " + t.getMessage());
            }
        });
    }

    public void rememberUser(String e, String p, boolean s) {
        SharedPreferences pref = getActivity().getSharedPreferences("User_Remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (s) {
            editor.putString("Email", e);
            editor.putString("Password", p);
            editor.putBoolean("Remember", s);
        } else {
            editor.clear();
        }
        editor.commit();
    }

    private void setLogin(Account acc){
        SharedPreferences sP = getActivity().getSharedPreferences("Account_File", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString("Email", acc.getEmail());
        editor.putInt("Id", acc.getId());
        editor.putString("Password", acc.getPassword());
        editor.putString("Role", acc.getRole());
        editor.putString("Fullname", acc.getName());
        editor.putString("Phone", acc.getPhone());

        editor.commit();
    }
}