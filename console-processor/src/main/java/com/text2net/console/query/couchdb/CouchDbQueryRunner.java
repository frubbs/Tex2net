package com.text2net.console.query.couchdb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.text2net.console.storage.couchdb.CouchDBConnectionWrapper;
import com.text2net.console.storage.couchdb.CouchDbConnectionRepository;
import com.text2net.console.storage.couchdb.CouchDbConnectionStorage;
import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.io.output.StringBuilderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.AliasDefinition;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

/**
 * Created by rafa on 15/04/2016.
 */
@Component
public class CouchDbQueryRunner {

    @Autowired
    CouchDbConnectionRepository couchDbConnectionRepository;

    List<String> vertices = SetUniqueList.decorate(new ArrayList<String>());
    ArrayList<String> edges = new ArrayList<String>();


    public void run(){
/*
       for (CouchDBConnectionWrapper c : couchDbConnectionRepository.getAll()){
           System.out.println(c.getElementA());
       }
*/
        BufferedReader buffer = new BufferedReader(new InputStreamReader(couchDbConnectionRepository.getAllAsStream()));
        buffer.lines().forEach(this::geraConexao);
        //buffer.lines().forEach(System.out::println);

        System.out.println("taamnho:" + vertices.size());
        StringBuilder net = new StringBuilder();
        net.append("*Vertices " + vertices.size());
        vertices.stream().forEach(v -> net.append(System.lineSeparator() + (vertices.indexOf(v)+1) + " " + v));

        net.append(System.lineSeparator());
        net.append("*Edges");

        edges.stream().forEach(v -> net.append(System.lineSeparator() + v + " 1"));

        //System.out.println(net.toString());
        try {
            Files.write(Paths.get("C:\\Users\\rafa\\Documents\\Doutorado\\Redes Dou\\Teste3.net"), net.toString().getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void geraConexao(String registro) {


        String ALabel = "##A";
        String ALabelEnd = "$$A";
        String BLabel = "##B";
        String BLabelEnd = "$$B";

        System.out.println(registro);
        try {
            String elementA = "\"" +  registro.substring(registro.indexOf(ALabel) + ALabel.length(), registro.indexOf(ALabelEnd)) + "\"";

            String elementB = "\"" + registro.substring(registro.indexOf(BLabel) + BLabel.length(), registro.indexOf(BLabelEnd)) + "\"";

            vertices.add(elementA);
            vertices.add(elementB);

            edges.add((vertices.indexOf(elementA) + 1) + " " + (vertices.indexOf(elementB) + 1));
        }
        catch (Exception e){
           // e.printStackTrace();
            System.out.println("ERRRO:" + registro);
            System.out.println(registro.indexOf(ALabel));
            System.out.println(registro.indexOf(ALabelEnd));
            System.out.println(registro.indexOf(BLabel));
            System.out.println(registro.indexOf(BLabelEnd));
        }

    }



}
