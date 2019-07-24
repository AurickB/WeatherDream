package com.belenus.monwebservicemeteo.utils;

import com.belenus.monwebservicemeteo.beans.ResultBean;
import com.google.gson.Gson;

public class WSUtils {
    private static final Gson gson = new Gson();
    private final static String URL = "http://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&q=";


    public static ResultBean getCityName(String name) throws Exception {
        String url = URL + name;

        String json = OkhttpUtils.sendGetOkHttpRequest(url);

        ResultBean jsonParser = gson.fromJson(json, ResultBean.class);

        // Annalyser le resultat
        if (jsonParser.getErrors() != null) {
            throw new Exception(jsonParser.getErrors().getMessage());
        } else {
            return jsonParser;
        }
    }
}
