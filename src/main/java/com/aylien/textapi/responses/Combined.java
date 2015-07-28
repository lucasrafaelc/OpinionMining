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

package com.aylien.textapi.responses;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="result")
public class Combined {

    private Article article;
    private Concepts concepts;
    private Entities entities;
    private HashTags hashTags;
    private Language language;
    private Summarize summary;
    private Sentiment sentiment;
    private Classifications classifications;

    @XmlElement(name="extract")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @XmlElement(name="sentiment")
    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }

    @XmlElement(name="classify")
    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    @XmlElement(name="concepts")
    public Concepts getConcepts() {
        return concepts;
    }

    public void setConcepts(Concepts concepts) {
        this.concepts = concepts;
    }

    @XmlElement(name="entities")
    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    @XmlElement(name="hashtags")
    public HashTags getHashTags() {
        return hashTags;
    }

    public void setHashTags(HashTags hashTags) {
        this.hashTags = hashTags;
    }

    @XmlElement(name="language")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @XmlElement(name="summarize")
    public Summarize getSummary() {
        return summary;
    }

    public void setSummary(Summarize summary) {
        this.summary = summary;
    }
}
