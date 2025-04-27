package com.example.reserve

abstract class DiscountPolicy(
    private vararg val conditions: DiscountCondition
) {
    fun calculateDiscountAmount(screening: Screening): Money {
        for (condition in conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening)
            }
        }
        return Money.ZERO
    }

    protected abstract fun getDiscountAmount(screening: Screening): Money
}

interface DiscountCondition {
    fun isSatisfiedBy(screening: Screening): Boolean
}
