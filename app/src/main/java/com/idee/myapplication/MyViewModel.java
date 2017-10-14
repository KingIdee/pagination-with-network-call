package com.idee.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

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
                            .setPageSize(15) //number of items loaded at once
                            .setPrefetchDistance(2)// the distance to the end of already loaded list before new data is loaded
                            .setEnablePlaceholders(false)
                            .build());


        }
        return listLiveData;
    }

}
