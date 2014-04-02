package com.example.webapitest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HTTPClient {

    // Returns JSON as String
    public static String getJSONString(String URL) {
        String result = null;
        InputStream stream = null;

        DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());

        HttpGet get = new HttpGet(URL);
        get.setHeader("Content-Type", "application/json");

        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            stream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            result = builder.toString();
            System.out.println("result = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
