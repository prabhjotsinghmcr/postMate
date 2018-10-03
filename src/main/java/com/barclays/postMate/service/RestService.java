package com.barclays.postMate.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestService {

    private static RestService restService = null;

    private RestService(){}

    public static RestService getInstance(){
        if (null == restService){
            restService = new RestService();
        }
        return restService;
    }

    public String processGetRequest(String url){

        HttpResponse httpResponse = null;
        String response = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type","application/json");
            httpResponse = httpClient.execute(httpGet);
//            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
            String contentEncoding = null!=httpResponse.getEntity().getContentEncoding() ? httpResponse.getEntity().getContentEncoding().toString() : null;
            response = EntityUtils.toString(httpResponse.getEntity(),contentEncoding);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String postRequest(String url, String body){
        HttpResponse httpResponse = null;
        String response = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(body));
            httpPost.addHeader("Content-Type","application/json");
            httpResponse = httpClient.execute(httpPost);
//            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
//            String contentEncoding = null!=httpResponse.getEntity().getContentEncoding() ? httpResponse.getEntity().getContentEncoding().toString() : null;
//            response = EntityUtils.toString(httpResponse.getEntity(),contentEncoding);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse.getStatusLine().toString();
    }

    public String deleteRequest(String url){
        HttpResponse httpResponse = null;
        String response = null;
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpDelete httpDelete = new HttpDelete(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
