package belajar.unit.test

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.Test

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS) // secara default lifecycle `PER_METHOD`
@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class) // menggunakan interface OrderAnnotation
class OrderTest {

    private var counter = 0 // dengan test instance per class, counter akan berubah nilainya setiap function/method dijalankan

    /*
        dengan mengubah lifecyle menjadi `PER_CLASS`
        maka kita bisa menggunakan `@BeforeAll` dan
        `@AfterAll` langsung tanpa perlu membuatnnya
        di dalam companion object seperti pada
        file CalculatorTest
    */
    @BeforeAll
    fun beforeAll() {
        println("sebelum semua unit test")
    }

    @AfterAll
    fun afterAll() {
        println("setelah semua unit test")
    }

    @Test
    @Order(3)
    fun testA() {
        counter++
        println(counter)
        println(this.hashCode())
    }

    @Test
    @Order(2)
    fun testB() {
        counter++
        println(counter)
        println(this.hashCode())
    }

    @Test
    @Order(1)
    fun testC() {
        counter++
        println(counter)
        println(this.hashCode())
    }

}