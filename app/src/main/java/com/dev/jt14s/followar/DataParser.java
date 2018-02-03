package com.dev.jt14s.followar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 1/27/2018.
 */

public class DataParser {

    public List<CardData> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("users");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getAllFollowers(jsonArray);
    }

    private List<CardData> getAllFollowers(JSONArray jsonArray) {
        List<CardData> followersList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                followersList.add(getFollower((JSONObject) jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return followersList;
    }

    private CardData getFollower(JSONObject jFollower) {
        CardData followerCardData = new CardData();

        try {
            if (!jFollower.isNull("created_at")) {
                String dateCreated = jFollower.getString("created_at");
                followerCardData.setHealth(Integer.parseInt(dateCreated.substring(dateCreated.length() - 2)) % 8);
            }
            if (!jFollower.isNull("followers_count"))
                followerCardData.setCost(Integer.parseInt(jFollower.getString("followers_count")) % 5);
            if (!jFollower.isNull("statuses_count"))
                followerCardData.setAttack(Integer.parseInt(jFollower.getString("statuses_count")) % 6);
            if (!jFollower.isNull("profile_image_url"))
                followerCardData.setCardImageURL(jFollower.getString("profile_image_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return followerCardData;
    }
}
