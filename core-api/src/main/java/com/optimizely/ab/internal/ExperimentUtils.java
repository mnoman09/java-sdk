/**
 *
 *    Copyright 2017-2018, Optimizely and contributors
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
package com.optimizely.ab.internal;

import com.optimizely.ab.config.Experiment;
import com.optimizely.ab.config.ProjectConfig;
import com.optimizely.ab.config.audience.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public final class ExperimentUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentUtils.class);

    private ExperimentUtils() {}

    /**
     * Helper method to validate all pre-conditions before bucketing a user.
     *
     * @param experiment the experiment we are validating pre-conditions for
     * @return whether the pre-conditions are satisfied
     */
    public static boolean isExperimentActive(@Nonnull Experiment experiment) {

        if (!experiment.isActive()) {
            logger.info("Experiment \"{}\" is not running.", experiment.getKey());
            return false;
        }

        return true;
    }

    /**
     * Determines whether a user satisfies audience conditions for the experiment.
     *
     * @param projectConfig the current projectConfig
     * @param experiment the experiment we are evaluating audiences for
     * @param attributes the attributes of the user
     * @return whether the user meets the criteria for the experiment
     */
    public static boolean isUserInExperiment(@Nonnull ProjectConfig projectConfig,
                                             @Nonnull Experiment experiment,
                                             @Nonnull Map<String, ?> attributes) {
        List<String> experimentAudienceIds = experiment.getAudienceIds();

        // if there are no audiences, ALL users should be part of the experiment
        if (experimentAudienceIds.isEmpty()) {
            return true;
        }

        for (String audienceId : experimentAudienceIds) {
            Condition conditions = projectConfig.getAudienceConditionsFromId(audienceId);
            Boolean conditionEval = conditions.evaluate(attributes);

            if (conditionEval != null && conditionEval) {
                return true;
            }
        }

        return false;
    }
}
