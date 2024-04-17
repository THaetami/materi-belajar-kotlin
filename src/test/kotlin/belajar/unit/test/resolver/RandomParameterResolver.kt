package belajar.unit.test.resolver

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import java.util.*


class RandomParameterResolver: ParameterResolver {
    /*
        Ini adalah contoh resolver yang bertanggung
        jawab untuk menyediakan objek Random
        sebagai dependensi kepada metode
        pengujian. Dimana object Random ini
        akan menghasilkan angka acak
        selama pengujian
    */
    private val random: Random = Random()

    override fun supportsParameter(p0: ParameterContext, p1: ExtensionContext): Boolean {
        return p0.parameter.type == Random::class.java
    }

    override fun resolveParameter(p0: ParameterContext?, p1: ExtensionContext?): Any {
        return random
    }

}