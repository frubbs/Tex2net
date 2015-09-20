package com.text2net.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;
import com.text2net.core.api.ConnectionElement;
import com.text2net.core.api.Entidade;

import gate.AnnotationSet;
import gate.SimpleFeatureMap;
import gate.util.InvalidOffsetException;

public class ConnectionProducer {

	protected final Logger log = Logger.getLogger(ConnectionProducer.class);
	
	public List<Connection> process(AnnotatedText annotatedText) {
		
		List<Connection> result = new ArrayList<Connection>();
		
		
		// Listas de anotacoes detectadas pelo gate ja na ordem em que aparecem no documento
		AnnotationSet todasAnnots = annotatedText.getDoc().getAnnotations();
		List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("Inicio"));
		List<gate.Annotation> entidadeList = gate.Utils.inDocumentOrder(todasAnnots.get("EntidadeIdentificada"));
		
		for (int i = 0; i < inicioList.size(); i++)
		{
			gate.Annotation annIni = inicioList.get(i);
			long inicioPortaria = annIni.getStartNode().getOffset();

			SimpleFeatureMap fMap = annIni.getFeatures();

			long fimPortaria;
			if (i == inicioList.size() - 1) { // se estivermos no ultimo inicio, o fim eh o fim e nao a proximo inicio
				fimPortaria = annotatedText.getDoc().getContent().size().longValue();
			} else	{
				fimPortaria = inicioList.get(i + 1).getStartNode().getOffset();
			}

			// se chegamos aqui temos uma portaria identificada. vamos
			// anotar as entidades nela presentes.

			log.warn("Entidades no arquivo: " + entidadeList.size());

			List<gate.Annotation> entidadesEncontradasGate = new ArrayList<gate.Annotation>();

			// List<Entidade> entidadesEncontradasNome = new ArrayList<Entidade>();
			HashMap<String, Entidade> entidadesEncontradasNome = new HashMap<String, Entidade>();
			// List<Entidade> entidadesEncontradasOrgao = new ArrayList<Entidade>();
			//HashMap<String, Entidade> entidadesEncontradasOrgao = new HashMap<String, Entidade>();

			


			for (int j = 0; j < entidadeList.size(); j++)
			{
				System.out.print('.');

				gate.Annotation annEnt = entidadeList.get(j);

				long inicioEntidade = annEnt.getStartNode().getOffset();

				if (inicioEntidade >= inicioPortaria)
				{
					if (inicioEntidade < fimPortaria) // Se esta entre o inicio e o final, pertence a portaria
					{
						long fimEntidade = annEnt.getEndNode().getOffset();

						
						String entidadeText;
						try {
								entidadeText = annotatedText.getDoc().getContent().getContent(inicioEntidade, fimEntidade).toString();
						} catch (InvalidOffsetException e) {
							e.printStackTrace();
							continue;
						}
						
						SimpleFeatureMap featureMap = annEnt.getFeatures();

						String particao = featureMap.get("Particao") != null ? featureMap.get("Particao").toString() : "";

						// Se houver sinonimo, usa
						entidadeText = featureMap.get("Sinonimo") != null ? featureMap.get("Sinonimo").toString()
								: entidadeText;

						Entidade entidade = new Entidade(entidadeText, null, particao, inicioEntidade, fimEntidade,
								featureMap.get("kind").toString());

						//if (entidade.tipoEntidade.equals("Orgao"))
						//	entidadesEncontradasOrgao.put(entidade.entidade, entidade); // entidadesEncontradasOrgao.add(entidade);
					//	else
						entidadesEncontradasNome.put(entidade.entidade, entidade);// .add(entidade);

						// adiciona para remover da lista depois. se achou nessa portaria, nao estara em nenhuma outra
						entidadesEncontradasGate.add(annEnt);

					}// TODO se falhar aqui ja pode sair fora. a lista esta
						// ordenada.
					else
					{
						// log.warn("saind pois inicioEntidade (" + inicioEntidade + ") > fimportaria (" +
						// fimPortaria
						// + ")");
						break;
					}
				}
				/*
				 * else { log.warn("saind pois inicioEntidade (" + inicioEntidade + ") < inicioportaria (" + fimPortaria + ")"
				 * ); break; }
				 */
			}
			Entidade[] entidadesEncontradas = entidadesEncontradasNome.values().toArray(new Entidade[0]);
			
			int countProcessed = 0;
			for (int k = 0; k < entidadesEncontradas.length; k++)
			{
				for (int l = k + 1; l < entidadesEncontradas.length; l++)
				{

					Entidade entidadeA = entidadesEncontradas[k];
					Entidade entidadeB = entidadesEncontradas[l];

					ConnectionElement elementA = new ConnectionElement(entidadeA.entidade, entidadeA.inicioEntidade, entidadeA.fimEntidade);
					ConnectionElement elementB = new ConnectionElement(entidadeB.entidade, entidadeB.inicioEntidade, entidadeB.fimEntidade);
					
					result.add(new Connection(elementA, elementB, entidadeA.inicioEntidade - entidadeB.inicioEntidade, inicioPortaria));
					
					
					if ((result.size() % 100) == 0)
						log.warn("Processadas ligacoes[" + result.size() + "]");

				}
			}
		
		}
		
		return result;
		
	}
}
