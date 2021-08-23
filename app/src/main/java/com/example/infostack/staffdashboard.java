package com.example.infostack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class staffdashboard extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdashboard);

        EditText mark1 = findViewById(R.id.cgpa) ;
        mark1.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;

        EditText mark2 = findViewById(R.id.hsc_score) ;
        mark2.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;

        EditText mark3 = findViewById(R.id.sslc_score) ;
        mark3.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "100" )}) ;


        Spinner spinner = findViewById(R.id.year_std_hsc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = findViewById(R.id.board_std_hsc);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.board, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = findViewById(R.id.year_std_sslc);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.Spinner_items, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);

        Spinner spinner3 = findViewById(R.id.board_std_sslc);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.board, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter4);

        Spinner spinner4 = findViewById(R.id.blood_grp_std);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.blood_grp, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter5);

        Spinner spinner5 = findViewById(R.id.year_std_college);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.Spinner_items, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter6);

        Spinner spinner6 = findViewById(R.id.sem_college);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.sem_college, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter7);


        ImageView logout= (ImageView) findViewById(R.id.logout_staff_dashboard);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // click handling code
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(staffdashboard.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(staffdashboard.this,stafflogin.class));
                finish();

            }
        });

        Button submit = (Button) findViewById(R.id.submit_btn_staff);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText studentName=(EditText) findViewById(R.id.student_name_staffdash);
                EditText stuid=(EditText) findViewById(R.id.student_id_staffdash);
                EditText stureg=(EditText) findViewById(R.id.student_regno_staffdash);
                EditText stuphno=(EditText) findViewById(R.id.student_phno_staffdash);
                EditText stumail=(EditText) findViewById(R.id.student_mail_staffdash);
                EditText stuclgname=(EditText) findViewById(R.id.student_clgname_staffdash);
                EditText studeptname=(EditText) findViewById(R.id.student_dept_staffdash);
                EditText stucgpa=(EditText) findViewById(R.id.cgpa);
                EditText stuschl=(EditText) findViewById(R.id.student_schoolname_staffdash);
                EditText stuhscscore=(EditText) findViewById(R.id.hsc_score);
                EditText stusslcschlname=(EditText) findViewById(R.id.student_sslcschoolname_staffdash);
                EditText stusslcscore=(EditText) findViewById(R.id.sslc_score);

                Spinner stubloodgrp = (Spinner)findViewById(R.id.blood_grp_std);
                Spinner stuclgyr = (Spinner)findViewById(R.id.year_std_college);
                Spinner stuclgsem = (Spinner)findViewById(R.id.sem_college);
                Spinner stuhscstd = (Spinner)findViewById(R.id.year_std_hsc);
                Spinner stuhscboard = (Spinner)findViewById(R.id.board_std_hsc);
                Spinner stusslcstd = (Spinner)findViewById(R.id.year_std_sslc);
                Spinner stusslcsboard = (Spinner)findViewById(R.id.board_std_sslc);

                TextView dob=(TextView) findViewById(R.id.dob_std);
                String stuDob=dob.getText().toString().trim();

                String strstudentName=studentName.getText().toString().trim();
                String strstuid=stuid.getText().toString().trim();
                String strstureg=stureg.getText().toString().trim();
                String strstuphno=stuphno.getText().toString().trim();
                String strstumail=stumail.getText().toString().trim();
                String strstuclgname=stuclgname.getText().toString().trim();
                String strstudept=studeptname.getText().toString().trim();
                String strstucgpa=stucgpa.getText().toString().trim();
                String strstuschl=stuschl.getText().toString().trim();
                String strstuhscscore=stuhscscore.getText().toString().trim();
                String strstusslcschlname=stusslcschlname.getText().toString().trim();
                String strstusslcscore=stusslcscore.getText().toString().trim();

                String strStuBloodGrp=stubloodgrp.getSelectedItem().toString();
                String strStuClgYear=stuclgyr.getSelectedItem().toString();
                String strStuClgSem=stuclgsem.getSelectedItem().toString();
                String strStuHscStd=stuhscstd.getSelectedItem().toString();
                String strStuHscBoard=stuhscboard.getSelectedItem().toString();
                String strStuSslcStd=stusslcstd.getSelectedItem().toString();
                String strStuSslcBoard=stusslcsboard.getSelectedItem().toString();

                if(strstudentName.equals("")){
                    studentName.setError("Name cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Name cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstuid.equals("")){
                    stuid.setError("Student Id cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Id cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstureg.equals("")){
                    stureg.setError("Student Reg.No cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Reg.No cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstuphno.equals("")){
                    stuphno.setError("Student Ph.no cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Ph.No cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstumail.equals("")){
                    stumail.setError("Student Mail cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Mail cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isValidEmail(strstumail)){
                    stumail.setError("Enter Valid Email");
                    Toast.makeText(getApplicationContext(),"Enter Valid Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isValidPhone(strstuphno)){
                    stuphno.setError("Enter Valid Phone");
                    Toast.makeText(getApplicationContext(),"Enter Valid Phone",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(stuDob.equals("")){
                    dob.setError("Student DOB cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student DOB cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstuclgname.equals("")){
                    stuclgname.setError("Student Clg name cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Clg name cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstudept.equals("")){
                    studeptname.setError("Student Dept cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Dept cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstucgpa.equals("")){
                    stucgpa.setError("Student CGPA cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student CGPA cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstuschl.equals("")){
                    stuschl.setError("Student School cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student School cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstuhscscore.equals("")){
                    stuhscscore.setError("Student Score cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Score cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstusslcschlname.equals("")){
                    stusslcschlname.setError("Student School cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student School cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strstusslcscore.equals("")){
                    stusslcscore.setError("Student Score cannot be Empty");
                    Toast.makeText(getApplicationContext(),"Student Score cannot be Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuBloodGrp.equals("BLOOD GROUP")){
                    Toast.makeText(getApplicationContext(),"Please Select Blood Group",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuClgYear.equals("YEAR")){
                    Toast.makeText(getApplicationContext(),"Please Select College Year",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuClgSem.equals("CURRENT SEM")){
                    Toast.makeText(getApplicationContext(),"Please Select the Sem",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuHscStd.equals("YEAR")){
                    Toast.makeText(getApplicationContext(),"Please Select HSC Year",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuHscBoard.equals("BOARD")){
                    Toast.makeText(getApplicationContext(),"Please Select HSC Board",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuSslcStd.equals("YEAR")){
                    Toast.makeText(getApplicationContext(),"Please Select SSLC Year",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strStuSslcBoard.equals("BOARD")){
                    Toast.makeText(getApplicationContext(),"Please Select SSLC Board",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("student");
                Student st=new Student(strstudentName,strstuid, strstureg,strstuphno,strstumail ,strstuclgname,strstudept,strstucgpa,strstusslcschlname, strstuschl, strstuhscscore, strstusslcscore, strStuBloodGrp, strStuClgYear,strStuClgSem, strStuHscStd, strStuSslcStd,strStuHscBoard, strStuSslcBoard,stuDob);
                databaseReference.child(getDBUserName(strstumail)).setValue(st);

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(strstumail, strstureg)
                        .addOnCompleteListener(staffdashboard.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(staffdashboard.this, "Try Again!",
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                }
                            }
                        });

                Toast.makeText(getApplicationContext(),"Student Details Added Sucessfully",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(staffdashboard.this,staffdashboard.class));
                finish();


            }
        });


        TextView mDisplayDate = (TextView) findViewById(R.id.dob_std);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
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
    public static boolean isValidPhone(String phone)
    {
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if(matcher.matches()) {
            return true;
        } else {
            return false;
        }
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
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        month = month + 1;
        Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
        String date = String.format("%02d",dayOfMonth)+"-"+String.format("%02d",month)+"-"+String.format("%02d",year);
        TextView textView = (TextView) findViewById(R.id.dob_std);
        textView.setText(date);
        int userYear = year;
    }
}