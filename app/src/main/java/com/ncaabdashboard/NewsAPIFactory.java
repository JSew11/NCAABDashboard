package com.ncaabdashboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewsAPIFactory {
    static final String TAG_SEARCH_NEWS = "SEARCH_NEWS";

    public static JsonObjectRequest getLatestNewsByUniversity(Context context, String university) {
        try {
            // setup connection
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.start();

            JsonObjectRequest stringRequest = searchNameStringRequest(university, context);
            stringRequest.setTag(TAG_SEARCH_NEWS);

            queue.add(stringRequest);


            return stringRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Creates a StringRequest with the response from an api request
     * @param nameSearch
     * @return
     */
    private static JsonObjectRequest searchNameStringRequest(String nameSearch, Context context) {
        // TODO: add capability to load this info from api.properties file
        String api_key = "01d0758178cb4734bdd33854b2eea5ca";

        String url = "https://newsapi.org/v2/everything?" +
                "q=" + nameSearch + " basketball" +
                "&sortBy=popularity" +
                "&from=2021-11-23" +
                "&apiKey=" + api_key;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "API WORKED", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(context, "Newsapi is not responding", Toast.LENGTH_LONG).show();
                        Log.d("ERROR_API", error.toString());
                        Log.d("ERROR_API", "" + error.networkResponse.statusCode);
                        Log.d("ERROR_API", "" + error.networkResponse.headers);
                    }
                })
        {
            // add headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();

                header.put("Authorization", api_key);
                return header;
            }
        };

        return jsonObjectRequest;


        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure
//        return new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    // 3rd param - method onResponse lays the code procedure of success return
//                    // SUCCESS
//                    @Override
//                    public void onResponse(String response) {
//
//                        //TODO: in response, need to create new news story to display?
//                        Toast.makeText(context, "Got response from newsapi", Toast.LENGTH_LONG).show();
//
//                        // try/catch block for returned JSON data
//                        // see API's documentation for returned format
//                        try {
//                            JSONObject result = new JSONObject(response).getJSONObject("list");
//                            int maxItems = result.getInt("end");
//                            JSONArray resultList = result.getJSONArray("item");
//
//                            // catch for the JSON parsing error
//                        } catch (JSONException e) {
//                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    } // public void onResponse(String response)
//                }, // Response.Listener<String>()
//                new Response.ErrorListener() {
//                    // 4th param - method onErrorResponse lays the code procedure of error return
//                    // ERROR
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // display a simple message on the screen
//                        Toast.makeText(context, "Newsapi is not responding", Toast.LENGTH_LONG).show();
//                        Log.d("ERROR_API", error.toString());
//                        Log.d("ERROR_API", "" + error.networkResponse.statusCode);
//                        Log.d("ERROR_API", "" + error.networkResponse.headers);
//
//                    }
//                })
//        {
//            // method overrides for the new string request
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", api_key);
//
//                return headers;
//            }
//        };
    }
}
