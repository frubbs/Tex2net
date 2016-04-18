package com.text2net.console;

import gate.AnnotationSet;
import gate.Document;
import gate.SimpleFeatureMap;
import gate.util.InvalidOffsetException;

import java.util.*;

/**
 * Created by rafa on 03/04/2016.
 */
public class PublicationProducer {
    public List<Publication> process(String filepath, Document annotatedText) throws InvalidOffsetException {


        List<Publication> result = new ArrayList<Publication>();


        // Listas de anotacoes detectadas pelo gate ja na ordem em que aparecem no documento
        AnnotationSet todasAnnots = annotatedText.getAnnotations();
        List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("Inicio"));

        System.out.println("################ Inicios:" + inicioList.size());

        for (int i = 0; i < inicioList.size(); i++)
        {
            gate.Annotation annIni = inicioList.get(i);
            long inicioPortaria = annIni.getStartNode().getOffset();


            long fimPortaria;
            if (i == inicioList.size() - 1) { // se estivermos no ultimo inicio, o fim eh o fim e nao a proximo inicio
                fimPortaria = annotatedText.getContent().size().longValue();
            } else	{
                fimPortaria = inicioList.get(i + 1).getStartNode().getOffset();
            }

            String textoPortaria = annotatedText.getContent().getContent(inicioPortaria, fimPortaria).toString();

            System.out.println("ini:" + inicioPortaria + " fim:"+fimPortaria);
            //System.out.println("t:" + textoPortaria);
            Publication p = new Publication();
            p.setText(textoPortaria);
            p.setIniOffset(inicioPortaria);
            p.setEndOffset(fimPortaria);
            p.setSourceFile(filepath);
            result.add(p);

        }
        System.out.println("####### FIM do connection producer ##########");
        return result;

    }
}
