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

class LinalgTest {

    @Test
    @Throws(Exception::class)
    fun testLls() {
        val tss = doubleArrayOf(4.0, 3.0, -1.0, -2.0)
        val dims = longArrayOf(2, 2, 1, 1)
        val b = doubleArrayOf(3.0, 1.0)
        val dimsB = longArrayOf(2, 1, 1, 1)
        Array(tss, dims).use { a ->
            Array(b, dimsB).use { c ->
                Linalg.lls(a, c).use { d ->
                    val result = d.getData<DoubleArray>()
                    Assert.assertEquals(result[0], 1.0, DELTA)
                    Assert.assertEquals(result[1], 1.0, DELTA)
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
