package com.example.reserve.discount.policy

import com.example.reserve.Money
import com.example.reserve.Screening
import com.example.reserve.discount.condition.DiscountCondition

class AmountDiscountPolicy(
    private val discountAmount: Money,
    vararg conditions: DiscountCondition,
) : DiscountPolicy(*conditions) {
    override fun getDiscountAmount(screening: Screening) = discountAmount
}
