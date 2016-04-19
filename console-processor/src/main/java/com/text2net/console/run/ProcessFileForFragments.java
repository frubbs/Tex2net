package com.text2net.console.run;

import com.text2net.console.FragmentProducer;
import com.text2net.console.Publication;
import com.text2net.console.TextAnnotator2;
import gate.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rafa on 03/04/2016.
 */
public class ProcessFileForFragments {
    public static void main(String[] args) {
        System.out.println("ProcessFileForFragments");

        if(args.length != 1)
            showUsage();

        long startTime = System.currentTimeMillis();

        String filePath = args[0];//"C:\\Users\\rafa\\Documents\\Artigos\\Rede_da_presidente\\DouDownloaded\\txt2\\04052011\\Dou-04052011-1.txt";

        //IF new File(path_to_file).length()
        getFragments(filePath);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("fim: " +  elapsedTime);
    }

    private static void showUsage() {
        System.out.println("Processa arquivos em formato texto, Baixados pelo Dou Downloader.");

    }


    public static void getFragments(String filePath) {
        try {
           // File gappFile =  new File(ProcessFileForFragments.class.getClassLoader().getResource("com/text2net/gate_inicioCandidate/Nomes_Trecho.xapp").getPath());

           // File gappFile =  new File("C:\\Users\\rafa\\Documents\\Projects\\Text2Net\\console-processor\\src\\main\\resources\\com\\text2net\\gate_inicioCandidate\\Nomes_Trecho.xapp");

            File gappFile =  new File("gate_inicioCandidate\\Nomes_Trecho.xapp");

            int publicationId = 0;
            for(Publication p : processRequestForFragments(filePath, gappFile)){

                Path logpath = Paths.get(filePath.replace(".txt","[" + publicationId++ + "].txt").replace("Dou-","\\Fragmentos\\Dou-"));
                if(!Files.exists(logpath.getParent())){
                    Files.createDirectory(logpath.getParent());
                }
                Files.write(logpath, p.getText().getBytes(), StandardOpenOption.CREATE_NEW);
            }

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro no arquivo:" + filePath);
        }

    }

    private static List<Publication> processRequestForFragments(String filePath, File gappFile)  {

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            System.out.println("Starting annotation with xapp: " + gappFile.getAbsolutePath());

            Document annotatedText = new TextAnnotator2().processString(gappFile, content);

            System.out.println("Finished Annotation. Starting connection detection");

            List<Publication> publications = new FragmentProducer().process(filePath,annotatedText);

            return publications;

            // String markedUpText = TextUpMarker.markUp(annotatedText, connections);
        }
        catch (Throwable e){
            e.printStackTrace();
            System.out.println("Erro a processar:" + filePath);
        }
        return new ArrayList<Publication>();
    }



}
