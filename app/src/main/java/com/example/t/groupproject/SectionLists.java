package com.example.t.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SectionLists extends AppCompatActivity implements ValueEventListener {
    private static final String TAG = "SectionLists";

    private String courseName, faculty;
    private DatabaseReference database;
    private List<Section> sectionList;

    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_lists);


        courseName = getIntent().getExtras().getString("COURSENAME");
        faculty = getIntent().getExtras().getString("FACULTY");
        makeLog(courseName + faculty);

        setTitle(courseName.replace("_", " "));
        database = FirebaseDatabase.getInstance().getReference().child(faculty).child(courseName);

        sectionList = new ArrayList<Section>();
        database.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String crn;
        String sectionCode;
        String sectionType;
        String sectionId;
        String location;
        String times;
        String currentSeat;
        String availableSeat;
        String maxSeat;
        String waitList;
        String professorLink;
        String tutCode;
        String professorName;
        String billingHours;
        String billingCode;
        String monday;
        String tuesday;
        String wednesday;
        String thursday;
        String friday;


        for (DataSnapshot section : dataSnapshot.getChildren()) {
            if (!section.getKey().equals("classlink")) {
                crn = String.valueOf(section.child("cnr").getValue());
                sectionCode = section.child("section_code").getValue().toString();
                sectionType = section.child("section_type").getValue().toString();
                sectionId = String.valueOf(dataSnapshot.getKey());
                location = section.child("location").getValue().toString();
                times = section.child("times").getValue().toString();
                currentSeat = String.valueOf(section.child("cur").getValue());
                availableSeat = String.valueOf(section.child("avail").getValue());
                maxSeat = String.valueOf(section.child("max").getValue());
                waitList = String.valueOf(section.child("wtlist").getValue());
                professorLink = section.child("proflink").getValue().toString();
                tutCode = section.child("tu").getValue().toString();
                professorName = section.child("instructor").getValue().toString();
                billingHours = String.valueOf(section.child("bhrs").getValue());
                billingCode = section.child("code").getValue().toString();
                monday = section.child("mo").getValue().toString();
                tuesday = section.child("tu").getValue().toString();
                wednesday = section.child("we").getValue().toString();
                thursday = section.child("th").getValue().toString();
                friday = section.child("fr").getValue().toString();

                sectionList.add(
                        new Section(
                                crn,
                                sectionCode,
                                sectionType,
                                sectionId,
                                location,
                                times,
                                currentSeat,
                                availableSeat,
                                maxSeat,
                                waitList,
                                professorLink,
                                tutCode,
                                professorName,
                                billingHours,
                                billingCode,
                                monday,
                                tuesday,
                                wednesday,
                                thursday,
                                friday
                        ));

            }
        }


    }

    private void displaySectionList() {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    public void makeLog(String message) {
        Log.w(TAG, message);
    }
}
