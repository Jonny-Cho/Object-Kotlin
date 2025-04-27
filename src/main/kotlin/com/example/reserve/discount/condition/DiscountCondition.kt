package com.example.reserve.discount.condition

import com.example.reserve.Screening

interface DiscountCondition {
    fun isSatisfiedBy(screening: Screening): Boolean
}
