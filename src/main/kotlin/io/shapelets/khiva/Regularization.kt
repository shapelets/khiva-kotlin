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
 * Khiva Regularization class containing different regularization methods.
 */
class Regularization : Library() {
    companion object {
        @JvmStatic
        private external fun groupBy(ref: Long, aggregationFunction: Int, nColumnsKey: Int, nColumnsValue: Int): LongArray

        /**
         * Group by operation in the input array using nColumnsKey columns as group keys and nColumnsValue columns as
         * values. The data is expected to be sorted. The aggregation function determines the operation to aggregate the
         * values.
         *
         * @param arr                 Expects an input array whose dimension zero is the length of the time
         * series (all the same) and dimension one indicates the number of time series.
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
         * @param nColumnsKey         Number of columns conforming the key.
         * @param nColumnsValue       Number of columns conforming the value (they are expected to be consecutive to the
         * column keys).
         * @return Array with the values of the group keys aggregated using the aggregationFunction.
         */
        fun groupBy(arr: Array, aggregationFunction: Int, nColumnsKey: Int = 1, nColumnsValue: Int = 1): Array {
            val refs = groupBy(arr.reference, aggregationFunction, nColumnsKey, nColumnsValue)
            arr.reference = refs[0]
            return Array(refs[1])
        }
    }
}
