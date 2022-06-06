package io.github.keddnyo.midoze.utils

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class FileManager(outputDir: File) {

    val outputZipFile = "${outputDir}/archive.zip"

    /*fun createZip(files: ArrayList<String>) {
        runCatching {
            ZipOutputStream(BufferedOutputStream(FileOutputStream(outputZipFile))).use { out ->
                val data = ByteArray(1024)
                for (file in files) {
                    FileInputStream(file).use { fi ->
                        BufferedInputStream(fi).use { origin ->
                            val entry = ZipEntry(file)
                            out.putNextEntry(entry)
                            while (true) {
                                val readBytes = origin.read(data)
                                if (readBytes == -1) {
                                    break
                                }
                                out.write(data, 0, readBytes)
                            }
                        }
                    }
                }
            }
        }.onFailure { _ ->
            // exception.printStackTrace()
        }
    }*/

    @Throws(IOException::class)
    fun zip(files: ArrayList<File>) {
        val bufferSize = 2048
        var origin: BufferedInputStream? = null
        val out = ZipOutputStream(BufferedOutputStream(FileOutputStream(outputZipFile)))
        try {
            val data = ByteArray(bufferSize)
            for (file in files) {
                val fileInputStream = FileInputStream(file)
                origin = BufferedInputStream(fileInputStream, bufferSize)
                val filePath = file.absolutePath
                try {
                    val entry = ZipEntry(filePath.substring(filePath.lastIndexOf("/") + 1))
                    out.putNextEntry(entry)
                    var count: Int
                    while (origin.read(data, 0, bufferSize).also { count = it } != -1) {
                        out.write(data, 0, count)
                    }
                } finally {
                    origin.close()
                }
            }
        } finally {
            out.close()
        }
    }
}