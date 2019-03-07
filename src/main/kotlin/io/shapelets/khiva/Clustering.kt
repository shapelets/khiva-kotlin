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
 * Khiva Clustering class containing several dimensionality reduction methods.
 */
class Clustering : Library() {
    companion object {

        @JvmStatic
        private external fun kMeans(tss: Long, k: Int, tolerance: Float, maxInterations: Int): LongArray

        @JvmStatic
        private external fun kShape(tss: Long, k: Int, tolerance: Float, maxInterations: Int): LongArray

        /**
         * Calculates the KMeans algorithm.
         *
         * [1] S. Lloyd. 1982. Least squares quantization in PCM. IEEE Transactions on Information Theory, 28, 2,
         * Pages 129-137.
         *
         * @param tss               The set of time series to be clusterized.
         * @param k                 The number of centroids.
         * @param tolerance         The maximum error tolerance.
         * @param maxIterations     The maximum number of iterations.
         *
         * @return An Array of arrays with the resulting centroids and labels.
         */
        fun kMeans(tss: Array, k: Int, tolerance: Float, maxInterations: Int): kotlin.Array<Array> {
            val refs = Clustering.kMeans(tss.reference, k, tolerance, maxInterations)
            tss.reference = refs[0]
            return arrayOf(Array(refs[1]), Array(refs[2]))
        }

        /**
         * Calculates the KShape algorithm.
         *
         * [1] John Paparrizos and Luis Gravano. 2016. k-Shape: Efficient and Accurate Clustering of Time Series.
         * SIGMOD Rec. 45, 1 (June 2016), 69-76.
         *
         * @param tss               The set of time series to be clusterized.
         * @param k                 The number of centroids.
         * @param tolerance         The maximum error tolerance.
         * @param maxIterations     The maximum number of iterations.
         *
         * @return An Array of arrays with the resulting centroids and labels.
         */
        fun kShape(tss: Array, k: Int, tolerance: Float, maxInterations: Int): kotlin.Array<Array>{
            val refs = Clustering.kShape(tss.reference, k, tolerance, maxInterations)
            tss.reference = refs[0]
            return arrayOf(Array(refs[1]), Array(refs[2]))
        }
    }
}
