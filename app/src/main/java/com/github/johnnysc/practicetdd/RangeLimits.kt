package com.github.johnnysc.practicetdd

interface RangeLimits {

    fun pair(number: Int): RangePair

    class Base(private val list: List<Int>) : RangeLimits {

        override fun pair(number: Int): RangePair {
            var left = Int.MIN_VALUE
            var right = Int.MAX_VALUE

            for (digit in list) {
                if (digit < number) {
                    left = digit
                } else if (digit > number) {
                    right = digit
                    break
                }
            }
            return RangePair(left, right)
        }

    }

}

data class RangePair(private val left: Int, private val right: Int)