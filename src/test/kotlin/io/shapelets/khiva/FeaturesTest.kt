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

class FeaturesTest {

    @Test
    @Throws(Exception::class)
    fun testCidCe() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Features.cidCe(arrayOfTimeSeries, true).use { b ->

                var cidCe = b.getData<FloatArray>()
                Assert.assertEquals(cidCe[0].toDouble(), 1.30930734141595, DELTA)
                Assert.assertEquals(cidCe[1].toDouble(), 1.30930734141595, DELTA)
                cidCe = Features.cidCe(arrayOfTimeSeries, false).getData()
                Assert.assertEquals(cidCe[0].toDouble(), 2.23606797749979, DELTA)
                Assert.assertEquals(cidCe[1].toDouble(), 2.23606797749979, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testC3() {
        val timeSeries = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(timeSeries, dims).use { arrayOfTimeSeries ->
            Features.c3(arrayOfTimeSeries, 2).use { b ->

                val c3 = b.getData<FloatArray>()
                Assert.assertEquals(c3[0].toDouble(), 7.5, DELTA)
                Assert.assertEquals(c3[1].toDouble(), 586.5, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAbsSumOfChanges() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 6.0, 8.0, 10.0, 11.0, 14.0, 17.0, 20.0)
        val dims = longArrayOf(4, 3, 1, 1)
        Array(tss, dims).use { a ->
            Features.absoluteSumOfChanges(a).use { absSum ->
                val absSumR = absSum.getData<DoubleArray>()
                Assert.assertEquals(absSumR[0], 3.0, DELTA)
                Assert.assertEquals(absSumR[1], 6.0, DELTA)
                Assert.assertEquals(absSumR[2], 9.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAbsEnergy() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val dims = longArrayOf(10, 1, 1, 1)
        Array(tss, dims).use { a ->
            Features.absEnergy(a).use { absEnergy ->
                val result = absEnergy.getData<DoubleArray>()
                Assert.assertEquals(result[0], 385.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testApproximateEntropy() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0)
        val dims = longArrayOf(10, 2, 1, 1)
        val r = 0.5.toFloat()

        Array(tss, dims).use { a ->
            Features.approximateEntropy(a, 4, r).use { b ->
                val approximateEntropy = b.getData<DoubleArray>()

                Assert.assertEquals(approximateEntropy[0], 0.13484281753639338, DELTA)
                Assert.assertEquals(approximateEntropy[1], 0.13484281753639338, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCrossCovariance() {
        val tss1 = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 10.0, 11.0, 12.0, 13.0)
        val tss2 = doubleArrayOf(4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0)
        val dims1 = longArrayOf(4, 2, 1, 1)
        val dims2 = longArrayOf(5, 2, 1, 1)
        Array(tss1, dims1).use { a ->
            Array(tss2, dims2).use { b ->
                Features.crossCovariance(a, b, false).use { c ->
                    val crossCovariance = c.getData<DoubleArray>()
                    for (i in 0..3) {
                        Assert.assertEquals(crossCovariance[i * 5], 2.5, DELTA)
                        Assert.assertEquals(crossCovariance[i * 5 + 1], 2.5, DELTA)
                        Assert.assertEquals(crossCovariance[i * 5 + 2], 0.25, DELTA)
                        Assert.assertEquals(crossCovariance[i * 5 + 3], -1.25, DELTA)
                        Assert.assertEquals(crossCovariance[i * 5 + 4], -1.5, DELTA)
                    }
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAutoCovariance() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 10.0, 11.0, 12.0, 13.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.autoCovariance(a, false).use { b ->
                val autoCovariance = b.getData<DoubleArray>()
                Assert.assertEquals(autoCovariance[0], 1.25, DELTA)
                Assert.assertEquals(autoCovariance[1], 0.3125, DELTA)
                Assert.assertEquals(autoCovariance[2], -0.375, DELTA)
                Assert.assertEquals(autoCovariance[3], -0.5625, DELTA)
                Assert.assertEquals(autoCovariance[4], 1.25, DELTA)
                Assert.assertEquals(autoCovariance[5], 0.3125, DELTA)
                Assert.assertEquals(autoCovariance[6], -0.375, DELTA)
                Assert.assertEquals(autoCovariance[7], -0.5625, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCrossCorrelation() {
        val tss1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
        val tss2 = doubleArrayOf(4.0, 6.0, 8.0, 10.0, 12.0)
        val dims1 = longArrayOf(4, 1, 1, 1)
        val dims2 = longArrayOf(5, 1, 1, 1)
        Array(tss1, dims1).use { a ->
            Array(tss2, dims2).use { b ->
                Features.crossCorrelation(a, b, false).use { c ->
                    val crossCorrelation = c.getData<DoubleArray>()
                    Assert.assertEquals(crossCorrelation[0], 0.790569415, 1e-9)
                    Assert.assertEquals(crossCorrelation[1], 0.790569415, 1e-9)
                    Assert.assertEquals(crossCorrelation[2], 0.079056941, 1e-9)
                    Assert.assertEquals(crossCorrelation[3], -0.395284707, 1e-9)
                    Assert.assertEquals(crossCorrelation[4], -0.474341649, 1e-9)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAutoCorrelation() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 10.0, 11.0, 12.0, 13.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.autoCorrelation(a, 4, false).use { b ->
                val autoCorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(autoCorrelationResult[0], 1.0, DELTA)
                Assert.assertEquals(autoCorrelationResult[1], 0.25, DELTA)
                Assert.assertEquals(autoCorrelationResult[2], -0.3, DELTA)
                Assert.assertEquals(autoCorrelationResult[3], -0.45, DELTA)
                Assert.assertEquals(autoCorrelationResult[4], 1.0, DELTA)
                Assert.assertEquals(autoCorrelationResult[5], 0.25, DELTA)
                Assert.assertEquals(autoCorrelationResult[6], -0.3, DELTA)
                Assert.assertEquals(autoCorrelationResult[7], -0.45, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testBinnedCorrelation() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 1.0, 1.0, 3.0, 10.0, 5.0, 6.0, 1.0, 8.0, 9.0, 10.0, 11.0, 1.0, 13.0, 14.0, 10.0, 16.0, 17.0, 10.0, 19.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.binnedEntropy(a, 5).use { b ->
                val binnedEntropyResult = b.getData<DoubleArray>()
                Assert.assertEquals(binnedEntropyResult[0], 1.6094379124341005, DELTA)
                Assert.assertEquals(binnedEntropyResult[1], 1.5614694247763998, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCountAboveMean() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.countAboveMean(a).use { b ->
                val countAboveMeanResult = b.getData<IntArray>()
                Assert.assertEquals(countAboveMeanResult[0].toDouble(), 3.0, DELTA)
                Assert.assertEquals(countAboveMeanResult[1].toDouble(), 3.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCountBelowMean() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.countBelowMean(a).use { b ->
                val countBelowMeanResult = b.getData<IntArray>()

                Assert.assertEquals(countBelowMeanResult[0].toDouble(), 3.0, DELTA)
                Assert.assertEquals(countBelowMeanResult[1].toDouble(), 3.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testEnergyRatioByChunks() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.energyRatioByChunks(a, 2, 0).use { b ->
                var energyRatioByChunksResult = b.getData<DoubleArray>()

                Assert.assertEquals(energyRatioByChunksResult[0], 0.090909091, DELTA)
                Assert.assertEquals(energyRatioByChunksResult[1], 0.330376940, DELTA)
                energyRatioByChunksResult = Features.energyRatioByChunks(a, 2, 1).getData()
                Assert.assertEquals(energyRatioByChunksResult[0], 0.909090909, DELTA)
                Assert.assertEquals(energyRatioByChunksResult[1], 0.669623060, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFirstLocationOfMaximum() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 5.0, 0.0, 1.0, 5.0, 3.0, 2.0, 1.0, 2.0, 4.0, 3.0, 5.0, 2.0, 5.0, 4.0, 3.0, 5.0, 2.0)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.firstLocationOfMaximum(a).use { b ->
                val firstLocationOfMaximumResult = b.getData<DoubleArray>()
                Assert.assertEquals(firstLocationOfMaximumResult[0], 0.0, DELTA)
                Assert.assertEquals(firstLocationOfMaximumResult[1], 0.3, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFirstLocationOfMinimum() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 0.0, 0.0, 1.0, 5.0, 4.0, 3.0, 0.0, 2.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.firstLocationOfMinimum(a).use { b ->
                val firstLocationOfMinimumResult = b.getData<DoubleArray>()
                Assert.assertEquals(firstLocationOfMinimumResult[0], 0.5, DELTA)
                Assert.assertEquals(firstLocationOfMinimumResult[1], 0.5, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFriedrichCoefficients() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.friedrichCoefficients(a, 4, 2f).use { b ->
                val expected = doubleArrayOf(-0.0009912563255056738, -0.0027067768387496471, -0.00015192681166809052, 0.10512571036815643, 0.89872437715530396, -0.0009912563255056738, -0.0027067768387496471, -0.00015192681166809052, 0.10512571036815643, 0.89872437715530396)
                val friedrichCoefficientsResult = b.getData<DoubleArray>()
                Assert.assertArrayEquals(friedrichCoefficientsResult, expected, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testHasDuplicates() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 0.0, 0.0, 1.0, 5.0, 4.0, 3.0, 0.0, 2.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.hasDuplicates(a).use { b ->
                val hasDuplicatesResult = b.getData<BooleanArray>()
                Assert.assertTrue(hasDuplicatesResult[0])
                Assert.assertFalse(hasDuplicatesResult[1])
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testHasDuplicateMax() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 0.0, 5.0, 1.0, 5.0, 4.0, 3.0, 0.0, 2.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.hasDuplicateMax(a).use { b ->
                val hasDuplicateMaxResult = b.getData<BooleanArray>()
                Assert.assertTrue(hasDuplicateMaxResult[0])
                Assert.assertFalse(hasDuplicateMaxResult[1])
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testIndexMaxQuantile() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 0.0, 0.0, 1.0, 5.0, 4.0, 0.0, 0.0, 2.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        val q = 0.5.toFloat()

        Array(tss, dims).use { a ->
            Features.indexMassQuantile(a, q).use { b ->
                val indexMaxQuantileResult = b.getData<DoubleArray>()
                Assert.assertEquals(indexMaxQuantileResult[0], 0.333333333, DELTA)
                Assert.assertEquals(indexMaxQuantileResult[1], 0.333333333, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testKurtosis() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 2.0, 2.0, 2.0, 20.0, 30.0, 25.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.kurtosis(a).use { b ->
                val kurtosisResult = b.getData<DoubleArray>()
                Assert.assertEquals(kurtosisResult[0], -1.2, DELTA)
                Assert.assertEquals(kurtosisResult[1], -2.66226722, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLargeStandardDeviation() {
        val tss = doubleArrayOf(-1.0, -1.0, -1.0, 1.0, 1.0, 1.0, 4.0, 6.0, 8.0, 4.0, 5.0, 4.0)
        val dims = longArrayOf(6, 2, 1, 1)
        val r = 0.4.toFloat()
        Array(tss, dims).use { a ->
            Features.largeStandardDeviation(a, r).use { b ->
                val largeStandardDeviationResult = b.getData<BooleanArray>()
                Assert.assertTrue(largeStandardDeviationResult[0])
                Assert.assertFalse(largeStandardDeviationResult[1])
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLastLocationOfMaximum() {
        val tss = doubleArrayOf(0.0, 4.0, 3.0, 5.0, 5.0, 1.0, 0.0, 4.0, 3.0, 2.0, 5.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.lastLocationOfMaximum(a).use { b ->
                val locationOfMaximumResult = b.getData<DoubleArray>()
                Assert.assertEquals(locationOfMaximumResult[0], 0.8333333333333334, DELTA)
                Assert.assertEquals(locationOfMaximumResult[1], 0.8333333333333334, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLastLocationOfMinimum() {
        val tss = doubleArrayOf(0.0, 4.0, 3.0, 5.0, 5.0, 1.0, 0.0, 4.0, 3.0, 2.0, 5.0, 1.0, 4.0, 5.0, 1.0, 2.0)
        val dims = longArrayOf(8, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.lastLocationOfMinimum(a).use { b ->
                val locationOfMinimumResult = b.getData<DoubleArray>()
                Assert.assertEquals(locationOfMinimumResult[0], 0.875, DELTA)
                Assert.assertEquals(locationOfMinimumResult[1], 0.875, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLength() {
        val tss = doubleArrayOf(0.0, 4.0, 3.0, 5.0, 5.0, 1.0, 0.0, 4.0, 3.0, 2.0, 5.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.length(a).use { b ->
                val lengthResult = b.getData<IntArray>()
                Assert.assertEquals(lengthResult[0].toDouble(), 6.0, DELTA)
                Assert.assertEquals(lengthResult[1].toDouble(), 6.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLinearTrend() {
        val tss = doubleArrayOf(0.0, 4.0, 3.0, 5.0, 5.0, 1.0, 2.0, 4.0, 1.0, 2.0, 5.0, 3.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            val b = Features.linearTrend(a)
            val pvalue = b[0].getData<DoubleArray>()
            val rvalue = b[1].getData<DoubleArray>()
            val intercept = b[2].getData<DoubleArray>()
            val slope = b[3].getData<DoubleArray>()
            val stdrr = b[4].getData<DoubleArray>()


            Assert.assertEquals(pvalue[0], 0.6260380997892747, DELTA)
            Assert.assertEquals(pvalue[1], 0.5272201945463578, DELTA)

            Assert.assertEquals(rvalue[0], 0.2548235957188128, DELTA)
            Assert.assertEquals(rvalue[1], 0.3268228676411533, DELTA)

            Assert.assertEquals(intercept[0], 2.2857142857142856, DELTA)
            Assert.assertEquals(intercept[1], 2.1904761904761907, DELTA)

            Assert.assertEquals(slope[0], 0.2857142857142857, DELTA)
            Assert.assertEquals(slope[1], 0.2571428571428572, DELTA)

            Assert.assertEquals(stdrr[0], 0.5421047417431507, DELTA)
            Assert.assertEquals(stdrr[1], 0.37179469135129783, DELTA)

            b[0].close()
            b[1].close()
            b[2].close()
            b[3].close()
            b[4].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testHasDuplicateMin() {
        val tss = doubleArrayOf(5.0, 4.0, 3.0, 0.0, 0.0, 1.0, 5.0, 4.0, 3.0, 0.0, 2.0, 1.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.hasDuplicateMin(a).use { b ->
                val hasDuplicateMinResult = b.getData<BooleanArray>()
                Assert.assertTrue(hasDuplicateMinResult[0])
                Assert.assertFalse(hasDuplicateMinResult[1])
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLongestStrikeAboveMean() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.longestStrikeAboveMean(a).use { b ->
                val longestStrikeAboveMeanResult = b.getData<DoubleArray>()
                Assert.assertEquals(longestStrikeAboveMeanResult[0], 4.0, DELTA)
                Assert.assertEquals(longestStrikeAboveMeanResult[1], 3.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLongestStrikeBelowMean() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 20.0, 20.0, 20.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.longestStrikeBelowMean(a).use { b ->
                val longestStrikeBelowMeanResult = b.getData<DoubleArray>()
                Assert.assertEquals(longestStrikeBelowMeanResult[0], 8.0, DELTA)
                Assert.assertEquals(longestStrikeBelowMeanResult[1], 9.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMaximum() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.maximum(a).use { b ->
                val maximumResult = b.getData<DoubleArray>()
                Assert.assertEquals(maximumResult[0], 50.0, DELTA)
                Assert.assertEquals(maximumResult[1], 30.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMeanAbsoluteChange() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.meanAbsoluteChange(a).use { b ->
                val meanAbsoluteResult = b.getData<DoubleArray>()
                Assert.assertEquals(meanAbsoluteResult[0], (5.toFloat() / 6).toDouble(), DELTA)
                Assert.assertEquals(meanAbsoluteResult[1], (10.toFloat() / 6).toDouble(), DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFftCoefficient() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            val b = Features.fftCoefficient(a, 0)

            val real = b[0].getData<DoubleArray>()
            val imag = b[1].getData<DoubleArray>()
            val abs = b[2].getData<DoubleArray>()
            val angle = b[3].getData<DoubleArray>()


            Assert.assertEquals(real[0], 15.0, DELTA)
            Assert.assertEquals(real[1], 51.0, DELTA)

            Assert.assertEquals(imag[0], 0.0, DELTA)
            Assert.assertEquals(imag[1], 0.0, DELTA)

            Assert.assertEquals(abs[0], 15.0, DELTA)
            Assert.assertEquals(abs[1], 51.0, DELTA)

            Assert.assertEquals(angle[0], 0.0, DELTA)
            Assert.assertEquals(angle[1], 0.0, DELTA)

            b[0].close()
            b[1].close()
            b[2].close()
            b[3].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationMean() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 0).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], -0.6571428571428571, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], -0.6571428571428571, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationMedian() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 1).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], -0.54285717010498047, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], -0.54285717010498047, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationMin() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 2).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], -2.142857142857143, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], -2.142857142857143, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationMax() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 3).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.6, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.6, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationStdev() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 4).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.9744490855905009, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.9744490855905009, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedAutocorrelationVar() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.aggregatedAutocorrelation(a, 5).use { b ->
                val aggregatedAutocorrelationResult = b.getData<DoubleArray>()
                Assert.assertEquals(aggregatedAutocorrelationResult[0], 0.9495510204081633, DELTA)
                Assert.assertEquals(aggregatedAutocorrelationResult[1], 0.9495510204081633, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedLinearTrendMean() {
        val tss = doubleArrayOf(2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 5.0, 5.0, 5.0)
        val dims = longArrayOf(12, 1, 1, 1)
        Array(tss, dims).use { a ->
            val b = Features.aggregatedLinearTrend(a, 3, 0)

            val pvalue = b[0].getData<DoubleArray>()
            val rvalue = b[1].getData<DoubleArray>()
            val intercept = b[2].getData<DoubleArray>()
            val slope = b[3].getData<DoubleArray>()
            val stdrr = b[4].getData<DoubleArray>()


            Assert.assertEquals(pvalue[0], 1.0, DELTA)
            Assert.assertEquals(rvalue[0], 2.0, DELTA)
            Assert.assertEquals(intercept[0], 1.0, DELTA)
            Assert.assertEquals(slope[0], 0.0, DELTA)
            Assert.assertEquals(stdrr[0], 0.0, DELTA)

            b[0].close()
            b[1].close()
            b[2].close()
            b[3].close()
            b[4].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testAggregatedLinearTrendMin() {
        val tss = doubleArrayOf(2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 5.0, 5.0, 5.0)
        val dims = longArrayOf(12, 1, 1, 1)
        Array(tss, dims).use { a ->
            val b = Features.aggregatedLinearTrend(a, 3, 1)

            val pvalue = b[0].getData<DoubleArray>()
            val rvalue = b[1].getData<DoubleArray>()
            val intercept = b[2].getData<DoubleArray>()
            val slope = b[3].getData<DoubleArray>()
            val stdrr = b[4].getData<DoubleArray>()


            Assert.assertEquals(pvalue[0], 1.0, DELTA)
            Assert.assertEquals(rvalue[0], 2.0, DELTA)
            Assert.assertEquals(intercept[0], 1.0, DELTA)
            Assert.assertEquals(slope[0], 0.0, DELTA)
            Assert.assertEquals(stdrr[0], 0.0, DELTA)

            b[0].close()
            b[1].close()
            b[2].close()
            b[3].close()
            b[4].close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testCwtCoefficients() {
        val tss = floatArrayOf(0.1f, 0.2f, 0.3f, 0.1f, 0.2f, 0.3f)
        val dims = longArrayOf(3, 2, 1, 1)
        val width = intArrayOf(1, 2, 3)
        val dimsW = longArrayOf(3, 1, 1, 1)
        Array(tss, dims).use { a ->
            Array(width, dimsW).use { w ->
                Features.cwtCoefficients(a, w, 2, 2).use { b ->
                    val cwtCoefficientsResult = b.getData<FloatArray>()
                    Assert.assertEquals(cwtCoefficientsResult[0].toDouble(), 0.26, 1e-2)
                    Assert.assertEquals(cwtCoefficientsResult[1].toDouble(), 0.26, 1e-2)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMeanSecondDerivativeCentral() {
        val tss = doubleArrayOf(1.0, 3.0, 7.0, 4.0, 8.0, 2.0, 5.0, 1.0, 7.0, 4.0)
        val dims = longArrayOf(5, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.meanSecondDerivativeCentral(a).use { b ->
                val meanSecondDerivativeCentralResult = b.getData<DoubleArray>()
                Assert.assertEquals(meanSecondDerivativeCentralResult[0], 1.0.toFloat() / 5.0, DELTA)
                Assert.assertEquals(meanSecondDerivativeCentralResult[1], (-3.0).toFloat() / 5.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMinimum() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 13.0, 15.0, 5.0, 16.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 4.0, 20.0, 20.0, 20.0, 4.0, 15.0, 6.0, 30.0, 7.0, 9.0, 18.0, 4.0, 10.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.minimum(a).use { b ->
                val minimumResult = b.getData<DoubleArray>()
                Assert.assertEquals(minimumResult[0], 1.0, DELTA)
                Assert.assertEquals(minimumResult[1], 2.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testNumberCrossingM() {
        val tss = doubleArrayOf(1.0, 2.0, 1.0, 1.0, -3.0, -4.0, 7.0, 8.0, 9.0, 10.0, -2.0, 1.0, -3.0, 5.0, 6.0, 7.0, -10.0, 1.0, 2.0, 1.0, 1.0, -3.0, -4.0, 7.0, 8.0, 9.0, 10.0, -2.0, 1.0, -3.0, 5.0, 6.0, 7.0, -10.0)
        val dims = longArrayOf(17, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.numberCrossingM(a, 0).use { b ->
                val numberCrossingMResult = b.getData<DoubleArray>()
                Assert.assertEquals(numberCrossingMResult[0], 7.0, DELTA)
                Assert.assertEquals(numberCrossingMResult[1], 7.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMedian() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 13.0, 15.0, 5.0, 16.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 4.0, 20.0, 20.0, 20.0, 4.0, 15.0, 6.0, 30.0, 7.0, 9.0, 18.0, 4.0, 10.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.median(a).use { b ->
                val medianResult = b.getData<DoubleArray>()
                Assert.assertEquals(medianResult[0], 20.0, DELTA)
                Assert.assertEquals(medianResult[1], 18.5, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMean() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.mean(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 18.55, DELTA)
                Assert.assertEquals(result[1], 12.7, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMeanChange() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.meanChange(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], (5.toFloat() / 6).toDouble(), DELTA)
                Assert.assertEquals(result[1], (10.toFloat() / 6).toDouble(), DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testMaxLangevinFixedPoint() {
        val tss = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 0f, 1f, 2f, 3f, 4f, 5f)
        val dims = longArrayOf(6, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.maxLangevinFixedPoint(a, 7, 2f).use { b ->
                val result = b.getData<FloatArray>()
                Assert.assertEquals(result[0].toDouble(), 4.562970585, 1e-4)
                Assert.assertEquals(result[1].toDouble(), 4.562970585, 1e-4)
            }
        }

    }

    @Test
    @Throws(Exception::class)
    fun testNumberCwtPeaks() {
        val tss = doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)
        val dims = longArrayOf(21, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.numberCwtPeaks(a, 2).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 2.0, DELTA)
                Assert.assertEquals(result[1], 2.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testNumberPeaks() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.numberPeaks(a, 2).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 1.0, DELTA)
                Assert.assertEquals(result[1], 1.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFftAggregated() {
        val tss = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.fftAggregated(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 1.135143, DELTA)
                Assert.assertEquals(result[1], 2.368324, DELTA)
                Assert.assertEquals(result[2], 1.248777, DELTA)
                Assert.assertEquals(result[3], 3.642666, DELTA)

                Assert.assertEquals(result[4], 1.135143, DELTA)
                Assert.assertEquals(result[5], 2.368324, DELTA)
                Assert.assertEquals(result[6], 1.248777, DELTA)
                Assert.assertEquals(result[7], 3.642666, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPartialAutocorrelation() {
        val len = 3000.0
        val input = DoubleArray((2 * len).toInt())
        val step = 1.0f / (len - 1)
        var i = 0
        while (i < 2 * len) {
            input[i] = step * (i % len)
            i++
        }

        val dims = longArrayOf(3000, 2, 1, 1)
        val lags = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val dimsLags = longArrayOf(10, 1, 1, 1)

        Array(input, dims).use { a ->
            Array(lags, dimsLags).use { b ->
                Features.partialAutocorrelation(a, b).use { c ->

                    val expected = doubleArrayOf(1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018, -0.0008041285327636, -0.0005360860959627, -0.0007371186511591, -0.0004690756904893, -0.0008041299879551, -0.0007371196406893, 1.0, 0.9993331432342529, -0.0006701064994559, -0.0006701068487018, -0.0008041285327636, -0.0005360860959627, -0.0007371186511591, -0.0004690756904893, -0.0008041299879551, -0.0007371196406893)
                    val result = c.getData<DoubleArray>()
                    Assert.assertArrayEquals(result, expected, 1e-3)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPercentageOfReocurringDatapointsToAllDatapoints() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.percentageOfReoccurringDatapointsToAllDatapoints(a, false).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 0.25, DELTA)
                Assert.assertEquals(result[1], 0.25, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testPercentageOfReocurringValuesToAllValues() {
        val tss = doubleArrayOf(1.0, 1.0, 2.0, 3.0, 4.0, 4.0, 5.0, 6.0, 1.0, 2.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
        val dims = longArrayOf(8, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.percentageOfReoccurringValuesToAllValues(a, false).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 4.0 / 8.0, DELTA)
                Assert.assertEquals(result[1], 2.0 / 8.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testQuantile() {
        val tss = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 3.0, 4.0, 13.0, 0.0, 0.0, 0.0, 0.0, 3.0, 4.0, 13.0)
        val q = doubleArrayOf(0.6)
        val dims = longArrayOf(7, 2, 1, 1)
        val dimsQ = longArrayOf(1, 1, 1, 1)
        Array(tss, dims).use { a ->
            Array(q, dimsQ).use { arrayQ ->
                Features.quantile(a, arrayQ).use { b ->
                    val result = b.getData<DoubleArray>()
                    Assert.assertEquals(result[0], 1.79999999, DELTA)
                    Assert.assertEquals(result[1], 1.79999999, DELTA)
                }
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRangeCount() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 5.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.rangeCount(a, 2f, 12f).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 2.0, DELTA)
                Assert.assertEquals(result[1], 3.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRatioBeyondRSigma() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.ratioBeyondRSigma(a, 0.5.toFloat()).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 0.7142857142857143, DELTA)
                Assert.assertEquals(result[1], 0.7142857142857143, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testRatioValueNumberToTimeSeriesLength() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 5.0, 0.0, 4.0, 6.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.ratioValueNumberToTimeSeriesLength(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 4.0 / 7.0, DELTA)
                Assert.assertEquals(result[1], 6.0 / 7.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSampleEntropy() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.sampleEntropy(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 1.252762968495368, DELTA)
                Assert.assertEquals(result[1], 1.252762968495368, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSkewness() {
        val tss = doubleArrayOf(3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0, 3.0, 0.0, 0.0, 4.0, 0.0, 0.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.skewness(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 2.038404735373753, DELTA)
                Assert.assertEquals(result[1], 2.038404735373753, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSpktWelchDensity() {
        val tss = doubleArrayOf(0.0, 1.0, 1.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.0, 1.0, 1.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val dims = longArrayOf(10, 2, 1, 1)
        Array(tss, dims).use { a ->
            val result = Features.spktWelchDensity(a, 0).getData<FloatArray>()
            Assert.assertEquals(result[0].toDouble(), 1.6666667461395264, DELTA)
            Assert.assertEquals(result[1].toDouble(), 1.6666667461395264, DELTA)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testStandardDeviation() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.standardDeviation(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 12.363150892875165, DELTA)
                Assert.assertEquals(result[1], 9.51367436903324, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSumOfReoccurringDatapoints() {
        val tss = doubleArrayOf(3.0, 3.0, 0.0, 4.0, 0.0, 13.0, 13.0, 3.0, 3.0, 0.0, 4.0, 0.0, 13.0, 13.0)
        val dims = longArrayOf(7, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.sumOfReoccurringDatapoints(a, false).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 32.0, DELTA)
                Assert.assertEquals(result[1], 32.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSumOfReoccurringValues() {
        val tss = doubleArrayOf(4.0, 4.0, 6.0, 6.0, 7.0, 4.0, 7.0, 7.0, 8.0, 8.0)
        val dims = longArrayOf(5, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.sumOfReoccurringValues(a, false).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 10.0, DELTA)
                Assert.assertEquals(result[1], 15.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSumValues() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.1, -1.2, -2.0, -3.0, -4.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.sumValues(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 10.1, DELTA)
                Assert.assertEquals(result[1], -10.2, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testSymmetryLooking() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.symmetryLooking(a, 0.1.toFloat()).use { b ->
                val result = b.getData<BooleanArray>()
                Assert.assertTrue(result[0])
                Assert.assertFalse(result[1])
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testTimeReversalAsymmetryStatistic() {
        val tss = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)

        Array(tss, dims).use { a ->
            Features.timeReversalAsymmetryStatistic(a, 2).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 1052.0, DELTA)
                Assert.assertEquals(result[1], -150.625, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testValueCount() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.valueCount(a, 20f).use { b ->
                val result = b.getData<IntArray>()
                Assert.assertEquals(result[0].toDouble(), 9.0, DELTA)
                Assert.assertEquals(result[1].toDouble(), 8.0, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testVariance() {
        val tss = doubleArrayOf(1.0, 1.0, -1.0, -1.0, 1.0, 2.0, -2.0, -1.0)
        val dims = longArrayOf(4, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.variance(a).use { b ->
                val result = b.getData<DoubleArray>()
                Assert.assertEquals(result[0], 1.0, DELTA)
                Assert.assertEquals(result[1], 2.5, DELTA)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testVarianceLargerThanStandardDeviation() {
        val tss = doubleArrayOf(20.0, 20.0, 20.0, 18.0, 25.0, 19.0, 20.0, 20.0, 20.0, 20.0, 40.0, 30.0, 1.0, 50.0, 1.0, 1.0, 5.0, 1.0, 20.0, 20.0, 20.0, 20.0, 20.0, 2.0, 19.0, 1.0, 20.0, 20.0, 20.0, 1.0, 15.0, 1.0, 30.0, 1.0, 1.0, 18.0, 4.0, 1.0, 20.0, 20.0)
        val dims = longArrayOf(20, 2, 1, 1)
        Array(tss, dims).use { a ->
            Features.varianceLargerThanStandardDeviation(a).use { b ->
                val result = b.getData<BooleanArray>()
                Assert.assertTrue(result[0])
                Assert.assertTrue(result[1])
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
