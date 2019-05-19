package de.theppi.generator.parser;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.theppi.generator.data.HtmlMetaInfo;

public class HtmlParser {

    public static Document parseHtml(File file) throws IOException {
        Document doc = Jsoup.parse(file, "UTF-8");
        return doc;
    }
    
    public static HtmlMetaInfo getMetaInfo(File anschreiben) throws IOException {
        HtmlMetaInfo info = new HtmlMetaInfo();
        Document doc = parseHtml(anschreiben);
        System.out.println(doc.getElementsByTag("title").text());
        
        info.setTitle(doc.getElementsByTag("title").text());
        
        return info;
    }

}
