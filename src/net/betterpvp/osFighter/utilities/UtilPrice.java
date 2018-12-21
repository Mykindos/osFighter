package net.betterpvp.osFighter.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class UtilPrice {

    private static HashMap<Integer, Integer> cache = new HashMap<>();

    private static final String PRICE_URL = "https://v51zl41bph.execute-api.us-west-2.amazonaws.com/prod?itemId=";


    public static int getPrice(int itemID) {
        if (itemID == 995) return 1;

        if (cache.containsKey(itemID)) {
            return cache.get(itemID);
        }

        int price = 0;

        try {
            URL url = new URL(PRICE_URL + itemID);
            URLConnection connect = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            price = Integer.valueOf(in.readLine());
            cache.put(itemID, price);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return price;
    }

}
