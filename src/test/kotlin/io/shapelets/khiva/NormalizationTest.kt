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

class NormalizationTest {

    @Test
    @Throws(Exception::class)
    fun testZnorm() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.znorm(a).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(-1.341640786499870, -0.447213595499958, 0.447213595499958, 1.341640786499870)
                for (i in expected.indices) {
                    Assert.assertEquals(result[i], expected[i], DELTA)
                    Assert.assertEquals(result[i + 4], expected[i], DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testZnormInPlace() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.znormInPlace(a)
            val result = a.getData<DoubleArray>()
            Assert.assertEquals(result[0], -1.341640786499870, DELTA)
            Assert.assertEquals(result[1], -0.447213595499958, DELTA)
            Assert.assertEquals(result[2], 0.447213595499958, DELTA)
            Assert.assertEquals(result[3], 1.341640786499870, DELTA)

            Assert.assertEquals(result[4], -1.341640786499870, DELTA)
            Assert.assertEquals(result[5], -0.447213595499958, DELTA)
            Assert.assertEquals(result[6], 0.447213595499958, DELTA)
            Assert.assertEquals(result[7], 1.341640786499870, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMaxMinNorm() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.maxMinNorm(a, 2.0, 1.0).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(1.0, 1.3333333333333, 1.66666667, 2.0, 1.0, 1.3333333333333, 1.66666667, 2.0)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMaxMinNormInPlace() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.maxMinNormInPlace(a, 2.0, 1.0)
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(1.0, 1.3333333333333, 1.66666667, 2.0, 1.0, 1.3333333333333, 1.66666667, 2.0)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun decimalScalingNorm() {
        val tss = doubleArrayOf(0.0, 1.0, -2.0, 3.0, 40.0, 50.0, 60.0, -70.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.decimalScalingNorm(a).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(0.0, 0.1, -0.2, 0.3, 0.4, 0.5, 0.6, -0.7)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun decimalScalingNormInPlace() {
        val tss = doubleArrayOf(0.0, 1.0, -2.0, 3.0, 40.0, 50.0, 60.0, -70.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.decimalScalingNormInPlace(a)
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(0.0, 0.1, -0.2, 0.3, 0.4, 0.5, 0.6, -0.7)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMeanNorm() {
        val tss = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.meanNorm(a).use { b ->
                val result = b.getData<FloatArray>()
                val expectedResult = floatArrayOf(-0.5f, -0.166666667f, 0.166666667f, 0.5f, -0.5f, -0.166666667f, 0.166666667f, 0.5f)
                Assert.assertEquals(expectedResult.size.toDouble(), result.size.toDouble(), DELTA)
                for (i in result.indices) {
                    Assert.assertEquals(expectedResult[i].toDouble(), result[i].toDouble(), DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMeanNormInPlace() {
        val tss = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Normalization.meanNormInPlace(a)
            val result = a.getData<FloatArray>()
            val expectedResult = floatArrayOf(-0.5f, -0.166666667f, 0.166666667f, 0.5f, -0.5f, -0.166666667f, 0.166666667f, 0.5f)
            Assert.assertEquals(expectedResult.size.toDouble(), result.size.toDouble(), DELTA)
            for (i in result.indices) {
                Assert.assertEquals(expectedResult[i].toDouble(), result[i].toDouble(), DELTA)
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
