package com.dev.jt14s.followar;

import android.content.Context;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Jorge on 1/27/2018.
 */

public class TwitterSessionHandler {

    private Context context;

    public TwitterSessionHandler(Context context) {
        this.context = context;
    }

    public void generateTwitterSession() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthToken authToken = session.getAuthToken();
        MyTwitterApiClient apiClient = new MyTwitterApiClient(session);
        UserService userService = apiClient.getUserServices();

        generateUserFollowersCall(session, userService);
    }

    private void generateUserFollowersCall(TwitterSession session, UserService userService) {
        Call<ResponseBody> responseBodyCall = userService.show(session.getUserId(), -1, session.getUserName(), true, false);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void success(Result<ResponseBody> result) {
                try {
                    TwitterResponseManager responseManager = new TwitterResponseManager(context);
                    responseManager.execute(new String(result.data.bytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(TwitterException exception) {
                //TODO::do something with error message
            }
        });
    }
}
