package com.mygdx.teleclicker.Service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.teleclicker.Entities.PlayerStats;
import com.mygdx.teleclicker.Enums.DBStatusEnum;
import com.mygdx.teleclicker.TeleClicker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Senpai on 01/08/2016.
 */
public class HttpService implements Net.HttpResponseListener {
    final String EXTERNAL_URL = "http://demo-tamburyniarz.rhcloud.com/";
    final String LOCAL_URL = "http://localhost/";

    final boolean IS_LOCAL = false;

    private DBStatusEnum status = DBStatusEnum.NOT_CONNECTED;
    private String responsStr;

    public void loadStatsRequest(String id) {
        status = DBStatusEnum.CONNECTING;
        final String SERVLET_NAME = "/loadstats";

        Map parameters = new HashMap();
        parameters.put("admin_key", TeleClicker.KEY);
        parameters.put("id", id);

        postRequest(SERVLET_NAME, parameters);
    }

    public void saveStatsRequest(PlayerStats playerStats) {
        status = DBStatusEnum.CONNECTING;
        final String SERVLET_NAME = "/savestats";

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        Map parameters = new HashMap();
        parameters.put("admin_key", TeleClicker.KEY);
        parameters.put("player_stats", json.toJson(playerStats));


        postRequest(SERVLET_NAME, parameters);
    }

    public void loginRequest(String login, String password) {
        status = DBStatusEnum.CONNECTING;
        final String SERVLET_NAME = "/login";

        Map parameters = new HashMap();
        parameters.put("login", login);
        parameters.put("pass", password);

        postRequest(SERVLET_NAME, parameters);
    }

    public void addPlayerRequest(String login, String email, String password) {
        status = DBStatusEnum.CONNECTING;
        final String SERVLET_NAME = "/addplayer";

        Map parameters = new HashMap();
        parameters.put("admin_key", "key");
        parameters.put("login", login);
        parameters.put("email", email);
        parameters.put("pass", password);

        postRequest(SERVLET_NAME, parameters);
    }

    private void postRequest(final String SERVLET_NAME, Map parameters) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest request = requestBuilder.newRequest()
                .method(Net.HttpMethods.POST)
                .url(getUrl() + SERVLET_NAME)
                .content(HttpParametersUtils.convertHttpParameters(parameters))
                .build();
        request.setTimeOut(1000);
        Gdx.net.sendHttpRequest(request, this);
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        status = DBStatusEnum.SUCCES;
        responsStr = new String(httpResponse.getResult());
    }

    @Override
    public void failed(Throwable t) {
        status = DBStatusEnum.FAILED;
    }

    @Override
    public void cancelled() {
        status = DBStatusEnum.CANCELLED;
    }

    public String getResponsStr() {
        return responsStr;
    }

    private String getUrl() {
        if (IS_LOCAL)
            return LOCAL_URL;
        else
            return EXTERNAL_URL;
    }

    public DBStatusEnum getStatus() {
        return status;
    }
}
