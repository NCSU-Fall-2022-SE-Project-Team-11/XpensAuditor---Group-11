package com.xa.xpensauditor;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This activity helps display transactions
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    private List<Transaction> transList;
    private static ClickListener mClickListener;

    private int position;

    /**
     * Get transaction position
     * @return
     */
    public int getPosition(){
        return position;
    }

    /**
     * Set transaction position
     * @param position
     */
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * View transaction
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alltrans_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * Binds transactions to a holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Transaction trans = transList.get(position);
        holder.tid.setText(trans.getTid());
        holder.tcat.setText(trans.getT_cat());
        holder.tamt.setText("$"+trans.getT_amt());
        holder.tshopname.setText(trans.getT_shopname());
        holder.tdate.setText(trans.getT_date());
        holder.tsharedwith.setText(trans.getT_sharedwith());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                setPosition(holder.getPosition());
                return false;
            }
        });

    }

    /**
     * Get item count
     * @return
     */
    @Override
    public int getItemCount() {
        return transList.size();
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnCreateContextMenuListener {
        public TextView tid, tamt, tcat,tshopname,tdate,tsharedwith;


        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            tid = (TextView) view.findViewById(R.id.tid);
            tamt = (TextView) view.findViewById(R.id.tamt);
            tcat = (TextView) view.findViewById(R.id.tcat);
            tshopname = (TextView) view.findViewById(R.id.tshopname);
            tsharedwith = (TextView) view.findViewById(R.id.tsharedwith);
            tdate=(TextView) view.findViewById(R.id.tdate);
            view.setOnCreateContextMenuListener(this);

        }


        @Override
        public void onClick(View v)
        {
            mClickListener.OnItemClick(getAdapterPosition(),v);
        }

        @Override
        public boolean onLongClick(View v){
            mClickListener.OnItemLongClick(getAdapterPosition(),v);
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select an action");
            menu.add(getAdapterPosition(),21,0,"Delete");
            menu.add(getAdapterPosition(),22,0,"Change category");

        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        TransactionAdapter.mClickListener = clickListener;

    }


    public interface ClickListener{
        void OnItemClick(int position, View v);
        void OnItemLongClick(int position, View v);
    }

    public TransactionAdapter(List<Transaction> transList) {
        this.transList = transList;
        int i = 0;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
    }
}
