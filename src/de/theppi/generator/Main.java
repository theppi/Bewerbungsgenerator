package de.theppi.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import com.realobjects.pdfreactor.Configuration;
import com.realobjects.pdfreactor.PDFreactor;

import de.theppi.generator.data.Config;
import de.theppi.generator.data.HtmlMetaInfo;
import de.theppi.generator.parser.HtmlParser;
import de.theppi.generator.parser.JsonParser;

import com.realobjects.pdfreactor.Configuration.JavaScriptMode;

public class Main {
    public static void main(String[] args) throws Exception {
        Config config = JsonParser.parseConfig();
        
        String baseFolder = config.getBaseFolder() != null ? config.getBaseFolder() : ClassLoader.getSystemClassLoader().getResource(".").getPath();
        File anschreiben = new File(baseFolder, config.getCoverLetter() + ".html");
        if(!anschreiben.exists()) {
            System.err.println("Cover letter not found: " + anschreiben.getAbsolutePath());
            return;
        }
        HtmlMetaInfo info = HtmlParser.getMetaInfo(anschreiben);
        
        File targetFolder = new File(baseFolder, info.getTitle());
        if(!targetFolder.exists()) targetFolder.mkdir();
        
        PDFreactor pdfReactor = new PDFreactor();
        Configuration pdfRconfig = new Configuration();
        pdfRconfig.setJavaScriptMode(JavaScriptMode.ENABLED);
        if(config.getLic() != null) pdfRconfig.setLicenseKey(config.getLic());
        convert(config.getCoverLetter(), baseFolder, targetFolder, pdfReactor, pdfRconfig);
        convert(config.getCvFile(), baseFolder, targetFolder, pdfReactor, pdfRconfig);
    }
    
    private static void convert(String name, String baseFolder, File targetFolder, PDFreactor pdfreactor, Configuration pdfRconfig) throws Exception {
        pdfRconfig.setDocument(new File(baseFolder, name + ".html").toURI().toString());
        
        File file = new File(targetFolder, name + ".pdf");
        System.out.println("Writing to " + file.getAbsolutePath());
        OutputStream os = new FileOutputStream(file);
        pdfreactor.convert(pdfRconfig,os);
        os.close();
        
    }

}
