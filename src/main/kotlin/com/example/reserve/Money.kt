package com.example.reserve

import java.math.BigDecimal

data class Money(
    private val amount: BigDecimal
) : Comparable<Money> {
    companion object {
        fun wons(amount: Long): Money = Money(BigDecimal.valueOf(amount))
        val ZERO: Money = wons(0)
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount.add(other.amount))
    }

    operator fun minus(other: Money): Money {
        return Money(this.amount.subtract(other.amount))
    }

    operator fun times(multiplier: BigDecimal): Money {
        return Money(this.amount.multiply(multiplier))
    }

    override fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }
}
