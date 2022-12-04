package com.xa.xpensauditor;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class SetLimitActivity extends AppCompatActivity {
    private Button setdailylimit, setmonthlylimit, setannuallimit, savebutton, clearbutton, retrievebutton;
    private ProgressBar progressBar;
    private TextView daylimitAmt, monlimitAmt, annlimitAmt;
    private FirebaseAuth auth;
    private Firebase mRootRef;
    private Firebase RefUid;
    Activity activity;

    public static final String SHARED_PREFS = "MySharedPref";
    public static final String DLIMIT = "DAILY_LIMIT";
    public static final String MLIMIT = "MON_LIMIT";
    public static final String ALIMIT = "ANN_LIMIT";

    String day, month, year;
    private String Uid, mail;
    private int dt, mt, yt;


//    protected void AnnLimitEmailsender(String yr, String email) {
//        Toast.makeText(activity, "TODO - SEND EMAIL TO THE USER AS YEARLY SET LIMIT IS EXCEEDED ", Toast.LENGTH_SHORT).show();
//        new LimitEmailSender(yr, email).execute();
//        return;
//    }


    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);


        mRootRef = new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
        mRootRef.keepSynced(true);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String Uid = auth.getUid();
        RefUid = mRootRef.child(Uid);

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            System.out.println("Current User mail" + currentUser.getEmail());
        }

        daylimitAmt = (TextView) findViewById(R.id.dlimitAmt);
        monlimitAmt = (TextView) findViewById(R.id.mlimitAmt);
        annlimitAmt = (TextView) findViewById(R.id.alimitAmt);


        setdailylimit = (Button) findViewById(R.id.setdailylimit);
        setmonthlylimit = (Button) findViewById(R.id.setmonthlylimit);
        setannuallimit = (Button) findViewById(R.id.setannuallimit);
        savebutton = (Button) findViewById(R.id.savebutton);
        clearbutton= (Button)findViewById((R.id.clearbutton)) ;
        retrievebutton= (Button)findViewById(R.id.retrievebutton) ;

       //SHARED PREFRENCES
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        if (sharedPreferences.contains(DLIMIT)) {
            daylimitAmt.setText(sharedPreferences.getString(DLIMIT, ""));
        }
        if (sharedPreferences.contains(MLIMIT)) {
            monlimitAmt.setText(sharedPreferences.getString(MLIMIT, ""));
        }
        if (sharedPreferences.contains(ALIMIT)) {
            annlimitAmt.setText(sharedPreferences.getString(ALIMIT, ""));
        }


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d = daylimitAmt.getText().toString();
                String m = monlimitAmt.getText().toString();
                String a = annlimitAmt.getText().toString();
                SharedPreferences.Editor limEdit = sharedPreferences.edit();
                limEdit.putString(DLIMIT, d);
                limEdit.putString(MLIMIT, m);
                limEdit.putString(ALIMIT, a);
                limEdit.apply();}
        });

        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            daylimitAmt.setText("0");
            monlimitAmt.setText("0");
            annlimitAmt.setText("0");
            }
        });

        retrievebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.contains(DLIMIT)) {
                    daylimitAmt.setText(sharedPreferences.getString(DLIMIT, "0"));
                }
                if (sharedPreferences.contains(MLIMIT)) {
                    monlimitAmt.setText(sharedPreferences.getString(MLIMIT, "0"));
                }
                if (sharedPreferences.contains(ALIMIT)) {
                    annlimitAmt.setText(sharedPreferences.getString(ALIMIT, "0"));
                }
            }
        });

// TRANSACTIONS TOTALS

        Calendar calendar = Calendar.getInstance();
        int thisYear= calendar.get(Calendar.YEAR);
        int thisMonth=calendar.get(Calendar.MONTH)+1;
        int thisDay= calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("This month      "+thisMonth);
        System.out.println("This year      "+thisYear);

// DAY TOTAL

        DatabaseReference reference_day= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(thisMonth + "-" + thisYear)).child("Transactions");
        reference_day.addValueEventListener(new ValueEventListener() {
        int day_tol;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot3 : snapshot.getChildren()) {
                    if (Integer.parseInt(snapshot3.child("Day").getValue().toString()) == (thisDay)) {
                        String amm = (snapshot3.child("Amount").getValue().toString());
                        int am1 = Integer.parseInt(amm);
                        day_tol = am1 + day_tol;
                    }
                }

                System.out.println("Day-total " + day_tol);
                if (day_tol > Integer.parseInt(sharedPreferences.getString(DLIMIT,"0"))){
                    System.out.println("Day limit crossed");
                    //Toast.makeText(activity, "TODO - SEND EMAIL TO THE USER AS DAILY SET LIMIT IS EXCEEDED ", Toast.LENGTH_SHORT).show();
                    new LimitEmailSender("You have exceeded the limit set for the day.Happy spending!", FirebaseAuth.getInstance().getCurrentUser().getEmail()).execute();
                }
                else {
                    System.out.println("Under daily limit");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("cancelled " + day_tol);
            }

        });

        // MONTH TOTAL
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(thisMonth + "-" + thisYear)).child("Transactions");
        reference.addValueEventListener(new ValueEventListener() {
            int mon_tot;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String amm = (snapshot1.child("Amount").getValue()).toString();
                    int am1 = Integer.parseInt(amm);
                    mon_tot = am1 + mon_tot;
                    mt=mon_tot;

                }
                System.out.println("Month-total " + mon_tot);
                if (mt > Integer.parseInt(sharedPreferences.getString(MLIMIT,"0"))){
                   // Toast.makeText(activity, "TODO - SEND EMAIL TO THE USER AS MONTHLY SET LIMIT IS EXCEEDED ", Toast.LENGTH_SHORT).show();
                    System.out.println("Email sent for monthly limit updtae");
                    new LimitEmailSender(" You have exceeded the limit set for month.Happy spending!", FirebaseAuth.getInstance().getCurrentUser().getEmail()).execute();
                }
                else {
                    System.out.println("Under monthly limit");
                }
            }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("cancelled " + mon_tot);
        }
        });

// YEAR TOTAL
        for(int ii=1;ii<=12;ii++) {
            // System.out.println("year-i values " + ii);
            DatabaseReference reference_yr = FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(ii + "-" + thisYear)).child("Transactions");
            reference_yr.addValueEventListener(new ValueEventListener() {
                int tot_yr = 0;
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                        String amm = (snapshot2.child("Amount").getValue()).toString();
                        //System.out.println(" Finallyyy_2   " + amm);
                        int am1 = Integer.parseInt(amm);
                        tot_yr = am1 + tot_yr;
                        yt = tot_yr;
                    }
                    System.out.println("Year-total " + tot_yr);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("cancelled " + tot_yr);
                }

            });

        }

//EMAIL RETRIEVAL
//        DatabaseReference ref3= FirebaseDatabase.getInstance().getReference().child(Uid).child("Email");
//        ref3.addValueEventListener(new ValueEventListener() {
//            String email=" ";
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                email= snapshot.getValue().toString();
////
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("Email cancelled");
//            }
//        });


setdailylimit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

setmonthlylimit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

setannuallimit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

 }

}