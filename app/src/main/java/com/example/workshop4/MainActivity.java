package com.example.workshop4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText username;
private EditText password;
private CheckBox mcheckbox;
SharedPreferences sharedpref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pword);
        mcheckbox= (CheckBox) findViewById(R.id.checkbox);

        SharedPreferences sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        getPreferencesData();

        String user_name = sharedpref.getString("username","");
        String pass_word = sharedpref.getString("password","");

        username.setText(user_name);
        password.setText(pass_word);

        //username.getText().clear();
        // password.getText().clear();
    }

    private void getPreferencesData() {
        SharedPreferences sp= getSharedPreferences("userInfo", MODE_PRIVATE );
        if (sp.contains("username")) {

            String U = sp.getString( "username", "not found.");
            username.setText(U.toString());
        }

        if (sp.contains("password")) {

            String p = sp.getString( "password", "not found.");
            password.setText(p.toString());
        }

        if (sp.contains("mcheckbox")) {

            Boolean b = sp.getBoolean( "mcheckbox",false);
            mcheckbox.setChecked(b);

        }
    }

    public void signin(View view) {
        if(mcheckbox.isChecked())
        {
            Boolean bollcheck = mcheckbox.isChecked();
            sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpref.edit();
            editor.putString("username", username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putBoolean("checkbox",bollcheck);
            editor.apply();
            Toast.makeText(this, "Your Username and Password has been saved", Toast.LENGTH_SHORT).show();
        }

        if(username.getText().toString().equals("shreeram")&&password.getText().toString().equals("shrestha")){
            Intent intent = new Intent(this,afterlogin.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Username or Password Error", Toast.LENGTH_SHORT).show();
        }
    }
}
