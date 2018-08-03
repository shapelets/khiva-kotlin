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

class DimensionalityTest {

    @Test
    @Throws(Exception::class)
    fun testRamerDouglasPeucker() {
        val tss = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.ramerDouglasPeucker(a, 1.0).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0f, 2f, 3f, 6f, 9f, 0f, -0.1f, 5.0f, 8.1f, 9.0f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testVisvalingam() {
        val tss = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.visvalingam(a, 5).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0f, 2f, 5f, 7f, 9f, 0f, -0.1f, 7.0f, 9.0f, 9.0f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }

    }

    @Test
    @Throws(Exception::class)
    fun testPaa() {
        val tss = floatArrayOf(0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f, 0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.paa(a, 5).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0.05f, 2.45f, 6.5f, 8.55f, 9.0f, 0.05f, 2.45f, 6.5f, 8.55f, 9.0f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSax() {
        val tss = floatArrayOf(0.05f, 2.45f, 6.5f, 8.55f, 9.0f, 0.05f, 2.45f, 6.5f, 8.55f, 9.0f)
        val dims = longArrayOf(5, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.sax(a, 3).use { b ->
                val result = b.getData<IntArray>()
                val expected = intArrayOf(0, 0, 1, 2, 2, 0, 0, 1, 2, 2)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPip() {
        val tss = floatArrayOf(0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.pip(a, 6).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0.0f, 2.0f, 3.0f, 6.0f, 7.0f, 9.0f, 0.0f, -0.1f, 5.0f, 8.1f, 9.0f, 9.0f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPLABottomUp() {
        val tss = floatArrayOf(0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.PLABottomUp(a, 1f).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0f, 1f, 2f, 3f, 4f, 7f, 8f, 9f, 0f, 0.1f, -0.1f, 5f, 6f, 9f, 9f, 9f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPLASlidingWindow() {
        val tss = floatArrayOf(0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 0.0f, 0.1f, -0.1f, 5.0f, 6.0f, 7.0f, 8.1f, 9.0f, 9.0f, 9.0f)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Dimensionality.PLASlidingWindow(a, 1f).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(0f, 2f, 3f, 7f, 8f, 9f, 0f, -0.1f, 5f, 9f, 9f, 9f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    companion object {

        @BeforeClass
        fun setUp() {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CPU
        }
    }
}
