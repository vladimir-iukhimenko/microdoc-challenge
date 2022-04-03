package com.microdoc.service

import java.io.BufferedReader
import java.io.FileReader
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths

interface ReaderService {

    fun readFromFile(filePath: String): Collection<BigDecimal>
}

class ReaderServiceImpl(
    private val numberService: NumberService
): ReaderService {
    override fun readFromFile(filePath: String): Collection<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        val buffer = mutableListOf<BigDecimal>()
        BufferedReader(FileReader(filePath)).use { reader ->
            var nextLine: String? = null
            val length = Files.lines(Paths.get(filePath)).count()
            do {
                buffer.clear()
                for (i in 1..length / countDivider(length)) {
                    nextLine = reader.readLine()
                    if (nextLine.isNullOrEmpty()) break
                    else buffer.add(BigDecimal(nextLine.replace(",", ".")))
                }
                if (buffer.isNotEmpty()) result.add(numberService.findMedianOfCollection(buffer))
            } while (nextLine != null)
        }
        return result
    }

    internal fun countDivider(linesNumber: Long): Long {
        var divider: Long = 2
        if (linesNumber <= 1000000) while (linesNumber % divider != 0L) divider++
        else while ((linesNumber / divider) > 100000 || linesNumber % divider != 0L) divider++
        return divider
    }

}