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

public class LanguageParams {
    private String text;
    private URL url;

    public LanguageParams(String text, URL url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public URL getUrl() {
        return url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String text;
        private URL url;

        public void setText(String text) {
            this.text = text;
        }

        public void setUrl(URL url) {
            this.url = url;
        }

        public LanguageParams build() {
            return new LanguageParams(text, url);
        }
    }
}
