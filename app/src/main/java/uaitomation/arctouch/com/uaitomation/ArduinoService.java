package uaitomation.arctouch.com.uaitomation;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by brunopinheiro on 1/6/15.
 */
public class ArduinoService {

    public static ArduinoService INSTANCE;
    private RequestQueue requestQueue;
    private String serverAddress;

    public static ArduinoService instance(Context context, String serverAddress) {
        if (INSTANCE == null) {
            INSTANCE = new ArduinoService(context);
        }

        INSTANCE.serverAddress = serverAddress;
        INSTANCE.requestQueue = Volley.newRequestQueue(context);
        return INSTANCE;
    }

    private ArduinoService(Context context) {

    }

    public void setAirConditionerState (boolean on, Response.Listener<String> callback) {
        // Instantiate the RequestQueue.
        String url = baseServerUrl();
        url+= on ? "?cold" : "?off";

        doRequest(url, callback);
    }

    public void setAirConditionerWarm(Response.Listener<String> callback) {
        String url = baseServerUrl();
        url+= "?warm";

        doRequest(url, callback);
    }

    public void setAirConditionerCold(Response.Listener<String> callback) {
        String url = baseServerUrl();
        url+= "?cold";

        doRequest(url, callback);
    }

    private void doRequest(String url, Response.Listener<String> callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                callback, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(this.getClass().getName(), "Command failed with error " + error.getLocalizedMessage());
            }
        });

        requestQueue.add(stringRequest);
    }

    private String baseServerUrl() {
        return "http://" + serverAddress + "/";
    }
}
