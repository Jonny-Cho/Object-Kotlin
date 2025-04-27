package com.example.reserve.discount.policy.testcondition

import com.example.reserve.Screening
import com.example.reserve.discount.condition.DiscountCondition

object NotSatisfiedCondition : DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean = false
}
