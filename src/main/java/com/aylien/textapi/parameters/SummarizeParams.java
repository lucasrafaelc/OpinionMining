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

package com.aylien.textapi.parameters;

import java.net.URL;

public class SummarizeParams {
    private String mode;
    private int numberOfSentences;
    private int percentageOfSentences;
    private String text;
    private String title;
    private URL url;

    public SummarizeParams(String text, String title, URL url, String mode, int numberOfSentences, int percentageOfSentences) {
        this.text = text;
        this.title = title;
        this.url = url;
        this.mode = mode;
        this.numberOfSentences = numberOfSentences;
        this.percentageOfSentences = percentageOfSentences;
    }

    public String getMode() {
        return mode;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public int getPercentageOfSentences() {
        return percentageOfSentences;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public URL getUrl() {
        return url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String mode = "default";
        private int numberOfSentences = 5;
        private int percentageOfSentences;
        private String text;
        private String title;
        private URL url;

        public Builder setMode(String mode) {
            this.mode = mode;
            return this;
        }

        public Builder setNumberOfSentences(int numberOfSentences) {
            this.numberOfSentences = numberOfSentences;
            return this;
        }

        public Builder setPercentageOfSentences(int percentageOfSentences) {
            this.percentageOfSentences = percentageOfSentences;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(URL url) {
            this.url = url;
            return this;
        }

        public SummarizeParams build() {
            return new SummarizeParams(text, title, url, mode, numberOfSentences, percentageOfSentences);
        }
    }
}
