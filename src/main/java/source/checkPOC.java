package source;

import org.nutz.el.El;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;

import java.io.IOException;
import java.util.*;

public class checkPOC {
    private String fileName;
    private String outURL;
    private String baseURL;
    private int status;
    private String type;
    private String vulName;
    private parseYML yml;

    public checkPOC(String baseURL, String fileName){
        this.baseURL = baseURL;
        this.fileName = fileName;
        this.yml = new parseYML(fileName);
    }

    public void check(String baseURL) throws IOException{
        String fileName = this.fileName;
        parseYML yml = this.yml;
        List tasks = yml.getTask();
        this.vulName = yml.getName();


        if(tasks.size()>1){
            List diff = new ArrayList();
            String turl = "";
            for(int i = 0;i < tasks.size(); i ++){
                rules tmp = (rules) tasks.get(i);
                String path = tmp.getPath();
                String expression = tmp.getExpression();
                String url = baseURL+path;
                turl = url;
                Encoder e = new Encoder(url);
                url = e.urlEncode();
                Request request = new Request(tmp.getMethod(), url);
                if(!tmp.getHeaders().isEmpty()){
                    request.setHeaders(tmp.getHeaders());
                }
                if(tmp.getMethod().contains("GET")){
                    String result = request.getRequest();
                    El el = new El();
                    Context context = Lang.context();
                    context.set("result", result);
                    context.set("expression", expression);
                    diff.add(el.eval(context, expression));
                }
                if(tmp.getMethod().contains("POST")){
                    String result = request.postRequest(tmp.getBody());
                    El el = new El();
                    Context context = Lang.context();
                    context.set("result", result);
                    context.set("expression", expression);
                    diff.add(el.eval(context, expression));
                }
            }
            if(tasks.size() == 2){
                if(!diff.get(0).equals(diff.get(1))){
                    this.type = yml.getType();
                    this.outURL = turl;
                    this.status = 1;
                }
                else if(Boolean.parseBoolean(diff.get(0).toString()) && Boolean.parseBoolean(diff.get(1).toString())){
                    this.type = yml.getType();
                    this.outURL = turl;
                    this.status = 1;
                }
            }
            else if(tasks.size() == 3){
                if(!diff.get(1).equals(diff.get(2))){
                    this.type = yml.getType();
                    this.outURL = turl;
                    this.status = 1;
                }
                else if(Boolean.parseBoolean(diff.get(1).toString()) && Boolean.parseBoolean(diff.get(2).toString())){
                    this.type = yml.getType();
                    this.outURL = turl;
                    this.status = 1;
                }
            }
        }
        if(tasks.size() == 1){
            rules tmp = (rules) tasks.get(0);
            String path = tmp.getPath();
            String expression = tmp.getExpression();
            String url = baseURL+path;
            String result = "";
            Encoder e = new Encoder(url);
            url = e.urlEncode();
            Request request = new Request(tmp.getMethod(), url);
            if(!tmp.getHeaders().isEmpty()){
                request.setHeaders(tmp.getHeaders());
            }
            if(tmp.getMethod().contains("GET")){
                result = request.getRequest();
            }

            if(tmp.getMethod().contains("POST")){
                result = request.postRequest(tmp.getBody());
            }
            El el = new El();
            Context context = Lang.context();
            context.set("result", result);
            context.set("expression", expression);
            if(Boolean.parseBoolean(el.eval(context, expression).toString())){
                this.outURL = url;
                this.type = yml.getType();
                this.status = 1;
            }
        }
    }

    public outputNode status(){
        outputNode o = new outputNode();
        o.setVulName(this.vulName);
        o.setVulType(this.type);
        o.setUrl(this.outURL);
        o.setStatus(this.status);
        return o;
    }


}
