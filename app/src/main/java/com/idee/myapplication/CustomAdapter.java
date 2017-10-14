package com.idee.myapplication;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by idee on 9/14/17.
 */

public class CustomAdapter extends PagedListAdapter<ApiResult, CustomAdapter.MyViewHolder> {

    public CustomAdapter() {
        super(ApiResult.DIFF_CALLBACK);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView t;

        MyViewHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.text_view);
        }

        void bindTo(ApiResult item) {
            //t.setText(result.getTitle());

            t.setText(item.getTitle());
        }

    }

}
