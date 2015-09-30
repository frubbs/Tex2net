package com.text2net.core;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.text2net.core.api.AnnotatedText;
import com.text2net.core.gateUtil.GATEExtractor;

import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

public class TextAnnotator2 {

	private String encoding = "UTF-8";
	
	public Document processDoc(File gappFile, Document doc) throws Exception 
	{
	
		// load the saved application
		CorpusController application = (CorpusController) PersistenceManager.loadObjectFromFile(gappFile);

		// Create a Corpus to use. We recycle the same Corpus object for each
		// iteration. The string parameter to newCorpus() is simply the
		// GATE-internal name to use for the corpus. It has no particular
		// significance.
		Corpus corpus = Factory.newCorpus("DouProcessor Corpus");
		application.setCorpus(corpus);

		// put the document in the corpus
		corpus.add(doc);

		application.execute();
	
		// remove the document from the corpus again
		corpus.clear();

			
		
		Factory.deleteResource(application);
		Factory.deleteResource(corpus);
		
		
		return doc;
		
		// Release the document, as it is no longer needed
		//Factory.deleteResource(doc);

	}

	private void initGate() throws GateException, IOException {
		GATEExtractor.getInstance();
	}
	
	public Document processString(File gappFile, String docString) throws Exception 
	{
		// load the document (using the specified encoding if one was given)
		//System.out.print("Processing docString ");
		initGate();
		Document doc = Factory.newDocument(docString);
		Document result =  processDoc(gappFile, doc);
		
		return result;
		
	}
	
	
	
	public Document processFile(File gappFile, File docFile) throws Exception
	{
		// load the document (using the specified encoding if one was given)
		//System.out.print("Processing document " + docFile + "...");
		initGate();
		@SuppressWarnings("deprecation")
		Document doc = Factory.newDocument(docFile.toURL(), encoding);
		return processDoc(gappFile, doc);
	}
}
