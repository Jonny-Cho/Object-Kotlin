package com.example.reserve.discount.policy

import com.example.reserve.Money
import com.example.reserve.Screening
import com.example.reserve.discount.condition.DiscountCondition

abstract class DiscountPolicy(
    vararg conditions: DiscountCondition,
) {
    private val conditions: List<DiscountCondition> = conditions.toList()

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
