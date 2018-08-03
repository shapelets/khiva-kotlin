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

class FloatComplexTest {

    @Test
    @Throws(Exception::class)
    fun testConstructor() {
        val fc = FloatComplex()
        Assert.assertEquals(fc.real.toDouble(), 0.0, DELTA)
        Assert.assertEquals(fc.imag.toDouble(), 0.0, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun testSettersAndGetters() {
        val fc = FloatComplex()
        fc.real = 1f
        fc.imag = 2f
        Assert.assertEquals(fc.real.toDouble(), 1.0, DELTA)
        Assert.assertEquals(fc.imag.toDouble(), 2.0, DELTA)
    }

    @Test
    @Throws(Exception::class)
    fun testToString() {
        val fc = FloatComplex()
        fc.real = 1f
        fc.imag = 2f
        Assert.assertEquals(fc.toString(), "1.0 + 2.0i")
        fc.imag = -2f
        Assert.assertEquals(fc.toString(), "1.0 - 2.0i")
    }

    companion object {
        private const val DELTA = 1e-6
    }
}
