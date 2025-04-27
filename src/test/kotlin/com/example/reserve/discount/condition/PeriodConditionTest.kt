package com.example.reserve.discount.condition

import com.example.reserve.Money
import com.example.reserve.Movie
import com.example.reserve.Screening
import com.example.reserve.discount.policy.DiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.time.Duration.Companion.minutes

private val dummyMovie = Movie(
    title = "Test Movie",
    runningTime = 120.minutes,
    fee = Money.wons(10000),
    discountPolicy = object : DiscountPolicy() {
        override fun getDiscountAmount(screening: Screening): Money = Money.ZERO
    }
)

class PeriodConditionTest {

    private val condition = PeriodCondition(
        dayOfWeek = DayOfWeek.MONDAY,
        startTime = LocalTime.of(10, 0),
        endTime = LocalTime.of(11, 59)
    )

    @Test
    fun `조건 기간 내 (시작 시간)`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 13, 10, 0)
        )
        assertThat(condition.isSatisfiedBy(screening)).isTrue()
    }

    @Test
    fun `조건 기간 내 (중간 시간)`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 13, 11, 0)
        )
        assertThat(condition.isSatisfiedBy(screening)).isTrue()
    }

    @Test
    fun `조건 기간 내 (종료 시간)`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 13, 11, 59)
        )
        assertThat(condition.isSatisfiedBy(screening)).isTrue()
    }

    @Test
    fun `조건 기간 이전 (시작 시간 바로 전)`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 13, 9, 59, 59)
        )
        assertThat(condition.isSatisfiedBy(screening)).isFalse()
    }

    @Test
    fun `조건 기간 이후 (종료 시간 바로 후)`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 13, 12, 0)
        )
        assertThat(condition.isSatisfiedBy(screening)).isFalse()
    }

    @Test
    fun `요일이 다름`() {
        val screening = Screening(
            movie = dummyMovie,
            sequence = 1,
            startTime = LocalDateTime.of(2024, 5, 14, 10, 0)
        )
        assertThat(condition.isSatisfiedBy(screening)).isFalse()
    }
} 
