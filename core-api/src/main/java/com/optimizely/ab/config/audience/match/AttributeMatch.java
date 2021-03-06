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

abstract class AttributeMatch<T> implements Match {
    T convert(Object o) {
        try {
            T rv = (T)o;
            return rv;
        } catch(java.lang.ClassCastException e) {
            MatchType.logger.error(
                    "Cannot evaluate targeting condition since the value for attribute is an incompatible type",
                    e
            );
            return null;
        }
    }
}
