package com.text2net.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.text2net.core.api.Connection;
import com.text2net.core.gateUtil.GATEExtractor;

import gate.AnnotationSet;
import gate.Document;
import gate.Factory;
import gate.util.GateException;

public class ConnectionProducerTest {

	@Test
	public void testProcess() throws Exception{

		//repetindo o teste do anootator... fodas
		String filePath = this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath().substring(1);
		File gappFile = new File(filePath);
		String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/discursoSample/lorem.txt").getPath().substring(1);
		File file = new File(docFilePath);
		GATEExtractor.getInstance();
		Document doc = Factory.newDocument(file.toURL(), "UTF-8");
		
		Document annotatedDoc = new TextAnnotator2().processDoc(gappFile, doc);
		
		Assert.assertEquals(2186, annotatedDoc.getAnnotations().size());
		AnnotationSet todasAnnots = annotatedDoc.getAnnotations();
		List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("Inicio"));
		List<gate.Annotation> entidadeList = gate.Utils.inDocumentOrder(todasAnnots.get("EntidadeIdentificada"));
		Assert.assertEquals(9, inicioList.size());
		Assert.assertEquals(9, entidadeList.size());

		
		//agora vamos testar o conection producer
		ConnectionProducer cp = new ConnectionProducer();
		List<Connection> connectionList = cp.process(annotatedDoc);
		
		
		Assert.assertEquals(10, connectionList.size());
		
		
		
		Factory.deleteResource(annotatedDoc);

		
	}
}
