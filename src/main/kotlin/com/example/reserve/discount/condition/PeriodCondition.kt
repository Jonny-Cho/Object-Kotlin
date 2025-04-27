package com.example.reserve.discount.condition

import com.example.reserve.Screening
import java.time.DayOfWeek
import java.time.LocalTime

class PeriodCondition(
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime,
) : DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean =
        screening.startTime.dayOfWeek == dayOfWeek &&
            this.startTime <= screening.startTime.toLocalTime() &&
            screening.startTime.toLocalTime() <= this.endTime
}
