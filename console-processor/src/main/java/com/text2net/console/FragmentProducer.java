package com.text2net.console;

import gate.AnnotationSet;
import gate.Document;
import gate.util.InvalidOffsetException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 03/04/2016.
 */
public class FragmentProducer {
    public List<Publication> process(String filepath, Document annotatedText) throws InvalidOffsetException {


        List<Publication> result = new ArrayList<Publication>();


        // Listas de anotacoes detectadas pelo gate ja na ordem em que aparecem no documento
        AnnotationSet todasAnnots = annotatedText.getAnnotations();
        List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("InicioCandidate"));

        System.out.println("################ Inicios:" + inicioList.size());

        System.out.println(annotatedText.getContent().toString().length());

        long inicioPortaria = 0;
        int iniAnnotation = 0;
        long fimPortaria = 0;
        for (int i = 0; i < inicioList.size(); i++)
        {
            //gate.Annotation annIni = inicioList.get(i);
            //long inicioPortaria = annIni.getStartNode().getOffset();



            if (i == inicioList.size() - 1) { // se estivermos no ultimo inicio, o fim eh o fim e nao a proximo inicio
                fimPortaria = annotatedText.getContent().size().longValue();
                System.out.println("ULTIMO fimPortaria:" + fimPortaria +" inicioPortaria:" + inicioPortaria +" diff:" + (fimPortaria - inicioPortaria));

            } else	{
                fimPortaria = inicioList.get(i + 1).getStartNode().getOffset();
                System.out.println("fimPortaria:" + fimPortaria +" inicioPortaria:" + inicioPortaria +" diff:" + (fimPortaria - inicioPortaria));

                if (fimPortaria - inicioPortaria < 500000) //Quando chegar a 500k processa...
                    continue;
                }

            gate.Annotation annIni = inicioList.get(iniAnnotation);

            inicioPortaria = annIni.getStartNode().getOffset();




            String textoPortaria = annotatedText.getContent().getContent(inicioPortaria, fimPortaria).toString();

            System.out.println("ini:" + inicioPortaria + " fim:"+fimPortaria);
            //System.out.println("t:" + textoPortaria);
            Publication p = new Publication();
            p.setText(textoPortaria);
            p.setIniOffset(inicioPortaria);
            p.setEndOffset(fimPortaria);
            p.setSourceFile(filepath);
            result.add(p);

            iniAnnotation = i+1;
            if(iniAnnotation < inicioList.size())
                inicioPortaria = inicioList.get(iniAnnotation).getStartNode().getOffset();


        }
        System.out.println("####### FIM do fragment producer ##########");
        return result;

    }
}
