package com.example.reserve.discount.policy

import com.example.reserve.Money
import com.example.reserve.Screening
import com.example.reserve.discount.condition.DiscountCondition
import java.math.BigDecimal

class PercentDiscountPolicy(
    private val percent: BigDecimal,
    vararg conditions: DiscountCondition,
): DiscountPolicy(*conditions) {

    override fun getDiscountAmount(screening: Screening): Money {
        return screening.getMovieFee().times(percent)
    }
}
