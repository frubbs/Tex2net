package com.text2net.console.storage.couchdb;

import com.text2net.console.ConnectionElement;
import com.text2net.console.storage.IConnectionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rafa on 15/04/2016.
 */
@Component
public class CouchDbConnectionStorage implements IConnectionStorage {

    @Autowired
    CouchDbConnectionRepository couchDbConnectionRepository;

    @Override
    public void add(ConnectionElement elementA, ConnectionElement elementB, long inicioPortaria, String textoPortaria) {

        CouchDBConnectionWrapper couchDBConnectionWrapper = new CouchDBConnectionWrapper();

        couchDBConnectionWrapper.setText(textoPortaria);
        couchDBConnectionWrapper.setElementA(elementA.getName());
        couchDBConnectionWrapper.setElementB(elementB.getName());
        couchDBConnectionWrapper.setIdPortaria(inicioPortaria);

        couchDbConnectionRepository.add(couchDBConnectionWrapper);


    }
}
