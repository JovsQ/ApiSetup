package com.example.jq.setupapirequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jq.setupapirequest.helpers.ApiAction;
import com.example.jq.setupapirequest.helpers.ApiRequestHelper;
import com.example.jq.setupapirequest.interfaces.OnApiRequestListener;
import com.example.jq.setupapirequest.models.AuthBody;
import com.example.jq.setupapirequest.models.Token;

public class MainActivity extends AppCompatActivity implements OnApiRequestListener{

    private Button btnLogin;
    private TextView tvHeader;
    private ApiRequestHelper apiRequestHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
    }

    private void initResources() {
        apiRequestHelper = new ApiRequestHelper(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvHeader = (TextView) findViewById(R.id.tvHeader);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiRequestHelper.authenticateUser(new AuthBody("ned@flanders.com", "password"));
            }
        });
    }

    @Override
    public void onApiRequestStart(ApiAction action) {
        tvHeader.setText("start");
    }

    @Override
    public void onApiRequestFailed(ApiAction action, Throwable error) {
        tvHeader.setText("failed >>> " + error);
    }

    @Override
    public void onApiRequestSuccessful(ApiAction action, Object result) {
        final Token token = (Token) result;
        tvHeader.setText(token.getToken());
    }
}
