package source;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class parseYML {
    private String name;
    private List task;
    private String type;

    public parseYML(String fileName){
        try {
            Yaml yaml = new Yaml();
            URL url = this.getClass().getClassLoader().getResource(fileName);
            if (url != null) {
                Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
                this.name = (String) map.get("name");
                this.type = (String) map.get("type");
                ArrayList rule = (ArrayList) map.get("rules");
                List<rules> tmp = new ArrayList<rules>();
                for(int i = 0;i < rule.size(); i ++){
                    LinkedHashMap node = (LinkedHashMap) rule.get(i);
                    rules r = new rules();
                    r.setMethod((String)node.get("method"));
                    r.setPath((String)node.get("path"));
                    r.setExpression((String)node.get("expression"));
                    if(node.containsKey("headers")){
                        r.setHeaders((LinkedHashMap)node.get("headers"));
                    }
                    if(node.containsKey("body")){
                        r.setBody((String)node.get("body"));
                    }
                    tmp.add(r);
                }
                this.task = tmp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return this.name;
    }

    public List getTask(){
        return this.task;
    }

    public String getType() { return this.type; }

}
