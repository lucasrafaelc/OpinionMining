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

public class ExtractParams {
    private String html;
    private URL url;
    private Boolean bestImage = false;

    public ExtractParams(String html, URL url, Boolean bestImage) {
        this.html = html;
        this.url = url;
        this.bestImage = bestImage;
    }

    public String getHtml() {
        return html;
    }

    public URL getUrl() {
        return url;
    }

    public Boolean getBestImage() {
        return bestImage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String html;
        private URL url;
        private Boolean bestImage = false;

        public Builder setHtml(String html) {
            this.html = html;
            return this;
        }

        public Builder setUrl(URL url) {
            this.url = url;
            return this;
        }

        public Builder setBestImage(Boolean bestImage) {
            this.bestImage = bestImage;
            return this;
        }

        public ExtractParams build() {
            return new ExtractParams(html, url, bestImage);
        }
    }
}
