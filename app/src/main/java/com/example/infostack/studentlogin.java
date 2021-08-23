package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class studentlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        TextView signin= (TextView) findViewById(R.id.sign_up_txt_std);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                startActivity(new Intent(studentlogin.this,stafflogin.class));
                finish();
            }
        });


        Button stdlogin= (Button) findViewById(R.id.sign_in_btn_std);
        stdlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText mail=(EditText) findViewById(R.id.user_id_edit_txt_std);
                EditText pass=(EditText) findViewById(R.id.password_edit_txt_std);

                String userEmail=mail.getText().toString().trim();
                String userPass=pass.getText().toString().trim();
                if(!isValidEmail(userEmail)){
                    mail.setError("Email cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPass.equals("")){
                    pass.setError("Password cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Password cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("student");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String,Object> value = (HashMap<String, Object>) snapshot.getValue();
                        if(value.containsKey(getDBUserName(userEmail))){
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(studentlogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Invalid Login Credentials",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(studentlogin.this, Studentdashboard.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Logged In Successfullty",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(studentlogin.this,"User Does Not Exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static String getDBUserName(String S){
        String result="";
        for(char ch:S.toCharArray()){
            if(ch=='@'){
                break;
            }
            result+=ch;
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}