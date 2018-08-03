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
 * Khiva Statistics class containing statistics methods.
 */
class Statistics : Library() {
    companion object {
        @JvmStatic
        private external fun covariance(ref: Long, unbiased: Boolean): LongArray

        @JvmStatic
        private external fun moment(ref: Long, k: Int): LongArray

        @JvmStatic
        private external fun sampleStdev(ref: Long): LongArray

        @JvmStatic
        private external fun kurtosis(ref: Long): LongArray

        @JvmStatic
        private external fun ljungBox(ref: Long, lags: Long): LongArray

        @JvmStatic
        private external fun skewness(ref: Long): LongArray

        @JvmStatic
        private external fun quantile(ref: Long, refQ: Long, precision: Float): LongArray

        @JvmStatic
        private external fun quantilesCut(ref: Long, quantiles: Float, precision: Float): LongArray

        /**
         * Returns the covariance matrix of the time series contained in tss.
         *
         * @param tss      Array containing the input time series.
         * @param unbiased Determines whether it divides by n - 1 (if false) or n (if true).
         * @return The covariance matrix of the time series.
         */
        fun covariance(tss: Array, unbiased: Boolean = true): Array {
            val refs = covariance(tss.reference, unbiased)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns the kurtosis of tss (calculated with the adjusted Fisher-Pearson standardized moment coefficient G2).
         *
         * @param tss Array containing the input time series.
         * @return The kurtosis of tss.
         */
        fun kurtosis(tss: Array): Array {
            val refs = kurtosis(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * The Ljung-Box test checks that data whithin the time series are independently distributed (i.e. the correlations in
         * the population from which the sample is taken are 0, so that any observed correlations in the data result from
         * randomness of the sampling process). Data are no independently distributed, if they exhibit serial correlation.
         *
         * The test statistic is:
         *
         * \[
         * Q = n\left(n+2\right)\sum_{k=1}^h\frac{\hat{\rho}^2_k}{n-k}
         * \]
         *
         * where ''n'' is the sample size, \(\hat{\rho}k \) is the sample autocorrelation at lag ''k'', and ''h'' is the
         * number of lags being tested. Under \( H_0 \) the statistic Q follows a \(\chi^2{(h)} \). For significance level
         * \(\alpha\), the \(critical region\) for rejection of the hypothesis of randomness is:
         *
         * \[
         * Q &gt; \chi_{1-\alpha,h}^2
         * \]
         *
         * where \( \chi_{1-\alpha,h}^2 \) is the \(\alpha\)-quantile of the chi-squared distribution with ''h'' degrees of
         * freedom.
         *
         * 1. G. M. Ljung  G. E. P. Box (1978). On a measure of lack of fit in time series models.
         * Biometrika, Volume 65, Issue 2, 1 August 1978, Pages 297-303.
         *
         * @param tss  Expects an input array whose dimension zero is the length of the time series (all the same) and dimension
         * one indicates the number of time series.
         * @param lags Number of lags being tested.
         * @return The updated ref and the Ljung-Box statistic test.
         */
        fun ljungBox(tss: Array, lags: Long): Array {
            val refs = ljungBox(tss.reference, lags)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns the kth moment of the given time series.
         *
         * @param tss Array containing the input time series.
         * @param k   The specific moment to be calculated.
         * @return The kth moment of the given time series.
         */
        fun moment(tss: Array, k: Int): Array {
            val refs = moment(tss.reference, k)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns values at the given quantile.
         *
         * @param tss       Array containing the input time series.
         * @param q         Percentile(s) at which to extract score(s). One or many.
         * @param precision Number of decimals expected.
         * @return Values at the given quantile.
         */
        fun quantile(tss: Array, q: Array, precision: Float = 1e8f): Array {
            val refs = quantile(tss.reference, q.reference, precision)
            tss.reference = refs[0]
            q.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Discretizes the time series into equal-sized buckets based on sample quantiles.
         *
         * @param tss       Array containing the input time series.
         * @param quantiles Number of quantiles to extract. From 0 to 1, step 1/quantiles.
         * @param precision Number of decimals expected.
         * @return Array with a matrix with the categories, one category per row, the start of the category in the first
         * column and the end in the second category.
         */
        fun quantilesCut(tss: Array, quantiles: Float, precision: Float = 1e-8f): Array {
            val refs = quantilesCut(tss.reference, quantiles, precision)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Estimates standard deviation based on a sample. The standard deviation is calculated using the "n-1" method.
         *
         * @param tss Array containing the input time series.
         * @return The sample standard deviation.
         */
        fun sampleStdev(tss: Array): Array {
            val refs = sampleStdev(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the sample skewness of tss (calculated with the adjusted Fisher-Pearson standardized moment
         * coefficient G1).
         *
         * @param tss Array containing the input time series.
         * @return Array containing the skewness of each time series in tss.
         */
        fun skewness(tss: Array): Array {
            val refs = skewness(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }
    }
}