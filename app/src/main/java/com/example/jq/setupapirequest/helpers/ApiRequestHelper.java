package com.example.jq.setupapirequest.helpers;

import com.example.jq.setupapirequest.interfaces.OnApiRequestListener;
import com.example.jq.setupapirequest.models.AuthBody;
import com.example.jq.setupapirequest.models.AuthResponse;
import com.example.jq.setupapirequest.models.Token;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ctmanalo on 9/15/16.
 */
public class ApiRequestHelper {

    private OnApiRequestListener onApiRequestListener;

    public ApiRequestHelper(OnApiRequestListener onApiRequestListener) {
        this.onApiRequestListener = onApiRequestListener;
    }

    /**
     * Authentication of user's email
     *
     * @param authBody request email
     */
    public void authenticateUser(final AuthBody authBody) {
        onApiRequestListener.onApiRequestStart(ApiAction.POST_AUTHENTICATE);
        final Observable<Token> observable = AppConstants.API_INTERFACE.auth(authBody);
        handleObservableResult(ApiAction.POST_AUTHENTICATE, observable);
    }

    public void authenticateUser(final String email, final String password) {
        onApiRequestListener.onApiRequestStart(ApiAction.POST_LOGIN);
        final Observable<AuthResponse> observable = AppConstants.API_INTERFACE.authenticateUser(email, password);
        handleObservableResult(ApiAction.POST_LOGIN, observable);
    }

    private void handleObservableResult(final ApiAction apiAction, final Observable observable) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onApiRequestListener.onApiRequestFailed(apiAction, e);
                    }

                    @Override
                    public void onNext(Object o) {
                        onApiRequestListener.onApiRequestSuccessful(apiAction, o);
                    }
                });
    }
}
