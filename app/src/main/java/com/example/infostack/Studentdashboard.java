package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Studentdashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdashboard);

        TextView studentName=(TextView) findViewById(R.id.student_name);
        TextView stuid=(TextView) findViewById(R.id.student_id);
        TextView stureg=(TextView) findViewById(R.id.student_regno);
        TextView stuphno=(TextView) findViewById(R.id.student_phno);
        TextView stumail=(TextView) findViewById(R.id.student_mail);
        TextView stuclgname=(TextView) findViewById(R.id.student_clgname);
        TextView stuyear=(TextView) findViewById(R.id.student_clgyear);
        TextView stuclgsem=(TextView) findViewById(R.id.student_clgsem);
        TextView stuschl=(TextView) findViewById(R.id.student_hsc_school_name);
        TextView stuhscyear=(TextView) findViewById(R.id.student_hsc_school_year);
        TextView stuhscboard=(TextView) findViewById(R.id.student_hsc_school_board);
        TextView stusslcschlname=(TextView) findViewById(R.id.student_slsc_school_name);
        TextView stusslcyear=(TextView) findViewById(R.id.student_slsc_school_year);
        TextView stusslcboard=(TextView) findViewById(R.id.student_slsc_school_board);
        TextView studob=(TextView) findViewById(R.id.student_dob);
        TextView studclgdept=(TextView) findViewById(R.id.student_dept);


        Button stdlogout= (Button) findViewById(R.id.logout_student_dashboard);
        stdlogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // click handling code
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Studentdashboard.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Studentdashboard.this,studentlogin.class));
                finish();

            }
        });

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("student");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> value = (HashMap<String, Object>) snapshot.getValue();
                HashMap<String,Object> hashMap=(HashMap<String, Object>) value.get(getDBUserName(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                String bloodGrp=String.valueOf(hashMap.get("blood_group"));
                String cgpa=String.valueOf(hashMap.get("cgpa"));
                String clgName=String.valueOf(hashMap.get("college_name"));
                String clgYr=String.valueOf(hashMap.get("college_year"));
                String dob=String.valueOf(hashMap.get("dob"));
                String dept=String.valueOf(hashMap.get("department"));
                String email=String.valueOf(hashMap.get("email"));
                String hscBoard=String.valueOf(hashMap.get("hsc_board"));
                String hscScl=String.valueOf(hashMap.get("hsc_school"));
                String hscScore=String.valueOf(hashMap.get("hsc_score"));
                String hscYr=String.valueOf(hashMap.get("hsc_year"));
                String id=String.valueOf(hashMap.get("id"));
                String name=String.valueOf(hashMap.get("name"));
                String regNo=String.valueOf(hashMap.get("register_no"));
                String sem=String.valueOf(hashMap.get("semester"));
                String sslcBoard=String.valueOf(hashMap.get("sslc_board"));
                String sslcScl=String.valueOf(hashMap.get("sslc_school"));
                String sslcScore=String.valueOf(hashMap.get("sslc_score"));
                String sslcYr=String.valueOf(hashMap.get("sslc_year"));


                stuclgname.setText(clgName);
                stuyear.setText(clgYr);
                studob.setText(dob);
                studclgdept.setText(dept);
                stumail.setText(email);
                stuhscboard.setText(hscBoard);
                stuschl.setText(hscScl);
                stuhscyear.setText(hscYr);
                stuid.setText(id);
                studentName.setText(name);
                stureg.setText(regNo);
                stuclgsem.setText(sem);
                stusslcboard.setText(sslcBoard);
                stusslcschlname.setText(sslcScl);
		        stusslcyear.setText(sslcYr);

                BarChart barChart = (BarChart) findViewById(R.id.barchart);

                ArrayList<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f, 0));
                entries.add(new BarEntry(Float.parseFloat(sslcScore), 1));
                entries.add(new BarEntry(0f, 2));
                entries.add(new BarEntry(Float.parseFloat(hscScore), 3));
                entries.add(new BarEntry(0f, 4));
                entries.add(new BarEntry(Float.parseFloat(cgpa), 5));


                BarDataSet bardataset = new BarDataSet(entries, "Cells");

                ArrayList<String> labels = new ArrayList<String>();

                labels.add("SSLC");
                labels.add("");
                labels.add("HSC");
                labels.add("");
                labels.add("B.Tech");
                labels.add("");



                BarData data = new BarData(labels, bardataset);
                barChart.setData(data); // set the data and list of labels into chart
                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                barChart.animateY(5000);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
    @Override
    public void onBackPressed() {
        finish();
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