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
 * Khiva Features class containing a number of features that can be extracted from time series. All the methods
 * operate with instances of the ARRAY class as input and output.
 */
class Features : Library() {
    companion object {
        @JvmStatic
        private external fun absEnergy(ref: Long): LongArray

        @JvmStatic
        private external fun absoluteSumOfChanges(ref: Long): LongArray

        @JvmStatic
        private external fun aggregatedAutocorrelation(ref: Long, aggregationFunction: Int): LongArray

        @JvmStatic
        private external fun aggregatedLinearTrend(ref: Long, chunkSize: Long, aggregationFunction: Int): LongArray

        @JvmStatic
        private external fun approximateEntropy(ref: Long, m: Int, r: Float): LongArray

        @JvmStatic
        private external fun crossCovariance(refXss: Long, refYss: Long, unbiased: Boolean): LongArray

        @JvmStatic
        private external fun autoCovariance(ref: Long, unbiased: Boolean): LongArray

        @JvmStatic
        private external fun crossCorrelation(refXss: Long, refYss: Long, unbiased: Boolean): LongArray

        @JvmStatic
        private external fun autoCorrelation(ref: Long, maxLag: Long, unbiased: Boolean): LongArray

        @JvmStatic
        private external fun binnedEntropy(ref: Long, maxBins: Long): LongArray

        @JvmStatic
        private external fun c3(ref: Long, lag: Long): LongArray

        @JvmStatic
        private external fun cidCe(ref: Long, zNormalize: Boolean): LongArray

        @JvmStatic
        private external fun countAboveMean(ref: Long): LongArray

        @JvmStatic
        private external fun countBelowMean(ref: Long): LongArray

        @JvmStatic
        private external fun cwtCoefficients(ref: Long, refW: Long, coeff: Int, w: Int): LongArray

        @JvmStatic
        private external fun energyRatioByChunks(ref: Long, numSegments: Long, segmentFocus: Long): LongArray

        @JvmStatic
        private external fun fftAggregated(ref: Long): LongArray

        @JvmStatic
        private external fun fftCoefficient(ref: Long, coefficient: Long): LongArray

        @JvmStatic
        private external fun firstLocationOfMaximum(ref: Long): LongArray

        @JvmStatic
        private external fun firstLocationOfMinimum(ref: Long): LongArray

        @JvmStatic
        private external fun friedrichCoefficients(ref: Long, m: Int, r: Float): LongArray

        @JvmStatic
        private external fun hasDuplicates(ref: Long): LongArray

        @JvmStatic
        private external fun hasDuplicateMax(ref: Long): LongArray

        @JvmStatic
        private external fun hasDuplicateMin(ref: Long): LongArray

        @JvmStatic
        private external fun indexMassQuantile(ref: Long, q: Float): LongArray

        @JvmStatic
        private external fun kurtosis(ref: Long): LongArray

        @JvmStatic
        private external fun largeStandardDeviation(ref: Long, r: Float): LongArray

        @JvmStatic
        private external fun lastLocationOfMaximum(ref: Long): LongArray

        @JvmStatic
        private external fun lastLocationOfMinimum(ref: Long): LongArray

        @JvmStatic
        private external fun length(ref: Long): LongArray

        @JvmStatic
        private external fun linearTrend(ref: Long): LongArray

        @JvmStatic
        private external fun localMaximals(ref: Long): LongArray

        @JvmStatic
        private external fun longestStrikeAboveMean(ref: Long): LongArray

        @JvmStatic
        private external fun longestStrikeBelowMean(ref: Long): LongArray

        @JvmStatic
        private external fun maxLangevinFixedPoint(ref: Long, m: Int, r: Float): LongArray

        @JvmStatic
        private external fun maximum(ref: Long): LongArray

        @JvmStatic
        private external fun mean(ref: Long): LongArray

        @JvmStatic
        private external fun meanAbsoluteChange(ref: Long): LongArray

        @JvmStatic
        private external fun meanChange(ref: Long): LongArray

        @JvmStatic
        private external fun meanSecondDerivativeCentral(ref: Long): LongArray

        @JvmStatic
        private external fun median(ref: Long): LongArray

        @JvmStatic
        private external fun minimum(ref: Long): LongArray

        @JvmStatic
        private external fun numberCrossingM(ref: Long, m: Int): LongArray

        @JvmStatic
        private external fun numberCwtPeaks(ref: Long, maxW: Int): LongArray

        @JvmStatic
        private external fun numberPeaks(ref: Long, n: Int): LongArray

        @JvmStatic
        private external fun partialAutocorrelation(ref: Long, refLags: Long): LongArray

        @JvmStatic
        private external fun percentageOfReoccurringDatapointsToAllDatapoints(ref: Long, isSorted: Boolean): LongArray

        @JvmStatic
        private external fun percentageOfReoccurringValuesToAllValues(ref: Long, isSorted: Boolean): LongArray

        @JvmStatic
        private external fun quantile(ref: Long, refQ: Long, precision: Float): LongArray

        @JvmStatic
        private external fun rangeCount(ref: Long, min: Float, max: Float): LongArray

        @JvmStatic
        private external fun ratioBeyondRSigma(ref: Long, r: Float): LongArray

        @JvmStatic
        private external fun ratioValueNumberToTimeSeriesLength(ref: Long): LongArray

        @JvmStatic
        private external fun sampleEntropy(ref: Long): LongArray

        @JvmStatic
        private external fun skewness(ref: Long): LongArray

        @JvmStatic
        private external fun spktWelchDensity(ref: Long, coeff: Int): LongArray

        @JvmStatic
        private external fun standardDeviation(ref: Long): LongArray

        @JvmStatic
        private external fun sumOfReoccurringDatapoints(ref: Long, isSorted: Boolean): LongArray

        @JvmStatic
        private external fun sumOfReoccurringValues(ref: Long, isSorted: Boolean): LongArray

        @JvmStatic
        private external fun sumValues(ref: Long): LongArray

        @JvmStatic
        private external fun symmetryLooking(ref: Long, r: Float): LongArray

        @JvmStatic
        private external fun timeReversalAsymmetryStatistic(ref: Long, lag: Int): LongArray

        @JvmStatic
        private external fun valueCount(ref: Long, v: Float): LongArray

        @JvmStatic
        private external fun variance(ref: Long): LongArray

        @JvmStatic
        private external fun varianceLargerThanStandardDeviation(ref: Long): LongArray

        /**
         * Calculates the sum over the square values of the time series.
         *
         * @param arr Array containing the input time series.
         * @return Array with the Absolute Energy.
         */
        fun absEnergy(arr: Array): Array {
            val refs = absEnergy(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the value of an aggregation function f_agg (e.g. var or mean) of the autocorrelation
         * (Compare to http://en.wikipedia.org/wiki/Autocorrelation#Estimation), taken over different all possible
         * lags (1 to length of x).
         *
         * @param arr Array containing the input time series.
         * @return Array with the absolute sum of changes.
         */
        fun absoluteSumOfChanges(arr: Array): Array {
            val refs = absoluteSumOfChanges(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a linear least-squares regression for values of the time series that were aggregated
         * over chunks versus the sequence from 0 up to the number of chunks minus one.
         *
         * @param arr                 Array containing the input time series.
         * @param aggregationFunction Function to be used in the aggregation. It receives an integer which indicates the
         * function to be applied:
         * {
         * 0 : mean,
         * 1 : median
         * 2 : min,
         * 3 : max,
         * 4 : stdev,
         * 5 : var,
         * default : mean
         * }
         * @return Array whose values contains the aggregated correlation for each time series.
         */
        fun aggregatedAutocorrelation(arr: Array, aggregationFunction: Int): Array {
            val refs = aggregatedAutocorrelation(arr.reference, aggregationFunction)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a linear least-squares regression for values of the time series that were aggregated
         * over chunks versus the sequence from 0 up to the number of chunks minus one.
         *
         * @param arr                 Array containing the input time series.
         * @param chunkSize           The chunk size used to aggregate the data.
         * @param aggregationFunction Function to be used in the aggregation. It receives an integer which indicates the
         * function to be applied:
         * {
         * 0 : mean,
         * 1 : median
         * 2 : min,
         * 3 : max,
         * 4 : stdev,
         * default : mean
         * }
         * @return The aggregated Linear Trend.
         */
        fun aggregatedLinearTrend(arr: Array, chunkSize: Long, aggregationFunction: Int): kotlin.Array<Array> {
            val refs = aggregatedLinearTrend(arr.reference, chunkSize, aggregationFunction)
            arr.reference = refs[0]
            return arrayOf(Array(refs[1]), Array(refs[2]), Array(refs[3]), Array(refs[4]), Array(refs[5]))

        }

        /**
         * Calculates a vectorized Approximate entropy algorithm.
         * https://en.wikipedia.org/wiki/Approximate_entropy
         * For short time-series this method is highly dependent on the parameters, but should be stable for N greater than 2000,
         * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
         * Other shortcomings and alternatives discussed in:
         * Richman and Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy
         *
         * @param arr Array containing the input time series.
         * @param m   Length of compared run of data.
         * @param r   Filtering level, must be positive.
         * @return Array with the vectorized approximate entropy for all the input time series in arr.
         */

        fun approximateEntropy(arr: Array, m: Int, r: Float): Array {
            val refs = approximateEntropy(arr.reference, m, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the cross-covariance of the given time series.
         *
         * @param arrXss   Array containing the input time series.
         * @param arrYss   Array containing the input time series.
         * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
         * @return Array with the cross-covariance value for the given time series.
         */
        fun crossCovariance(arrXss: Array, arrYss: Array, unbiased: Boolean?): Array {
            val refs = crossCovariance(arrXss.reference, arrYss.reference, unbiased!!)
            arrXss.reference = refs[0]
            arrYss.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Calculates the auto-covariance the given time series.
         *
         * @param arr      Array containing the input time series.
         * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
         * @return Array with the auto-covariance value for the given time series.
         */
        fun autoCovariance(arr: Array, unbiased: Boolean?): Array {
            val refs = autoCovariance(arr.reference, unbiased!!)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the cross-correlation of the given time series.
         *
         * @param arrXss   Array containing the input time series.
         * @param arrYss   Array containing the input time series.
         * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
         * @return Double array with cross-correlation value for the given time series.
         */
        fun crossCorrelation(arrXss: Array, arrYss: Array, unbiased: Boolean?): Array {

            val refs = crossCorrelation(arrXss.reference, arrYss.reference,
                    unbiased!!)
            arrXss.reference = refs[0]
            arrYss.reference = refs[1]

            return Array(refs[2])
        }

        /**
         * Calculates the autocorrelation of the specified lag for the given time series.
         *
         * @param arr      Array containing the input time series.
         * @param maxLag   The maximum lag to compute.
         * @param unbiased Determines whether it divides by n - lag (if true) or n (if false).
         * @return The autocorrelation value for the given time series.
         */
        fun autoCorrelation(arr: Array, maxLag: Long, unbiased: Boolean?): Array {
            val refs = autoCorrelation(arr.reference, maxLag, unbiased!!)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the binned entropy for the given time series and number of bins.
         *
         * @param arr     Array containing the input time series.
         * @param maxBins The number of bins.
         * @return The binned entropy value for the given time series.
         */
        fun binnedEntropy(arr: Array, maxBins: Long): Array {
            val refs = binnedEntropy(arr.reference, maxBins)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the Schreiber, T. and Schmitz, A. (1997) measure of non-linearity
         * for the given time series.
         *
         * @param arr Array containing the input time series.
         * @param lag The lag.
         * @return The non-linearity value for the given time series.
         */
        fun c3(arr: Array, lag: Long): Array {
            val refs = c3(arr.reference, lag)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates an estimate for the time series complexity defined by
         * Batista, Gustavo EAPA, et al (2014). (A more complex time series has more peaks,
         * valleys, etc.)
         *
         * @param arr        Array containing the input time series.
         * @param zNormalize Controls whether the time series should be z-normalized or not.
         * @return The complexity value for the given time series.
         */
        fun cidCe(arr: Array, zNormalize: Boolean?): Array {

            val refs = cidCe(arr.reference, zNormalize!!)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the number of values in the time series that are higher than
         * the mean.
         *
         * @param arr Array containing the input time series.
         * @return The number of values in the time series that are higher than the mean.
         */
        fun countAboveMean(arr: Array): Array {

            val refs = countAboveMean(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the number of values in the time series that are lower than
         * the mean.
         *
         * @param arr Array containing the input time series.
         * @return The number of values in the time series that are lower than the mean.
         */
        fun countBelowMean(arr: Array): Array {

            val refs = countBelowMean(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a Continuous wavelet transform for the Ricker wavelet, also known as
         * the "Mexican hat wavelet".
         *
         * @param arr   Array containing the input time series.
         * @param arrW  Widths. It accepts a list of lists or a numpy array with one or several widths.
         * @param coeff Coefficient of interest.
         * @param w     Width of interest.
         * @return Result of calculated coefficients.
         */
        fun cwtCoefficients(arr: Array, arrW: Array, coeff: Int, w: Int): Array {
            val refs = cwtCoefficients(arr.reference, arrW.reference, coeff, w)
            arr.reference = refs[0]
            arrW.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Calculates the sum of squares of chunk i out of N chunks expressed as a ratio
         * with the sum of squares over the whole series. segmentFocus should be lower
         * than the number of segments.
         *
         * @param arr          Array containing the input time series.
         * @param numSegments  The number of segments to divide the series into.
         * @param segmentFocus The segment number (starting at zero) to return a feature on.
         * @return The energy ratio by chunk of the time series.
         */
        fun energyRatioByChunks(arr: Array, numSegments: Long, segmentFocus: Long): Array {
            val refs = energyRatioByChunks(arr.reference, numSegments, segmentFocus)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the spectral centroid(mean), variance, skew, and kurtosis of the absolute fourier transform
         * spectrum.
         *
         * @param arr Array containing the input time series.
         * @return The spectral centroid (mean), variance, skew, and kurtosis of the absolute fourier transform
         * spectrum.
         */
        fun fftAggregated(arr: Array): Array {
            val refs = fftAggregated(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the fourier coefficients of the one-dimensional discrete
         * Fourier Transform for real input by fast fourier transformation algorithm.
         *
         * @param arr         Array containing the input time series.
         * @param coefficient The coefficient to extract from the FFT.
         * @return Array of Arrays with:
         *
         * real        The real part of the coefficient.
         * imag        The imaginary part of the coefficient.
         * abs         The absolute value of the coefficient.
         * angle       The angle of the coefficient.
         */
        fun fftCoefficient(arr: Array, coefficient: Long): kotlin.Array<Array> {
            val refs = fftCoefficient(arr.reference, coefficient)
            arr.reference = refs[0]
            return arrayOf(Array(refs[1]), Array(refs[2]), Array(refs[3]), Array(refs[4]))
        }

        /**
         * Calculates the first relative location of the maximal value for each time series.
         *
         * @param arr Array containing the input time series.
         * @return The first relative location of the maximum value to the length of the times series,
         * for each time series.
         */
        fun firstLocationOfMaximum(arr: Array): Array {
            val refs = firstLocationOfMaximum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the first location of the minimal value of each time series. The position
         * is calculated relatively to the length of the series.
         *
         * @param arr Array containing the input time series.
         * @return The first relative location of the minimal value of each series.
         */
        fun firstLocationOfMinimum(arr: Array): Array {
            val refs = firstLocationOfMinimum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Coefficients of polynomial \(h(x)\), which has been fitted to the deterministic
         * dynamics of Langevin model:
         * \[
         * \dot(x)(t) = h(x(t)) + R \mathcal(N)(0,1)
         * \]
         * as described by 1. For short time series this method is highly dependent on the parameters.
         *
         * 1. Friedrich et al. (2000): Physics Letters A 271, p. 217-222
         * Extracting model equations from experimental data.
         *
         * @param arr Array containing the input time series.
         * @param m   Order of polynom to fit for estimating fixed points of dynamics.
         * @param r   Number of quantils to use for averaging.
         * @return Array containing the coefficients for each time series.
         */
        fun friedrichCoefficients(arr: Array, m: Int, r: Float): Array {
            val refs = friedrichCoefficients(arr.reference, m, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates if the input time series contain duplicated elements.
         *
         * @param arr Array containing the input time series.
         * @return Array containing True if the time series contains duplicated elements
         * and false otherwise.
         */
        fun hasDuplicates(arr: Array): Array {
            val refs = hasDuplicates(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates if the maximum within input time series is duplicated.
         *
         * @param arr Array containing the input time series.
         * @return Calculates if the maximum within input time series is duplicated.
         */
        fun hasDuplicateMax(arr: Array): Array {

            val refs = hasDuplicateMax(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates if the minimum of the input time series is duplicated.
         *
         * @param arr Array containing the input time series.
         * @return Array containing True if the minimum of the time series is duplicated and False otherwise.
         */
        fun hasDuplicateMin(arr: Array): Array {

            val refs = hasDuplicateMin(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the index of the mass quantile.
         *
         * @param arr Array containing the input time series.
         * @param q   The quantile.
         * @return The index of the mass quantile q.
         */
        fun indexMassQuantile(arr: Array, q: Float): Array {
            val refs = indexMassQuantile(arr.reference, q)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns the kurtosis of arr (calculated with the adjusted Fisher-Pearson standardized moment coefficient G2).
         *
         * @param arr Array containing the input time series.
         * @return The kurtosis of each arr.
         */
        fun kurtosis(arr: Array): Array {

            val refs = kurtosis(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Checks if the time series within arr have a large standard deviation.
         *
         * @param arr Array containing the input time series.
         * @param r   The threshold.
         * @return Array containing True for those time series in arr that have a large standard deviation.
         */
        fun largeStandardDeviation(arr: Array, r: Float): Array {

            val refs = largeStandardDeviation(arr.reference, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the last location of the maximum value of each time series. The position
         * is calculated relatively to the length of the series.
         *
         * @param arr Array containing the input time series.
         * @return The last relative location of the maximum value of each series.
         */
        fun lastLocationOfMaximum(arr: Array): Array {

            val refs = lastLocationOfMaximum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the last location of the minimum value of each time series. The position
         * is calculated relatively to the length of the series.
         *
         * @param arr Array containing the input time series.
         * @return The last relative location of the minimum value of each series.
         */
        fun lastLocationOfMinimum(arr: Array): Array {
            val refs = lastLocationOfMinimum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns the length of the input time series.
         *
         * @param arr Array containing the input time series.
         * @return The length of arr.
         */
        fun length(arr: Array): Array {
            val refs = length(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a linear least-squares regression for the values of the time series versus the sequence from 0 to
         * length of the time series minus one.
         *
         * @param arr Array containing the input time series.
         * @return Array of Arrays with:
         * pvalue    The pvalues for all time series.
         * rvalue    The rvalues for all time series.
         * intercept The intercept values for all time series.
         * slope     The slope for all time series.
         * stdrr     The stdrr values for all time series.
         */
        fun linearTrend(arr: Array): kotlin.Array<Array> {
            val refs = linearTrend(arr.reference)
            arr.reference = refs[0]
            return arrayOf(Array(refs[1]), Array(refs[2]), Array(refs[3]), Array(refs[4]), Array(refs[5]))
        }

        /**
         * Calculates all Local Maximals fot the time series in arr.
         *
         * @param arr Array containing the input time series.
         * @return The calculated local maximals for each time series in array.
         */
        fun localMaximals(arr: Array): Array {
            val refs = localMaximals(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the length of the longest consecutive subsequence in arr that is bigger than the mean of arr.
         *
         * @param arr Array containing the input time series.
         * @return The length of the longest consecutive subsequence in the input time series that is bigger than the
         * mean.
         */
        fun longestStrikeAboveMean(arr: Array): Array {
            val refs = longestStrikeAboveMean(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the length of the longest consecutive subsequence in arr that is below the mean of arr.
         *
         * @param arr Array containing the input time series.
         * @return The length of the longest consecutive subsequence in the input time series that is below the mean.
         */
        fun longestStrikeBelowMean(arr: Array): Array {
            val refs = longestStrikeBelowMean(arr.reference)
            arr.reference = refs[0]

            return Array(refs[1])
        }

        /**
         * Largest fixed point of dynamics \(\max_x {h(x)=0}\) estimated from polynomial
         * \(h(x)\), which has been fitted to the deterministic dynamics of Langevin model
         * \[
         * \dot(x)(t) = h(x(t)) + R \mathcal(N)(0,1)
         * \]
         * as described by
         * Friedrich et al. (2000): Physics Letters A 271, p. 217-222 *Extracting model equations from experimental data.
         *
         * @param arr Array containing the input time series.
         * @param m   Order of polynom to fit for estimating fixed points of dynamics.
         * @param r   Number of quantiles to use for averaging.
         * @return Largest fixed point of deterministic dynamics.
         */
        fun maxLangevinFixedPoint(arr: Array, m: Int, r: Float): Array {
            val refs = maxLangevinFixedPoint(arr.reference, m, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the maximum value for each time series within arr.
         *
         * @param arr Array containing the input time series.
         * @return The maximum value of each time series within arr.
         */
        fun maximum(arr: Array): Array {

            val refs = maximum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the mean value for each time series within arr.
         *
         * @param arr Array containing the input time series.
         * @return The mean value of each time series within arr.
         */
        fun mean(arr: Array): Array {

            val refs = mean(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the mean over the absolute differences between subsequent time series values in arr.
         *
         * @param arr Array containing the input time series.
         * @return The mean over the absolute differences between subsequent time series values.
         */
        fun meanAbsoluteChange(arr: Array): Array {
            val refs = meanAbsoluteChange(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the mean over the differences between subsequent time series values in arr.
         *
         * @param arr Array containing the input time series.
         * @return The mean over the differences between subsequent time series values.
         */
        fun meanChange(arr: Array): Array {
            val refs = meanChange(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates mean value of a central approximation of the second derivative for each time series in arr.
         *
         * @param arr Array containing the input time series.
         * @return The mean value of a central approximation of the second derivative for each time series.
         */
        fun meanSecondDerivativeCentral(arr: Array): Array {
            val refs = meanSecondDerivativeCentral(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the median value for each time series within arr.
         *
         * @param arr Array containing the input time series.
         * @return The median value of each time series within arr.
         */
        fun median(arr: Array): Array {
            val refs = median(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the minimum value for each time series within arr.
         *
         * @param arr Array containing the input time series.
         * @return The minimum value of each time series within arr.
         */
        fun minimum(arr: Array): Array {
            val refs = minimum(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the number of m-crossings. A m-crossing is defined as two sequential values where the first
         * value is lower than m and the next is greater, or viceversa. If you set m to zero, you will get the number of
         * zero crossings.
         *
         * @param arr Array containing the input time series.
         * @param m   The m value.
         * @return The number of m-crossings of each time series within arr.
         */
        fun numberCrossingM(arr: Array, m: Int): Array {
            val refs = numberCrossingM(arr.reference, m)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * This feature calculator searches for different peaks. To do so, the time series is smoothed by a ricker
         * wavelet and for widths ranging from 1 to maxW. This feature calculator returns the number of peaks that occur at
         * enough width scales and with sufficiently high Signal-to-Noise-Ratio (SNR).
         *
         * @param arr  Array containing the input time series.
         * @param maxW The maximum width to consider.
         * @return The number of peaks for each time series.
         */
        fun numberCwtPeaks(arr: Array, maxW: Int): Array {
            val refs = numberCwtPeaks(arr.reference, maxW)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the number of peaks of at least support \(n\) in the time series \(arr\). A peak of support
         * \(n\) is defined as a subsequence of \(arr\) where a value occurs, which is bigger than
         * its \(n\) neighbours to the left and to the right.
         *
         * @param arr Array containing the input time series.
         * @param n   The support of the peak.
         * @return The number of peaks of at least support \(n\).
         */
        fun numberPeaks(arr: Array, n: Int): Array {
            val refs = numberPeaks(arr.reference, n)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the value of the partial autocorrelation function at the given lag. The lag \(k\) partial
         * autocorrelation of a time series \(\lbrace x_t, t = 1 \ldots T \rbrace\) equals the partial correlation of
         * \(x_t\) and \(x_{t-k}\), adjusted for the intermediate variables \(\lbrace x_{t-1}, \ldots, x_{t-k+1}
         * \rbrace\) (1). Following (2), it can be defined as:
         *
         * \[
         * \alpha_k = \frac{ Cov(x_t, x_{t-k} | x_{t-1}, \ldots, x_{t-k+1})}
         * {\sqrt{ Var(x_t | x_{t-1}, \ldots, x_{t-k+1}) Var(x_{t-k} | x_{t-1}, \ldots, x_{t-k+1} )}}
         * \]
         * with (a) \(x_t = f(x_{t-1}, \ldots, x_{t-k+1})\) and (b) \( x_{t-k} = f(x_{t-1}, \ldots, x_{t-k+1})\)
         * being AR(k-1) models that can be fitted by OLS. Be aware that in (a), the regression is done on past values to
         * predict \( x_t \) whereas in (b), future values are used to calculate the past value \(x_{t-k}\).
         * It is said in (1) that "for an AR(p), the partial autocorrelations \( \alpha_k \) will be nonzero for
         * \( k \leq p \) and zero for \( k&gt;p \)."
         * With this property, it is used to determine the lag of an AR-Process.
         *
         * 1. Box, G. E., Jenkins, G. M., Reinsel, G. C., &amp; Ljung, G. M. (2015).
         * Time series analysis: forecasting and control. John Wiley &amp; Sons.
         * 2. https://onlinecourses.science.psu.edu/stat510/node/62
         *
         * @param arr     Array containing the input time series.
         * @param arrLags Array containing the lags to be calculated.
         * @return Returns the partial autocorrelation for each time series for the given lags.
         */
        fun partialAutocorrelation(arr: Array, arrLags: Array): Array {
            val refs = partialAutocorrelation(arr.reference, arrLags.reference)
            arr.reference = refs[0]
            arrLags.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Calculates the percentage of unique values, that are present in the time series more than once.
         * \[
         * len(different values occurring more than once) / len(different values)
         * \]
         * This means the percentage is normalized to the number of unique values, in contrast to the
         * percentageOfReoccurringValuesToAllValues.
         *
         * @param arr      Array containing the input time series.
         * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
         * @return Returns the percentage of unique values, that are present in the time series more than once.
         */
        fun percentageOfReoccurringDatapointsToAllDatapoints(arr: Array, isSorted: Boolean): Array {
            val refs = percentageOfReoccurringDatapointsToAllDatapoints(arr.reference, isSorted)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the percentage of unique values, that are present in the time series more than once.
         * \[
         * \frac{\textit{number of data points occurring more than once}}{\textit{number of all data points})}
         * \]
         * This means the percentage is normalized to the number of unique values, in contrast to the
         * percentageOfReoccurringDatapointsToAllDatapoints.
         *
         * @param arr      Array containing the input time series.
         * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
         * @return Returns the percentage of unique values, that are present in the time series more than once.
         */
        fun percentageOfReoccurringValuesToAllValues(arr: Array, isSorted: Boolean): Array {
            val refs = percentageOfReoccurringValuesToAllValues(arr.reference, isSorted)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Returns values at the given quantile.
         *
         * @param arr  Array containing the input time series.
         * @param arrQ Percentile(s) at which to extract score(s). One or many.
         * @return Values at the given quantile.
         */
        fun quantile(arr: Array, arrQ: Array): Array {
            return quantile(arr, arrQ, 1e8.toFloat())
        }

        /**
         * Returns values at the given quantile.
         *
         * @param arr       Array containing the input time series.
         * @param arrQ      Percentile(s) at which to extract score(s). One or many.
         * @param precision Number of decimals expected.
         * @return Values at the given quantile.
         */
        fun quantile(arr: Array, arrQ: Array, precision: Float): Array {
            val refs = quantile(arr.reference, arrQ.reference, precision)
            arr.reference = refs[0]
            arrQ.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Counts observed values within the interval [min, max).
         *
         * @param arr Array containing the input time series.
         * @param min Value that sets the lower limit.
         * @param max Value that sets the upper limit.
         * @return Values at the given range.
         */
        fun rangeCount(arr: Array, min: Float, max: Float): Array {
            val refs = rangeCount(arr.reference, min, max)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the ratio of values that are more than  \(r*std(x)\] (so \(r\) sigma) away from the mean of
         * \(x\).
         *
         * @param arr Array containing the input time series.
         * @param r   Number of times that the values should be away from.
         * @return The ratio of values that are more than \(r*std(x)\) (so \(r\) sigma) away from the mean of
         * \(x\).
         */
        fun ratioBeyondRSigma(arr: Array, r: Float): Array {
            val refs = ratioBeyondRSigma(arr.reference, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a factor which is 1 if all values in the time series occur only once, and below one if this is
         * not the case. In principle, it just returns:
         *
         * \[
         * \frac{\textit{number_unique_values}}{\textit{number_values}}
         * \]
         *
         * @param arr Array containing the input time series.
         * @return The ratio of unique values with respect to the total number of values.
         */
        fun ratioValueNumberToTimeSeriesLength(arr: Array): Array {
            val refs = ratioValueNumberToTimeSeriesLength(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates a vectorized sample entropy algorithm.
         * https://en.wikipedia.org/wiki/Sample_entropy
         * https://www.ncbi.nlm.nih.gov/pubmed/10843903?dopt=Abstract
         * For short time-series this method is highly dependent on the parameters, but should be stable for N &gt; 2000,
         * see: Yentes et al. (2012) - The Appropriate Use of Approximate Entropy and Sample Entropy with Short Data Sets
         * Other shortcomings and alternatives discussed in:
         * Richman &amp; Moorman (2000) - Physiological time-series analysis using approximate entropy and sample entropy.
         *
         * @param arr Array containing the input time series.
         * @return An array with the same dimensions as arr, whose values (time series in dimension 0)
         * contains the vectorized sample entropy for all the input time series in arr.
         */
        fun sampleEntropy(arr: Array): Array {
            val refs = sampleEntropy(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the sample skewness of arr (calculated with the adjusted Fisher-Pearson standardized
         * moment coefficient G1).
         *
         * @param arr Array containing the input time series.
         * @return Array containing the skewness of each time series in arr.
         */
        fun skewness(arr: Array): Array {
            val refs = skewness(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Estimates the cross power spectral density of the time series array at different frequencies. To do so, the
         * time series is first shifted from the time domain to the frequency domain.
         *
         * Welch's method computes an estimate of the power spectral density by dividing the data into overlapping
         * segments, computing a modified periodogram for each segment and averaging the periodograms.
         * 1. P. Welch, "The use of the fast Fourier transform for the estimation of power spectra: A method based on time
         * averaging over short, modified periodograms", IEEE Trans. Audio Electroacoust. vol. 15, pp. 70-73, 1967.
         * 2. M.S. Bartlett, "Periodogram Analysis and Continuous Spectra", Biometrika, vol. 37, pp. 1-16, 1950.
         * 3. Rabiner, Lawrence R., and B. Gold. "Theory and Application of Digital Signal Processing" Prentice-Hall, pp.
         * 414-419, 1975.
         *
         * @param arr   Array containing the input time series.
         * @param coeff The coefficient to be returned.
         * @return Array containing the power spectrum of the different frequencies for each time series in array.
         */
        fun spktWelchDensity(arr: Array, coeff: Int): Array {
            val refs = spktWelchDensity(arr.reference, coeff)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the standard deviation of each time series within arr.
         *
         * @param arr Array containing the input time series.
         * @return The standard deviation of each time series within arr.
         */
        fun standardDeviation(arr: Array): Array {
            val refs = standardDeviation(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the sum of all data points, that are present in the time series more than once.
         *
         * @param arr      Array containing the input time series.
         * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
         * @return Returns the sum of all data points, that are present in the time series more than once.
         */
        fun sumOfReoccurringDatapoints(arr: Array, isSorted: Boolean = false): Array {
            val refs = sumOfReoccurringDatapoints(arr.reference, isSorted)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the sum of all values, that are present in the time series more than once.
         *
         * @param arr      Array containing the input time series.
         * @param isSorted Indicates if the input time series is sorted or not. Defaults to false.
         * @return Array containing the the sum of all values, that are present in the time series more than once.
         */
        fun sumOfReoccurringValues(arr: Array, isSorted: Boolean = false): Array {
            val refs = sumOfReoccurringValues(arr.reference, isSorted)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the sum over the time series array.
         *
         * @param arr Array containing the input time series.
         * @return Srray containing the sum of values in each time series.
         */
        fun sumValues(arr: Array): Array {
            val refs = sumValues(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates if the distribution of arr *looks symmetric*. This is the case if
         * \[
         * | mean(arr)-median(arr)| &lt; r * (max(arr)-min(arr))
         * \]
         *
         * @param arr Array containing the input time series.
         * @param r   The percentage of the range to compare with.
         * @return An array denoting if the input time series look symmetric.
         */
        fun symmetryLooking(arr: Array, r: Float): Array {
            val refs = symmetryLooking(arr.reference, r)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * This function calculates the value of:
         * \[
         * \frac{1}{n-2lag} \sum_{i=0}^{n-2lag} x_{i + 2 \cdot lag}^2 \cdot x_{i + lag} - x_{i + lag} \cdot  x_{i}^2
         * \]
         * which is
         * \[
         * \mathbb{E}(L^2(X)^2 \cdot L(X) - L(X) \cdot X^2)
         * \]
         * where \f$ \mathbb{E} \f$ is the mean and \f$ L \f$ is the lag operator. It was proposed in (1) as a promising
         * feature to extract from time series.
         *
         * 1. Fulcher, B.D., Jones, N.S. (2014). Highly comparative feature-based time-series classification.
         * Knowledge and Data Engineering, IEEE Transactions on 26, 3026-3037.
         *
         * @param arr Array containing the input time series.
         * @param lag The lag to be computed.
         * @return An array containing the time reversal asymetry statistic value in each time series.
         */
        fun timeReversalAsymmetryStatistic(arr: Array, lag: Int): Array {
            val refs = timeReversalAsymmetryStatistic(arr.reference, lag)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Counts occurrences of value in the time series arr.
         *
         * @param arr Array containing the input time series.
         * @param v   The value to be counted.
         * @return An array containing the count of the given value in each time series.
         */
        fun valueCount(arr: Array, v: Float): Array {
            val refs = valueCount(arr.reference, v)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Computes the variance for the time series array.
         *
         * @param arr Array containing the input time series.
         * @return An array containing the variance in each time series.
         */
        fun variance(arr: Array): Array {
            val refs = variance(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates if the variance of array is greater than the standard deviation. In other words, if the variance of
         * array is larger than 1.
         *
         * @param arr Array containing the input time series.
         * @return An array denoting if the variance of array is greater than the standard deviation.
         */
        fun varianceLargerThanStandardDeviation(arr: Array): Array {
            val refs = varianceLargerThanStandardDeviation(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }
    }
}
