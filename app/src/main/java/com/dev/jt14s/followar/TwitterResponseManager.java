package com.dev.jt14s.followar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 1/27/2018.
 */

public class TwitterResponseManager extends AsyncTask<Object, String, String> {

    private List<CardData> cardData;
    private Context context;

    public TwitterResponseManager(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... objects) {
        DataParser parser = new DataParser();
        cardData = parser.parse(String.valueOf(objects[0]));
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(context, BoardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("follower_cards", (Serializable) cardData);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }
}