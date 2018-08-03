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

class RegressionTest {

    @Test
    @Throws(Exception::class)
    fun testLinear() {
        val timeSeriesXss = floatArrayOf(0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f)
        val timeSeriesYss = floatArrayOf(0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f)
        val dims = longArrayOf(10, 1, 1, 1)

        Array(timeSeriesXss, dims).use { xss ->
            Array(timeSeriesYss, dims).use { yss ->
                val result = Regression.linear(xss, yss)
                val slope = result[0].getData<FloatArray>()
                val intercept = result[1].getData<FloatArray>()
                val rvalue = result[2].getData<FloatArray>()
                val pvalue = result[3].getData<FloatArray>()
                val stderrest = result[4].getData<FloatArray>()

                Assert.assertEquals(slope[0].toDouble(), 0.344864266, DELTA)
                Assert.assertEquals(intercept[0].toDouble(), 0.268578232, DELTA)
                Assert.assertEquals(rvalue[0].toDouble(), 0.283552942, DELTA)
                Assert.assertEquals(pvalue[0].toDouble(), 0.427239418, DELTA)
                Assert.assertEquals(stderrest[0].toDouble(), 0.412351891, DELTA)

                result[0].close()
                result[1].close()
                result[2].close()
                result[3].close()
                result[4].close()

            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testLinearMultipleTimeSeries() {
        val timeSeriesXss = floatArrayOf(0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f, 0.24580423f, 0.59642861f, 0.35879163f, 0.37891011f, 0.02445137f, 0.23830957f, 0.38793433f, 0.68054104f, 0.83934083f, 0.76073689f)
        val timeSeriesYss = floatArrayOf(0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f, 0.2217416f, 0.06344161f, 0.77944375f, 0.72174137f, 0.19413884f, 0.51146167f, 0.06880307f, 0.39414268f, 0.98172767f, 0.30490851f)
        val dims = longArrayOf(10, 2, 1, 1)

        Array(timeSeriesXss, dims).use { xss ->
            Array(timeSeriesYss, dims).use { yss ->
                val result = Regression.linear(xss, yss)
                val slope = result[0].getData<FloatArray>()
                val intercept = result[1].getData<FloatArray>()
                val rvalue = result[2].getData<FloatArray>()
                val pvalue = result[3].getData<FloatArray>()
                val stderrest = result[4].getData<FloatArray>()

                Assert.assertEquals(slope[0].toDouble(), 0.344864266, DELTA)
                Assert.assertEquals(intercept[0].toDouble(), 0.268578232, DELTA)
                Assert.assertEquals(rvalue[0].toDouble(), 0.283552942, DELTA)
                Assert.assertEquals(pvalue[0].toDouble(), 0.427239418, DELTA)
                Assert.assertEquals(stderrest[0].toDouble(), 0.412351891, DELTA)
                Assert.assertEquals(slope[1].toDouble(), 0.344864266, DELTA)
                Assert.assertEquals(intercept[1].toDouble(), 0.268578232, DELTA)
                Assert.assertEquals(rvalue[1].toDouble(), 0.283552942, DELTA)
                Assert.assertEquals(pvalue[1].toDouble(), 0.427239418, DELTA)
                Assert.assertEquals(stderrest[1].toDouble(), 0.412351891, DELTA)

                result[0].close()
                result[1].close()
                result[2].close()
                result[3].close()
                result[4].close()
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