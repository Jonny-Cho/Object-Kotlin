package com.example.reserve.discount.condition

import com.example.reserve.Screening

class SequenceCondition(
    private val sequence: Int
) : DiscountCondition {

    override fun isSatisfiedBy(screening: Screening): Boolean {
        return screening.isSequence(sequence)
    }
}
