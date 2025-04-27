package com.example.reserve

import java.time.LocalDateTime

class Screening(
    private val movie: Movie,
    private val sequence: Int,
    val startTime: LocalDateTime,
) {
    fun reserve(
        customer: Customer,
        audienceCount: Long,
    ): Reservation = Reservation(customer, this, calculateFee(audienceCount), audienceCount)

    private fun calculateFee(audienceCount: Long): Money = movie.calculateMovieFee(this).times(audienceCount)

    fun isSequence(sequence: Int) = sequence == this.sequence

    fun getMovieFee() = movie.fee
}
