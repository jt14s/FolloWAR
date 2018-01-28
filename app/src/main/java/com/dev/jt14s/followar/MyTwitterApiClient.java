package com.dev.jt14s.followar;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jorge on 1/25/2018.
 */

public class MyTwitterApiClient extends TwitterApiClient {

    public  MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public UserService getUserServices() {
        return getService(UserService.class);
    }
}

interface UserService {
    @GET("/1.1/followers/list.json")
    Call<ResponseBody> show(@Query("user_id") long id,
                            @Query("cursor") long cursor,
                            @Query("screen_name") String screenName,
                            @Query("skip_status") boolean skip,
                            @Query("include_user_entities") boolean include);
}
