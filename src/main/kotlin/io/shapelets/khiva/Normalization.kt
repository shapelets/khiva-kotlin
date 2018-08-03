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
 * Khiva Normalization class containing several normalization methods.
 */
class Normalization : Library() {
    companion object {
        @JvmStatic
        private external fun znorm(ref: Long, epsilon: Double): LongArray

        @JvmStatic
        private external fun znormInPlace(ref: Long, epsilon: Double): Long

        @JvmStatic
        private external fun maxMinNorm(ref: Long, high: Double, low: Double, epsilon: Double): LongArray

        @JvmStatic
        private external fun maxMinNormInPlace(ref: Long, high: Double, low: Double, epsilon: Double): Long

        @JvmStatic
        private external fun decimalScalingNorm(ref: Long): LongArray

        @JvmStatic
        private external fun decimalScalingNormInPlace(ref: Long): Long

        @JvmStatic
        private external fun meanNorm(ref: Long): LongArray

        @JvmStatic
        private external fun meanNormInPlace(ref: Long): Long

        /**
         * Normalizes the given time series according to its maximum value and adjusts each value within the range (-1, 1).
         *
         * @param arr Array containing the input time series.
         * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have been normalized by
         * dividing each number by \(10^j\), where j is the number of integer digits of the max number in the time series.
         */
        fun decimalScalingNorm(arr: Array): Array {
            val refs = decimalScalingNorm(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Same as decimalScalingNorm, but it performs the operation in place, without allocating further memory.
         *
         * @param arr Array containing the input time series.
         */
        fun decimalScalingNormInPlace(arr: Array) {
            val ref = decimalScalingNormInPlace(arr.reference)
            arr.reference = ref
        }

        /**
         * Normalizes the given time series according to its minimum and maximum value and adjusts each value within the
         * range (low, high) both inclusive.
         *
         * @param arr     Array containing the input time series.
         * @param high    Maximum final value (Defaults to 1.0).
         * @param low     Minimum final value (Defaults to 0.0).
         * @param epsilon Minimum standard deviation to consider.  It acts a a gatekeeper for
         * those time series that may be constant or near constant.
         * @return Array with the same dimensions as ref, whose values (time series in dimension 0) have
         * been normalized by maximum and minimum values, and scaled as per high and low parameters.
         */
        fun maxMinNorm(arr: Array, high: Double = 1.0, low: Double = 0.0, epsilon: Double = 0.00000001): Array {
            val refs = maxMinNorm(arr.reference, high, low, epsilon)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Same as maxMinNorm, but it performs the operation in place, without allocating further memory.
         *
         * @param arr     Array containing the input time series.
         * @param high    Maximum final value (Defaults to 1.0).
         * @param low     Minimum final value (Defaults to 0.0).
         * @param epsilon epsilon Minimum standard deviation to consider.  It acts as a gatekeeper for
         * those time series that may be constant or near constant.
         */
        fun maxMinNormInPlace(arr: Array, high: Double = 1.0, low: Double = 0.0, epsilon: Double = 0.00000001) {
            val ref = maxMinNormInPlace(arr.reference, high, low, epsilon)
            arr.reference = ref
        }

        /**
         * Normalizes the given time series according to its maximum-minimum value and its mean. It follows the following
         * formulae:
         * \[
         * \acute{x} = \frac{x - mean(x)}{max(x) - min(x)}.
         * \]
         *
         * @param arr Array containing the input time series.
         * @return An array with the same dimensions as tss, whose values (time series in dimension 0) have been
         * normalized by substracting the mean from each number and dividing each number by \(max(x) - min(x)\), in the
         * time series.
         */
        fun meanNorm(arr: Array): Array {
            val refs = meanNorm(arr.reference)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Normalizes the given time series according to its maximum-minimum value and its mean. It follows the following
         * formulae:
         * \[
         * \acute{x} = \frac{x - mean(x)}{max(x) - min(x)}.
         * \]
         *
         * @param arr Array containing the input time series.
         */
        fun meanNormInPlace(arr: Array) {
            val ref = meanNormInPlace(arr.reference)
            arr.reference = ref
        }

        /**
         * Calculates a new set of time series with zero mean and standard deviation one.
         *
         * @param arr     Array containing the input time series.
         * @param epsilon Minimum standard deviation to consider. It acts a a gatekeeper for
         * those time series that may be constant or near constant.
         * @return Array with the same dimensions as arr where the time series have been adjusted for zero mean and
         * one as standard deviation.
         */
        fun znorm(arr: Array, epsilon: Double = 0.00000001): Array {
            val refs = znorm(arr.reference, epsilon)
            arr.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Adjusts the time series in the given input and performs z-norm
         * in place (without allocating further memory).
         *
         * @param arr     Array containing the input time series.
         * @param epsilon epsilon Minimum standard deviation to consider. It acts as a gatekeeper for
         * those time series that may be constant or near constant.
         */
        fun znormInPlace(arr: Array, epsilon: Double = 0.00000001) {
            val ref = znormInPlace(arr.reference, epsilon)
            arr.reference = ref
        }
    }
}
