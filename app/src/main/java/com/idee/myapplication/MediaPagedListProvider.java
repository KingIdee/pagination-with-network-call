package com.idee.myapplication;

import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idee on 10/11/17.
 */

public class MediaPagedListProvider {

    DataClass dataClass = new DataClass() {
        @Override
        List<ApiResult> convertToItems(List<ApiResult> result, int size) {
            return result;
        }
    };

    public LivePagedListProvider<Integer,ApiResult> getAll(){
        return new LivePagedListProvider<Integer, ApiResult>() {
            @Override
            protected DataSource<Integer, ApiResult> createDataSource() {
                return dataClass;
            }
        };

    }

}
