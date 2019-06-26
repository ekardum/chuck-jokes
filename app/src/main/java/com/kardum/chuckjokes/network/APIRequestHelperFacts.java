package com.kardum.chuckjokes.network;

import com.kardum.chuckjokes.model.Fact;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestHelperFacts {

    String kRandomFacts = "";

    @GET("random.json?language=en")
    Call<Fact> getRandomFact();



}
