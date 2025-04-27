package com.example.reserve.discount.policy

import com.example.reserve.Money
import com.example.reserve.Movie
import com.example.reserve.Screening
import com.example.reserve.discount.policy.testcondition.NotSatisfiedCondition
import com.example.reserve.discount.policy.testcondition.SatisfiedCondition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class PercentDiscountPolicyTest {

    private val movieFee = Money.wons(10000)
    private val screening = Screening(
        movie = Movie(
            title = "Test Movie",
            runningTime = 120.minutes,
            fee = Money.wons(10000),
            discountPolicy = object : DiscountPolicy() {
                override fun getDiscountAmount(screening: Screening): Money = Money.ZERO
            }
        ),
        sequence = 1,
        startTime = LocalDateTime.now(),
    )
    private val percent = BigDecimal("0.1")

    @Test
    fun `할인 조건 만족 시 비율 할인 적용`() {
        val policy = PercentDiscountPolicy(percent, SatisfiedCondition)
        val expectedDiscount = movieFee.times(percent)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(expectedDiscount)
    }

    @Test
    fun `할인 조건 불만족 시 할인 없음 (ZERO)`() {
        val policy = PercentDiscountPolicy(percent, NotSatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(Money.ZERO)
    }

    @Test
    fun `여러 조건 중 하나 만족 시 비율 할인 적용`() {
        val policy = PercentDiscountPolicy(percent, NotSatisfiedCondition, SatisfiedCondition)
        val expectedDiscount = movieFee.times(percent)

        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(expectedDiscount)
    }

    @Test
    fun `여러 조건 모두 불만족 시 할인 없음 (ZERO)`() {
        val policy = PercentDiscountPolicy(percent, NotSatisfiedCondition, NotSatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(Money.ZERO)
    }
} 
