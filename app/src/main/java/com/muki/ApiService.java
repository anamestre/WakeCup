package com.muki;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Toni on 27/11/2016.
 */

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("readiness.json")
    Call<HackerNews> getMyJSON();
}
