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
 * Class to change internal properties of the Khiva library.
 */
open class Library {

    /**
     * Khiva Backend.
     */
    enum class Backend constructor(
            /**
             * Gets the ordinal from the Khiva Backend.
             *
             * @return The ordinal of the Khiva Backend.
             */
            val khivaOrdinal: Int) {
        /**
         * DEFAULT Backend.
         */
        KHIVA_BACKEND_DEFAULT(0),
        /**
         * CPU Backend.
         */
        KHIVA_BACKEND_CPU(1),
        /**
         * CUDA Backend.
         */
        KHIVA_BACKEND_CUDA(2),
        /**
         * OPENCL Backend.
         */
        KHIVA_BACKEND_OPENCL(4);


        companion object {
            /**
             * Gets the backend from the ordinal.
             *
             * @param ordinal Integer representing the Backend ordinal.
             * @return The corresponding Khiva BACKEND.
             */
            fun getBackendFromOrdinal(ordinal: Int): Backend {
                return when (ordinal) {
                    0 -> KHIVA_BACKEND_DEFAULT
                    1 -> KHIVA_BACKEND_CPU
                    2 -> KHIVA_BACKEND_CUDA
                    4 -> KHIVA_BACKEND_OPENCL
                    else -> KHIVA_BACKEND_DEFAULT
                }
            }
        }
    }

    companion object {
        private var OS: String? = null

        init {
            OS = System.getProperty("os.name").toLowerCase()
            if (OS!!.indexOf("mac") >= 0) {
                System.load("/usr/local/lib/libkhiva_jni.dylib")
            } else if (OS!!.indexOf("win") >= 0) {
                System.load("C:\\Program Files\\Khiva\\v0\\lib\\khiva_jni.dll")
            } else if (OS!!.indexOf("nix") >= 0 || OS!!.indexOf("nux") >= 0 || OS!!.indexOf("aix") > 0) {
                System.load("/usr/local/lib/libkhiva_jni.so")
            }
        }

        @JvmStatic
        private external fun backendInfo(): String

        @JvmStatic
        private external fun setDevice(device: Int)

        @JvmStatic
        private external fun getBackends(): Int

        @JvmStatic
        private external fun getDeviceID(): Int

        @JvmStatic
        private external fun getBackend(): Int

        @JvmStatic
        private external fun setBackend(be: Int): Int

        @JvmStatic
        private external fun getDeviceCount(): Int

        @JvmStatic
        private external fun version(): String


        /**
         * Prints information from the current backend.
         */
        fun printBackendInfo() {
            println(backendInfo())
        }

        /**
         * Gets information from the current backend.
         *
         * @return String with information from the active backend.
         */
        val backendInfo: String
            get() = backendInfo()

        /**
         * Sets the Khiva device.
         *
         * @param device Device selected.
         */
        fun setKhivaDevice(device: Int) {
            setDevice(device)
        }


        /**
         * Gets the available backends.
         *
         * @return The available backends.
         */
        val khivaBackends: Int
            get() = getBackends()

        /**
         * Get the device id.
         *
         * @return The device id.
         */
        val khivaDeviceID: Int
            get() = getDeviceID()

        /**
         * Gets the active backend.
         *
         * @return The active backend.
         */
        /**
         * Sets the Khiva backend.
         *
         * @param khivaBE selected backend.
         */
        var khivaBackend: Backend
            get() = Backend.getBackendFromOrdinal(getBackend())
            set(khivaBE) {
                setBackend(khivaBE.khivaOrdinal)
            }

        /**
         * Gets the devices count.
         *
         * @return The devices count.
         */
        val khivaDeviceCount: Int
            get() = getDeviceCount()

        /**
         * Gets the vesion of the library.
         *
         * @return A string with the khiva version.
         */
        val khivaVersion: String
            get() = version()
    }
}