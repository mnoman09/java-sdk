/**
 *
 *    Copyright 2018, Optimizely and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.optimizely.ab.config.audience.match;

import javax.annotation.Nullable;

class ExactMatch<T> extends AttributeMatch<T> {
    T value;
    protected ExactMatch(T value) {
        this.value = value;
    }

    public @Nullable
    Boolean eval(Object attributeValue) {
        if (!value.getClass().isInstance(attributeValue)) return null;
        return value.equals(convert(attributeValue));
    }
}
