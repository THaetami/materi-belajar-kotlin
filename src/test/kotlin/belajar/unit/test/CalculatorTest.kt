package belajar.unit.test

import belajar.unit.test.generator.SimpleDisplayNameGenerator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assumptions.*
import org.opentest4j.TestAbortedException
import kotlin.test.assertEquals

// @DisplayName("Test for calculator class")
@DisplayNameGeneration(SimpleDisplayNameGenerator::class)
class CalculatorTest {

    companion object {

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("sebelum semua unit test")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("setelah semua unit test")
        }
    }

    private val calculator = Calculator()

    @BeforeEach
    fun beforeEach() {
        println("sebelum unit test")
    }

    @AfterEach
    fun afterEach() {
        println("setelah unit test")
    }

    @Test
    // @DisplayName("Test for function Calculator.add(10, 10)")
    fun testAddSuccess() {
        println("unit test testAddSuccess()")
        val result = calculator.add(10, 10)
        assertEquals(20, result, "hasil harus 20")
    }

    @Test
    // @DisplayName("Test for function Calculator.divide(100, 10)")
    fun testDivideSuccess() {
        println("unit test testDivideSuccess()")
        val result = calculator.divide(100, 10)
        assertEquals(10, result, "hasil harus 10")
    }

    @Test
    // @DisplayName("Test for function Calculator.divide(100, 0)")
    fun testDivideFailed() {
        println("unit test testDivideFailed()")
        assertThrows<IllegalArgumentException> {
            calculator.divide(100, 0)
        }
    }

    @Test
    @Disabled("sedang diperbaiki")
    fun testComingSoon() {
        //
    }

    // membatalkan test secara manual dengan menambahkan throw TestAbortedException()
    @Test
    fun testAborted() {

        val profile = System.getenv()["PROFILE"]
        if ("DEV" != profile) {
            throw TestAbortedException() // dengan ini test akan dibatalkan sehingga tidak muncul peringatan error
        }

        println("Test not aborted because dev profile")
    }


    // membatalkan tidak secara manual menggunakan throw error TestAbortedException
    @Test
    fun testAssumptions() {
        assumeTrue("DEV" == System.getenv()["PROFILE"])

        println("Test not aborted because dev profile")
    }

}