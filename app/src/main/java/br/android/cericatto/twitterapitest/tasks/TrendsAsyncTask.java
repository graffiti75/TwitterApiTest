package br.android.cericatto.twitterapitest.tasks;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import br.android.cericatto.twitterapitest.global.Globals;
import retrofit.RequestInterceptor;

/**
 * TrendsAsyncTask.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 28, 2016
 */
public class TrendsAsyncTask extends AsyncTask<Object, Void, RequestInterceptor> {

    //--------------------------------------------------
    // Async Task
    //--------------------------------------------------

    @Override
    protected RequestInterceptor doInBackground(Object... params) {
        String authentication = getAuthentication();
        RequestInterceptor request = getHeaders(authentication);
        return request;
    }

    //--------------------------------------------------
    // Authentication Methods
    //--------------------------------------------------

    private String getAuthentication() {
        HttpURLConnection httpConnection = null;
        StringBuilder response = null;
        try {
            URL url = new URL(Globals.URL_AUTHENTICATION);
            httpConnection = setHttpConnection(url);

            String accessCredential = Globals.TWITTER_KEY + ":" + Globals.TWITTER_SECRET;
            String authorization = Globals.PREFIX + Base64.encodeToString(accessCredential.getBytes(), Base64.NO_WRAP);

            httpConnection.addRequestProperty(Globals.AUTHORIZATION, authorization);
            httpConnection.setRequestProperty(Globals.CONTENT_TYPE, Globals.APPLICATION_WWW_FORM);
            httpConnection.connect();

            setOutputStream(httpConnection);
//            int statusCode = httpConnection.getResponseCode();
//            String reason = httpConnection.getResponseMessage();
            response = getHttpResponse(httpConnection);
            Log.d(Globals.TAG, "POST response code: " + String.valueOf(httpConnection.getResponseCode()));
            Log.d(Globals.TAG, "JSON response: " + response.toString());
        } catch (Exception e) {
            Log.e(Globals.TAG, "POST error: " + Log.getStackTraceString(e));
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return response.toString();
    }

    private HttpURLConnection setHttpConnection(URL url) {
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod(Globals.METHOD_POST);
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpConnection;
    }

    private StringBuilder getHttpResponse(HttpURLConnection httpConnection) {
        BufferedReader bufferedReader;
        StringBuilder response = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String line;
            response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void setOutputStream(HttpURLConnection httpConnection) {
        OutputStream outputStream;
        try {
            outputStream = httpConnection.getOutputStream();
            outputStream.write(Globals.CLIENT_CREDENTIALS.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------
    // Request Interceptor Methods
    //--------------------------------------------------

    private RequestInterceptor getHeaders(String authorization) {
        HttpURLConnection httpConnection;
        String token = "";
        URL url;
        try {
            url = new URL(Globals.URL_TRENDING);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod(Globals.METHOD_GET);
            JSONObject jsonObjectDocument = new JSONObject(authorization);
            token = jsonObjectDocument.getString(Globals.TOKEN_TYPE) + " "
                + jsonObjectDocument.getString(Globals.ACCESS_TOKEN);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getRequestInterceptor(token);
    }

    private RequestInterceptor getRequestInterceptor(final String token) {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader(Globals.AUTHORIZATION, token);
                request.addHeader(Globals.CONTENT_TYPE, Globals.APPLICATION_JSON);
            }
        };
        return requestInterceptor;
    }
}