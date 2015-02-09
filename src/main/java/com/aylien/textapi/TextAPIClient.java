/**
 * Copyright 2015 Aylien, Inc. All Rights Reserved.
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

package com.aylien.textapi;

import com.aylien.textapi.parameters.*;
import com.aylien.textapi.responses.*;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.*;

public class TextAPIClient {

    private String applicationId;

    private String applicationKey;

    private boolean useHttps;

    private HttpSender httpSender;

    private String apiHostAndPath;

    private RateLimits rateLimits;

    /**
     * Constructs a Text API Client.
     *
     * @param applicationId Your application ID
     * @param applicationKey Your application key
     */
    public TextAPIClient(String applicationId, String applicationKey) {
        this(applicationId, applicationKey, true);
    }

    /**
     * Constructs a Text API Client.
     *
     * @param applicationId Your application ID
     * @param applicationKey Your application key
     * @param useHttps Whether to use HTTPS for web service calls
     */
    public TextAPIClient(String applicationId, String applicationKey, boolean useHttps) {
        if (applicationId == null || applicationId.isEmpty() ||
                applicationKey == null || applicationKey.isEmpty())
        {
            throw new IllegalArgumentException("Invalid Application ID or Application Key");
        }
        this.applicationId = applicationId;
        this.applicationKey = applicationKey;
        this.useHttps = useHttps;
        this.httpSender = new HttpSender();
        this.apiHostAndPath = "api.aylien.com/api/v1";
        this.rateLimits = new RateLimits();
    }

    public void setApiHostAndPath(String apiHostAndPath) {
        this.apiHostAndPath = apiHostAndPath;
    }

    /**
     * Extracts the main body of article, including embedded media such as
     * images & videos from a URL and removes all the surrounding clutter.
     *
     * @param extractParams extract parameters
     * @return Article
     */
    public Article extract(ExtractParams extractParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (extractParams.getHtml() != null) {
            parameters.put("html", extractParams.getHtml());
        } else if (extractParams.getUrl() != null) {
            parameters.put("url", extractParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide html or url");
        }

        if (extractParams.getBestImage()) {
            parameters.put("best_image", "true");
        } else {
            parameters.put("best_image", "false");
        }

        Article article;
        try {
            String response = this.doHttpRequest("extract", parameters);
            JAXBContext jc = JAXBContext.newInstance(Article.class);
            Unmarshaller u = jc.createUnmarshaller();

            article = (Article) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return article;
    }

    /**
     * Classifies a body of text according to IPTC NewsCode standard.
     *
     * @param classifyParams classify parameters
     * @return Classifications
     */
    public Classifications classify(ClassifyParams classifyParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (classifyParams.getText() != null) {
            parameters.put("text", classifyParams.getText());
        } else if (classifyParams.getUrl() != null) {
            parameters.put("url", classifyParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide text or url");
        }

        if (classifyParams.getLanguage() != null) {
            parameters.put("language", classifyParams.getLanguage());
        }

        Classifications classifications;
        try {
            String response = this.doHttpRequest("classify", parameters);
            JAXBContext jc = JAXBContext.newInstance(Classifications.class);
            Unmarshaller u = jc.createUnmarshaller();

            classifications = (Classifications) u.unmarshal(new StringReader((response)));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return classifications;
    }

    /**
     * Extracts named entities mentioned in a document, disambiguates and
     * cross link them to DBPedia and Linked Data entities, along with their
     * semantic types (including DBPedia and schema.org).
     *
     * @param conceptsParams concepts parameters
     * @return Concepts
     */
    public Concepts concepts(ConceptsParams conceptsParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (conceptsParams.getText() != null) {
            parameters.put("text", conceptsParams.getText());
        } else if (conceptsParams.getUrl() != null) {
            parameters.put("url", conceptsParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide text or url");
        }

        if (conceptsParams.getLanguage() != null) {
            parameters.put("language", conceptsParams.getLanguage());
        }

        Concepts concepts;
        try {
            String response = this.doHttpRequest("concepts", parameters);
            JAXBContext jc = JAXBContext.newInstance(Concepts.class);
            Unmarshaller u = jc.createUnmarshaller();

            concepts = (Concepts) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return concepts;
    }

    /**
     * Extracts named entities (people, organizations and locations) and values
     * (URLs, emails, telephone numbers, currency amounts and percentages)
     * mentioned in a bod of text.
     *
     * @param entitiesParams entities parameters
     * @return Entities
     */
    public Entities entities(EntitiesParams entitiesParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (entitiesParams.getText() != null) {
            parameters.put("text", entitiesParams.getText());
        } else if (entitiesParams.getUrl() != null) {
            parameters.put("url", entitiesParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide html or url");
        }

        Entities entities;
        try {
            String response = this.doHttpRequest("entities", parameters);
            JAXBContext jc = JAXBContext.newInstance(Entities.class);
            Unmarshaller u = jc.createUnmarshaller();

            entities = (Entities) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return entities;
    }

    /**
     * Suggests hashtags describing the document.
     *
     * @param hashTagsParams hashtags parameters
     * @return HashTags
     */
    public HashTags hashtags(HashTagsParams hashTagsParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();

        if (hashTagsParams.getText() != null) {
            parameters.put("text", hashTagsParams.getText());
        } else if (hashTagsParams.getUrl() != null) {
            parameters.put("url", hashTagsParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide text or url");
        }

        if (hashTagsParams.getLanguage() != null) {
            parameters.put("language", hashTagsParams.getLanguage());
        }

        HashTags hashTags;
        try {
            String response = this.doHttpRequest("hashtags", parameters);
            JAXBContext jc = JAXBContext.newInstance(HashTags.class);
            Unmarshaller u = jc.createUnmarshaller();

            hashTags = (HashTags) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return hashTags;
    }

    /**
     * Detects the main language of a document is written in.
     *
     * @param languageParams language parameters
     * @return Language
     */
    public Language language(LanguageParams languageParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (languageParams.getText() != null) {
            parameters.put("text", languageParams.getText());
        } else if (languageParams.getUrl() != null) {
            parameters.put("url", languageParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide text or url");
        }

        Language language;
        try {
            String response = this.doHttpRequest("language", parameters);
            JAXBContext jc = JAXBContext.newInstance(Language.class);
            Unmarshaller u = jc.createUnmarshaller();

            language = (Language) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return language;
    }

    /**
     * Returns phrases related to the provided unigram or bigram.
     *
     * @param relatedParams related parameters
     * @return Related
     */
    public Related related(RelatedParams relatedParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (relatedParams.getPhrase() != null) {
            parameters.put("phrase", relatedParams.getPhrase());
        } else {
            throw new IllegalArgumentException("You must provide a phrase");
        }

        if (relatedParams.getCount() > 0) {
            parameters.put("count", Integer.toString(relatedParams.getCount()));
        }

        Related related;
        try {
            String response = this.doHttpRequest("related", parameters);
            JAXBContext jc = JAXBContext.newInstance(Related.class);
            Unmarshaller u = jc.createUnmarshaller();

            related = (Related) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return related;
    }

    /**
     * Detects sentiment of a body of text in terms of polarity
     * ("positive" or "negative") and subjectivity
     * ("subjective" or "objective").
     *
     * @param sentimentParams sentiment parameters
     * @return Sentiment.
     */
    public Sentiment sentiment(SentimentParams sentimentParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (sentimentParams.getText() != null) {
            parameters.put("text", sentimentParams.getText());
        } else if (sentimentParams.getUrl() != null) {
            parameters.put("url", sentimentParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide url or text");
        }

        if (sentimentParams.getMode() != null) {
            parameters.put("mode", sentimentParams.getMode());
        }

        Sentiment sentiment;
        try {
            String response = this.doHttpRequest("sentiment", parameters);

            JAXBContext jc = JAXBContext.newInstance(Sentiment.class);
            Unmarshaller u = jc.createUnmarshaller();

            sentiment = (Sentiment) u.unmarshal(new StringReader(response));
        } catch(Exception e) {
            throw new TextAPIException(e);
        }

        return sentiment;
    }

    /**
     * Summarizes an article into a few key sentences.
     *
     * @param summarizeParams summarize params
     * @return Summarize
     */
    public Summarize summarize(SummarizeParams summarizeParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (summarizeParams.getTitle() != null && summarizeParams.getText() != null) {
            parameters.put("title", summarizeParams.getTitle());
            parameters.put("text", summarizeParams.getText());
        } else if (summarizeParams.getUrl() != null) {
            parameters.put("url", summarizeParams.getUrl().toString());
        } else {
            throw new IllegalArgumentException("You must either provide url or a pair of text and title");
        }

        if (summarizeParams.getMode() != null) {
            parameters.put("mode", summarizeParams.getMode());
        }

        if (summarizeParams.getPercentageOfSentences() > 0) {
            parameters.put("sentences_percentage", Integer.toString(summarizeParams.getPercentageOfSentences()));
        } else if (summarizeParams.getNumberOfSentences() > 0) {
            parameters.put("sentences_number", Integer.toString(summarizeParams.getNumberOfSentences()));
        }

        Summarize summarize;
        try {
            String response = this.doHttpRequest("summarize", parameters);
            JAXBContext jc = JAXBContext.newInstance(Summarize.class);
            Unmarshaller u = jc.createUnmarshaller();

            summarize = (Summarize) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return summarize;
    }

    /**
     * Extracts microformats
     * @param microformatsParams microformats params
     * @return Microformats
     */
    public Microformats microformats(MicroformatsParams microformatsParams) throws TextAPIException {
        Map<String, String> parameters = new HashMap<String, String>();
        if (microformatsParams.getUrl() == null) {
            throw new IllegalArgumentException("You must provide a url");
        }

        parameters.put("url", microformatsParams.getUrl().toString());

        Microformats microformats;
        try {
            String response = this.doHttpRequest("microformats", parameters);
            JAXBContext jc = JAXBContext.newInstance(Microformats.class);
            Unmarshaller u = jc.createUnmarshaller();

            microformats = (Microformats) u.unmarshal(new StringReader(response));
        } catch (Exception e) {
            throw new TextAPIException(e);
        }

        return microformats;
    }

    private String doHttpRequest(String endpoint, Map<String, String> parameters) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "text/xml");
        headers.put("X-AYLIEN-TextAPI-Application-ID", this.applicationId);
        headers.put("X-AYLIEN-TextAPI-Application-Key", this.applicationKey);

        String url = String.format("%s://%s/%s",
                (this.useHttps ? "https" : "http"),
                this.apiHostAndPath,
                endpoint
        );

        String response = this.httpSender.post(url, parameters, headers);
        Map<String, List<String>> responseHeaders = this.httpSender.getLastResponseHeaders();
        for (Map.Entry<String, List<String>> h: responseHeaders.entrySet()) {
            if (h.getKey() != null && h.getKey().startsWith("X-RateLimit-")) {
                String key = h.getKey();
                int value = Integer.parseInt(h.getValue().get(0));
                if (key.endsWith("-Limit")) this.rateLimits.setLimit(value);
                if (key.endsWith("-Remaining")) this.rateLimits.setRemaining(value);
                if (key.endsWith("-Reset")) this.rateLimits.setReset(value);
            }
        }

        return response;
    }

    public class RateLimits {
        private int limit;
        private int remaining;
        private int reset;

        public RateLimits() {
            new RateLimits(0, 0, 0);
        }

        public RateLimits(int limit, int remaining, int reset) {
            this.limit = limit;
            this.remaining = remaining;
            this.reset = reset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getRemaining() {
            return remaining;
        }

        public void setRemaining(int remaining) {
            this.remaining = remaining;
        }

        public int getReset() {
            return reset;
        }

        public void setReset(int reset) {
            this.reset = reset;
        }
    }

    public RateLimits getRateLimits() {
        return this.rateLimits;
    }
}
