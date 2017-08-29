package com.lhjl.tzzs.proxy.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageDownloader {

    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Mobile Safari/537.36";

    private static Logger logger = LoggerFactory.getLogger(ImageDownloader.class);

    private static final int TIMEOUT_SECONDS = 120;

    private static final int POOL_SIZE = 120;

    private static CloseableHttpClient httpclient;

    public static void main(String[] args) throws ClientProtocolException, IOException {

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.initApacheHttpClient();

        String result = "";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile", "13269279933"));
        params.add(new BasicNameValuePair("securitycode", "520878"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("passworda", "123456"));

        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/rest/user/umima.json");

        httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        httpRequest.setHeader("Host","chuangxinzhishu.wware.org");
        httpRequest.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpRequest.setHeader("Origin","https://chuangxinzhishu.wware.org");
        httpRequest.setHeader("Referer","https://chuangxinzhishu.wware.org");
        HttpResponse response = httpclient.execute(httpRequest);
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity he=  response.getEntity();
            result = EntityUtils.toString(he,"UTF-8");
            System.out.println(result);
        }

    }

    public void initApacheHttpClient() {
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000).build();

        // Create an HttpClient with the given custom dependencies and
        // configuration.
        httpclient = HttpClients.custom().setUserAgent(USER_AGENT).setMaxConnTotal(POOL_SIZE)
                .setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    private void destroyApacheHttpClient() {
        try {
            httpclient.close();
        } catch (IOException e) {
            logger.error("httpclient close fail", e);
        }
    }

    public void fetchContent(String imageUrl) throws ClientProtocolException, IOException {

        HttpGet httpget = new HttpGet(imageUrl);
        httpget.setHeader("Referer", "http://www.google.com");

        System.out.println("executing request " + httpget.getURI());
        CloseableHttpResponse response = httpclient.execute(httpget);

        try {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode()
                        + " imageUrl: " + imageUrl);
            }
            if (entity != null) {
                InputStream input = entity.getContent();
                OutputStream output = new FileOutputStream(new File("D:\\website\\1.gif"));
                IOUtils.copy(input, output);
                output.flush();
            }
        } finally {
            response.close();

        }

    }
}