package com.text2net.core;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.text2net.core.api.AnnotatedText;

import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.Gate;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

public class TextAnnotator {

	private String encoding = "UTF-8";
	protected final Logger log = Logger.getLogger(TextAnnotator.class);
	
	
	
	public AnnotatedText processDoc(File gappFile, Document doc) throws Exception 
	{
		long startTime = System.currentTimeMillis();



		// load the saved application
		CorpusController application = (CorpusController) PersistenceManager.loadObjectFromFile(gappFile);

		// Create a Corpus to use. We recycle the same Corpus object for each
		// iteration. The string parameter to newCorpus() is simply the
		// GATE-internal name to use for the corpus. It has no particular
		// significance.
		Corpus corpus = Factory.newCorpus("DouProcessor Corpus");
		application.setCorpus(corpus);

		long arquivoStart = System.currentTimeMillis();

		
		// put the document in the corpus
		corpus.add(doc);

		// run the application
		long appExecStart = System.currentTimeMillis();
		application.execute();
		long appExecEnd = System.currentTimeMillis();
		//System.out.print("appExecute: " + (appExecEnd - appExecStart) + " ms");
		log.warn("appExecute: " + (appExecEnd - appExecStart) + " ms");

		// remove the document from the corpus again
		corpus.clear();

		long portariaStart = System.currentTimeMillis();

		//processador.process(docFile, doc, registroStrategy);

		long portariaEnd = System.currentTimeMillis();
		log.warn("Portaria: " + (portariaEnd - portariaStart) + " ms");


		log.warn("done");
		long arquivoEnd = System.currentTimeMillis();
		log.warn("Arquivo: " + (arquivoEnd - arquivoStart) + "ms");

		long stopTime = System.currentTimeMillis();
		log.warn("Fim do programa: " + (stopTime - startTime) + " ms");
		
		AnnotatedText result = new AnnotatedText();
		result.setDoc(doc);
		return result;
		
		// Release the document, as it is no longer needed
		//Factory.deleteResource(doc);

	}

	private void initGate() throws GateException {
		// Configura variaveis de ambiente para o GATE
		configureGateProps();

		// initialise GATE - this must be done before calling any GATE APIs
		Gate.init();
	}
	
	public AnnotatedText processString(File gappFile, String docString) throws Exception 
	{
		// load the document (using the specified encoding if one was given)
		//System.out.print("Processing docString ");
		initGate();
		Document doc = Factory.newDocument(docString);
		return processDoc(gappFile, doc);
	}
	
	
	
	public AnnotatedText processFile(File gappFile, File docFile) throws Exception
	{
		// load the document (using the specified encoding if one was given)
		//System.out.print("Processing document " + docFile + "...");
		initGate();
		@SuppressWarnings("deprecation")
		Document doc = Factory.newDocument(docFile.toURL(), encoding);
		return processDoc(gappFile, doc);
	}

	private void configureGateProps()
	{
		Properties props = System.getProperties();
		// props.setProperty("gate.plugins.home", ".\\plugins\\ANNIE");
		/*props.setProperty("gate.home",
				"C:\\Users\\rafa\\Documents\\GitHub\\analisador-dou\\AnalisadorDou\\lib\\GateAPI\\gate-7.1-build4485-ALL");
	*/
		
		
//essa eh a boa:
		//props.setProperty("gate.home",".\\gate");

		String home = this.getClass().getClassLoader().getResource("com/text2net/gate/gatehome").getPath();
		 
		props.setProperty("gate.home",home);
		
		
		
		// props.setProperty("gate.site.config", ".\\gate.xml");

		/*
		 * Properties props = System.getProperties(); props.setProperty("gate.plugins.home", ".\\plugins\\ANNIE");
		 * props.setProperty("gate.home", ".\\bin\\gate.jar"); props.setProperty("gate.site.config", ".\\gate.xml");
		 * 
		 * String gateHomeStr = System.getProperty(Gate.GATE_HOME_PROPERTY_NAME);
		 * 
		 * // gateHomeStr = // Thread.currentThread().getContextClassLoader().getResource ("gate/Gate.class").toString();
		 * 
		 * log.warn("#########8gfah: " + gateHomeStr);
		 */

		//
		//
		String gateHomeStr = System.getProperty(Gate.GATE_HOME_PROPERTY_NAME);

		// //gateHomeStr =
		// Thread.currentThread().getContextClassLoader().getResource("gate/Gate.class").toString();
		//
		log.warn("## Gate home: " + gateHomeStr);

	}

}
