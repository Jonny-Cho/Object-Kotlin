package com.example.reserve

import com.example.reserve.discount.condition.PeriodCondition
import com.example.reserve.discount.condition.SequenceCondition
import com.example.reserve.discount.policy.AmountDiscountPolicy
import com.example.reserve.discount.policy.PercentDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.time.Duration.Companion.minutes

class MovieTest {

    private val movieWithAmountDiscount = Movie(
        title = "Amount Test Movie",
        runningTime = 120.minutes,
        fee = Money.wons(10000),
        discountPolicy = AmountDiscountPolicy(
            discountAmount = Money.wons(800),
            SequenceCondition(1),
            PeriodCondition(
                dayOfWeek = DayOfWeek.MONDAY,
                startTime = LocalTime.of(10, 0),
                endTime = LocalTime.of(11, 59, 59, 999999999)
            )
        )
    )

    @Test
    fun `AmountDiscount - 조건 만족 시 (Sequence)`() {
        val screening = Screening(movieWithAmountDiscount, 1, LocalDateTime.of(2024, 5, 14, 14, 0))
        val expectedFee = Money.wons(10000).minus(Money.wons(800))
        assertThat(movieWithAmountDiscount.calculateMovieFee(screening)).isEqualTo(expectedFee)
    }

    @Test
    fun `AmountDiscount - 조건 만족 시 (Period)`() {
        val screening = Screening(movieWithAmountDiscount, 2, LocalDateTime.of(2024, 5, 13, 10, 30))
        val expectedFee = Money.wons(10000).minus(Money.wons(800))
        assertThat(movieWithAmountDiscount.calculateMovieFee(screening)).isEqualTo(expectedFee)
    }

    @Test
    fun `AmountDiscount - 조건 불만족 시`() {
        val screening = Screening(movieWithAmountDiscount, 2, LocalDateTime.of(2024, 5, 14, 14, 0))
        assertThat(movieWithAmountDiscount.calculateMovieFee(screening)).isEqualTo(Money.wons(10000))
    }

    private val movieWithPercentDiscount = Movie(
        title = "Percent Test Movie",
        runningTime = 90.minutes,
        fee = Money.wons(10000),
        discountPolicy = PercentDiscountPolicy(
            percent = BigDecimal("0.1"),
            SequenceCondition(1)
        )
    )

    @Test
    fun `PercentDiscount - 조건 만족 시`() {
        val screening = Screening(movieWithPercentDiscount, 1, LocalDateTime.now())
        assertThat(movieWithPercentDiscount.calculateMovieFee(screening)).isEqualTo(Money.wons(9000))
    }

    @Test
    fun `PercentDiscount - 조건 불만족 시`() {
        val screening = Screening(movieWithPercentDiscount, 2, LocalDateTime.now())
        assertThat(movieWithPercentDiscount.calculateMovieFee(screening)).isEqualTo(Money.wons(10000))
    }
}
