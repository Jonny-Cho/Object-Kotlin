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

    operator fun times(multiplier: Long): Money {
        return Money(this.amount.multiply(BigDecimal.valueOf(multiplier)))
    }

    override fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Money) return false
        return this.amount.compareTo(other.amount) == 0
    }

    override fun hashCode(): Int {
        return amount.stripTrailingZeros().hashCode()
    }
}
