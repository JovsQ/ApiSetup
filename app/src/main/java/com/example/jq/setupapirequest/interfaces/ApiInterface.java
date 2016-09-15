package com.example.jq.setupapirequest.interfaces;

import com.example.jq.setupapirequest.models.AuthBody;
import com.example.jq.setupapirequest.models.AuthResponse;
import com.example.jq.setupapirequest.models.Token;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ctmanalo on 9/14/16.
 */
public interface ApiInterface {

    /**
     * authenticate user
     *
     * @param authBody data that holds the user info need for authentication
     * @return Token model that holds the token returned by successful auth
     */
    @POST("/api/user/auth")
    Observable<Token> auth(@Body AuthBody authBody);

    /**
     * Authenticate user
     *
     * @param email email of user trying to login
     * @param password password of user trying to
     *
     * @return AuthResponse which includes the basic info of info of successfully
     *                      authenticated user together with Token
     * */
    @POST("/api/v1/login")
    @FormUrlEncoded
    Observable<AuthResponse> authenticateUser(@Field("email") String email,
                                              @Field("password") String password);
}
