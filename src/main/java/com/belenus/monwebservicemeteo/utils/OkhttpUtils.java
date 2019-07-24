package com.belenus.monwebservicemeteo.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    public static String sendGetOkHttpRequest(String url) throws Exception {
        Log.w("tag", "url : " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build();
        //Execution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            //ATTENTION .string() ne peut être appelée qu’une seule fois.
            return response.body().string();
        }
    }
}
