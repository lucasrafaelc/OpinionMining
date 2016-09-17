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
import com.aylien.textapi.parameters.ConceptsParams;
import com.aylien.textapi.parameters.LanguageParams;
import com.aylien.textapi.parameters.SentimentParams;
import com.aylien.textapi.responses.Aspect;
import com.aylien.textapi.responses.AspectSentence;
import com.aylien.textapi.responses.AspectsSentiment;
import com.aylien.textapi.responses.Concept;
import com.aylien.textapi.responses.Concepts;
import com.aylien.textapi.responses.Language;
import com.aylien.textapi.responses.Sentiment;
import com.aylien.textapi.responses.SurfaceForm;
import java.net.URL;

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) throws Exception {
    TextAPIClient client = new TextAPIClient("b78f6c15", "3321d78093a52399d8a159e4c3a53fd5");
    AspectBasedSentimentParams.Builder builder = AspectBasedSentimentParams.newBuilder();
    builder.setDomain(AspectBasedSentimentParams.StandardDomain.RESTAURANTS);
    builder.setText("Delicious food. Disappointing service.");
    AspectsSentiment aspectsSentiment = client.aspectBasedSentiment(builder.build());
    for (Aspect aspect: aspectsSentiment.getAspects()) {
        System.out.println(aspect);
    }
    for (AspectSentence sentence: aspectsSentiment.getSentences()) {
        System.out.println(sentence);
    }
  }
}
