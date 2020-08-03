package source;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Manage {
    public List crawl(String url){
        Crawler p = new Crawler();
        p.setRegex(url+".*");
        Spider.create(p)
                .addUrl(url)
                .thread(5)
                .run();
        return p.getLinks();
    }

    public List scan(String url) throws IOException{
        List vulns = new ArrayList();
        String path = "src/main/resources/pocs";
        File file = new File(path);
        File[] fs = file.listFiles();
        for(File f:fs){
            if(!f.isDirectory()){
                checkPOC check = new checkPOC(url, "pocs/"+f.getName());
                System.out.println(f.getName());
                check.check(url);
                outputNode o = check.status();
                vulns.add(o);
            }
        }
        return vulns;
    }


}
