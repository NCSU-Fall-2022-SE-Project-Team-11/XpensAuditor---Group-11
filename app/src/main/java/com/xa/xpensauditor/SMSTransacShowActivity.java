package com.xa.xpensauditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Show Categorised and un Categorised transactions in main menu.
 */
public class SMSTransacShowActivity extends AppCompatActivity {

    TextView smstid,smstamnt,smsshpname,smscat,smsdate,sms,smssharedwith;
    private Firebase mRootRef;
    private Firebase RefUid;
    String d,m,y;

    /**
     * Loads data when page is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smstransac_show);
        Intent i = getIntent();
        String tid = i.getStringExtra("indexPos");
        //Toast.makeText(getApplicationContext()," yo : "+tid,Toast.LENGTH_SHORT).show();
        smstid=findViewById(R.id.smstid);
        smstid.setText(tid);
        smstamnt=findViewById(R.id.smstamnt);
        smsshpname=findViewById(R.id.smsshpname);
        smssharedwith=findViewById(R.id.smssharedwith);
        smscat=findViewById(R.id.smscat);
        smsdate=findViewById(R.id.smsdate);
        sms=findViewById(R.id.sms);


        mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");

        mRootRef.keepSynced(true);
        com.google.firebase.auth.FirebaseAuth auth = FirebaseAuth.getInstance();
        String Uid=auth.getUid();
        RefUid= mRootRef.child(Uid);

        RefUid.child("UnCatTran").child(tid).child("Amount").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                smstamnt.setText(dataSnapshot.getValue().toString().trim());
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefUid.child("UnCatTran").child(tid).child("Category").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                smscat.setText(dataSnapshot.getValue().toString().trim());
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefUid.child("UnCatTran").child(tid).child("Shop Name").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                smsshpname.setText(dataSnapshot.getValue().toString().trim());
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        RefUid.child("UnCatTran").child(tid).child("Shared With").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String label = (dataSnapshot.getChildrenCount() > 1) ? "Shared With: " : "";
                String sharedWith=dataSnapshot.getValue().toString().replace("[", label).replace("]", "");
                smssharedwith.setText(sharedWith);
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefUid.child("UnCatTran").child(tid).child("ZMessage").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sms.setText(dataSnapshot.getValue().toString().trim());
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefUid.child("UnCatTran").child(tid).child("Day").addValueEventListener(new ValueEventListener() {

            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                d=dataSnapshot.getValue().toString().trim();
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        RefUid.child("UnCatTran").child(tid).child("Month").addValueEventListener(new ValueEventListener() {
            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m=dataSnapshot.getValue().toString().trim();
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        RefUid.child("UnCatTran").child(tid).child("Year").addValueEventListener(new ValueEventListener() {
            /**
             * Method is triggered when data is changed or added.
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                y=dataSnapshot.getValue().toString().trim();
                smsdate.setText(d+"/"+m+"/"+y);
            }

            /**
             * Method is triggered when there is a Firebase error.
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
}