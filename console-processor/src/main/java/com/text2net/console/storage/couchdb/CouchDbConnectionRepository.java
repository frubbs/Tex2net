package com.text2net.console.storage.couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

/**
 * Created by rafa on 15/04/2016.
 */
@Repository
public class CouchDbConnectionRepository extends CouchDbRepositorySupport<CouchDBConnectionWrapper> {
    @Autowired
    public CouchDbConnectionRepository(@Qualifier("connectionDatabase") CouchDbConnector db) {
        super(CouchDBConnectionWrapper.class, db);
        initStandardDesignDocument();
    }


    @GenerateView
    @Override
    public List<CouchDBConnectionWrapper> getAll() {
        ViewQuery q = createQuery("all")
                .descending(true)
                .includeDocs(true);
        return db.queryView(q, CouchDBConnectionWrapper.class);
    }


    public List<CouchDBConnectionWrapper> getAllNames() {
        ViewQuery q = createQuery("all")
                .descending(true)
                .includeDocs(true);
        return db.queryView(q, CouchDBConnectionWrapper.class);
    }


    //@View( name="allE", map = "function(doc) { if (doc.elementA == 'Dania Costa Pires.') { emit(doc._id, {LastName: doc.elementA + doc.elementB}) } }")
    public InputStream getAllAsStream() {
        ViewQuery q = createQuery("all")
               // .descending(true)
               // .includeDocs(true)
                .viewName("allE");
        return db.queryForStream(q);
    }



}
