/*
 * Copyright (c) 2018 Shapelets.io
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 */

package io.shapelets.khiva

/**
 * Khiva Polynomial class containing a number of polynomial methods.
 */
class Polynomial : Library() {
    companion object {
        @JvmStatic
        private external fun polyfit(refX: Long, refY: Long, deg: Int): LongArray

        @JvmStatic
        private external fun roots(ref: Long): LongArray

        /**
         * Least squares polynomial fit. Fit a polynomial \(p(x) = p(0) * x^{deg} + ... + p(deg)\) of degree \(deg\)
         * to points \((x, y)\). Returns a vector of coefficients \(p\) that minimises the squared error.
         *
         * @param x   Array containing the x-coordinates of the M sample points \((x(i), y(i))\).
         * @param y   Array containing the y-coordinates of the sample points.
         * @param deg Degree of the fitting polynomial.
         * @return Array with the polynomial coefficients, highest power first.
         */
        fun polyfit(x: Array, y: Array, deg: Int): Array {
            val refs = polyfit(x.reference, y.reference, deg)
            x.reference = refs[0]
            y.reference = refs[1]
            return Array(refs[2])
        }

        /**
         * Calculates the roots of a polynomial with coefficients given in \(ref\). The values in the rank-1 array
         * \(ref\) are coefficients of a polynomial. If the length of \(ref\) is \(n+1\) then the polynomial is described
         * by:
         * \[
         * ref(0) * x^n + ref(1) * x^{n-1} + ... + ref(n-1) * x + ref(n)
         * \]
         *
         * @param p Array of polynomial coefficients.
         * @return Array with the roots of the polynomial.
         */
        fun roots(p: Array): Array {
            val refs = roots(p.reference)
            p.reference = refs[0]
            return Array(refs[1])
        }
    }
}
