package com.text2net.console.storage.couchdb;

import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * Created by rafa on 15/04/2016.
 */

@Getter
@Setter
public class CouchDBConnectionWrapper extends CouchDbDocument {
    String elementA;
    String elementB;
    long idPortaria;
    String text;

    /**
     * @TypeDiscriminator is used to mark properties that makes this class's documents unique in the database.
     */
    @TypeDiscriminator
    String typeDiscriminator;


    public String getTypeDiscriminator() {
        return elementA+elementB+idPortaria;
    }

}


