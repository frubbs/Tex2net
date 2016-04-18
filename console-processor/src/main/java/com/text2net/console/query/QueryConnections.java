package com.text2net.console.query;




import com.text2net.console.query.couchdb.CouchDbQueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by rafa on 05/04/2016.
 */
@Component
public class QueryConnections {

    @Autowired
    CouchDbQueryRunner couchDbQueryRunner;

    public static void main(String[] args) {

        System.out.println("Iniciando QueryConnections");

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring\\app-config.xml"});

        QueryConnections main = context.getBean(QueryConnections.class);

        main.couchDbQueryRunner.run();


        System.out.println("Epa");
    }
}
