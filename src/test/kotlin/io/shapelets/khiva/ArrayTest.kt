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

class ArrayTest {

    @Test
    fun testDoubleNull() {
        val tss: DoubleArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testDoubleMismatchingDims() {
        val tss = doubleArrayOf(1.0, 2.0)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testFloatNull() {
        val tss: FloatArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testFloatMismatchingDims() {
        val tss = floatArrayOf(1f, 2f)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testIntNull() {
        val tss: IntArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testIntMismatchingDims() {
        val tss = intArrayOf(1, 2)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testFloatComplexNull() {
        val tss: kotlin.Array<FloatComplex>? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testFloatComplexMismatchingDims() {
        val tss = arrayOf(FloatComplex(1f, 2f), FloatComplex(3f, 4f))
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testDoubleComplexNull() {
        val tss: kotlin.Array<DoubleComplex>? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testDoubleComplexMismatchingDims() {
        val tss = arrayOf(DoubleComplex(1.0, 2.0), DoubleComplex(3.0, 4.0))
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testBooleanNull() {
        val tss: BooleanArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testBooleanMismatchingDims() {
        val tss = booleanArrayOf(true, false)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testShortNull() {
        val tss: ShortArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testShortMismatchingDims() {
        val tss = shortArrayOf(1, 2)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testByteNull() {
        val tss: ByteArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testByteMismatchingDims() {
        val tss = byteArrayOf(1, 2)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    fun testLongNull() {
        val tss: LongArray? = null
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null elems object provided")
        }

    }

    @Test
    fun testLongMismatchingDims() {
        val tss = longArrayOf(1, 2)
        val dims = longArrayOf(1, 1, 1, 1)
        try {
            Array(tss, dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Mismatching dims and array size")
        }

    }

    @Test
    @Throws(Exception::class)
    fun testByte() {
        val tss = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<ByteArray>()
            val expected = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testShort() {
        val tss = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<ShortArray>()
            val expected = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testUnsignedShort() {
        val tss = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            a.`as`(Array.Dtype.u16).use { b ->
                val result = b.getData<ShortArray>()
                val expected = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLong() {
        val tss = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<LongArray>()
            val expected = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testUnsignedLong() {
        val tss = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            a.`as`(Array.Dtype.u64).use { b ->
                val result = b.getData<LongArray>()
                val expected = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    fun testDim4Null() {
        val dims: LongArray? = null
        try {
            Array.dim4(dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "Null dimensions object provided")
        }
    }

    @Test
    fun testDim4FiveDimensions() {
        val dims = longArrayOf(1, 1, 1, 1, 1)
        try {
            Array.dim4(dims)
        } catch (e: Exception) {
            Assert.assertEquals(e.message, "ArrayFire supports up to 4 dimensions only")
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGetDims() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(2, 2, 2, 1)
        Array(tss, dims).use { a ->
            val result = a.dims
            Assert.assertArrayEquals(result, dims)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testReal1D() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testReal2D() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testReal3D() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(2, 2, 2, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testReal4D() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0)
        val dims = longArrayOf(2, 2, 2, 2)
        Array(tss, dims).use { a ->
            val result = a.getData<DoubleArray>()
            val expected = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0)
            Assert.assertArrayEquals(result, expected, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFloatComplex1D() {
        val tss = arrayOf(FloatComplex(1f, 5f), FloatComplex(2f, 6f), FloatComplex(3f, 7f), FloatComplex(4f, 8f))
        val dims = longArrayOf(4, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<FloatComplex>>()
            val expected = arrayOf(FloatComplex(1f, 5f), FloatComplex(2f, 6f), FloatComplex(3f, 7f), FloatComplex(4f, 8f))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFloatComplex2D() {
        val tss = arrayOf(FloatComplex(1f, 5f), FloatComplex(2f, 6f), FloatComplex(3f, 7f), FloatComplex(4f, 8f), FloatComplex(9f, 13f), FloatComplex(10f, 14f), FloatComplex(11f, 15f), FloatComplex(12f, 16f))
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<FloatComplex>>()
            val expected = arrayOf(FloatComplex(1f, 5f), FloatComplex(2f, 6f), FloatComplex(3f, 7f), FloatComplex(4f, 8f), FloatComplex(9f, 13f), FloatComplex(10f, 14f), FloatComplex(11f, 15f), FloatComplex(12f, 16f))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFloatComplex3D() {
        val tss = arrayOf(FloatComplex(1f, 1f), FloatComplex(2f, 2f), FloatComplex(3f, 3f), FloatComplex(4f, 4f), FloatComplex(5f, 5f), FloatComplex(6f, 6f), FloatComplex(7f, 7f), FloatComplex(8f, 8f))
        val dims = longArrayOf(2, 2, 2, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<FloatComplex>>()
            val expected = arrayOf(FloatComplex(1f, 1f), FloatComplex(2f, 2f), FloatComplex(3f, 3f), FloatComplex(4f, 4f), FloatComplex(5f, 5f), FloatComplex(6f, 6f), FloatComplex(7f, 7f), FloatComplex(8f, 8f))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFloatComplex4D() {
        val tss = arrayOf(FloatComplex(1f, 1f), FloatComplex(2f, 2f), FloatComplex(3f, 3f), FloatComplex(4f, 4f), FloatComplex(5f, 5f), FloatComplex(6f, 6f), FloatComplex(7f, 7f), FloatComplex(8f, 8f), FloatComplex(9f, 9f), FloatComplex(10f, 10f), FloatComplex(11f, 11f), FloatComplex(12f, 12f), FloatComplex(13f, 13f), FloatComplex(14f, 14f), FloatComplex(15f, 15f), FloatComplex(16f, 16f))
        val dims = longArrayOf(2, 2, 2, 2)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<FloatComplex>>()
            val expected = arrayOf(FloatComplex(1f, 1f), FloatComplex(2f, 2f), FloatComplex(3f, 3f), FloatComplex(4f, 4f), FloatComplex(5f, 5f), FloatComplex(6f, 6f), FloatComplex(7f, 7f), FloatComplex(8f, 8f), FloatComplex(9f, 9f), FloatComplex(10f, 10f), FloatComplex(11f, 11f), FloatComplex(12f, 12f), FloatComplex(13f, 13f), FloatComplex(14f, 14f), FloatComplex(15f, 15f), FloatComplex(16f, 16f))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDoubleComplex1D() {
        val tss = arrayOf(DoubleComplex(1.0, 5.0), DoubleComplex(2.0, 6.0), DoubleComplex(3.0, 7.0), DoubleComplex(4.0, 8.0))
        val dims = longArrayOf(4, 1, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<DoubleComplex>>()
            val expected = arrayOf(DoubleComplex(1.0, 5.0), DoubleComplex(2.0, 6.0), DoubleComplex(3.0, 7.0), DoubleComplex(4.0, 8.0))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDoubleComplex2D() {
        val tss = arrayOf(DoubleComplex(1.0, 5.0), DoubleComplex(2.0, 6.0), DoubleComplex(3.0, 7.0), DoubleComplex(4.0, 8.0), DoubleComplex(9.0, 13.0), DoubleComplex(10.0, 14.0), DoubleComplex(11.0, 15.0), DoubleComplex(12.0, 16.0))
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<DoubleComplex>>()
            val expected = arrayOf(DoubleComplex(1.0, 5.0), DoubleComplex(2.0, 6.0), DoubleComplex(3.0, 7.0), DoubleComplex(4.0, 8.0), DoubleComplex(9.0, 13.0), DoubleComplex(10.0, 14.0), DoubleComplex(11.0, 15.0), DoubleComplex(12.0, 16.0))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDoubleComplex3D() {
        val tss = arrayOf(DoubleComplex(1.0, 1.0), DoubleComplex(2.0, 2.0), DoubleComplex(3.0, 3.0), DoubleComplex(4.0, 4.0), DoubleComplex(5.0, 5.0), DoubleComplex(6.0, 6.0), DoubleComplex(7.0, 7.0), DoubleComplex(8.0, 8.0))
        val dims = longArrayOf(2, 2, 2, 1)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<DoubleComplex>>()
            val expected = arrayOf(DoubleComplex(1.0, 1.0), DoubleComplex(2.0, 2.0), DoubleComplex(3.0, 3.0), DoubleComplex(4.0, 4.0), DoubleComplex(5.0, 5.0), DoubleComplex(6.0, 6.0), DoubleComplex(7.0, 7.0), DoubleComplex(8.0, 8.0))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDoubleComplex4D() {
        val tss = arrayOf(DoubleComplex(1.0, 1.0), DoubleComplex(2.0, 2.0), DoubleComplex(3.0, 3.0), DoubleComplex(4.0, 4.0), DoubleComplex(5.0, 5.0), DoubleComplex(6.0, 6.0), DoubleComplex(7.0, 7.0), DoubleComplex(8.0, 8.0), DoubleComplex(9.0, 9.0), DoubleComplex(10.0, 10.0), DoubleComplex(11.0, 11.0), DoubleComplex(12.0, 12.0), DoubleComplex(13.0, 13.0), DoubleComplex(14.0, 14.0), DoubleComplex(15.0, 15.0), DoubleComplex(16.0, 16.0))
        val dims = longArrayOf(2, 2, 2, 2)
        Array(tss, dims).use { a ->
            val result = a.getData<kotlin.Array<DoubleComplex>>()
            val expected = arrayOf(DoubleComplex(1.0, 1.0), DoubleComplex(2.0, 2.0), DoubleComplex(3.0, 3.0), DoubleComplex(4.0, 4.0), DoubleComplex(5.0, 5.0), DoubleComplex(6.0, 6.0), DoubleComplex(7.0, 7.0), DoubleComplex(8.0, 8.0), DoubleComplex(9.0, 9.0), DoubleComplex(10.0, 10.0), DoubleComplex(11.0, 11.0), DoubleComplex(12.0, 12.0), DoubleComplex(13.0, 13.0), DoubleComplex(14.0, 14.0), DoubleComplex(15.0, 15.0), DoubleComplex(16.0, 16.0))
            Assert.assertArrayEquals(result, expected)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGetType() {
        val tss = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a -> Assert.assertEquals(a.type, Array.Dtype.s64) }
    }

    @Test
    @Throws(Exception::class)
    fun testAdd() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            Array(data, dims).use { b ->
                (a + b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(2f, 4f, 6f, 8f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMul() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            Array(data, dims).use { b ->
                (a * b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(1f, 4f, 9f, 16f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSub() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            Array(data, dims).use { b ->
                (a - b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(0f, 0f, 0f, 0f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testDiv() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            Array(data, dims).use { b ->
                a.div(b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(1f, 1f, 1f, 1f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMod() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                (a % b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(1f, 0f, 1f, 0f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPow() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.pow(b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(1f, 4f, 9f, 16f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLt() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.lt(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, false, false, false)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGt() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.gt(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(false, false, true, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLe() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.le(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, true, false, false)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testGe() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(2f, 2f, 2f, 2f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.ge(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(false, true, true, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testEq() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(1f, 2f, 3f, 5f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.eq(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, true, true, false)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testNe() {
        val dataA = floatArrayOf(1f, 2f, 3f, 4f)
        val dataB = floatArrayOf(1f, 2f, 3f, 5f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                a.ne(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(false, false, false, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBitAnd() {
        val dataA = booleanArrayOf(true, true, true, true)
        val dataB = booleanArrayOf(true, false, true, false)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                (a and b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, false, true, false)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBitOr() {
        val dataA = booleanArrayOf(true, true, true, true)
        val dataB = booleanArrayOf(true, false, true, false)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                (a or b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, true, true, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBitXor() {
        val dataA = booleanArrayOf(true, true, true, true)
        val dataB = booleanArrayOf(true, false, true, false)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                (a xor b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(false, true, false, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBitShiftL() {
        val data = intArrayOf(2, 4, 6, 8)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            (a shl 1).use { b ->
                val result = b.getData<IntArray>()
                val expected = intArrayOf(4, 8, 12, 16)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBitShiftR() {
        val data = intArrayOf(2, 4, 6, 8)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            (a shr 1).use { b ->
                val result = b.getData<IntArray>()
                val expected = intArrayOf(1, 2, 3, 4)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testNot() {
        val data = booleanArrayOf(true, false)
        val dims = longArrayOf(2, 1, 1, 1)
        Array(data, dims).use { a ->
            a.not().use { b ->
                val result = b.getData<BooleanArray>()
                val expected = booleanArrayOf(false, true)
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testTransposeConjugate() {
        val data = arrayOf(DoubleComplex(0.0, -1.0), DoubleComplex(2.0, 1.0), DoubleComplex(4.0, 2.0), DoubleComplex(0.0, -2.0))
        val dims = longArrayOf(2, 2, 1, 1)
        Array(data, dims).use { a ->
            a.transpose(true).use { b ->
                val result = b.getData<kotlin.Array<DoubleComplex>>()
                val expected = arrayOf(DoubleComplex(0.0, 1.0), DoubleComplex(4.0, -2.0), DoubleComplex(2.0, -1.0), DoubleComplex(0.0, 2.0))
                Assert.assertArrayEquals(result, expected)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testTranspose() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dims = longArrayOf(2, 2, 1, 1)
        Array(data, dims).use { a ->
            a.transpose().use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1f, 3f, 2f, 4f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCol() {
        val data = floatArrayOf(1f, 3f, 2f, 4f)
        val dims = longArrayOf(2, 2, 1, 1)
        Array(data, dims).use { a ->
            a.col(0).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1f, 3f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCols() {
        val data = floatArrayOf(1f, 4f, 2f, 5f, 3f, 6f)
        val dims = longArrayOf(2, 3, 1, 1)
        Array(data, dims).use { a ->
            a.cols(0, 1).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1f, 4f, 2f, 5f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRow() {
        val data = floatArrayOf(1f, 3f, 2f, 4f)
        val dims = longArrayOf(2, 2, 1, 1)
        Array(data, dims).use { a ->
            a.row(0).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1f, 2f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRows() {
        val data = floatArrayOf(1f, 3f, 5f, 2f, 4f, 6f)
        val dims = longArrayOf(3, 2, 1, 1)
        Array(data, dims).use { a ->
            a.rows(0, 1).use { b ->
                val result = b.getData<FloatArray>()
                val expected = floatArrayOf(1f, 3f, 2f, 4f)
                Assert.assertArrayEquals(result, expected, 1e-6f)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMatmul() {
        val data = floatArrayOf(1f, 2f, 3f, 4f)
        val dimsA = longArrayOf(4, 1, 1, 1)
        val dimsB = longArrayOf(1, 4, 1, 1)
        Array(data, dimsA).use { a ->
            Array(data, dimsB).use { b ->
                a.matmul(b).use { c ->
                    val result = c.getData<FloatArray>()
                    val expected = floatArrayOf(1f, 2f, 3f, 4f, 2f, 4f, 6f, 8f, 3f, 6f, 9f, 12f, 4f, 8f, 12f, 16f)
                    Assert.assertArrayEquals(result, expected, 1e-6f)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCopy() {
        val data = floatArrayOf(1.1f, 2.1f, 3.1f, 4.1f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            a.copy().use { b ->
                a.eq(b).use { c ->
                    val result = c.getData<BooleanArray>()
                    val expected = booleanArrayOf(true, true, true, true)
                    Assert.assertArrayEquals(result, expected)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCopyConstructor() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(8, 1, 1, 1)
        Array(tss, dims).use { a ->
            Array(a).use { b ->
                val result = b.getData<DoubleArray>()
                val expected = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
                Assert.assertArrayEquals(result, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testClose() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val dims = longArrayOf(8, 1, 1, 1)
        val a = Array(tss, dims)
        a.close()
    }

    @Test
    @Throws(Exception::class)
    fun testAs() {
        val data = floatArrayOf(1.1f, 2.1f, 3.1f, 4.1f)
        val dims = longArrayOf(4, 1, 1, 1)
        Array(data, dims).use { a ->
            a.`as`(Array.Dtype.s32).use { b ->
                val result = b.getData<IntArray>()
                val expected = intArrayOf(1, 2, 3, 4)
                Assert.assertArrayEquals(result, expected)
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
