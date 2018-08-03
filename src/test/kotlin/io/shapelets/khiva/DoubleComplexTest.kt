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
import org.junit.Test

class DoubleComplexTest {

    @Test
    @Throws(Exception::class)
    fun testConstructor() {
        val dc = DoubleComplex()
        Assert.assertEquals(dc.real, 0.0, DELTA)
        Assert.assertEquals(dc.imag, 0.0, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun testSettersAndGetters() {
        val dc = DoubleComplex()
        dc.real = 1.0
        dc.imag = 2.0
        Assert.assertEquals(dc.real, 1.0, DELTA)
        Assert.assertEquals(dc.imag, 2.0, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun testToString() {
        val dc = DoubleComplex()
        dc.real = 1.0
        dc.imag = 2.0
        Assert.assertEquals(dc.toString(), "1.0 + 2.0i")
        dc.imag = -2.0
        Assert.assertEquals(dc.toString(), "1.0 - 2.0i")
    }

    companion object {
        private const val DELTA = 1e-6
    }
}
