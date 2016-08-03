package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.teleclicker.Entities.PlayerStats;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Senpai on 01/08/2016.
 */
public class HttpService implements Net.HttpResponseListener {
    final String EXTERNAL_URL = "http://demo-tamburyniarz.rhcloud.com/";
    final String LOCAL_URL = "http://localhost/";

    final boolean IS_LOCAL = false;

    private String responsStr = "NOT CONNECTED";

    private String getUrl() {
        if (IS_LOCAL)
            return LOCAL_URL;
        else
            return EXTERNAL_URL;
    }

    public void saveStatsRequest(PlayerStats playerStats) {
        final String SERVLET_NAME = "/savestats";

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        System.out.println(json.toJson(playerStats));

        Map parameters = new HashMap();
        parameters.put("admin_key", "key");
        parameters.put("player_stats", json.toJson(playerStats));

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(getUrl() + SERVLET_NAME)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();

        Gdx.net.sendHttpRequest(request, this);
    }

    public void loginRequest(String login, String password) {
        final String SERVLET_NAME = "/login";

        Map parameters = new HashMap();
        parameters.put("login", login);
        parameters.put("pass", password);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(getUrl() + SERVLET_NAME)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();

        Gdx.net.sendHttpRequest(request, this);
    }

    public void addPlayerRequest(String login, String email, String password) {
        final String SERVLET_NAME = "/addplayer";

        Map parameters = new HashMap();
        parameters.put("admin_key", "key");
        parameters.put("login", login);
        parameters.put("email", email);
        parameters.put("pass", password);

        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(getUrl() + SERVLET_NAME)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();

        Gdx.net.sendHttpRequest(request, this);
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
