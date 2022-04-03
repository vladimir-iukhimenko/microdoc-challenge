package com.microdoc

import com.microdoc.service.NumberServiceImpl
import com.microdoc.service.ReaderServiceImpl

fun main(args: Array<String>) {

    val numberService = NumberServiceImpl()
    val reader = ReaderServiceImpl(numberService)

    val filePath = runCatching { args[0] }.getOrElse { throw RuntimeException("File path was not provided!") }
    val testList = reader.readFromFile(filePath)

    val service = NumberServiceImpl()
    println(service.findMedianOfCollection(testList))

}

