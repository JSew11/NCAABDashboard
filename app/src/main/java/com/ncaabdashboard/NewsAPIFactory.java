package com.ncaabdashboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsAPIFactory {
    static final String TAG_SEARCH_NEWS = "SEARCH_NEWS";

    public static StringRequest getLatestNewsByUniversity(Context context, String university) {
        // TODO: add capability to load this info from api.properties file
        String api_key = "01d0758178cb4734bdd33854b2eea5ca";

        String url = "https://newsapi.org/v2/top-headlines?api-key=" + api_key +
                "&country=us" +
                "&q=" + university + " college basketball" +
                "&category=sports";
        try {
            // setup connection
            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest stringRequest = searchNameStringRequest(university, context);
            stringRequest.setTag(TAG_SEARCH_NEWS);

            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * Creates a StringRequest with the response from an api request
     * @param nameSearch
     * @return
     */
    private static StringRequest searchNameStringRequest(String nameSearch, Context context) {
        // TODO: add capability to load this info from api.properties file
        String api_key = "01d0758178cb4734bdd33854b2eea5ca";

        String url = "https://newsapi.org/v2/everything?q=" + nameSearch + " college basketball" +
                "&category=sports" +
                "&apiKey=" + api_key;

        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure
        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    @Override
                    public void onResponse(String response) {
                        Log.d("DEBUG",  "GOT RESPONSE FROM SITE");

                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
                        try {
                            JSONObject result = new JSONObject(response).getJSONObject("list");
                            int maxItems = result.getInt("end");
                            JSONArray resultList = result.getJSONArray("item");

                            // catch for the JSON parsing error
                        } catch (JSONException e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } // public void onResponse(String response)
                }, // Response.Listener<String>()
                new Response.ErrorListener() {
                    // 4th param - method onErrorResponse lays the code procedure of error return
                    // ERROR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen
                        Toast.makeText(context, "Newsapi is not responding", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
