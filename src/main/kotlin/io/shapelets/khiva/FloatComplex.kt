/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva

import kotlin.math.absoluteValue
import kotlin.math.sign

/**
 * FloatComplex Class.
 *
 * @param real Real value.
 * @param imag Imaginary value.
 */
data class FloatComplex(var real: Float = 0.0f, var imag: Float = 0.0f) {
    /**
     * toString function.
     *
     * @return String with the Object representation.
     */
    override fun toString(): String {
        return "$real ${if (imag.sign >= 0) '+' else '-'} ${imag.absoluteValue}i"
    }
}