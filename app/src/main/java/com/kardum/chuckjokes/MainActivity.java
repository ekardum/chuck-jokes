package com.kardum.chuckjokes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.kardum.chuckjokes.model.Joke;
import com.kardum.chuckjokes.network.APIManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public static String TAG = MainActivity.class.getSimpleName();
    public static int REQUEST_INVITE = 101;
    private TextView jokeTextView;
    private ImageView chuckImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        jokeTextView = findViewById(R.id.jokeTextView);
        chuckImageView = findViewById(R.id.chuckImageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomJoke();
            }
        });


    }

    void getRandomJoke(){
        APIManager.APIManager().getRequestHelper().getRandomJoke().enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                Log.v(TAG,"onResponse");
                jokeTextView.setText(response.body().getValue());
                updateImage(response.body().getIconUrl());
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Log.v(TAG,"onFailure");
            }
        });
    }

    private void onFact() {
        startActivity(FactActivity.getIntent(this));
    }

    private void onJoke(){
        startActivity(MainActivity.getIntent(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }

    private void updateImage(String iconUrl) {
        Picasso.get().load(R.drawable.chucknorris_logo).into(chuckImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_invite:
                onFact();
                break;
            case R.id.action_about:
                onJoke();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
