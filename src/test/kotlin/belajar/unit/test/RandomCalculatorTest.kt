package belajar.unit.test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

/*
    ini adalah contoh child test class
    yang mengekstend class `ParentCalculatorTest()`
*/
class RandomCalculatorTest(private val random: Random): ParentCalculatorTest(random) {

    companion object {
        @JvmStatic // mengubah menjadi method static
        fun parameterSource(): List<Int> {
            return listOf(10,30, 50, 70)
        }
    }

    @DisplayName("test calculator with method source")
    @ParameterizedTest(name = "{displayName} with data {0}")
    @MethodSource(value = ["parameterSource"]) // parameter dengan method static parameterSource()
    fun testWithMethodSource(value: Int) {
        val result = calculator.add(value, value)

        assertEquals(result, value + value)
        println(result)
    }

    @DisplayName("test calculator with parameter")
    @ParameterizedTest(name = "{displayName} with data {0}")
    @ValueSource(ints = [12, 30, 30, 89]) // parameter dengan object `int, string, float, double dll`
    fun testWithParameter(value: Int) {
        val result = calculator.add(value, value)

        assertEquals(result, value + value)
        println(result)
    }

    @DisplayName("Test calculator random")
    @RepeatedTest( // untuk mengulang testRandomRepeated() sebanyak 10 kali
        value = 10,
        name = "{displayName} ke {currentRepetition} dari {totalRepetitions}"
    )
    fun testRandomRepeated(repetionInfo: RepetitionInfo, testInfo: TestInfo) { // injection resolver RepetitionInfo dan TestInfo
        println("${testInfo.displayName} | repeated ke ${repetionInfo.currentRepetition} dari ${repetionInfo.totalRepetitions}")

        val first = random.nextInt(1000)
        val second = random.nextInt(200)

        val result = calculator.add(first, second)

        assertEquals(first + second, result)
    }

    @Test
    fun testRandom() {
        val first = random.nextInt(1000)
        val second = random.nextInt(200)

        val result = calculator.add(first, second)
        println(result)
        println(first + second)

        assertEquals(first + second, result)
    }

}