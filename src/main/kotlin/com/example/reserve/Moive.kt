package com.example.reserve

import com.example.reserve.discount.policy.DiscountPolicy
import kotlin.time.Duration

class Moive(
    private val title: String,
    private val runningTime: Duration,
    private val fee: Money,
    private val discountPolicy: DiscountPolicy,
) {

    fun calculateMovieFee(screening: Screening) = fee.minus(discountPolicy.calculateDiscountAmount(screening))
}
