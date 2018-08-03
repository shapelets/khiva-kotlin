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
 * Khiva Array Class.
 */
class Array : Library, AutoCloseable {
    /**
     * Device reference.
     */
    var reference: Long = 0L

    /**
     * Array data type.
     *
     * @return Array data type.
     */
    val type: Dtype
        get() = Dtype.values()[nativeGetType()]

    /**
     * Array dimensions.
     *
     * @return The dimensions.
     */
    val dims: LongArray
        get() = nativeGetDims()

    private external fun getDoubleComplexFromArray(): kotlin.Array<DoubleComplex>

    private external fun getFloatComplexFromArray(): kotlin.Array<FloatComplex>

    private external fun getDoubleFromArray(): DoubleArray

    private external fun getFloatFromArray(): FloatArray

    private external fun getShortFromArray(): ShortArray

    private external fun getByteFromArray(): ByteArray

    private external fun getBooleanFromArray(): BooleanArray

    private external fun getIntFromArray(): IntArray

    private external fun getLongFromArray(): LongArray

    /**
     * Data type of the Array
     */
    enum class Dtype {
        /**
         * Floating point of single precision. khiva.dtype.
         */
        f32,
        /**
         * Complex floating point of single precision. khiva.dtype.
         */
        c32,
        /**
         * Floating point of double precision. khiva.dtype.
         */
        f64,
        /**
         * Complex floating point of double precision. khiva.dtype.
         */
        c64,
        /**
         * Boolean. khiva.dtype.
         */
        b8,
        /**
         * 32 bits Int. khiva.dtype.
         */
        s32,
        /**
         * 32 bits Unsigned Int. khiva.dtype.
         */
        u32,
        /**
         * 8 bits Unsigned Int. khiva.dtype.
         */
        u8,
        /**
         * 64 bits Integer. khiva.dtype.
         */
        s64,
        /**
         * 64 bits Unsigned Int. khiva.dtype.
         */
        u64,
        /**
         * 16 bits Int. khiva.dtype.
         */
        s16,
        /**
         * 16 bits Unsigned Int. khiva.dtype.
         */
        u16
    }

    /**
     * Array constructor receiving a Kotlin Array of a given type.
     *
     * @param arr Kotlin Array of a given type.
     * @param dims Dimensions of the Array to be created in the device.
     */
    @Throws(Exception::class)
    constructor(arr: Any?, dims: LongArray) {
        val adims = dim4(dims)

        var totalSize = 1
        for (i in adims.indices) totalSize *= adims[i].toInt()

        if (arr == null) {
            throw Exception("Null elems object provided")
        }

        when (arr) {
            is DoubleArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromDouble(arr, dims)
            }
            is FloatArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromFloat(arr, dims)
            }
            is IntArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromInt(arr, dims)
            }
            is BooleanArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromBoolean(arr, dims)
            }
            is ShortArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromShort(arr, dims)
            }
            is ByteArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromByte(arr, dims)
            }
            is LongArray -> {
                if (arr.size > totalSize || arr.size < totalSize) {
                    throw Exception("Mismatching dims and array size")
                }
                this.reference = createArrayFromLong(arr, dims)
            }
        }
    }

    /**
     * Array constructor receiving a Kotlin Array of FloatComplex objects.
     *
     * @param arr Kotlin Array of FloatComplex objects.
     * @param dims Dimensions of the Array to be created in the device.
     */
    @Throws(Exception::class)
    constructor(arr: kotlin.Array<FloatComplex>?, dims: LongArray) {

        val adims = dim4(dims)

        val totalSize = adims.reduce { acc, e -> acc * e }.toInt()

        if (arr == null) {
            throw Exception("Null elems object provided")
        }

        if (arr.size > totalSize || arr.size < totalSize) {
            throw Exception("Mismatching dims and array size")
        }

        this.reference = createArrayFromFloatComplex(arr, dims)
    }

    /**
     * Array constructor receiving a Kotlin Array of DoubleComplex objects.
     *
     * @param arr Kotlin Array of DoubleComplex objects.
     * @param dims Dimensions of the Array to be created in the device.
     */
    constructor(arr: kotlin.Array<DoubleComplex>?, dims: LongArray) {
        val adims = dim4(dims)

        val totalSize = adims.reduce { acc, e -> acc * e }.toInt()

        if (arr == null) {
            throw Exception("Null elems object provided")
        }

        if (arr.size > totalSize || arr.size < totalSize) {
            throw Exception("Mismatching dims and array size")
        }

        this.reference = createArrayFromDoubleComplex(arr, dims)
    }

    /**
     * Array constructor receiving a device reference.
     *
     * @param ref Device reference.
     */
    constructor(ref: Long) {
        this.reference = ref
    }

    /**
     * Array copy constructor.
     *
     * @param other Array to be copied.
     */
    constructor(other: Array) {
        this.reference = other.copy().reference
    }

    private external fun createArrayFromDouble(arr: DoubleArray, dims: LongArray): Long

    private external fun createArrayFromFloat(arr: FloatArray, dims: LongArray): Long

    private external fun createArrayFromBoolean(arr: BooleanArray, dims: LongArray): Long

    private external fun createArrayFromInt(arr: IntArray, dims: LongArray): Long

    private external fun createArrayFromByte(arr: ByteArray, dims: LongArray): Long

    private external fun createArrayFromShort(arr: ShortArray, dims: LongArray): Long

    private external fun createArrayFromLong(arr: LongArray, dims: LongArray): Long

    private external fun createArrayFromFloatComplex(arr: kotlin.Array<FloatComplex>, dims: LongArray): Long

    private external fun createArrayFromDoubleComplex(arr: kotlin.Array<DoubleComplex>, dims: LongArray): Long

    private external fun deleteArray()

    private external fun nativeGetDims(): LongArray

    private external fun nativePrint(): Long

    private external fun nativeGetType(): Int

    private external fun add(refRhs: Long): LongArray

    private external fun mul(refRhs: Long): LongArray

    private external fun sub(refRhs: Long): LongArray

    private external fun div(refRhs: Long): LongArray

    private external fun mod(refRhs: Long): LongArray

    private external fun pow(refRhs: Long): LongArray

    private external fun lt(refRhs: Long): LongArray

    private external fun gt(refRhs: Long): LongArray

    private external fun le(refRhs: Long): LongArray

    private external fun ge(refRhs: Long): LongArray

    private external fun eq(refRhs: Long): LongArray

    private external fun ne(refRhs: Long): LongArray

    private external fun bitAnd(refRhs: Long): LongArray

    private external fun bitOr(refRhs: Long): LongArray

    private external fun bitXor(refRhs: Long): LongArray

    private external fun nativeBitShiftL(n: Int): Long

    private external fun nativeBitShiftR(n: Int): Long

    private external fun nativeNot(): Long

    private external fun nativeTranspose(conjugate: Boolean): Long

    private external fun nativeCol(index: Int): Long

    private external fun nativeCols(first: Int, last: Int): Long

    private external fun nativeRow(index: Int): Long

    private external fun nativeRows(first: Int, last: Int): Long

    private external fun matmul(refRhs: Long): LongArray

    private external fun nativeCopy(): Long

    private external fun `as`(type: Int): Long

    /**
     * Gets the data stored in the array.
     *
     * @param <Any> The data type to be returned.
     * @return The data to an array of its type.
    </Any> */
    @Suppress("UNCHECKED_CAST")
    fun <T> getData(): T {
        return when (type) {
            Dtype.f32 -> getFloatFromArray() as T
            Dtype.c32 -> getFloatComplexFromArray() as T
            Dtype.f64 -> getDoubleFromArray() as T
            Dtype.c64 -> getDoubleComplexFromArray() as T
            Dtype.b8 -> getBooleanFromArray() as T
            Dtype.s32 -> getIntFromArray() as T
            Dtype.u32 -> getIntFromArray() as T
            Dtype.u8 -> getByteFromArray() as T
            Dtype.s64 -> getLongFromArray() as T
            Dtype.u64 -> getLongFromArray() as T
            Dtype.s16 -> getShortFromArray() as T
            Dtype.u16 -> getShortFromArray() as T
        }
    }

    /**
     * Prints the Array.
     */
    fun print() {
        nativePrint()
    }

    /**
     * Adds this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The sum of both arrays.
     */
    operator fun plus(rhs: Array): Array {
        val refs = add(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Multiplies this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The product of both arrays.
     */
    operator fun times(rhs: Array): Array {
        val refs = mul(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Subtracts this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The subtraction of both arrays.
     */
    operator fun minus(rhs: Array): Array {
        val refs = sub(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Divides this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The division of both arrays.
     */
    operator fun div(rhs: Array): Array {
        val refs = div(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs the modulo operation of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The modulo of this array with the one passed as parameter.
     */
    operator fun rem(rhs: Array): Array {
        val refs = mod(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Powers this array with the one specified as exponent parameter.
     *
     * @param exponent Exponent for the power operation.
     * @return The power of this array with the one passed as parameter.
     */
    fun pow(exponent: Array): Array {
        val refs = pow(exponent.reference)
        exponent.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is lower than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun lt(rhs: Array): Array {
        val refs = lt(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is greater than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun gt(rhs: Array): Array {
        val refs = gt(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is lower or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun le(rhs: Array): Array {
        val refs = le(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is greater or equal than the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun ge(rhs: Array): Array {
        val refs = ge(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun eq(rhs: Array): Array {
        val refs = eq(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Compares (element-wise) if this array is not equal to the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of comparing element-wise this array with the one passed as parameter.
     */
    fun ne(rhs: Array): Array {
        val refs = ne(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs an AND operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an AND operation of this array with the one passed as parameter.
     */
    infix fun and(rhs: Array): Array {
        val refs = bitAnd(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs an OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an OR operation of this array with the one passed as parameter.
     */
    infix fun or(rhs: Array): Array {
        val refs = bitOr(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs an eXclusive-OR operation with this array and the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of an eXclusive-OR operation of this array with the one passed as parameter.
     */
    infix fun xor(rhs: Array): Array {
        val refs = bitXor(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs a left bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a left bit shift operation to this array as many times as specified in the
     * parameter.
     */
    infix fun shl(n: Int): Array {
        val ref = nativeBitShiftL(n)
        return Array(ref)
    }

    /**
     * Performs a right bit shift operation to this array (element-wise) as many times as specified in the parameter n.
     *
     * @param n Number of bits to be shifted.
     * @return The result of a right bit shift operation to this array as many times as specified in the
     * parameter.
     */
    infix fun shr(n: Int): Array {
        val ref = nativeBitShiftR(n)
        return Array(ref)
    }

    /**
     * Logical NOT operation to this array.
     *
     * @return The result of a logical NOT operation to this array.
     */
    operator fun not(): Array {
        val ref = nativeNot()
        return Array(ref)
    }

    /**
     * Transposes this array.
     *
     * @param conjugate If true a conjugate transposition is performed.
     * @return The transposed (conjugate) array.
     */
    fun transpose(conjugate: Boolean = false): Array {
        val ref = nativeTranspose(conjugate)
        return Array(ref)
    }

    /**
     * Returns the specified column by index from this array.
     *
     * @param index The column to be retrieved.
     * @return The specified column of this array.
     */
    fun col(index: Int): Array {
        val ref = nativeCol(index)
        return Array(ref)
    }

    /**
     * Retrieves a subset of columns from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of columns to be retrieved.
     * @param last  End of the subset of columns to be retrieved.
     * @return The subset of columns of this array starting at first and finishing at last, both inclusive.
     */
    fun cols(first: Int, last: Int): Array {
        val ref = nativeCols(first, last)
        return Array(ref)
    }

    /**
     * Retrieves a given row from this array.
     *
     * @param index The row to be retrieved.
     * @return The specified row of this array.
     */
    fun row(index: Int): Array {
        val ref = nativeRow(index)
        return Array(ref)
    }

    /**
     * Retrieves a subset of rows from this array, starting at first and finishing at last, both inclusive.
     *
     * @param first Start of the subset of rows to be retrieved.
     * @param last  End of the subset of rows to be retrieved.
     * @return The subset of rows of this array starting at first and finishing at last, both inclusive.
     */
    fun rows(first: Int, last: Int): Array {
        val ref = nativeRows(first, last)
        return Array(ref)
    }

    /**
     * Performs a matrix multiplication of this array with the one passed as parameter.
     *
     * @param rhs Right-hand side array for the operation.
     * @return The result of a matrix multiplication of both arrays.
     */
    fun matmul(rhs: Array): Array {
        val refs = matmul(rhs.reference)
        rhs.reference = refs[0]
        return Array(refs[1])
    }

    /**
     * Performs a deep copy of this array. Both the data stored in the device and all the object properties in Java.
     *
     * @return A deep copy of this array.
     */
    fun copy(): Array {
        val ref = nativeCopy()
        return Array(ref)
    }

    /**
     * Changes the type of this array.
     *
     * @param type Target type of the output array.
     * @return The result of changing the type of the input array to the one passed as parameter.
     */
    fun `as`(type: Dtype): Array {
        val ref = `as`(type.ordinal)
        return Array(ref)
    }

    /**
     * Releases the device memory.
     */
    @Throws(Exception::class)
    override fun close() {
        deleteArray()
        reference = 0L
    }

    companion object {

        /**
         * Gets the dim4 in order to construct the Array.
         *
         * @param dims The dimensions.
         * @return The dim4.
         * @throws java.lang.Exception When the input parameter is null or the length is greater than 4, because ArrayFire
         * supports up to 4 dimension.
         */
        @Throws(Exception::class)
        fun dim4(dims: LongArray?): LongArray {

            if (dims == null) {
                throw Exception("Null dimensions object provided")
            } else if (dims.size > 4) {
                throw Exception("ArrayFire supports up to 4 dimensions only")
            }

            val adims = longArrayOf(1, 1, 1, 1)
            for (i in dims.indices) adims[i] = dims[i]

            return adims
        }
    }
}
