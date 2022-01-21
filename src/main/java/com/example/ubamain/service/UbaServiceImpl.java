package com.example.ubamain.service;

import com.example.ubamain.dto.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UbaServiceImpl {
    String BASE_URL = "";
    Gson gson = new Gson();


    public DataResponse fetchData() throws IOException {

        //fetch data from third party API. use endpoint as value of BASE_URL above
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get =  new HttpGet(BASE_URL + "getBalance");
        CloseableHttpResponse response;
        DataResponse dataResponse = new DataResponse();
        response = client.execute(get);
        int data = response.getStatusLine().getStatusCode();
        String responseString = EntityUtils.toString(response.getEntity());
        dataResponse = new ObjectMapper().readValue(responseString, DataResponse.class);
        return dataResponse;
    }
}
