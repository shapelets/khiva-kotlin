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
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.regex.Pattern

class LibraryTest {

    private val khivaVersionFromFile: String
        get() {
            var version = ""
            val filePath: String

            if (System.getProperty("os.name").startsWith("Windows")) {
                filePath = "C:/Program Files/Khiva/v0/include/khiva/version.h"
            } else {
                filePath = "/usr/local/include/khiva/version.h"
            }

            var data = ""
            try {
                data = String(Files.readAllBytes(Paths.get(filePath)))
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val m = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+)").matcher(data)
            if (m.find()) {
                version = m.group(1)
            }

            return version
        }

    @Test
    fun testPrintBackendInfo() {
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val old = System.out
        System.setOut(ps)

        Library.printBackendInfo()

        // Put things back
        System.out.flush()
        System.setOut(old)

        val info = baos.toString()
        val words = info.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        Assert.assertEquals(words[0], "ArrayFire")
    }

    @Test
    fun testBackendInfo() {
        val info = Library.backendInfo
        val words = info.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        Assert.assertEquals(words[0], "ArrayFire")
    }

    @Test
    fun testSetBackend() {
        val backends = Library.khivaBackends
        val cuda = backends and Library.Backend.KHIVA_BACKEND_CUDA.khivaOrdinal
        val opencl = backends and Library.Backend.KHIVA_BACKEND_OPENCL.khivaOrdinal
        val cpu = backends and Library.Backend.KHIVA_BACKEND_CPU.khivaOrdinal

        if (cuda != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CUDA
            Assert.assertEquals(Library.khivaBackend, Library.Backend.KHIVA_BACKEND_CUDA)
        }
        if (opencl != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_OPENCL
            Assert.assertEquals(Library.khivaBackend, Library.Backend.KHIVA_BACKEND_OPENCL)
        }
        if (cpu != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CPU
            Assert.assertEquals(Library.khivaBackend, Library.Backend.KHIVA_BACKEND_CPU)
        }
    }

    @Test
    fun testGetDevice() {
        val backends = Library.khivaBackends
        val cuda = backends and Library.Backend.KHIVA_BACKEND_CUDA.khivaOrdinal
        val opencl = backends and Library.Backend.KHIVA_BACKEND_OPENCL.khivaOrdinal
        val cpu = backends and Library.Backend.KHIVA_BACKEND_CPU.khivaOrdinal

        if (cuda != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CUDA
            for (i in 0 until Library.khivaDeviceCount) {
                Library.setKhivaDevice(i)
                Assert.assertEquals(Library.khivaDeviceID, i)
            }
        }
        if (opencl != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_OPENCL
            for (i in 0 until Library.khivaDeviceCount) {
                Library.setKhivaDevice(i)
                Assert.assertEquals(Library.khivaDeviceID, i)
            }
        }
        if (cpu != 0) {
            Library.khivaBackend = Library.Backend.KHIVA_BACKEND_CPU
            for (i in 0 until Library.khivaDeviceCount) {
                Library.setKhivaDevice(i)
                Assert.assertEquals(Library.khivaDeviceID, i)
            }
        }
    }

    @Test
    fun testGetKhivaVersion() {
        Assert.assertEquals(Library.khivaVersion, khivaVersionFromFile)
    }
}