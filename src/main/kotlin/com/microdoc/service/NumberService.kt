package com.microdoc.service

import java.math.BigDecimal

interface NumberService {
    fun findMedianOfCollection(numbers: Collection<BigDecimal>): BigDecimal
}

class NumberServiceImpl: NumberService {
    override fun findMedianOfCollection(numbers: Collection<BigDecimal>): BigDecimal {
        return if (numbers.size % 2 == 0) (getNth(numbers, (numbers.size / 2) - 1) + getNth(numbers, numbers.size / 2)).divide(BigDecimal(2))
        else getNth(numbers, numbers.size / 2)
    }

    internal fun getNth(numbers: Collection<BigDecimal>, position: Int): BigDecimal  {
        val supportElement = numbers.elementAt(numbers.size / 2)
        val result: BigDecimal
        val elementsLowerSupportElement = mutableListOf<BigDecimal>()
        val elementsEqualToSupportElement = mutableListOf<BigDecimal>()
        val elementsBiggerSupportElement = mutableListOf<BigDecimal>()
        numbers.forEach {
            if (it < supportElement) elementsLowerSupportElement.add(it)
            else if (it > supportElement) elementsBiggerSupportElement.add(it)
            else elementsEqualToSupportElement.add(it)
        }

        if (position < elementsLowerSupportElement.size) result = getNth(elementsLowerSupportElement, position)
        else if (position < elementsLowerSupportElement.size + elementsEqualToSupportElement.size) result = supportElement
        else result = getNth(elementsBiggerSupportElement, position - elementsLowerSupportElement.size - elementsEqualToSupportElement.size)
        return result
    }

}
