package belajar.unit.test

import belajar.unit.test.resolver.RandomParameterResolver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import java.util.*


/*
    ini merupakan contoh proses dependency
    injection, yaitu proses bagaimana kita
    bisa memasukan dependency (object atau
    instance secara otomatis)
*/
@Extensions(value = [ // menggunakan RandomParameterResolver |
    ExtendWith(RandomParameterResolver::class)
])
abstract class ParentCalculatorTest(private val random: Random) {
    /*
        ini adalah contoh parent test class
        yang nantinya akan diextend sehingga
        akan mewariskan object calculator,
        method beforeEach dan juga object
        random yang didapat dari
        `RandomParameterResolver`
    */
    val calculator = Calculator()

    @BeforeEach
    fun beforeEach() {
        println("sebelum semua unit test")
    }

}