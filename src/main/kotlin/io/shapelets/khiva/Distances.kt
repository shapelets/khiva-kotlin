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
 * Khiva Distances class containing distances methods.
 */
class Distances : Library() {
    companion object {
        @JvmStatic
        private external fun euclidean(ref: Long): LongArray

        @JvmStatic
        private external fun dtw(ref: Long): LongArray

        @JvmStatic
        private external fun hamming(ref: Long): LongArray

        @JvmStatic
        private external fun manhattan(ref: Long): LongArray

        @JvmStatic
        private external fun squaredEuclidean(ref: Long): LongArray

        /**
         * Calculates euclidean distances between time series.
         *
         * @param tss Array containing the input time series.
         * @return Array with an upper triangular matrix where each position corresponds to the distance between two
         * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance
         * between time series 0 and time series 1.
         */
        fun euclidean(tss: Array): Array {
            val refs = euclidean(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the Dynamic Time Warping Distance.
         *
         * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
         * dimension one indicates the number of time series.
         * @return Array with an upper triangular matrix where each position corresponds to the distance between
         * two time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the
         * distance between time series 0 and time series 1.
         */
        fun dtw(tss: Array): Array {
            val refs = dtw(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates Hamming distances between time series.
         *
         * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
         * dimension one indicates the number of time series.
         * @return Array with an upper triangular matrix where each position corresponds to the
         * distance between two time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the
         * distance between time series 0 and time series 1.
         */
        fun hamming(tss: Array): Array {
            val refs = hamming(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates Manhattan distances between time series.
         *
         * @param tss Expects an input array whose dimension zero is the length of the time series (all the same) and
         * dimension one indicates the number of time series.
         * @return Array with an upper triangular matrix where each position corresponds to the distance between two
         * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance between time
         * series 0 and time series 1.
         */
        fun manhattan(tss: Array): Array {
            val refs = manhattan(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }

        /**
         * Calculates the non squared version of the euclidean distance.
         *
         * @param tss Array containing the input time series.
         * @return Array with an upper triangular matrix where each position corresponds to the distance between two
         * time series. Diagonal elements will be zero. For example: Position row 0 column 1 records the distance
         * between time series 0 and time series 1.
         */
        fun squaredEuclidean(tss: Array): Array {
            val refs = squaredEuclidean(tss.reference)
            tss.reference = refs[0]
            return Array(refs[1])
        }
    }
}
