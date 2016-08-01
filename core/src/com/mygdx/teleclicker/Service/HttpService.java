package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Senpai on 01/08/2016.
 */
public class HttpService implements Net.HttpResponseListener{
    final String EXTERNAL_URL = "http://tomcat-tamburyniarz.rhcloud.com/";
    final String LOCAL_URL = "http://tomcat-tamburyniarz.rhcloud.com/";

    final boolean IS_LOCAL = true;

    private String responsStr = "NOT CONNECTED";

    private String getUrl(){
        if(IS_LOCAL)
            return LOCAL_URL;
        else
            return EXTERNAL_URL;
    }

    public void addPlayerRequest(String login, String password){
        final String SERVLET_NAME = "/addplayer";

        Map parameters = new HashMap();
        parameters.put("admin_key", "key");
        parameters.put("login", "Android");
        parameters.put("pass", "testowe haslo");

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(getUrl() + SERVLET_NAME)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();

        Gdx.net.sendHttpRequest(request,this);
    }


    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        responsStr = new String(httpResponse.getResult());
    }

    @Override
    public void failed(Throwable t) {
        responsStr = "FAILED'";
    }

    @Override
    public void cancelled() {
        responsStr = "CANCELLED";
    }

    public String getResponsStr() {
        return responsStr;
    }
}
