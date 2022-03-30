package com.hkm.curier

internal class USConversion {
    companion object {
        internal fun convert(value: Double, locale: Currency.Locale) : Double {
            return when(locale) {
                Currency.Locale.NIO -> value * 35.696
                Currency.Locale.USD -> value
                Currency.Locale.EUR -> value * 0.90084
                Currency.Locale.MXN -> value * 19.998
                Currency.Locale.JPY -> value * 122.78
                Currency.Locale.GBP -> value * 0.76139
                Currency.Locale.INR -> value * 75.608
            }
        }
    }
}

class Currency(value: Double, locale: Locale) {
    enum class Locale {NIO, USD, EUR, MXN, JPY, GBP, INR}
    private var value = 0.0
    private var locale = Locale.NIO

    fun getValue() : Double = this.value
//    fun getLocale() : Locale = this.locale

    init {
        this.value = value
        this.locale = locale
    }

    fun convertTo(locale: Locale) : Currency {
        val valueConverted = relativeConversion(from = this.locale, target = locale)
        return Currency(valueConverted, locale)
    }

    private fun relativeConversion(from: Locale, target: Locale) : Double {
        if (from == Locale.USD) {
            return USConversion.convert(this.value, target)
        }
        val usToFrom = USConversion.convert(1.0, from)
        val usToTarget = USConversion.convert(1.0, target)
        return this.value * (usToTarget/usToFrom)
    }
}