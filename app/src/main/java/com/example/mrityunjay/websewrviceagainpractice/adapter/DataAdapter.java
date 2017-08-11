package com.example.mrityunjay.websewrviceagainpractice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mrityunjay.websewrviceagainpractice.R;
import com.example.mrityunjay.websewrviceagainpractice.model.DataHandler;

import java.util.List;

/**
 * Created by Mrityunjay on 08-08-2017.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    Context context;
    List<DataHandler> data;
    ClickListener clickListener;

    public DataAdapter(Context context, List<DataHandler> data){
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.temp.setText(data.get(position).getTemp()+"");
        holder.main.setText(data.get(position).getMain());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.ItemClicked(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, temp, main;
        RelativeLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView)itemView.findViewById(R.id.name);
            temp= (TextView)itemView.findViewById(R.id.order);
            main= (TextView)itemView.findViewById(R.id.character);
            parent= (RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }

    public interface ClickListener{
        void ItemClicked(View v, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
