package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.CommonHttpService;
import net.minidev.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service("commonHttpService")
public class CommonHttpServiceImpl implements CommonHttpService {


    /**
     * 登录接口
     * @param loginReqBody
     * @return
     */
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

    /**
     * 验证码接口
     * @param sendsecuritycodeReqBody
     * @return
     */
    @Override
    public String requestSendsecuritycode(SendsecuritycodeReqBody sendsecuritycodeReqBody) {
        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/sendsecuritycode.json?_ajax=true&ct=public_json");
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

    /**
     * 注册接口
     * @param zhuceReqBody
     * @return
     */
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

    @Override
    public String requestFindPwd(FindPwdReqBody findPwdReqBody) {
        return null;
    }


    /**
     * 测试登录
     * @param args
     */
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


    /**
     * 测试验证码
     * @param args
     */
//    public static void main(String[] args) {
//        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/v/auth/sendsecuritycode.json?_ajax=true&ct=public_json");
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


    /**
     * 测试注册
     * @param args
     */
//    public static void main(String[] args) {
//        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/rest/user/zhuce.json");
//        String result = "";
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("mobile","13683273656"));
//        params.add(new BasicNameValuePair("username","luo"));
//        params.add(new BasicNameValuePair("password","123456"));
//        params.add(new BasicNameValuePair("securitycode","130816"));
//        try {
//            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//            if (httpResponse.getStatusLine().getStatusCode()==200){
//                HttpEntity httpEntity = httpResponse.getEntity();
//                result = EntityUtils.toString(httpEntity);//取出应答字符串
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//                result = e.getMessage().toString();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
 //                result = e.getMessage().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//                 result = e.getMessage().toString();
//        }
//
//    }


    /**
     * 找回密码测试
     * @param args
     */
    public static void main(String[] args) {
        HttpPost httpRequest = new HttpPost("https://chuangxinzhishu.wware.org/rest/user/umima.json");
        CloseableHttpClient client = HttpClients.createDefault();

        String result = "";

//        JSONObject jsonParam = new JSONObject();
//        jsonParam.put("mobile", "15135332285");
//        jsonParam.put("securitycode", "019912");
//        jsonParam.put("password", "369369");
//        jsonParam.put("passworda", "369369");
        List <NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile", "13269279933"));
        params.add(new BasicNameValuePair("securitycode", "995316"));
        params.add(new BasicNameValuePair("password", "123456"));
        params.add(new BasicNameValuePair("passworda", "123456"));

//            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");

        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            httpRequest.setHeader("Host","chuangxinzhishu.wware.org");
            httpRequest.setHeader("Content-Type","application/x-www-form-urlencoded");
            httpRequest.setHeader("Access-Control-Allow-Origin","*");
            httpRequest.setHeader("Access-Control-Allow-Methods","POST");
            httpRequest.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
            httpRequest.setHeader("User-Agent","Mozilla/5.0 Firefox/26.0");
            HttpResponse response = new DefaultHttpClient().execute(httpRequest);
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity he=  response.getEntity();
                result = EntityUtils.toString(he,"UTF-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
