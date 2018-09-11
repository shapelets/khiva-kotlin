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

class DistancesTest {

    @Test
    @Throws(Exception::class)
    fun testEuclidean() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val d = longArrayOf(4, 3, 1, 1)
        Array(timeSeries, d).use { arrayOfTimeSeries ->
            Distances.euclidean(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                Assert.assertEquals(result[0].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[1].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[2].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[3].toDouble(), 8.0, DELTA)
                Assert.assertEquals(result[4].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[5].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[6].toDouble(), 16.0, DELTA)
                Assert.assertEquals(result[7].toDouble(), 8.0, DELTA)
                Assert.assertEquals(result[8].toDouble(), 0.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSquaredEuclidean() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(4, 3, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Distances.squaredEuclidean(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                Assert.assertEquals(result[0].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[1].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[2].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[3].toDouble(), 64.0, DELTA)
                Assert.assertEquals(result[4].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[5].toDouble(), 0.0, DELTA)
                Assert.assertEquals(result[6].toDouble(), 256.0, DELTA)
                Assert.assertEquals(result[7].toDouble(), 64.0, DELTA)
                Assert.assertEquals(result[8].toDouble(), 0.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDwt() {
        val timeSeries = floatArrayOf(1f, 1f, 1f, 1f, 1f, 2f, 2f, 2f, 2f, 2f, 3f, 3f, 3f, 3f, 3f, 4f, 4f, 4f, 4f, 4f, 5f, 5f, 5f, 5f, 5f)
        val dims = longArrayOf(5, 5, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Distances.dtw(arrayOfTimeSeries).use { resultArray ->
                val result = resultArray.getData<FloatArray>()
                val expectedResult = floatArrayOf(0f, 0f, 0f, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 10f, 5f, 0f, 0f, 0f, 15f, 10f, 5f, 0f, 0f, 20f, 15f, 10f, 5f, 0f)
                Assert.assertEquals(expectedResult.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expectedResult[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testHamming() {
        val timeSeries = floatArrayOf(1f, 1f, 1f, 1f, 1f, 2f, 2f, 2f, 2f, 2f, 3f, 3f, 3f, 3f, 3f, 4f, 4f, 4f, 4f, 4f, 5f, 5f, 5f, 5f, 5f)
        val dims = longArrayOf(5, 5, 1, 1)
        Array(timeSeries, dims).use { a ->
            Distances.hamming(a).use { b ->
                val result = b.getData<FloatArray>()
                val expectedResult = floatArrayOf(0f, 0f, 0f, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 5f, 5f, 0f, 0f, 0f, 5f, 5f, 5f, 0f, 0f, 5f, 5f, 5f, 5f, 0f)
                Assert.assertEquals(expectedResult.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expectedResult[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testManhattan() {
        val timeSeries = floatArrayOf(1f, 1f, 1f, 1f, 1f, 2f, 2f, 2f, 2f, 2f, 3f, 3f, 3f, 3f, 3f, 4f, 4f, 4f, 4f, 4f, 5f, 5f, 5f, 5f, 5f)
        val dims = longArrayOf(5, 5, 1, 1)
        Array(timeSeries, dims).use { a ->
            Distances.manhattan(a).use { b ->
                val result = b.getData<FloatArray>()
                val expectedResult = floatArrayOf(0f, 0f, 0f, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 10f, 5f, 0f, 0f, 0f, 15f, 10f, 5f, 0f, 0f, 20f, 15f, 10f, 5f, 0f)
                Assert.assertEquals(expectedResult.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expectedResult[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    companion object {

        private const val DELTA = 1e-6

        @BeforeClass
        fun setUp() {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CPU
        }
    }
}