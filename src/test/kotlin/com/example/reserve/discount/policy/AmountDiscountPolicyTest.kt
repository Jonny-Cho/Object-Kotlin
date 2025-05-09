package com.example.reserve.discount.policy

import com.example.reserve.Money
import com.example.reserve.Movie
import com.example.reserve.Screening
import com.example.reserve.discount.policy.testcondition.NotSatisfiedCondition
import com.example.reserve.discount.policy.testcondition.SatisfiedCondition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class AmountDiscountPolicyTest {
    private val screening = Screening(
        movie = Movie(
            title = "Test Movie",
            runningTime = 120.minutes,
            fee = Money.wons(10000),
            discountPolicy = object : DiscountPolicy() {
                override fun getDiscountAmount(screening: Screening): Money = Money.ZERO
            },
        ),
        sequence = 1,
        startTime = LocalDateTime.now(),
    )

    private val discountAmount = Money.wons(1000)

    @Test
    fun `할인 조건 만족 시 고정 금액 할인`() {
        val policy = AmountDiscountPolicy(discountAmount, SatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(discountAmount)
    }

    @Test
    fun `할인 조건 불만족 시 할인 없음 (ZERO)`() {
        val policy = AmountDiscountPolicy(discountAmount, NotSatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(Money.ZERO)
    }

    @Test
    fun `여러 조건 중 하나 만족 시 고정 금액 할인`() {
        val policy = AmountDiscountPolicy(discountAmount, NotSatisfiedCondition, SatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(discountAmount)
    }

    @Test
    fun `여러 조건 모두 불만족 시 할인 없음 (ZERO)`() {
        val policy = AmountDiscountPolicy(discountAmount, NotSatisfiedCondition, NotSatisfiedCondition)
        assertThat(policy.calculateDiscountAmount(screening)).isEqualTo(Money.ZERO)
    }
}
