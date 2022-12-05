package com.xa.xpensauditor;

import static java.lang.System.currentTimeMillis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Visualisation class for showing the spending pattern in graphs.
 */
public class Day_wise_visualization extends AppCompatActivity {
    private Firebase mRootRef;
    private Firebase RefUid,RefTran,RefUid1;
    private Firebase RefName,RefEmail,RefPhnnum;
    private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
    private List<Transaction> transList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TransactionAdapter mAdapter;
    private DatePicker dateTransac1;
    private DatePicker dateTransac2;
    private Button Vis;
    private String Tid;
    int tot=0;
    Date date = new Date();
    static Map<Date, Integer> hm = new HashMap<>();
    Map<Date, Integer> sortedMap = new TreeMap<Date, Integer>();
    String tot1="Yes";
    FirebaseAuth auth;
    String Uid;
    String day1, month1, year1;
    String day2, month2, year2;
    int d1, m1, y1;
    int d2, m2, y2;
    private Firebase RefTran3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_wise_visualization);
        auth = FirebaseAuth.getInstance();
        mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
        mRootRef.keepSynced(true);
        Uid=auth.getUid();
        RefUid= mRootRef.child(Uid);
        RefName = RefUid.child("Name");
        RefEmail=RefUid.child("Email");


        // RefUid.child("DateRange").child(String.valueOf(month + "-" + year)).child("Transactions").child(Tid).child("Amount").setValue(Amount);
        Bundle bundle = getIntent().getExtras();
        String month2= bundle.getString("m2");
        String year2= bundle.getString("y2");
        String day2= bundle.getString("d2");
int m2=Integer.parseInt(month2);
        int y2=Integer.parseInt(year2);
        int d2=Integer.parseInt(day2);
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        List<Integer> arr=new ArrayList<>();

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        {

            Boolean f = false;
            Calendar calendar = Calendar.getInstance();

            int thisYear = calendar.get(Calendar.YEAR);
            // Log.d(TAG, "# thisYear : " + thisYear);

            int thisMonth = calendar.get(Calendar.MONTH)+1;
            //Log.d(TAG, "@ thisMonth : " + thisMonth);

            int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
            //Log.d(TAG, "$ thisDay : " + thisDay);


            if (thisYear > y2) {
                f = true;
            } else {
                if (thisYear == y2) {
                    if (thisMonth > m2) {
                        f = true;
                    } else {
                        if (thisMonth == m2) {
                            if (thisDay >= d2) {
                                f = true;
                            }
                        }
                    }
                }
            }

            if (f) {
System.out.println("Day_Wise   "+m2 +"    "+y2);
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(m2 + "-" + y2)).child("Transactions");
                reference.addValueEventListener(new ValueEventListener() {
                    List<DataEntry> data = new ArrayList<>();
                    String dayy,dumm;
                    int dayy1,dumm1;
                    int dayyy,dayyy1;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        for(DataSnapshot snapshot :datasnapshot.getChildren() ){
                            String amm=snapshot.child("Amount").getValue().toString();
                            System.out.println(" Finallyyy   "+amm);
                            int am1=Integer.valueOf(amm);
                            tot=am1+tot;
                            dumm=snapshot.child("Day").getValue().toString();
                            dayyy1=Integer.valueOf(dumm);
                            Calendar c1 = Calendar.getInstance();
                            c1.set(y2, m2-1, dayyy1, 0, 0);
                            Date date = new GregorianCalendar(y2, m2-1, dayyy1).getTime();


      dumm1=Integer.valueOf(dumm);
      System.out.println("dummmmm   "+dumm1+"    "+date);

      if(sortedMap.containsKey(date)){
          System.out.println("inside");
          int temp=0;
          temp=sortedMap.get(date);
      temp=temp+am1;
          System.out.println("dummmmm1   "+dayyy+"    "+temp);
          sortedMap.put(date,temp);
          System.out.println("Dates printing-4   ");
      }
      else{
          sortedMap.put(date,am1);}
                 }

//                        ArrayList<Integer> sortedKeys
//                                = new ArrayList<Integer>(hm.keySet());
//
//                        Collections.sort(sortedKeys);

                        // Display the TreeMap which is naturally sorted
//                        for (Integer x : sortedKeys) {
//                            data.add(new ValueDataEntry(x,hm.get(x)));
//                        }
                        System.out.println("Dates printing-1   ");

                        for (Map.Entry< Date,Integer> entry :
                                sortedMap.entrySet()) {
                            System.out.println("Dates printing  "+entry.getValue()+"  "+entry.getKey());

                            Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
                            data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
                        }
                        for (Map.Entry< Date,Integer> entry :
                                sortedMap.entrySet()) {
                            Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
                            data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
                        }

                            sortedMap.clear();
                        Column column = cartesian.column(data);
                        column.tooltip()
                                .titleFormat("{%X}")
                                .position(Position.CENTER_BOTTOM)
                                .anchor(Anchor.CENTER_BOTTOM)
                                .offsetX(0d)
                                .offsetY(5d)
                                .format("${%Value}{groupsSeparator: }");

                        cartesian.animation(true);
                        cartesian.title("Expenses day wise");

                        cartesian.yScale().minimum(0d);

                        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

                        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                        cartesian.interactivity().hoverMode(HoverMode.BY_X);

                        cartesian.xAxis(0).title("Dates");
                        cartesian.yAxis(0).title("Expences");

                        anyChartView.setChart(cartesian);
                        // System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy  ");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy2  ");
            }
            System.out.println(data+"   data2222  ");
            System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy1  ");
        }
    }
}