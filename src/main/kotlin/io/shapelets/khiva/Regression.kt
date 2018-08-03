/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva

/**
 * Khiva Regression class containing regression methods.
 */
class Regression : Library() {
    companion object {
        @JvmStatic
        private external fun linear(xssRef: Long, yssRef: Long): LongArray

        /**
         * Calculates a linear least-squares regression for two sets of measurements. Both arrays should have the same
         * length.
         *
         * @param xss Array containing the input time series.
         * @param yss Array containing the input time series.
         * @return An Array of Khiva Arrays whose components are:
         * {
         * slope Slope of the regression line.
         * intercept Intercept of the regression line.
         * rvalue Correlation coefficient.
         * pvalue Two-sided p-value for a hypothesis test whose null hypothesis is that the slope is zero, using Wald
         * Test with t-distribution of the test statistic.
         * stderrest Standard error of the estimated gradient.
         * }
         */
        fun linear(xss: Array, yss: Array): kotlin.Array<Array> {
            val refs = linear(xss.reference, yss.reference)
            xss.reference = refs[0]
            yss.reference = refs[1]
            return arrayOf(Array(refs[2]), Array(refs[3]), Array(refs[4]), Array(refs[5]), Array(refs[6]))
        }
    }
}
