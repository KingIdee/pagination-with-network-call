package com.idee.myapplication;

import android.arch.paging.DataSource;
import android.arch.paging.TiledDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idee on 9/15/17.
 */

public abstract class DataClass extends TiledDataSource<ApiResult> {

    @Override
    public int countItems() {
        return DataSource.COUNT_UNDEFINED;
    }

    @Override
    public List<ApiResult> loadRange(int startPosition, int count) {

        List<ApiResult> webServiceResponse = null;
        try {
            webServiceResponse = parseJson(NetworkModule.providesWebService().makeRequest().execute().body());
            Log.d("TAG", String.valueOf(webServiceResponse.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertToItems(webServiceResponse,webServiceResponse.size());

    }

    abstract List<ApiResult> convertToItems(List<ApiResult> result, int size);

    private List<ApiResult> parseJson(String response) {

        List<ApiResult> apiResults = new ArrayList<>();

        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            jsonObject = new JSONObject(response);
            jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                ApiResult mMovieModel = new ApiResult();
                //mMovieModel.setId(object.getString("id"));
                mMovieModel.setPosterPath(object.getString("poster_path"));
                mMovieModel.setTitle(object.getString("original_title"));
                mMovieModel.setBackdropPath(object.getString("backdrop_path"));
                mMovieModel.setReleaseDate(object.getString("release_date"));
                //mMovieModel.setVoteAverage(object.getString("vote_average"));
                mMovieModel.setOverview(object.getString("overview"));

                apiResults.add(mMovieModel);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(getClass().getSimpleName(), String.valueOf(apiResults.size()));
        return apiResults;

    }

}