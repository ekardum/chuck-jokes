package com.kardum.chuckjokes.network;

import com.kardum.chuckjokes.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestHelper {

    String kRandomJokes = "";

    @GET("jokes/random")
    Call<Joke> getRandomJoke();



}
