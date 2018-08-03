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

class RegularizationTest {

    @Test
    @Throws(Exception::class)
    fun testGroupBySingleColumn() {
        val tss = doubleArrayOf(0.0, 1.0, 1.0, 2.0, 2.0, 3.0, 0.0, 3.0, 3.0, 1.0, 1.0, 2.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Regularization.groupBy(a, 0).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(0.0, 3.0, 1.0, 2.0)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGroupByDoubleKeyColumn() {
        val tss = doubleArrayOf(0.0, 1.0, 1.0, 2.0, 2.0, 3.0, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0, 0.0, 3.0, 3.0, 1.0, 1.0, 2.0)
        val dims = longArrayOf(6, 3, 1, 1)
        Array(tss, dims).use { a ->
            Regularization.groupBy(a, 0, 2).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(0.0, 3.0, 1.0, 2.0)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGroupByDoubleKeyColumn2() {
        val tss = doubleArrayOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 2.0, 3.0, 4.0, 5.0)
        val dims = longArrayOf(5, 3, 1, 1)

        Array(tss, dims).use { a ->
            Regularization.groupBy(a, 0, 2).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(1.0, 2.0, 3.5, 5.0)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGroupByDoubleKeyDoubleValueColumn() {
        val tss = doubleArrayOf(0.0, 0.0, 0.0, 2.0, 2.0, 2.0, 2.0, 2.0, 4.0, 4.0, 0.0, 1.0, 2.0, 3.0, 4.0, 1.0, 1.0, 1.0, 1.0, 1.0)
        val dims = longArrayOf(5, 4, 1, 1)

        Array(tss, dims).use { a ->
            Regularization.groupBy(a, 0, 2, 2).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(1.0, 3.5, 1.0, 1.0)
                Assert.assertArrayEquals(result, expected, DELTA)
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
