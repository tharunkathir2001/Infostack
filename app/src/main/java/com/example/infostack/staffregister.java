package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class staffregister extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffregister);

        Button button=(Button) findViewById(R.id.register_btn_staff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText collegeName=(EditText) findViewById(R.id.college_staff_reg);
                EditText department=(EditText) findViewById(R.id.dept_staff_reg);
                EditText email=(EditText) findViewById(R.id.mail_id_staff_reg);
                EditText pass=(EditText) findViewById(R.id.password_staff_reg);
                EditText conPass=(EditText) findViewById(R.id.confirm_password_staff_reg);
                EditText name=(EditText) findViewById(R.id.name_staff_reg);

                String userEmail=email.getText().toString().trim();
                String userName=name.getText().toString().trim();
                String userPass=pass.getText().toString().trim();
                String userConPass=conPass.getText().toString().trim();
                String userdept=department.getText().toString().trim();
                String userclgname=collegeName.getText().toString().trim();

                if(userName.equals("")){
                    name.setError("Name cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Name cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userEmail.equals("")){
                    email.setError("Email cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Email cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPass.equals("")){
                    pass.setError("Password cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Password cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userConPass.equals("")){
                    conPass.setError("Password cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Confirm Password cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPass.length()<=6){
                    pass.setError("Choose a Strong Password");
                    Toast.makeText(getApplicationContext(),"Choose a Strong Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userdept.equals("")){
                    department.setError("Department cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Department cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userclgname.equals("")){
                    collegeName.setError("College Name cannot be Empty");
                    Toast.makeText(getApplicationContext(),"College Name cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!isValidEmail(userEmail)){
                    email.setError("Enter Valid Email ID");
                    Toast.makeText(getApplicationContext(),"Enter Valid Email ID",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!userPass.equals(userConPass)){
                    conPass.setError("Password must be same");

                    Toast.makeText(getApplicationContext(),"Password must be same",Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPass)
                        .addOnCompleteListener(staffregister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(staffregister.this, "Try Again!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("staff");
                                    Staff detail=new Staff(userName,userEmail,userclgname,userdept);
                                    databaseReference.child(getDBUserName(userEmail)).setValue(detail);
                                    Toast.makeText(getApplicationContext(),"Registration Successful!",Toast.LENGTH_SHORT).show();
                                    Log.d("bvfhjv",detail.toString());
                                    startActivity(new Intent(staffregister.this,stafflogin.class));
                                    finish();

                                }
                            }
                        });

            }
        });

        ImageView backarrowreg= (ImageView) findViewById(R.id.back_arrow_staff_reg);
        backarrowreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                startActivity(new Intent(staffregister.this,stafflogin.class));
                finish();

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(staffregister.this,stafflogin.class));
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