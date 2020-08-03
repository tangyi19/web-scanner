package source;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Output {
    private List<outputNode> vList;

    public Output(List<outputNode> vList){
        this.vList = vList;
    }

    public void run(String fileName){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("vList",this.vList);
        String DEST_PATH = "src/main/resources/demo";//目标路径
        genFileWithTemplate("src/main/resources/templates",DEST_PATH,"output-html.ftl",fileName,dataMap);
    }

    private static void genFileWithTemplate(String templateDir,String destDir,String templateFileName,String destFileName,Map<String,Object> dataMap)
    {
        //创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            //设置模版路径
            configuration.setDirectoryForTemplateLoading(new File(templateDir));
            //加载模版文件
            Template template = configuration.getTemplate(templateFileName);
            //生成数据
            File docFile = new File(destDir + "\\" + destFileName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            template.process(dataMap, out);
            System.out.println(destFileName+" 模板文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
