package com.example.reserve.discount.condition

import com.example.reserve.Movie
import com.example.reserve.Screening
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

private val dummyMovie = Movie()

class SequenceConditionTest {

    @Test
    fun `조건의 순번과 상영 순번이 일치하면 true를 반환한다`() {
        val screening = Screening(dummyMovie, 1, LocalDateTime.now())
        val condition = SequenceCondition(1)

        assertThat(condition.isSatisfiedBy(screening)).isTrue()
    }

    @Test
    fun `조건의 순번과 상영 순번이 다르면 false를 반환한다`() {
        val screening = Screening(dummyMovie, 1, LocalDateTime.now())
        val condition = SequenceCondition(2)

        assertThat(condition.isSatisfiedBy(screening)).isFalse()
    }
} 
