package com.idee.myapplication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

/**
 * Created by idee on 9/15/17.
 */

class NetworkModule {

    public interface WebService {
        @GET("/3/movie/popular?api_key=API_KEY")
        Call<String> makeRequest();
    }

    private static OkHttpClient providesOkHttpClientBuilder(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }

    static WebService providesWebService() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
                .client(providesOkHttpClientBuilder())
                .build();

        return retrofit.create(WebService.class);

    }

}
