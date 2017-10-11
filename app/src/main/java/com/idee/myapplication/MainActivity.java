package com.idee.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by idee on 9/14/17.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        final CustomAdapter adapter = new CustomAdapter();
        RecyclerView r = findViewById(R.id.recycler_view);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(adapter);
        //viewModel.getData().observe(this, adapter::setList);

        viewModel.getData().observe(this, new Observer<PagedList<ApiResult>>() {
            @Override
            public void onChanged(@Nullable PagedList<ApiResult> results) {
                Log.i(getClass().getSimpleName(), String.valueOf(results.size()));
                adapter.setList(results);
                adapter.notifyDataSetChanged();
                r.setAdapter(adapter);
            }
        });


        
        //adapter.notifyDataSetChanged();

    }

}