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

    public List<Card> parse(String jsonData) {
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

    private List<Card> getAllFollowers(JSONArray jsonArray) {
        List<Card> followersList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                followersList.add(getFollower((JSONObject) jsonArray.get(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return followersList;
    }

    private Card getFollower(JSONObject jFollower) {
        Card followerCard = new Card();

        try {
            if (!jFollower.isNull("created_at")) {
                String dateCreated = jFollower.getString("created_at");
                followerCard.setHealth(Integer.parseInt(dateCreated.substring(dateCreated.length() - 4)));
            }
            if (!jFollower.isNull("followers_count"))
                followerCard.setCost(Integer.parseInt(jFollower.getString("followers_count")));
            if (!jFollower.isNull("statuses_count"))
                followerCard.setAttack(Integer.parseInt(jFollower.getString("statuses_count")) % 10);
            if (!jFollower.isNull("profile_image_url"))
                followerCard.setCardImageURL(jFollower.getString("profile_image_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return followerCard;
    }
}
