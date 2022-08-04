package com.webianks.popupbubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by R Ankit on 29-05-2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder>{


    private Context context;
    private List<DataClass> list;

    public ContentAdapter(Context context, List<DataClass> list){

        this.context=context;
        this.list=list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        MyViewHolder myViewHolder =  new MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text);

        }
    }


}
