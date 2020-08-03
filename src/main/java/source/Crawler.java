package source;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class Crawler implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String regex;
    private List links;

    @Override
    public void process(Page page) {
        this.links = page.getHtml().links().regex(regex).all();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setRegex(String regex){
        this.regex = regex;
    }

    public List getLinks(){
        return this.links;
    }

}