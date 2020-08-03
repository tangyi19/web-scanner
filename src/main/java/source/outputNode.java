package source;

public class outputNode{
    private int id;
    private String vulName;
    private String vulType;
    private String url;
    private int status;

    public void setId(int id){
        this.id = id;
    }
    public void setVulName(String vulName){
        this.vulName = vulName;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setVulType(String vulType) { this.vulType = vulType; }
    public void setStatus(int status) { this.status = status; }

    public int getId(){
        return this.id;
    }
    public String getVulName(){
        return this.vulName;
    }
    public String getUrl(){
        return this.url;
    }
    public String getVulType() { return this.vulType; }
    public int getStatus() { return this.status; }
}
