package com.example.reserve

import java.time.LocalDateTime

class Screening(
    private val movie: Movie,
    private val sequence: Int,
    private val whenScreened: LocalDateTime,
) {
    fun reserve(
        customer: Customer,
        audienceCount: Long,
    ): Reservation = Reservation(customer, this, calculateFee(audienceCount), audienceCount)

    private fun calculateFee(audienceCount: Long): Money = movie.calculatemoviewFee(this).times(audienceCount)
}
