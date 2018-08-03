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
 * Khiva Linear Algebra class containing linear algebra methods.
 */
class Linalg : Library() {
    companion object {
        @JvmStatic
        private external fun lls(refA: Long, refB: Long): LongArray

        /**
         * Calculates the minimum norm least squares solution \(x\) \((\left\lVert{A·x - b}\right\rVert^2)\) to
         * \(A·x = b\). This function uses the singular value decomposition function of Arrayfire. The actual formula that
         * this function computes is \(x = V·D\dagger·U^T·b\). Where \(U\) and \(V\) are orthogonal matrices and
         * \(D\dagger\) contains the inverse values of the singular values contained in D if they are not zero, and zero
         * otherwise.
         *
         * @param arrA Coefficients of the linear equation problem to solve.
         * @param arrB Array with the measured values.
         * @return Contains the solution to the linear equation problem minimizing the norm 2.
         */
        fun lls(arrA: Array, arrB: Array): Array {
            val refs = lls(arrA.reference, arrB.reference)
            arrA.reference = refs[0]
            arrB.reference = refs[1]
            return Array(refs[2])
        }
    }
}
