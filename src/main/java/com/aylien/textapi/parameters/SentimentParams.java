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

public class SentimentParams {
    private String text;
    private URL url;
    private String mode;

    public SentimentParams(String text, URL url, String mode) {
        this.text = text;
        this.url = url;
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public URL getUrl() {
        return url;
    }

    public String getMode() {
        return mode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String text;
        private URL url;
        private String mode = "tweet";

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setUrl(URL url) {
            this.url = url;
            return this;
        }

        public Builder setMode(String mode) {
            this.mode = mode;
            return this;
        }

        public SentimentParams build() {
            return new SentimentParams(text, url, mode);
        }
    }
}
