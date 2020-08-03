package source;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Request {
    private String method;
    private String url;
    private LinkedHashMap headers;
    private String body;

    public Request(String method, String url){
        this.method = method;
        this.url = url;
    }

    public void setHeaders(LinkedHashMap headers) {
        this.headers = headers;
    }
    public LinkedHashMap getHeaders(){
        return this.headers;
    }
    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }

    public String getRequest() throws IOException {
        HttpClient httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpGet httpGet = new HttpGet(this.url);
        LinkedHashMap headers = this.headers;
        if(!headers.isEmpty()){
            Set set = headers.keySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                httpGet.setHeader(key,(String)headers.get(key));
            }
        }
        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            InputStream inputStream = response.getEntity().getContent();
            String result = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();
            return result;
        } else {
            System.out.println("服务器异常" + statusCode);
            return "";
        }
        //httpClient.close();
    }

    public String postRequest(String body) throws IOException {
        HttpClient httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpPost httpost = new HttpPost(this.url);
        StringEntity stringEntity = new StringEntity(body, "utf-8");
        httpost.setEntity(stringEntity);
        LinkedHashMap headers = this.headers;
        if(!headers.isEmpty()){
            Set set = headers.keySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                httpost.setHeader(key,(String)headers.get(key));
            }
        }else{
            httpost.setHeader("Content-type", "application/x-www-form-urlencoded");
        }
        CloseableHttpResponse httpResponse = null;
        HttpResponse response = httpClient.execute(httpost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            InputStream inputStream = response.getEntity().getContent();
            String result = IOUtils.toString(inputStream, "UTF-8");
            inputStream.close();
            return result;
        }
        else {
            System.out.println("服务器异常" + statusCode);
        }
        //httpClient.close();
        return "";

    }
}
