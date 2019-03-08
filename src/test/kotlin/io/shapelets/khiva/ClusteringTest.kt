/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva

import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

class ClusteringTest {

    @Test
    @Throws(Exception::class)
    fun testKMeans() {
        val tss = floatArrayOf(0.0f, 1.0f, 2.0f, 3.0f, 6.0f, 7.0f, 8.0f, 9.0f, 2.0f, -2.0f, 4.0f, -4.0f,
                8.0f, 5.0f, 3.0f, 1.0f, 15.0f, 10.0f, 5.0f, 0.0f, 7.0f, -7.0f, 1.0f, -1.0f)
        val dims = longArrayOf(4, 6, 1, 1)

        val tolerance = 1e-10f
        val maxIterations = 100
        val k = 3

        Array(tss, dims).use { a ->
            val kMeansResult = Clustering.kMeans(a, k, tolerance, maxIterations)
            val result = kMeansResult[0].getData<FloatArray>()
            val expected = floatArrayOf(0.0f, 0.1667f, 0.3333f, 0.5f,
                    1.5f, -1.5f, 0.8333f, -0.8333f, 4.8333f, 3.6667f, 2.6667f, 1.6667f)
            for (i in 0..3) {
                Assert.assertEquals(expected[i] + expected[i + 4] + expected[i + 8],
                        result[i] + result[i + 4] + result[i + 8], 1e-4f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testKShape() {
        val tss = floatArrayOf(1.0f,  2.0f,  3.0f,  4.0f,  5.0f,   6.0f,  7.0f,  0.0f, 10.0f, 4.0f, 5.0f, 7.0f,
                -3.0f, 0.0f,  -1.0f, 15.0f, -12.0f, 8.0f,  9.0f,  4.0f, 5.0f,  2.0f, 8.0f, 7.0f,
                -6.0f, -1.0f, 2.0f,  9.0f,  -5.0f,  -5.0f, -6.0f, 7.0f, 9.0f,  9.0f, 0.0f)
        val dims = longArrayOf(7, 5, 1, 1)

        val tolerance = 1e-10f
        val maxIterations = 100
        val k = 3

        Array(tss, dims).use { a ->
            val kShapeResult = Clustering.kShape(a, k, tolerance, maxIterations)
            val result = kShapeResult[0].getData<FloatArray>()
            val expected = floatArrayOf(-0.5234f, 0.1560f, -0.3627f, -1.2764f, -0.7781f,  0.9135f,  1.8711f,
                    -0.7825f, 1.5990f,  0.1701f,  0.4082f,  0.8845f, -1.4969f, -0.7825f,
                    -0.6278f, 1.3812f, -2.0090f,  0.5022f,  0.6278f, -0.0000f,  0.1256f)
            Assert.assertArrayEquals(result, expected, 1e-4f)
        }
    }

    companion object {
        @BeforeClass
        fun setUp() {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CPU
        }
    }
}