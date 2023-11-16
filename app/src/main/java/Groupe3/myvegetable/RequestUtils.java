package Groupe3.myvegetable;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import Groupe3.myvegetable.beans.ProduitBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestUtils {
    private static final String URL_API = "http://90.113.118.136:8081";
    public static List<ProduitBean> loadProduit() throws Exception {

        //requête
        String json = sendGet(URL_API+"/getAllProducts");

        //parser le résultat
        Gson gson = new Gson();


       return Arrays.asList(gson.fromJson(json, ProduitBean[].class));
    }
    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

}
