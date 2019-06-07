/*
 * Copyright (c) 2019 Shapelets.io
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

class MatrixTest {

    @Test
    @Throws(Exception::class)
    fun testMass() {
        val tss = doubleArrayOf(10.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 12.0, 11.0, 14.0, 10.0, 10.0)
        val dimsTss = longArrayOf(14, 1, 1, 1)

        val query = doubleArrayOf(4.0, 3.0, 8.0)
        val dimsQuery = longArrayOf(3, 1, 1, 1)

        Array(tss, dimsTss).use { t ->
            Array(query, dimsQuery).use { q ->

                val expectedDistance = doubleArrayOf(
                        1.732051, 0.328954, 1.210135, 3.150851, 3.245858, 2.822044,
                        0.328954, 1.210135, 3.150851, 0.248097, 3.30187, 2.82205)
                val result = Matrix.mass(q, t)
                val distances = result.getData<DoubleArray>()

                Assert.assertArrayEquals(expectedDistance, distances, 1e-3)

                result.close()
            }
        }

    }

    @Test
    @Throws(Exception::class)
    fun testMassMultiple() {
        val tss = doubleArrayOf(10.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 12.0, 11.0, 14.0, 10.0, 10.0)
        val dimsTss = longArrayOf(7, 2, 1, 1)

        val query = doubleArrayOf(10.0, 10.0, 11.0, 11.0, 10.0, 11.0, 10.0, 10.0)
        val dimsQuery = longArrayOf(4, 2, 1, 1)

        Array(tss, dimsTss).use { t ->
            Array(query, dimsQuery).use { q ->

                val expectedDistance = doubleArrayOf(
                        1.8388, 0.8739, 1.5307, 3.6955, 3.2660, 3.4897, 2.8284, 1.2116,
                        1.5307, 2.1758, 2.5783, 3.7550, 2.8284, 2.8284, 3.2159, 0.5020)
                val result = Matrix.mass(q, t)
                val distances = result.getData<DoubleArray>()

                Assert.assertArrayEquals(expectedDistance, distances, 1e-3)

                result.close()
            }
        }

    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNOccurrences() {
        val tss = doubleArrayOf(10.0, 10.0, 11.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 10.0, 10.0, 11.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0)
        val dimsTss = longArrayOf(28, 1, 1, 1)

        val query = doubleArrayOf(10.0, 11.0, 12.0)
        val dimsQuery = longArrayOf(3, 1, 1, 1)

        Array(tss, dimsTss).use { t ->
            Array(query, dimsQuery).use { q ->
                val result = Matrix.findBestNOccurrences(q, t, 1)
                val distances = result[0].getData<DoubleArray>()
                val indexes = result[1].getData<IntArray>()

                Assert.assertEquals(distances[0], 0.0, DELTA)
                Assert.assertEquals(indexes[0], 7)

                result[0].close()
                result[1].close()
            }
        }

    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNOccurrencesMultipleQueries() {
        val tss = doubleArrayOf(10.0, 10.0, 11.0, 11.0, 10.0, 11.0, 10.0, 10.0, 11.0, 11.0, 10.0, 11.0, 10.0, 10.0, 11.0, 10.0, 10.0, 11.0, 10.0, 11.0, 11.0, 10.0, 11.0, 11.0, 14.0, 10.0, 11.0, 10.0)
        val dimsTss = longArrayOf(14, 2, 1, 1)

        val query = doubleArrayOf(11.0, 11.0, 10.0, 11.0, 10.0, 11.0, 11.0, 12.0)
        val dimsQuery = longArrayOf(4, 2, 1, 1)

        Array(tss, dimsTss).use { t ->
            Array(query, dimsQuery).use { q ->
                val result = Matrix.findBestNOccurrences(q, t, 4)

                val distance = getSingleValueDouble(result[0], 2, 0, 1, 0)
                Assert.assertEquals(distance, 1.83880, 1e-3)

                val index = getSingleValueInt(result[1], 3, 1, 0, 0)
                Assert.assertEquals(index.toLong(), 2)

                result[0].close()
                result[1].close()
            }
        }

    }


    @Test
    @Throws(Exception::class)
    fun testStompSelfJoin() {
        val ta = doubleArrayOf(10.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 11.0, 12.0, 11.0, 10.0, 10.0, 10.0)
        val dims = longArrayOf(14, 1, 1, 1)

        Array(ta, dims).use { a ->
            val stompSelfJoinResult = Matrix.stompSelfJoin(a, 3)
            val expectedIndex = doubleArrayOf(11.0, 6.0, 7.0, 8.0, 9.0, 10.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0)
            val matrix = stompSelfJoinResult[0].getData<DoubleArray>()
            val index = stompSelfJoinResult[1].getData<IntArray>()

            for (i in expectedIndex.indices) {
                Assert.assertEquals(matrix[i], 0.0, DELTA)
                Assert.assertEquals(index[i].toDouble(), expectedIndex[i], DELTA)
            }
            stompSelfJoinResult[0].close()
            stompSelfJoinResult[1].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testStomp() {
        val tss = doubleArrayOf(10.0, 10.0, 11.0, 11.0, 10.0, 11.0, 10.0, 10.0)
        val dims = longArrayOf(8, 1, 1, 1)

        Array(tss, dims).use { a ->
            Array(tss, dims).use { b ->
                val expectedIndex = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
                val stompResult = Matrix.stomp(a, b, 3)
                val matrix = stompResult[0].getData<DoubleArray>()
                val index = stompResult[1].getData<IntArray>()
                for (i in expectedIndex.indices) {
                    Assert.assertEquals(matrix[i], 0.0, DELTA)
                    Assert.assertEquals(index[i].toDouble(), expectedIndex[i], DELTA)
                }
                stompResult[0].close()
                stompResult[1].close()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNMotifs() {
        val tss = arrayOf(floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 9f, 10f, 10f, 10f, 10f, 10f, 11f, 10f, 9f), floatArrayOf(10f, 11f, 10f, 9f))
        val dims = longArrayOf(15, 1, 1, 1)
        val dimsB = longArrayOf(4, 1, 1, 1)

        Array(tss[0], dims).use { a ->
            Array(tss[1], dimsB).use { b ->
                val stompResult = Matrix.stomp(a, b, 3)
                val findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1)

                val index = findMotifs[1].getData<IntArray>()
                val subsequenceIndex = findMotifs[2].getData<IntArray>()

                Assert.assertEquals(index[0].toDouble(), 12.0, DELTA)
                Assert.assertEquals(subsequenceIndex[0].toDouble(), 1.0, DELTA)

                stompResult[0].close()
                stompResult[1].close()
                findMotifs[0].close()
                findMotifs[1].close()
                findMotifs[2].close()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNMotifsMultipleProfiles() {
        val dataA = floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 9f, 10f, 10f, 10f, 10f, 10f, 11f, 10f, 9f, 10f, 10f, 10f, 10f, 10f, 10f, 9f, 10f, 10f, 10f, 10f, 10f, 11f, 10f, 9f)
        val dataB = floatArrayOf(10f, 11f, 10f, 9f, 10f, 11f, 10f, 9f)
        val dims = longArrayOf(15, 2, 1, 1)
        val dimsB = longArrayOf(4, 2, 1, 1)

        Array(dataA, dims).use { a ->
            Array(dataB, dimsB).use { b ->
                val stompResult = Matrix.stomp(a, b, 3)
                val findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 1)

                val index = findMotifs[1].getData<IntArray>()
                val subsequenceIndex = findMotifs[2].getData<IntArray>()

                val expectedIndex = intArrayOf(12, 12, 12, 12)
                val expectedSubsequenceIndex = intArrayOf(1, 1, 1, 1)

                Assert.assertArrayEquals(index, expectedIndex)
                Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex)

                stompResult[0].close()
                stompResult[1].close()
                findMotifs[0].close()
                findMotifs[1].close()
                findMotifs[2].close()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNMotifsMirror() {
        val tss = floatArrayOf(10.1f, 11f, 10.2f, 10.15f, 10.775f, 10.1f, 11f, 10.2f)
        val dims = longArrayOf(8, 1, 1, 1)

        Array(tss, dims).use { a ->
            val stompResult = Matrix.stompSelfJoin(a, 3)
            val findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true)

            val index = findMotifs[1].getData<IntArray>()
            val subsequenceIndex = findMotifs[2].getData<IntArray>()

            val expectedIndex = intArrayOf(0, 0)
            val expectedSubsequenceIndex = intArrayOf(5, 3)

            Assert.assertArrayEquals(index, expectedIndex)
            Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex)

            stompResult[0].close()
            stompResult[1].close()
            findMotifs[0].close()
            findMotifs[1].close()
            findMotifs[2].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNMotifsConsecutive() {
        val tss = floatArrayOf(10.1f, 11f, 10.1f, 10.15f, 10.075f, 10.1f, 11f, 10.1f, 10.15f)
        val dims = longArrayOf(9, 1, 1, 1)

        Array(tss, dims).use { a ->
            val stompResult = Matrix.stompSelfJoin(a, 3)
            val findMotifs = Matrix.findBestNMotifs(stompResult[0], stompResult[1], 3, 2, true)

            val index = findMotifs[1].getData<IntArray>()
            val subsequenceIndex = findMotifs[2].getData<IntArray>()

            Assert.assertEquals(index[1].toDouble(), 6.0, DELTA)
            Assert.assertEquals(subsequenceIndex[1].toDouble(), 3.0, DELTA)

            stompResult[0].close()
            stompResult[1].close()
            findMotifs[0].close()
            findMotifs[1].close()
            findMotifs[2].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNDiscords() {
        val dataA = floatArrayOf(11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 1f)
        val dataB = floatArrayOf(9f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9f)
        val dims = longArrayOf(13, 1, 1, 1)

        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                val stompResult = Matrix.stomp(a, b, 3)
                val findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2)
                val subsequenceIndex = findDiscords[2].getData<IntArray>()

                Assert.assertEquals(subsequenceIndex[0].toDouble(), 0.0, DELTA)
                Assert.assertEquals(subsequenceIndex[1].toDouble(), 10.0, DELTA)

                stompResult[0].close()
                stompResult[1].close()
                findDiscords[0].close()
                findDiscords[1].close()
                findDiscords[2].close()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNDiscordsMultipleProfiles() {
        val dataA = floatArrayOf(11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 1f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 1f)
        val dataB = floatArrayOf(9f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9f, 9f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 10.2f, 10.1f, 9f)
        val dims = longArrayOf(13, 2, 1, 1)

        Array(dataA, dims).use { a ->
            Array(dataB, dims).use { b ->
                val stompResult = Matrix.stomp(a, b, 3)
                val findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2)
                val subsequenceIndex = findDiscords[2].getData<IntArray>()

                val expectedSubsequenceIndex = intArrayOf(0, 10, 0, 10, 0, 10, 0, 10)

                Assert.assertArrayEquals(subsequenceIndex, expectedSubsequenceIndex)

                stompResult[0].close()
                stompResult[1].close()
                findDiscords[0].close()
                findDiscords[1].close()
                findDiscords[2].close()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNDiscordsMirror() {
        val dataA = floatArrayOf(10f, 11f, 10f, 10f, 11f, 10f)
        val dims = longArrayOf(6, 1, 1, 1)

        Array(dataA, dims).use { a ->
            val stompResult = Matrix.stompSelfJoin(a, 3)
            val findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 1, true)
            val index = findDiscords[1].getData<IntArray>()
            val subsequenceIndex = findDiscords[2].getData<IntArray>()

            Assert.assertEquals(index[0].toDouble(), 3.0, DELTA)
            Assert.assertEquals(subsequenceIndex[0].toDouble(), 1.0, DELTA)

            stompResult[0].close()
            stompResult[1].close()
            findDiscords[0].close()
            findDiscords[1].close()
            findDiscords[2].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindBestNDiscordsConsecutive() {
        val dataA = floatArrayOf(10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 11f, 10f, 9.999f, 9.998f)
        val dims = longArrayOf(15, 1, 1, 1)

        Array(dataA, dims).use { a ->
            val stompResult = Matrix.stompSelfJoin(a, 3)
            val findDiscords = Matrix.findBestNDiscords(stompResult[0], stompResult[1], 3, 2, true)
            val subsequenceIndex = findDiscords[2].getData<IntArray>()

            Assert.assertEquals(subsequenceIndex[0].toDouble(), 12.0, DELTA)
            val os = System.getenv("TRAVIS")
            if (os == null || os != "true") {
                Assert.assertNotEquals(subsequenceIndex[1].toDouble(), 11.0, DELTA)
            } else {
                Assert.assertEquals(subsequenceIndex[1].toDouble(), 11.0, DELTA)
            }
            stompResult[0].close()
            stompResult[1].close()
            findDiscords[0].close()
            findDiscords[1].close()
            findDiscords[2].close()
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

private fun getSingleValueDouble(arr: Array, dim0: Long, dim1: Long, dim2: Long, dim3: Long): Double {
    val data = arr.getData<DoubleArray>()

    val dims4 = arr.dims
    var offset = dims4[0] * dims4[1] * dims4[2] * dim3
    offset += dims4[0] * dims4[1] * dim2
    offset += dims4[0] * dim1
    offset += dim0

    return data[offset.toInt()]
}

private fun getSingleValueInt(arr: Array, dim0: Long, dim1: Long, dim2: Long, dim3: Long): Int {
    val data = arr.getData<IntArray>()

    val dims4 = arr.dims
    var offset = dims4[0] * dims4[1] * dims4[2] * dim3
    offset += dims4[0] * dims4[1] * dim2
    offset += dims4[0] * dim1
    offset += dim0

    return data[offset.toInt()]
}
