package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends Activity {

    private static int splash_screen = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run () {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();

                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("staff");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String,Object> value = (HashMap<String, Object>) snapshot.getValue();
                            if(value.containsKey(getDBUserName(email))){
                                startActivity(new Intent(MainActivity.this, staffdashboard.class));
                                finish();
                                return;
                            }
                            else{
                                startActivity(new Intent(MainActivity.this, Studentdashboard.class));
                                finish();
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    Intent intent = new Intent(MainActivity.this, studentlogin.class);
                    startActivity(intent);
                    finish();
                }

                }
            }, splash_screen);
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
