package com.idee.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by idee on 9/14/17.
 */

public class MyViewModel extends ViewModel {

    private LiveData<PagedList<ApiResult>> listLiveData = null;

    LiveData<PagedList<ApiResult>> getData(){

        if (listLiveData==null){

            listLiveData = new MediaPagedListProvider()
                    .getAll()
                    .create(0, new PagedList.Config.Builder()
                            .setPageSize(5) //number of items loaded at once
                            .setPrefetchDistance(1)// the distance to the end of already loaded list before new data is loaded
                            .setEnablePlaceholders(false)
                            .build());


        }
        return listLiveData;
    }

}
