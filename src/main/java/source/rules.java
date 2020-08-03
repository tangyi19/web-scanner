package source;

import java.util.LinkedHashMap;

public class rules{
    private String method;
    private String path;
    private LinkedHashMap headers;
    private String body;
    private String expression;

    public String getMethod(){
        return this.method;
    }
    public String getPath(){
        return this.path;
    }
    public String getBody(){ return this.body; }
    public String getExpression(){
        return this.expression;
    }
    public LinkedHashMap getHeaders(){
        return this.headers;
    }

    public void setMethod(String method){
        this.method = method;
    }
    public void setPath(String path){
        this.path = path;
    }
    public void setHeaders(LinkedHashMap headers){
        this.headers = headers;
    }
    public void setBody(String body){
        this.body = body;
    }
    public void setExpression(String expression){
        this.expression = expression;
    }
}