package com.dlb.utils;

import com.dlb.pojo.elk.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ELKUtils {
    public List<Crawler> getData(String url) throws Exception {
        ArrayList<Crawler> list = new ArrayList<>();
        //前提：联网 ， AJAX 不能获取需要模拟浏览器
        //获取请求
        if ("".equals(url)){
            url="https://zhutix.com/tag/win11-zhuti/";
        }
        //解析网页 , 返回的是前端页面 url不支持中文
        Document document = Jsoup.parse(new URL(url), 3000);
        Element elementById = document.getElementById("post-list");
        Elements li = elementById.getElementsByTag("li");
        for (Element el : li) {
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String name = el.getElementsByClass("post-info").eq(0).text();
            if (!"".equals(img)&&!"".equals(name)){
                list.add(new Crawler(img,name));
                System.out.println(img);
                System.out.println("name:"+name);
            }
        }
        return list;
    }
}
