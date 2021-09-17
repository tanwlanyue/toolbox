package com.tanwlanyue.url;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tanwlanyue.url.pojo.ResponseJsonBean;

/**
 * @link {https://www.bejson.com/json2javapojo/new/} json生成java实体类
 * @author zhanglei211 on 2021/9/17.
 */
public class HttpClient {

    static boolean flag = true;
    static FileWriter fileWriter;
    static {
        File file = new File("/Users/zhanglei/Repository/toolbox/url/src/main/resources/result.md");
        try {
            fileWriter=new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        int page = 1;
        while (flag) {
            pageRequest(page++);
        }
    }

    private static void pageRequest(int page) {
        String url = "https://codetop.cc/api/discuss/articles/?tags=[]&page=" + page + "&ordering=-read_num";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseJsonBean> responseEntity = restTemplate.getForEntity(url, ResponseJsonBean.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            flag = false;
        } else {
            // TODO
            responseEntity.getBody().getResults().stream().forEach(e->{
                try {
                    fileWriter.write("------- \n");
                    fileWriter.write(e.getTitle()+" \n");
                    fileWriter.write(e.getContent()+" \n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
