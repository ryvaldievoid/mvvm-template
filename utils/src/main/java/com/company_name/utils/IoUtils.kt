package com.company_name.utils

import android.util.Log
import java.io.*


/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 23/02/19
 * dedicated to build android-cus-store
 *
 */

object IoUtils {
    private val BUFFER_SIZE = 1024 * 2

    @Throws(Exception::class, IOException::class)
    fun copy(input: InputStream, output: OutputStream): Int {
        val buffer = ByteArray(BUFFER_SIZE)

        val `in` = BufferedInputStream(input, BUFFER_SIZE)
        val out = BufferedOutputStream(output, BUFFER_SIZE)
        var count = 0
        var n: Int
        try {
            while (true) {
                n = `in`.read(buffer)
                if (n < 0) break

                count += n
                out.write(buffer, 0, n)
            }

            out.flush()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                Log.d("", e.message ?: "")
            }

            try {
                `in`.close()
            } catch (e: IOException) {
                Log.d("", e.message ?: "")
            }

        }
        return count
    }

}// Utility class.