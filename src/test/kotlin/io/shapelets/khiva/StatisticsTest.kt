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

class StatisticsTest {

    @Test
    @Throws(Exception::class)
    fun testCovarianceUnbiased() {
        val timeSeries = floatArrayOf(-2.1f, -1f, 4.3f, 3f, 1.1f, 0.12f, 3f, 1.1f, 0.12f)
        val dims = longArrayOf(3, 3, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.covariance(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(11.70999999f, -4.286f, -4.286f, -4.286f, 2.14413333f, 2.14413333f, -4.286f, 2.14413333f, 2.14413333f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCovarianceBiased() {
        val timeSeries = floatArrayOf(-2.1f, -1f, 4.3f, 3f, 1.1f, 0.12f, 3f, 1.1f, 0.12f)
        val dims = longArrayOf(3, 3, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.covariance(arrayOfTimeSeries, false).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(7.80666667f, -2.85733333f, -2.85733333f, -2.85733333f, 1.42942222f, 1.42942222f, -2.85733333f, 1.42942222f, 1.42942222f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testKurtosis() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 2f, 2f, 2f, 20f, 30f, 25f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.kurtosis(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(-1.2f, -2.66226722f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLjungBox() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.ljungBox(arrayOfTimeSeries, 3).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(6.4400f, 6.4400f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMoment() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 0f, 1f, 2f, 3f, 4f, 5f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.moment(arrayOfTimeSeries, 2).use { b ->
                var result = b.getData<FloatArray>()
                val expected = floatArrayOf(9.166666666f, 9.166666666f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), DELTA)
                }
                result = Statistics.moment(arrayOfTimeSeries, 4).getData()
                expected[0] = 163.1666666666f
                expected[1] = 163.1666666666f

                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), 1e-2)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testQuantile() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        val q = floatArrayOf(0.1f, 0.2f)
        val dimsQ = longArrayOf(2, 1, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Array(q, dimsQ).use { qa ->
                Statistics.quantile(arrayOfTimeSeries, qa).use { b ->
                    val result = b.getData<FloatArray>()
                    val expected = floatArrayOf(0.5f, 1.0f, 6.5f, 7.0f)
                    Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                    for (i in result.indices) {
                        Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                    }
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testQuantileCut2() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.quantilesCut(arrayOfTimeSeries, 2.0f).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(-1.0E-8f, -1.0E-8f, -1.0E-8f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 2.5f, 5.0f, 5.0f, 5.0f, 6.0f, 6.0f, 6.0f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 11.0f, 11.0f, 11.0f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testQuantileCut3() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.quantilesCut(arrayOfTimeSeries, 3.0f).use { b ->
                val result = b.getData<FloatArray>()

                val expected = floatArrayOf(-1.0E-8f, -1.0E-8f, 1.6666667f, 1.6666667f, 3.3333335f, 3.3333335f, 1.6666667f, 1.6666667f, 3.3333335f, 3.3333335f, 5.0f, 5.0f, 6.0f, 6.0f, 7.6666665f, 7.6666665f, 9.333333f, 9.333333f, 7.6666665f, 7.6666665f, 9.333333f, 9.333333f, 11.0f, 11.0f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testQuantileCut7() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.quantilesCut(arrayOfTimeSeries, 7.0f).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(-1.0E-8f, 0.71428573f, 1.4285715f, 2.857143f, 3.5714288f, 4.2857146f, 0.71428573f, 1.4285715f, 2.1428573f, 3.5714288f, 4.2857146f, 5.0f, 6.0f, 6.714286f, 7.4285717f, 8.857143f, 9.571428f, 10.285714f, 6.714286f, 7.4285717f, 8.142858f, 9.571428f, 10.285714f, 11.0f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSampleStdev() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 2f, 2f, 2f, 20f, 30f, 25f)
        val dims = longArrayOf(6, 2, 1, 1)

        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.sampleStdev(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1.870828693f, 12.988456413f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSkewness() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 2f, 2f, 2f, 20f, 30f, 25f)
        val dims = longArrayOf(6, 2, 1, 1)

        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Statistics.skewness(arrayOfTimeSeries).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0.0f, 0.236177069879499f)
                Assert.assertEquals(expected.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expected[i].toDouble(), result[i].toDouble(), 1e-2)
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
