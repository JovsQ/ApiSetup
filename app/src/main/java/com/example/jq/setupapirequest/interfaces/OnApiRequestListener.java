package com.example.jq.setupapirequest.interfaces;

import com.example.jq.setupapirequest.helpers.ApiAction;

/**
 * Created by ctmanalo on 9/15/16.
 */
public interface OnApiRequestListener {

    void onApiRequestStart(final ApiAction action);
    void onApiRequestFailed(final ApiAction action, final Throwable error);
    void onApiRequestSuccessful(final ApiAction action, final Object result);
}
