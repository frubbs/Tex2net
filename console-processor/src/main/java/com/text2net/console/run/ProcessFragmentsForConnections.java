package com.text2net.console.run;


import com.text2net.console.ConnectionProducer;
import com.text2net.console.TextAnnotator2;
import com.text2net.console.storage.couchdb.CouchDbConnectionStorage;
import gate.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rafa on 03/04/2016.
 */
@Component
public class ProcessFragmentsForConnections {

    @Autowired
    CouchDbConnectionStorage couchDbConnectionStorage;

    public static void main(String[] args) {
        System.out.println("ini");

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring\\app-config.xml"});

        ProcessFragmentsForConnections main = context.getBean(ProcessFragmentsForConnections.class);


        long startTime = System.currentTimeMillis();

        String filePath = "C:\\Users\\rafa\\Documents\\Artigos\\Rede_da_presidente\\DouDownloaded\\txt2\\04052011\\Fragmentos\\Dou-04052011-1[0].txt";
        System.out.println("pegando conn:" + filePath);

        main.getConnections(Paths.get(filePath));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("fim: " +  elapsedTime);
    }

    private void getConnections(Path fpath) {
        try {
            File gappFile = new File("C:\\Users\\rafa\\Documents\\Projects\\Text2Net\\console-processor\\src\\main\\resources\\com\\text2net\\gate_brnames\\Nomes_Trecho.xapp");
            String content = new String(Files.readAllBytes(fpath), ("ISO-8859-1"));

            System.out.println("get connection: " + fpath);

         /*   if (content.length() > 1) {
                System.out.println(content);
                return;
            }
            */
            Document annotatedText = new TextAnnotator2().processString(gappFile, content);

            System.out.println("Finished Annotation. Starting connection detection");

            new ConnectionProducer().process(annotatedText,couchDbConnectionStorage );

        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
