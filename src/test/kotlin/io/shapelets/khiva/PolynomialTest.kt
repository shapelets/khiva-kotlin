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

class PolynomialTest {

    @Test
    @Throws(Exception::class)
    fun testPolyfit1() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
        val dims = longArrayOf(6, 1, 1, 1)

        Array(tss, dims).use { x ->
            Array(tss, dims).use { y ->
                Polynomial.polyfit(x, y, 1).use { b ->
                    val result = b.getData<DoubleArray>()
                    val expected = doubleArrayOf(1.0, 0.0)
                    Assert.assertArrayEquals(result, expected, DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPolyfit3() {
        val tss1 = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
        val tss2 = doubleArrayOf(0.0, 0.8, 0.9, 0.1, -0.8, -1.0)
        val dims = longArrayOf(6, 1, 1, 1)

        Array(tss1, dims).use { x ->
            Array(tss2, dims).use { y ->
                Polynomial.polyfit(x, y, 3).use { b ->
                    val result = b.getData<DoubleArray>()
                    val expected = doubleArrayOf(0.08703704, -0.81349206, 1.69312169, -0.03968254)
                    Assert.assertArrayEquals(result, expected, DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRoots() {
        val tss = doubleArrayOf(5.0, -20.0, 5.0, 50.0, -20.0, -40.0)
        val dims = longArrayOf(6, 1, 1, 1)

        Array(tss, dims).use { p ->
            Polynomial.roots(p).use { b ->
                val result = b.getData<kotlin.Array<FloatComplex>>()
                val expected = arrayOf(FloatComplex(2f, 0f), FloatComplex(2f, 0f), FloatComplex(2f, 0f), FloatComplex(-1f, 0f), FloatComplex(-1f, 0f))
                for (i in 0..4) {
                    Assert.assertEquals(expected[i].real.toDouble(), result[i].real.toDouble(), 1e-2)
                    Assert.assertEquals(expected[i].imag.toDouble(), result[i].imag.toDouble(), 1e-2)
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
