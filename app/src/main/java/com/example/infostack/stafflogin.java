package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import static com.example.infostack.staffregister.isValidEmail;

public class stafflogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflogin);

        TextView signup= (TextView) findViewById(R.id.sign_up_txt_staff);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                startActivity(new Intent(stafflogin.this,staffregister.class));

            }
        });

        ImageView backarrow= (ImageView) findViewById(R.id.back_arrow_staff);
        backarrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                startActivity(new Intent(stafflogin.this,studentlogin.class));

            }
        });


        Button sign= (Button) findViewById(R.id.sign_in_btn_staff);
        sign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText mail=(EditText) findViewById(R.id.mail_id_edit_txt_staff);
                EditText pass=(EditText) findViewById(R.id.password_edit_txt_staff);

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
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("staff");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String,Object> value = (HashMap<String, Object>) snapshot.getValue();
                        if(value.containsKey(getDBUserName(userEmail))){
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(stafflogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Invalid Login Credentials",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(stafflogin.this, staffdashboard.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Logged In Successfullty",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(stafflogin.this,"User Does Not Exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(stafflogin.this,studentlogin.class));
        finish();
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
}