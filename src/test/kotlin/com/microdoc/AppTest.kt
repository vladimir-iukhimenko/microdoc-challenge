package com.microdoc

import com.microdoc.service.NumberServiceImpl
import com.microdoc.service.ReaderServiceImpl
import org.junit.Test
import java.io.File
import java.io.PrintWriter
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.random.Random
import kotlin.test.assertEquals

class AppTest {

    @Test
    fun checkSmallMedian() {
        generateFile(201, false)
        val numberService = NumberServiceImpl()
        val reader = ReaderServiceImpl(numberService)

        val testList = reader.readFromFile("./test.txt")

        val service = NumberServiceImpl()
        Files.delete(Paths.get("./test.txt"))
        assertEquals(BigDecimal(100), service.findMedianOfCollection(testList))
    }

    @Test
    fun checkBigMedian() {
        generateFile(9999999, false)
        val numberService = NumberServiceImpl()
        val reader = ReaderServiceImpl(numberService)

        val testList = reader.readFromFile("./test.txt")

        val service = NumberServiceImpl()
        Files.delete(Paths.get("./test.txt"))
        assertEquals(BigDecimal(4999999), service.findMedianOfCollection(testList))
    }

    private fun generateFile(numberLines: Int, random: Boolean) {
        val outWriter = PrintWriter(File("./test.txt"))
        repeat(numberLines) {
            outWriter.println(
                if (random) selectNumber(Random.nextInt(0,4))
                else it
            )
        }
        outWriter.close()
    }

    private fun selectNumber(num: Int): String {
        return when(num) {
            0 -> Random.nextInt(0, 100000)
            1 -> (Math.random() * 900) + 10.0
            2 -> ((Math.random() * 90000) + 10.0).toString().replace(".", ",")
            else -> Random.nextInt()
        }.toString()
    }
}
