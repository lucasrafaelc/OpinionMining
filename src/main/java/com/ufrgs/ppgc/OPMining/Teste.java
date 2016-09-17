/*
 * Copyright 2016 lucas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ufrgs.ppgc.OPMining;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.parameters.AspectBasedSentimentParams;
import com.aylien.textapi.responses.Aspect;
import com.aylien.textapi.responses.AspectSentence;
import com.aylien.textapi.responses.AspectsSentiment;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) throws Exception {
    TextAPIClient client = new TextAPIClient("b78f6c15", "3321d78093a52399d8a159e4c3a53fd5");
    AspectBasedSentimentParams.Builder builder = AspectBasedSentimentParams.newBuilder();
    builder.setDomain(AspectBasedSentimentParams.StandardDomain.HOTELS);
    //builder.setText("Delicious food. Disappointing service.");
    //AspectsSentiment aspectsSentiment = client.aspectBasedSentiment(builder.build());
    AspectsSentiment aspectsSentiment;
    ProcessaArquivo pa = new ProcessaArquivo();
    ArrayList<String> listaOps = pa.carregaArquivo();
    for(int i = 0; i < 100; i++){
        String opiniao = listaOps.get(i);
        builder.setText(opiniao);
        aspectsSentiment = client.aspectBasedSentiment(builder.build());
        System.out.println("=======================================================================");
        System.out.println("=======================================================================");
        System.out.println(opiniao);
        System.out.println("-----------------------------------------------------------------------");
        for (Aspect aspect: aspectsSentiment.getAspects()) {
            System.out.println(aspect);
        }
        System.out.println("-----------------------------------------------------------------------");
        for (AspectSentence sentence: aspectsSentiment.getSentences()) {
            System.out.println(sentence);        
        }
        System.out.println("=======================================================================");
        System.out.println("=======================================================================");
    }
  }
}
