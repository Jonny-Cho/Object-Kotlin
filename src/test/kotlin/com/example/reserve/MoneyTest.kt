@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.reserve

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `같은 금액은 같다고 판별한다`() {
        assertThat(Money.wons(1000)).isEqualTo(Money.wons(1000))
    }

    @Test
    fun `다른 금액은 다르다고 판별한다`() {
        assertThat(Money.wons(1000)).isNotEqualTo(Money.wons(2000))
    }

    @Test
    fun `wons 팩토리 메소드는 정확한 Money 객체를 생성한다`() {
        assertThat(Money.wons(5000)).isEqualTo(Money(BigDecimal.valueOf(5000)))
    }

    @Test
    fun `ZERO는 0원을 나타낸다`() {
        assertThat(Money.ZERO).isEqualTo(Money(BigDecimal.ZERO))
        assertThat(Money.ZERO).isEqualTo(Money.wons(0))
    }

    @Test
    fun `덧셈 연산 테스트`() {
        val result = Money.wons(1000) + Money.wons(500)
        assertThat(result).isEqualTo(Money.wons(1500))
    }

    @Test
    fun `뺄셈 연산 테스트`() {
        val result = Money.wons(1000) - Money.wons(500)
        assertThat(result).isEqualTo(Money.wons(500))
    }

    @Test
    fun `곱셈 연산 테스트`() {
        val result = Money.wons(1000) * BigDecimal("1.5")
        assertThat(result.compareTo(Money.wons(1500))).isZero()
        assertThat(result).isEqualTo(Money(BigDecimal("1500.0")))
    }

    @Test
    fun `비교 연산자 테스트 (작음)`() {
        assertThat(Money.wons(1000)).isLessThan(Money.wons(2000))
        assertThat(Money.wons(1000)).isEqualByComparingTo(Money.wons(1000))
        assertThat(Money.wons(2000)).isGreaterThan(Money.wons(1000))
    }

    @Test
    fun `비교 연산자 테스트 (크거나 같음)`() {
        assertThat(Money.wons(2000)).isGreaterThanOrEqualTo(Money.wons(1000))
        assertThat(Money.wons(1000)).isGreaterThanOrEqualTo(Money.wons(1000))
        assertThat(Money.wons(1000)).isLessThan(Money.wons(2000))
    }
}
