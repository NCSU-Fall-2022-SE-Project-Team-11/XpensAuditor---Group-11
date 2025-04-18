package com.xa.xpensauditor;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 * Handles fragments in the main menu, to view transactions.
 */
public class TabFragment extends Fragment {
    private Firebase mRootRef;
    private Firebase RefUid,RefTran, RefCat, RefCatTran;
    int pos, currentDay,currentMonth,currentYear;
    private String tagId, delCategory ;


    private ArrayList<String> CatgTF=new ArrayList<>();
    private ArrayAdapter<String> arrayAdapterTF;

    private List<Transaction> TransactionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TransAdapter mAdapter1;

    /**
     * Handles Fragments and transcation in the main page based on the position
     * @param position
     * @return tabFragment
     */
    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos1", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Loads data when fragment is loaded
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);

    }

    /**
     * Loads data when view is created.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Calendar calendar = Calendar.getInstance();
        currentDay = (calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth = (calendar.get(Calendar.MONTH)+1);
        currentYear = (calendar.get(Calendar.YEAR));
        mRootRef=new Firebase("https://xpensauditor-g11-default-rtdb.firebaseio.com/");

        mRootRef.keepSynced(true);
        com.google.firebase.auth.FirebaseAuth auth = FirebaseAuth.getInstance();
        String Uid=auth.getUid();
        RefUid= mRootRef.child(Uid);
        RefTran = RefUid.child("DateRange").child(currentMonth+"-"+currentYear).child("Transactions");
        RefCatTran = RefUid.child("DateRange").child(currentMonth+"-"+currentYear).child("CatTran");
        RefCat = RefUid.child("Categories");

        arrayAdapterTF=new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,CatgTF);

        RefCat.addChildEventListener(new ChildEventListener() {
            /**
             * Triggered when a child node is added.
             * @param dataSnapshot
             * @param s
             */
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value= dataSnapshot.getKey().trim();
                CatgTF.add(value);
                arrayAdapterTF.notifyDataSetChanged();
            }

            /**
             * Triggered when a child node in the database is updated or added.
             * @param dataSnapshot
             * @param s
             */
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            /**
             * Triggered when a child node in the database is removed.
             * @param dataSnapshot
             */
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            /**
             * Triggered when a child node in the database is moved.
             * @param dataSnapshot
             * @param s
             */
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            /**
             * Handles any firebase related errors in the program
             * @param firebaseError
             */
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter1 = new TransAdapter(TransactionList);
        recyclerView.setAdapter(mAdapter1);
        prepareTransactionData();


        registerForContextMenu(recyclerView);

        mAdapter1.setOnItemClickListener(new TransAdapter.ClickListener() {
            /**
             * When a transaction is clicked, it shows the details of the transaction.
             * @param position
             * @param v
             */
            @Override
            public void OnItemClick(int position, View v) {

                Intent i = new Intent(getActivity(),SMSDBFetchActivity.class);
                i.putExtra("indexPos",TransactionList.get(position).getTid());
                startActivity(i);
            }

            /**
             * On a long clock, view position of the transaction in the Fragment.
             * @param position
             * @param v
             */
            @Override
            public void OnItemLongClick(int position, View v) {
                Log.i("yoyoyo","Here: "+position);
                pos=position;
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case 11:{
                int show = item.getGroupId();

                tagId=TransactionList.get(show).getTid();
                delCategory = TransactionList.get(show).getT_cat();
                

                RefTran.child(tagId).removeValue();
                RefUid.child("DateRange").child(currentMonth+"-"+currentYear).child("CatTran").child(delCategory).child(tagId).removeValue();
                RefUid.child("UnCatTran").child(tagId).removeValue();
                mAdapter1.notifyDataSetChanged();//updated

                TransactionList.clear();
                prepareTransactionData();

            }break;
        }
        return super.onContextItemSelected(item);
    }

    private void prepareTransactionData() {

        RefTran.addChildEventListener(new ChildEventListener() {
            String amount,cat,shname,shDay,shMonth,shYear,shMsg, sharedWith;

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int i=0;

                String tid = dataSnapshot.getKey().toString().trim();
                for (DataSnapshot S:dataSnapshot.getChildren()) {
                    //Log.d("yomon",i+":"+S.getValue().toString().trim());
                    switch(i)
                    {
                        case 0:
                            amount=S.getValue().toString().trim();
                            break;
                        case 1:
                            cat=S.getValue().toString().trim();
                            break;
                        case 2:
                            shDay=S.getValue().toString().trim();
                            break;
                        case 3:
                            shMonth=S.getValue().toString().trim();
                            break;
                        case 4:
                            String label = (S.getChildrenCount() > 1) ? "Shared With: " : "";
                            sharedWith=S.getValue().toString().replace("[", label).replace("]", "");
                            break;
                        case 5:
                            shname=S.getValue().toString().trim();
                            break;
                        case 6:
                            shYear=S.getValue().toString().trim();
                            break;
                        case 7:
                            shMsg=S.getValue().toString().trim();
                            break;
                    }
                    i++;
                }
                try{
                    String monthString = new DateFormatSymbols().getMonths()[Integer.parseInt(shMonth)-1];
                    String shdate= shDay+" " + monthString.substring(0,3).toUpperCase() +" "+shYear;

                    Transaction transaction=new Transaction(tid,amount,cat,shname,shdate,shMsg,sharedWith);

                    TransactionList.add(transaction);
                    mAdapter1.notifyDataSetChanged();
                }
                catch (Exception e){

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

}

