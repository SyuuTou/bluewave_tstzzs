package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.dto.SendsecuritycodeReqBody;
import com.lhjl.tzzs.proxy.dto.ZhuceReqBody;
import com.lhjl.tzzs.proxy.service.CommonHttpService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service("commonHttpService")
public class CommonHttpServiceImpl implements CommonHttpService {



    @Override
    public String requestLogin(LoginReqBody loginReqBody) {

        HttpPost httpRequst = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/login.json?ct=public_json");
        //创建HttpPost对象
        String result = "";

        List <NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loginid", loginReqBody.getUserName()));
        params.add(new BasicNameValuePair("password", loginReqBody.getPassword()));

        try {
            httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }

    @Override
    public String requestSendsecuritycode(SendsecuritycodeReqBody sendsecuritycodeReqBody) {
        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/sendsecuritycode.json");
        String result = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile",sendsecuritycodeReqBody.getSecuritycode()));
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String requestZhuce(ZhuceReqBody zhuceReqBody) {
        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/rest/user/zhuce.json?ct=public_json");
        String result = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile",zhuceReqBody.getTel()));
        params.add(new BasicNameValuePair("username",zhuceReqBody.getUserName()));
        params.add(new BasicNameValuePair("password",zhuceReqBody.getPassword()));
        params.add(new BasicNameValuePair("securitycode",zhuceReqBody.getSecuritycode()));
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpRequest.setHeader("Host","chuangxinzhishu.wware.org");
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
                System.out.println(result);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        HttpPost httpRequst = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/login.json?ct=public_json");
//        //创建HttpPost对象
//        String result = "";
//
//        List <NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("loginid", "13691024755"));
//        params.add(new BasicNameValuePair("password", "123456"));
//
//        try {
//            httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
//            if(httpResponse.getStatusLine().getStatusCode() == 200)
//            {
//                HttpEntity httpEntity = httpResponse.getEntity();
//                result = EntityUtils.toString(httpEntity);//取出应答字符串
//
//                httpResponse.getHeaders("set-cookie");
//            }
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        }
//        catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        }
//    }


//    public static void main(String[] args) {
//        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/sendsecuritycode.json");
//        String result = "";
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("mobile","15135332285"));
//        try {
//            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//            if (httpResponse.getStatusLine().getStatusCode()==200){
//                HttpEntity httpEntity = httpResponse.getEntity();
//                result = EntityUtils.toString(httpEntity);//取出应答字符串
//                System.out.println(result);
//                httpResponse.getHeaders("set-cookie");
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        }
//    }
    public static void main(String[] args) {
        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/rest/user/zhuce.json");
        String result = "";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile","13683273656"));
        params.add(new BasicNameValuePair("username","luo"));
        params.add(new BasicNameValuePair("password","123456"));
        params.add(new BasicNameValuePair("securitycode","130816"));
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
