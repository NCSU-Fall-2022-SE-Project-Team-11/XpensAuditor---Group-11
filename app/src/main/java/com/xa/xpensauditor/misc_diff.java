// package com.xa.xpensauditor;

// import androidx.annotation.NonNull;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.recyclerview.widget.RecyclerView;

// import android.annotation.SuppressLint;
// import android.os.Bundle;
// import android.widget.Button;
// import android.widget.DatePicker;

// import com.anychart.AnyChart;
// import com.anychart.AnyChartView;
// import com.anychart.chart.common.dataentry.DataEntry;
// import com.anychart.chart.common.dataentry.ValueDataEntry;
// import com.anychart.charts.Cartesian;
// import com.anychart.core.cartesian.series.Column;
// import com.anychart.enums.Anchor;
// import com.anychart.enums.HoverMode;
// import com.anychart.enums.Position;
// import com.anychart.enums.TooltipPositionMode;
// import com.firebase.client.Firebase;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
// import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;

// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Collections;
// import java.util.Date;
// import java.util.GregorianCalendar;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.TreeMap;

// public class MonthWiseVis extends AppCompatActivity {
//     private Firebase mRootRef;
//     private Firebase RefUid,RefTran,RefUid1;
//     private Firebase RefName,RefEmail,RefPhnnum;
//     private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
//     private List<Transaction> transList = new ArrayList<>();
//     private RecyclerView recyclerView;
//     private TransactionAdapter mAdapter;
//     private DatePicker dateTransac1;
//     private DatePicker dateTransac2;
//     private Button Vis;
//     private String Tid;
//     int tot=0;
//     int ii=0;
//     static Map<Integer, Integer> hm = new HashMap<>();
//     Map<Date, Integer> sortedMap = new TreeMap<Date, Integer>();

//     String tot1="Yes";
//     FirebaseAuth auth;
//     String Uid;
//     String day1, month1, year1;
//     String day2, month2, year2;
//     int d1, m1, y1;
//     int d2, m2, y2;
//     private Firebase RefTran3;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_month_wise_vis);
//         auth = FirebaseAuth.getInstance();
//         mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
//         mRootRef.keepSynced(true);
//         Uid=auth.getUid();
//         RefUid= mRootRef.child(Uid);
//         RefName = RefUid.child("Name");
//         RefEmail=RefUid.child("Email");


//         // RefUid.child("DateRange").child(String.valueOf(month + "-" + year)).child("Transactions").child(Tid).child("Amount").setValue(Amount);
//         Bundle bundle = getIntent().getExtras();
//         String month2= bundle.getString("m2");
//         String year2= bundle.getString("y2");
//         String day2= bundle.getString("d2");
//         String month1= bundle.getString("m1");
//         String year1= bundle.getString("y1");
//         String day1= bundle.getString("d1");
//         int m2=Integer.parseInt(month2);
//         int y2=Integer.parseInt(year2);
//         int d2=Integer.parseInt(day2);
//         int m1=Integer.parseInt(month1);
//         int y1=Integer.parseInt(year1);
//         int d1=Integer.parseInt(day1);

//         Cartesian cartesian = AnyChart.column();
//         AnyChartView anyChartView = findViewById(R.id.any_chart_view);

//         List<DataEntry> data = new ArrayList<>();
//         {

//             Boolean f = false;
//             Calendar calendar = Calendar.getInstance();

//             int thisYear = calendar.get(Calendar.YEAR);
//             // Log.d(TAG, "# thisYear : " + thisYear);

//             int thisMonth = calendar.get(Calendar.MONTH)+1;
//             //Log.d(TAG, "@ thisMonth : " + thisMonth);

//             int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
//             //Log.d(TAG, "$ thisDay : " + thisDay);
//             if (thisYear > y2) {
//                 f = true;
//             } else {
//                 if (thisYear == y2) {
//                     if (thisMonth > m2) {
//                         f = true;
//                     } else {
//                         if (thisMonth == m2) {
//                             if (thisDay >= d2) {
//                                 f = true;
//                             }
//                         }
//                     }
//                 }
//             }

//             if (f) {
//                 System.out.println("Day_Wise   "+m2 +"    "+y2);
//                 for( ii=m1;ii<=m2;ii++){
//                 DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(ii + "-" + y2)).child("Transactions");
//                 reference.addValueEventListener(new ValueEventListener() {
//                     List<DataEntry> data = new ArrayList<>();
//                     String dayy, dumm;
//                     int dayy1, dumm1;
//                     int dayyy;
//                    s @Override
//                     public void onDataChange(@NonNull DataSnapshot datasnapshot) {

//                         for (DataSnapshot snapshot : datasnapshot.getChildren()) {
//                             String amm = snapshot.child("Amount").getValue().toString();
//                             System.out.println(" Finallyyy   " + amm);
//                             int am1 = Integer.valueOf(amm);
//                             tot = am1 + tot;
//                             dumm = snapshot.child("Day").getValue().toString();
//                             dumm1 = Integer.valueOf(dumm);
//                             System.out.println("dummmmm   " + dumm1 + "    " + dayyy);
//                             Date date = new GregorianCalendar(y2, ii-1, dayyy).getTime();

//                             if(sortedMap.containsKey(date)){
//                                 System.out.println("inside");
//                                 int temp=0;
//                                 temp=sortedMap.get(date);
//                                 temp=temp+am1;
//                                 System.out.println("dummmmm1   "+dayyy+"    "+temp);
//                                 sortedMap.put(date,temp);
//                                 System.out.println("Dates printing-4   ");
//                             } else {
//                                 dayy = snapshot.child("Day").getValue().toString();
//                                 dayyy = Integer.parseInt(dayy);
//                                 sortedMap.put(date,am1);
//                             }
//                         }

//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }

//                         sortedMap.clear();
//                         Column column = cartesian.column(data);
//                         column.tooltip()
//                                 .titleFormat("{%X}")
//                                 .position(Position.CENTER_BOTTOM)
//                                 .anchor(Anchor.CENTER_BOTTOM)
//                                 .offsetX(0d)
//                                 .offsetY(5d)
//                                 .format("${%Value}{groupsSeparator: }");

//                         cartesian.animation(true);
//                         cartesian.title("Expenses month wise");

//                         cartesian.yScale().minimum(0d);

//                         cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

//                         cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//                         cartesian.interactivity().hoverMode(HoverMode.BY_X);

//                         cartesian.xAxis(0).title("Dates");
//                         cartesian.yAxis(0).title("Expences");


//                         // System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy  ");
//                     }

//                     @Override
//                     public void onCancelled(@NonNull DatabaseError error) {

//                     }
//                 });

//                 }
//                 anyChartView.setChart(cartesian);
//                     }





//         }
// }}

// visualization_all
// package com.xa.xpensauditor;

// import androidx.appcompat.app.AppCompatActivity;

// import android.content.Intent;
// import android.os.Bundle;
// import android.view.View;
// import android.widget.Button;
// import android.widget.EditText;

// public class Visualization_all extends AppCompatActivity {
//     EditText mEdit;
//     EditText mEdit1,mEdit2,mEdit3,mEdit4,mEdit5;
//     private Button Vis,Vis1;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_visualization_all);

//                 Vis = (Button) findViewById(R.id.button2);
//         Vis1 = (Button) findViewById(R.id.button5);
//         Vis.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent c=new Intent(Visualization_all.this,Day_wise_visualization.class);
//                 mEdit   = (EditText)findViewById(R.id.editTextNumber);
//                 String m2=mEdit.getText().toString();
//                 mEdit1   = (EditText)findViewById(R.id.editTextNumber2);

//                 String y2=mEdit1.getText().toString();
//                 String day2="0";
//                 System.out.println(m2+"yesssss     ");
//                 Bundle b = new Bundle();
//                 b.putString("m2",m2);
//                 b.putString("y2",y2);
//                b.putString("d2",day2);
//                 c.putExtras(b);
//                 startActivity(c);
//             }

//         });
//         Vis1.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent c=new Intent(Visualization_all.this,MonthWiseVis.class);
//                 mEdit2=(EditText)findViewById(R.id.editTextNumber3);
//                 String m1=mEdit2.getText().toString();
//                 mEdit3=(EditText)findViewById(R.id.editTextNumber6);
//                 String m2=mEdit3.getText().toString();
//                 mEdit4   = (EditText)findViewById(R.id.editTextNumber4);
//                 String y1=mEdit4.getText().toString();
//                 mEdit5   = (EditText)findViewById(R.id.editTextNumber7);
//                 String y2=mEdit5.getText().toString();

//                 String day2="0";
//                 System.out.println(m2+"yesssss     ");
//                 Bundle b = new Bundle();
//                 b.putString("m1",m1);
//                 b.putString("y1",y1);
//                 b.putString("d1",day2);
//                 b.putString("m2",m2);
//                 b.putString("y2",y2);
//                 b.putString("d2",day2);

//                 c.putExtras(b);
//                 startActivity(c);
//             }

//         });
//     }
// }

// Daywise

//  package com.xa.xpensauditor;

// import static java.lang.System.currentTimeMillis;

// import androidx.annotation.NonNull;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.recyclerview.widget.RecyclerView;

// import android.os.Bundle;
// import android.util.Log;
// import android.widget.Button;
// import android.widget.DatePicker;
// import android.widget.TextView;

// import com.anychart.AnyChart;
// import com.anychart.AnyChartView;
// import com.anychart.chart.common.dataentry.DataEntry;
// import com.anychart.chart.common.dataentry.ValueDataEntry;
// import com.anychart.charts.Cartesian;
// import com.anychart.core.cartesian.series.Column;
// import com.anychart.enums.Anchor;
// import com.anychart.enums.HoverMode;
// import com.anychart.enums.Position;
// import com.anychart.enums.TooltipPositionMode;
// import com.firebase.client.Firebase;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
// import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Calendar;
// import java.util.Collections;
// import java.util.Date;
// import java.util.GregorianCalendar;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.TreeMap;

// public class Day_wise_visualization extends AppCompatActivity {
//     private Firebase mRootRef;
//     private Firebase RefUid,RefTran,RefUid1;
//     private Firebase RefName,RefEmail,RefPhnnum;
//     private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
//     private List<Transaction> transList = new ArrayList<>();
//     private RecyclerView recyclerView;
//     private TransactionAdapter mAdapter;
//     private DatePicker dateTransac1;
//     private DatePicker dateTransac2;
//     private Button Vis;
//     private String Tid;
//     int tot=0;
//     Date date = new Date();
//     static Map<Date, Integer> hm = new HashMap<>();
//     Map<Date, Integer> sortedMap = new TreeMap<Date, Integer>();
//     String tot1="Yes";
//     FirebaseAuth auth;
//     String Uid;
//     String day1, month1, year1;
//     String day2, month2, year2;
//     int d1, m1, y1;
//     int d2, m2, y2;
//     private Firebase RefTran3;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {

//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_day_wise_visualization);
//         auth = FirebaseAuth.getInstance();
//         mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
//         mRootRef.keepSynced(true);
//         Uid=auth.getUid();
//         RefUid= mRootRef.child(Uid);
//         RefName = RefUid.child("Name");
//         RefEmail=RefUid.child("Email");


//         // RefUid.child("DateRange").child(String.valueOf(month + "-" + year)).child("Transactions").child(Tid).child("Amount").setValue(Amount);
//         Bundle bundle = getIntent().getExtras();
//         String month2= bundle.getString("m2");
//         String year2= bundle.getString("y2");
//         String day2= bundle.getString("d2");
// int m2=Integer.parseInt(month2);
//         int y2=Integer.parseInt(year2);
//         int d2=Integer.parseInt(day2);
//         AnyChartView anyChartView = findViewById(R.id.any_chart_view);
//         List<Integer> arr=new ArrayList<>();

//         Cartesian cartesian = AnyChart.column();

//         List<DataEntry> data = new ArrayList<>();
//         {

//             Boolean f = false;
//             Calendar calendar = Calendar.getInstance();

//             int thisYear = calendar.get(Calendar.YEAR);
//             // Log.d(TAG, "# thisYear : " + thisYear);

//             int thisMonth = calendar.get(Calendar.MONTH)+1;
//             //Log.d(TAG, "@ thisMonth : " + thisMonth);

//             int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
//             //Log.d(TAG, "$ thisDay : " + thisDay);


//             if (thisYear > y2) {
//                 f = true;
//             } else {
//                 if (thisYear == y2) {
//                     if (thisMonth > m2) {
//                         f = true;
//                     } else {
//                         if (thisMonth == m2) {
//                             if (thisDay >= d2) {
//                                 f = true;
//                             }
//                         }
//                     }
//                 }
//             }

//             if (f) {
// System.out.println("Day_Wise   "+m2 +"    "+y2);
//                 DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(m2 + "-" + y2)).child("Transactions");
//                 reference.addValueEventListener(new ValueEventListener() {
//                     List<DataEntry> data = new ArrayList<>();
//                     String dayy,dumm;
//                     int dayy1,dumm1;
//                     int dayyy,dayyy1;

//                     @Override
//                     public void onDataChange(@NonNull DataSnapshot datasnapshot) {

//                         for(DataSnapshot snapshot :datasnapshot.getChildren() ){
//                             String amm=snapshot.child("Amount").getValue().toString();
//                             System.out.println(" Finallyyy   "+amm);
//                             int am1=Integer.valueOf(amm);
//                             tot=am1+tot;
//                             dumm=snapshot.child("Day").getValue().toString();
//                             dayyy1=Integer.valueOf(dumm);
//                             Calendar c1 = Calendar.getInstance();
//                             c1.set(y2, m2-1, dayyy1, 0, 0);
//                             Date date = new GregorianCalendar(y2, m2-1, dayyy1).getTime();


//       dumm1=Integer.valueOf(dumm);
//       System.out.println("dummmmm   "+dumm1+"    "+date);

//       if(sortedMap.containsKey(date)){
//           System.out.println("inside");
//           int temp=0;
//           temp=sortedMap.get(date);
//       temp=temp+am1;
//           System.out.println("dummmmm1   "+dayyy+"    "+temp);
//           sortedMap.put(date,temp);
//           System.out.println("Dates printing-4   ");
//       }
//       else{
//           sortedMap.put(date,am1);}
//                  }

// //                        ArrayList<Integer> sortedKeys
// //                                = new ArrayList<Integer>(hm.keySet());
// //
// //                        Collections.sort(sortedKeys);

//                         // Display the TreeMap which is naturally sorted
// //                        for (Integer x : sortedKeys) {
// //                            data.add(new ValueDataEntry(x,hm.get(x)));
// //                        }
//                         System.out.println("Dates printing-1   ");

//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             System.out.println("Dates printing  "+entry.getValue()+"  "+entry.getKey());

//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }
//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }

//                             sortedMap.clear();
//                         Column column = cartesian.column(data);
//                         column.tooltip()
//                                 .titleFormat("{%X}")
//                                 .position(Position.CENTER_BOTTOM)
//                                 .anchor(Anchor.CENTER_BOTTOM)
//                                 .offsetX(0d)
//                                 .offsetY(5d)
//                                 .format("${%Value}{groupsSeparator: }");

//                         cartesian.animation(true);
//                         cartesian.title("Expenses day wise");

//                         cartesian.yScale().minimum(0d);

//                         cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

//                         cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//                         cartesian.interactivity().hoverMode(HoverMode.BY_X);

//                         cartesian.xAxis(0).title("Dates");
//                         cartesian.yAxis(0).title("Expences");

//                         anyChartView.setChart(cartesian);
//                         // System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy  ");
//                     }

//                     @Override
//                     public void onCancelled(@NonNull DatabaseError error) {

//                     }
//                 });
//                 System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy2  ");
//             }
//             System.out.println(data+"   data2222  ");
//             System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy1  ");
//         }
//     }
// }

// Search Product

// package com.xa.xpensauditor;

// import androidx.appcompat.app.AppCompatActivity;
// import androidx.appcompat.widget.Toolbar;
// import android.app.ProgressDialog;
// import android.content.Intent;
// import android.os.Bundle;
// import android.os.Handler;
// import android.view.View;
// import android.widget.AdapterView;
// import android.widget.ArrayAdapter;
// import android.widget.Button;
// import android.widget.EditText;
// import android.widget.ImageView;
// import android.content.Context;
// import android.widget.ListView;
// import android.widget.Toast;
// import org.json.JSONArray;
// import org.json.JSONObject;
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.Arrays;
// import android.app.ProgressDialog;

// public class SearchProduct extends AppCompatActivity{

//     static ListView listView;
//     EditText input;
//     ImageView search_button;
//     static ListViewAdapter adapter;
//     static ArrayList<String> rows;
//     static Context context;
//     Handler fetchHandler = new Handler();
//     ProgressDialog progressDialog;

//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_search_product);
//         listView = findViewById(R.id.list);
//         input = findViewById(R.id.input);
//         search_button = findViewById(R.id.search_button);
//         context = getApplicationContext();

//         rows = new ArrayList<>();

//         listView.setLongClickable(true);
//         adapter = new ListViewAdapter(this, rows);
//         listView.setAdapter(adapter);

//         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 String clickedItem = (String) listView.getItemAtPosition(position);
//                 Toast.makeText(SearchProduct.this, clickedItem, Toast.LENGTH_SHORT).show();
//             }
//         });

//        search_button.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 String text = input.getText().toString();
//                 if (text == null || text.length() == 0) {
//                     makeToast("Enter a valid product name.");
//                 } else {
//                     new FetchProduct().start();
//                     addItem();
//                 }
//             }
//         });
//         loadContent();
//     }

//     public void loadContent() {
//         File path = getApplicationContext().getFilesDir();
//         File readFrom = new File(path, "list.txt");
//         byte[] content = new byte[(int) readFrom.length()];

//         FileInputStream stream = null;
//         try {
//             stream = new FileInputStream(readFrom);
//             stream.read(content);
//             String s = new String(content);
//             s = s.substring(1, s.length() - 1);
//             String split[] = s.split(", ");

//             if (split.length == 1 && split[0].isEmpty())
//                 rows = new ArrayList<>();
//             else rows = new ArrayList<>(Arrays.asList(split));

//             adapter = new ListViewAdapter(this, rows);
//             listView.setAdapter(adapter);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void addItem() {
//         listView.setAdapter(adapter);
//     }

//     class FetchProduct extends Thread{
//         String data = "";

//         @Override
//         public void run(){

//             fetchHandler.post(new Runnable() {
//                 @Override
//                 public void run() {
//                     progressDialog = new ProgressDialog(SearchProduct.this);
//                     progressDialog.setMessage("Fetching Data");
//                     progressDialog.setCancelable(false);
//                     progressDialog.show();
//                 }
//             });
//             try {
//                 String api_link = "https://online-product-scrapper.onrender.com/search_walmart?query="+input.getText().toString();
//                 URL url = new URL(api_link);
//                 HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                 InputStream inputStream = httpURLConnection.getInputStream();
//                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                 String line;
//                 while ((line = bufferedReader.readLine()) != null ){
//                     data = data + line;
//                 }
//                 if(!data.isEmpty()){
//                     JSONArray jSONArray = new JSONArray(data);
//                     for(int i=0;i<jSONArray.length();i++){
//                         JSONObject product = jSONArray.getJSONObject(i);
//                         String title = product.getString("title");
//                         String price = product.getString("price");
//                         String website = product.getString("website");
//                         if(!title.isEmpty()){
//                             rows.add("Name: "+title+ "\nPrice: "+price+ "\nWebsite: "+website);
//                         }
//                     }
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }

//             fetchHandler.post(new Runnable() {
//                 @Override
//                 public void run() {
//                     if(progressDialog.isShowing())
//                         progressDialog.dismiss();
//                     adapter.notifyDataSetChanged();
//                 }
//             });
//         }
//     }

//     static Toast t;
//     private static void makeToast(String s) {
//         if (t != null) t.cancel();
//         t = Toast.makeText(context, s, Toast.LENGTH_SHORT);
//         t.show();
//     }
// }
// package com.xa.xpensauditor;

// import androidx.annotation.NonNull;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.recyclerview.widget.RecyclerView;

// import android.annotation.SuppressLint;
// import android.os.Bundle;
// import android.widget.Button;
// import android.widget.DatePicker;

// import com.anychart.AnyChart;
// import com.anychart.AnyChartView;
// import com.anychart.chart.common.dataentry.DataEntry;
// import com.anychart.chart.common.dataentry.ValueDataEntry;
// import com.anychart.charts.Cartesian;
// import com.anychart.core.cartesian.series.Column;
// import com.anychart.enums.Anchor;
// import com.anychart.enums.HoverMode;
// import com.anychart.enums.Position;
// import com.anychart.enums.TooltipPositionMode;
// import com.firebase.client.Firebase;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
// import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;

// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Collections;
// import java.util.Date;
// import java.util.GregorianCalendar;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.TreeMap;

// public class MonthWiseVis extends AppCompatActivity {
//     private Firebase mRootRef;
//     private Firebase RefUid,RefTran,RefUid1;
//     private Firebase RefName,RefEmail,RefPhnnum;
//     private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
//     private List<Transaction> transList = new ArrayList<>();
//     private RecyclerView recyclerView;
//     private TransactionAdapter mAdapter;
//     private DatePicker dateTransac1;
//     private DatePicker dateTransac2;
//     private Button Vis;
//     private String Tid;
//     int tot=0;
//     int ii=0;
//     static Map<Integer, Integer> hm = new HashMap<>();
//     Map<Date, Integer> sortedMap = new TreeMap<Date, Integer>();

//     String tot1="Yes";
//     FirebaseAuth auth;
//     String Uid;
//     String day1, month1, year1;
//     String day2, month2, year2;
//     int d1, m1, y1;
//     int d2, m2, y2;
//     private Firebase RefTran3;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_month_wise_vis);
//         auth = FirebaseAuth.getInstance();
//         mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
//         mRootRef.keepSynced(true);
//         Uid=auth.getUid();
//         RefUid= mRootRef.child(Uid);
//         RefName = RefUid.child("Name");
//         RefEmail=RefUid.child("Email");


//         // RefUid.child("DateRange").child(String.valueOf(month + "-" + year)).child("Transactions").child(Tid).child("Amount").setValue(Amount);
//         Bundle bundle = getIntent().getExtras();
//         String month2= bundle.getString("m2");
//         String year2= bundle.getString("y2");
//         String day2= bundle.getString("d2");
//         String month1= bundle.getString("m1");
//         String year1= bundle.getString("y1");
//         String day1= bundle.getString("d1");
//         int m2=Integer.parseInt(month2);
//         int y2=Integer.parseInt(year2);
//         int d2=Integer.parseInt(day2);
//         int m1=Integer.parseInt(month1);
//         int y1=Integer.parseInt(year1);
//         int d1=Integer.parseInt(day1);

//         Cartesian cartesian = AnyChart.column();
//         AnyChartView anyChartView = findViewById(R.id.any_chart_view);

//         List<DataEntry> data = new ArrayList<>();
//         {

//             Boolean f = false;
//             Calendar calendar = Calendar.getInstance();

//             int thisYear = calendar.get(Calendar.YEAR);
//             // Log.d(TAG, "# thisYear : " + thisYear);

//             int thisMonth = calendar.get(Calendar.MONTH)+1;
//             //Log.d(TAG, "@ thisMonth : " + thisMonth);

//             int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
//             //Log.d(TAG, "$ thisDay : " + thisDay);
//             if (thisYear > y2) {
//                 f = true;
//             } else {
//                 if (thisYear == y2) {
//                     if (thisMonth > m2) {
//                         f = true;
//                     } else {
//                         if (thisMonth == m2) {
//                             if (thisDay >= d2) {
//                                 f = true;
//                             }
//                         }
//                     }
//                 }
//             }

//             if (f) {
//                 System.out.println("Day_Wise   "+m2 +"    "+y2);
//                 for( ii=m1;ii<=m2;ii++){
//                 DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(ii + "-" + y2)).child("Transactions");
//                 reference.addValueEventListener(new ValueEventListener() {
//                     List<DataEntry> data = new ArrayList<>();
//                     String dayy, dumm;
//                     int dayy1, dumm1;
//                     int dayyy;
//                     @Override
//                     public void onDataChange(@NonNull DataSnapshot datasnapshot) {

//                         for (DataSnapshot snapshot : datasnapshot.getChildren()) {
//                             String amm = snapshot.child("Amount").getValue().toString();
//                             System.out.println(" Finallyyy   " + amm);
//                             int am1 = Integer.valueOf(amm);
//                             tot = am1 + tot;
//                             dumm = snapshot.child("Day").getValue().toString();
//                             dumm1 = Integer.valueOf(dumm);
//                             System.out.println("dummmmm   " + dumm1 + "    " + dayyy);
//                             Date date = new GregorianCalendar(y2, ii-1, dayyy).getTime();

//                             if(sortedMap.containsKey(date)){
//                                 System.out.println("inside");
//                                 int temp=0;
//                                 temp=sortedMap.get(date);
//                                 temp=temp+am1;
//                                 System.out.println("dummmmm1   "+dayyy+"    "+temp);
//                                 sortedMap.put(date,temp);
//                                 System.out.println("Dates printing-4   ");
//                             } else {
//                                 dayy = snapshot.child("Day").getValue().toString();
//                                 dayyy = Integer.parseInt(dayy);
//                                 sortedMap.put(date,am1);
//                             }
//                         }

//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }

//                         sortedMap.clear();
//                         Column column = cartesian.column(data);
//                         column.tooltip()
//                                 .titleFormat("{%X}")
//                                 .position(Position.CENTER_BOTTOM)
//                                 .anchor(Anchor.CENTER_BOTTOM)
//                                 .offsetX(0d)
//                                 .offsetY(5d)
//                                 .format("${%Value}{groupsSeparator: }");

//                         cartesian.animation(true);
//                         cartesian.title("Expenses month wise");

//                         cartesian.yScale().minimum(0d);

//                         cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

//                         cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//                         cartesian.interactivity().hoverMode(HoverMode.BY_X);

//                         cartesian.xAxis(0).title("Dates");
//                         cartesian.yAxis(0).title("Expences");


//                         // System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy  ");
//                     }

//                     @Override
//                     public void onCancelled(@NonNull DatabaseError error) {

//                     }
//                 });

//                 }
//                 anyChartView.setChart(cartesian);
//                     }





//         }
// }}

// visualization_all
// package com.xa.xpensauditor;

// import androidx.appcompat.app.AppCompatActivity;

// import android.content.Intent;
// import android.os.Bundle;
// import android.view.View;
// import android.widget.Button;
// import android.widget.EditText;

// public class Visualization_all extends AppCompatActivity {
//     EditText mEdit;
//     EditText mEdit1,mEdit2,mEdit3,mEdit4,mEdit5;
//     private Button Vis,Vis1;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_visualization_all);

//                 Vis = (Button) findViewById(R.id.button2);
//         Vis1 = (Button) findViewById(R.id.button5);
//         Vis.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent c=new Intent(Visualization_all.this,Day_wise_visualization.class);
//                 mEdit   = (EditText)findViewById(R.id.editTextNumber);
//                 String m2=mEdit.getText().toString();
//                 mEdit1   = (EditText)findViewById(R.id.editTextNumber2);

//                 String y2=mEdit1.getText().toString();
//                 String day2="0";
//                 System.out.println(m2+"yesssss     ");
//                 Bundle b = new Bundle();
//                 b.putString("m2",m2);
//                 b.putString("y2",y2);
//                b.putString("d2",day2);
//                 c.putExtras(b);
//                 startActivity(c);
//             }

//         });
//         Vis1.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Intent c=new Intent(Visualization_all.this,MonthWiseVis.class);
//                 mEdit2=(EditText)findViewById(R.id.editTextNumber3);
//                 String m1=mEdit2.getText().toString();
//                 mEdit3=(EditText)findViewById(R.id.editTextNumber6);
//                 String m2=mEdit3.getText().toString();
//                 mEdit4   = (EditText)findViewById(R.id.editTextNumber4);
//                 String y1=mEdit4.getText().toString();
//                 mEdit5   = (EditText)findViewById(R.id.editTextNumber7);
//                 String y2=mEdit5.getText().toString();

//                 String day2="0";
//                 System.out.println(m2+"yesssss     ");
//                 Bundle b = new Bundle();
//                 b.putString("m1",m1);
//                 b.putString("y1",y1);
//                 b.putString("d1",day2);
//                 b.putString("m2",m2);
//                 b.putString("y2",y2);
//                 b.putString("d2",day2);

//                 c.putExtras(b);
//                 startActivity(c);
//             }

//         });
//     }
// }

// Daywise

//  package com.xa.xpensauditor;

// import static java.lang.System.currentTimeMillis;

// import androidx.annotation.NonNull;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.recyclerview.widget.RecyclerView;

// import android.os.Bundle;
// import android.util.Log;
// import android.widget.Button;
// import android.widget.DatePicker;
// import android.widget.TextView;

// import com.anychart.AnyChart;
// import com.anychart.AnyChartView;
// import com.anychart.chart.common.dataentry.DataEntry;
// import com.anychart.chart.common.dataentry.ValueDataEntry;
// import com.anychart.charts.Cartesian;
// import com.anychart.core.cartesian.series.Column;
// import com.anychart.enums.Anchor;
// import com.anychart.enums.HoverMode;
// import com.anychart.enums.Position;
// import com.anychart.enums.TooltipPositionMode;
// import com.firebase.client.Firebase;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.database.DataSnapshot;
// import com.google.firebase.database.DatabaseError;
// import com.google.firebase.database.DatabaseReference;
// import com.google.firebase.database.FirebaseDatabase;
// import com.google.firebase.database.ValueEventListener;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Calendar;
// import java.util.Collections;
// import java.util.Date;
// import java.util.GregorianCalendar;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.TreeMap;

// public class Day_wise_visualization extends AppCompatActivity {
//     private Firebase mRootRef;
//     private Firebase RefUid,RefTran,RefUid1;
//     private Firebase RefName,RefEmail,RefPhnnum;
//     private Firebase RefCat,RefFood,RefHealth,RefTravel,RefEdu,RefBills,RefHomeNeeds,RefOthers,RefUncat;
//     private List<Transaction> transList = new ArrayList<>();
//     private RecyclerView recyclerView;
//     private TransactionAdapter mAdapter;
//     private DatePicker dateTransac1;
//     private DatePicker dateTransac2;
//     private Button Vis;
//     private String Tid;
//     int tot=0;
//     Date date = new Date();
//     static Map<Date, Integer> hm = new HashMap<>();
//     Map<Date, Integer> sortedMap = new TreeMap<Date, Integer>();
//     String tot1="Yes";
//     FirebaseAuth auth;
//     String Uid;
//     String day1, month1, year1;
//     String day2, month2, year2;
//     int d1, m1, y1;
//     int d2, m2, y2;
//     private Firebase RefTran3;
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {

//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_day_wise_visualization);
//         auth = FirebaseAuth.getInstance();
//         mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");
//         mRootRef.keepSynced(true);
//         Uid=auth.getUid();
//         RefUid= mRootRef.child(Uid);
//         RefName = RefUid.child("Name");
//         RefEmail=RefUid.child("Email");


//         // RefUid.child("DateRange").child(String.valueOf(month + "-" + year)).child("Transactions").child(Tid).child("Amount").setValue(Amount);
//         Bundle bundle = getIntent().getExtras();
//         String month2= bundle.getString("m2");
//         String year2= bundle.getString("y2");
//         String day2= bundle.getString("d2");
// int m2=Integer.parseInt(month2);
//         int y2=Integer.parseInt(year2);
//         int d2=Integer.parseInt(day2);
//         AnyChartView anyChartView = findViewById(R.id.any_chart_view);
//         List<Integer> arr=new ArrayList<>();

//         Cartesian cartesian = AnyChart.column();

//         List<DataEntry> data = new ArrayList<>();
//         {

//             Boolean f = false;
//             Calendar calendar = Calendar.getInstance();

//             int thisYear = calendar.get(Calendar.YEAR);
//             // Log.d(TAG, "# thisYear : " + thisYear);

//             int thisMonth = calendar.get(Calendar.MONTH)+1;
//             //Log.d(TAG, "@ thisMonth : " + thisMonth);

//             int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
//             //Log.d(TAG, "$ thisDay : " + thisDay);


//             if (thisYear > y2) {
//                 f = true;
//             } else {
//                 if (thisYear == y2) {
//                     if (thisMonth > m2) {
//                         f = true;
//                     } else {
//                         if (thisMonth == m2) {
//                             if (thisDay >= d2) {
//                                 f = true;
//                             }
//                         }
//                     }
//                 }
//             }

//             if (f) {
// System.out.println("Day_Wise   "+m2 +"    "+y2);
//                 DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Uid).child("DateRange").child(String.valueOf(m2 + "-" + y2)).child("Transactions");
//                 reference.addValueEventListener(new ValueEventListener() {
//                     List<DataEntry> data = new ArrayList<>();
//                     String dayy,dumm;
//                     int dayy1,dumm1;
//                     int dayyy,dayyy1;

//                     @Override
//                     public void onDataChange(@NonNull DataSnapshot datasnapshot) {

//                         for(DataSnapshot snapshot :datasnapshot.getChildren() ){
//                             String amm=snapshot.child("Amount").getValue().toString();
//                             System.out.println(" Finallyyy   "+amm);
//                             int am1=Integer.valueOf(amm);
//                             tot=am1+tot;
//                             dumm=snapshot.child("Day").getValue().toString();
//                             dayyy1=Integer.valueOf(dumm);
//                             Calendar c1 = Calendar.getInstance();
//                             c1.set(y2, m2-1, dayyy1, 0, 0);
//                             Date date = new GregorianCalendar(y2, m2-1, dayyy1).getTime();


//       dumm1=Integer.valueOf(dumm);
//       System.out.println("dummmmm   "+dumm1+"    "+date);

//       if(sortedMap.containsKey(date)){
//           System.out.println("inside");
//           int temp=0;
//           temp=sortedMap.get(date);
//       temp=temp+am1;
//           System.out.println("dummmmm1   "+dayyy+"    "+temp);
//           sortedMap.put(date,temp);
//           System.out.println("Dates printing-4   ");
//       }
//       else{
//           sortedMap.put(date,am1);}
//                  }

// //                        ArrayList<Integer> sortedKeys
// //                                = new ArrayList<Integer>(hm.keySet());
// //
// //                        Collections.sort(sortedKeys);

//                         // Display the TreeMap which is naturally sorted
// //                        for (Integer x : sortedKeys) {
// //                            data.add(new ValueDataEntry(x,hm.get(x)));
// //                        }
//                         System.out.println("Dates printing-1   ");

//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             System.out.println("Dates printing  "+entry.getValue()+"  "+entry.getKey());

//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }
//                         for (Map.Entry< Date,Integer> entry :
//                                 sortedMap.entrySet()) {
//                             Integer strtype=Integer.parseInt(String.valueOf(entry.getValue()));
//                             data.add(new ValueDataEntry(String.valueOf(entry.getKey()),strtype));
//                         }

//                             sortedMap.clear();
//                         Column column = cartesian.column(data);
//                         column.tooltip()
//                                 .titleFormat("{%X}")
//                                 .position(Position.CENTER_BOTTOM)
//                                 .anchor(Anchor.CENTER_BOTTOM)
//                                 .offsetX(0d)
//                                 .offsetY(5d)
//                                 .format("${%Value}{groupsSeparator: }");

//                         cartesian.animation(true);
//                         cartesian.title("Expenses day wise");

//                         cartesian.yScale().minimum(0d);

//                         cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

//                         cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//                         cartesian.interactivity().hoverMode(HoverMode.BY_X);

//                         cartesian.xAxis(0).title("Dates");
//                         cartesian.yAxis(0).title("Expences");

//                         anyChartView.setChart(cartesian);
//                         // System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy  ");
//                     }

//                     @Override
//                     public void onCancelled(@NonNull DatabaseError error) {

//                     }
//                 });
//                 System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy2  ");
//             }
//             System.out.println(data+"   data2222  ");
//             System.out.println(Arrays.toString(arr.toArray())+" arrrrrayyyyy1  ");
//         }
//     }
// }

// Search Product

// package com.xa.xpensauditor;

// import androidx.appcompat.app.AppCompatActivity;
// import androidx.appcompat.widget.Toolbar;
// import android.app.ProgressDialog;
// import android.content.Intent;
// import android.os.Bundle;
// import android.os.Handler;
// import android.view.View;
// import android.widget.AdapterView;
// import android.widget.ArrayAdapter;
// import android.widget.Button;
// import android.widget.EditText;
// import android.widget.ImageView;
// import android.content.Context;
// import android.widget.ListView;
// import android.widget.Toast;
// import org.json.JSONArray;
// import org.json.JSONObject;
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.Arrays;
// import android.app.ProgressDialog;

// public class SearchProduct extends AppCompatActivity{

//     static ListView listView;
//     EditText input;
//     ImageView search_button;
//     static ListViewAdapter adapter;
//     static ArrayList<String> rows;
//     static Context context;
//     Handler fetchHandler = new Handler();
//     ProgressDialog progressDialog;

//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_search_product);
//         listView = findViewById(R.id.list);
//         input = findViewById(R.id.input);
//         search_button = findViewById(R.id.search_button);
//         context = getApplicationContext();

//         rows = new ArrayList<>();

//         listView.setLongClickable(true);
//         adapter = new ListViewAdapter(this, rows);
//         listView.setAdapter(adapter);

//         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//             @Override
//             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 String clickedItem = (String) listView.getItemAtPosition(position);
//                 Toast.makeText(SearchProduct.this, clickedItem, Toast.LENGTH_SHORT).show();
//             }
//         });

//        search_button.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 String text = input.getText().toString();
//                 if (text == null || text.length() == 0) {
//                     makeToast("Enter a valid product name.");
//                 } else {
//                     new FetchProduct().start();
//                     addItem();
//                 }
//             }
//         });
//         loadContent();
//     }

//     public void loadContent() {
//         File path = getApplicationContext().getFilesDir();
//         File readFrom = new File(path, "list.txt");
//         byte[] content = new byte[(int) readFrom.length()];

//         FileInputStream stream = null;
//         try {
//             stream = new FileInputStream(readFrom);
//             stream.read(content);
//             String s = new String(content);
//             s = s.substring(1, s.length() - 1);
//             String split[] = s.split(", ");

//             if (split.length == 1 && split[0].isEmpty())
//                 rows = new ArrayList<>();
//             else rows = new ArrayList<>(Arrays.asList(split));

//             adapter = new ListViewAdapter(this, rows);
//             listView.setAdapter(adapter);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void addItem() {
//         listView.setAdapter(adapter);
//     }

//     class FetchProduct extends Thread{
//         String data = "";

//         @Override
//         public void run(){

//             fetchHandler.post(new Runnable() {
//                 @Override
//                 public void run() {
//                     progressDialog = new ProgressDialog(SearchProduct.this);
//                     progressDialog.setMessage("Fetching Data");
//                     progressDialog.setCancelable(false);
//                     progressDialog.show();
//                 }
//             });
//             try {
//                 String api_link = "https://online-product-scrapper.onrender.com/search_walmart?query="+input.getText().toString();
//                 URL url = new URL(api_link);
//                 HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                 InputStream inputStream = httpURLConnection.getInputStream();
//                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                 String line;
//                 while ((line = bufferedReader.readLine()) != null ){
//                     data = data + line;
//                 }
//                 if(!data.isEmpty()){
//                     JSONArray jSONArray = new JSONArray(data);
//                     for(int i=0;i<jSONArray.length();i++){
//                         JSONObject product = jSONArray.getJSONObject(i);
//                         String title = product.getString("title");
//                         String price = product.getString("price");
//                         String website = product.getString("website");
//                         if(!title.isEmpty()){
//                             rows.add("Name: "+title+ "\nPrice: "+price+ "\nWebsite: "+website);
//                         }
//                     }
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }

//             fetchHandler.post(new Runnable() {
//                 @Override
//                 public void run() {
//                     if(progressDialog.isShowing())
//                         progressDialog.dismiss();
//                     adapter.notifyDataSetChanged();
//                 }
//             });
//         }
//     }

//     static Toast t;
//     private static void makeToast(String s) {
//         if (t != null) t.cancel();
//         t = Toast.makeText(context, s, Toast.LENGTH_SHORT);
//         t.show();
//     }
// }


