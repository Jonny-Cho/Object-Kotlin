package com.example.reserve.discount.policy.testcondition

import com.example.reserve.Screening
import com.example.reserve.discount.condition.DiscountCondition

object SatisfiedCondition : DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean = true
}
