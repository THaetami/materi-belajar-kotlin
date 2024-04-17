package belajar.unit.test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.TestInfo
import kotlin.test.Test

@DisplayName("Test with TestInfo")
class InformationTest {

    @Test
    @Tags(value = [
        Tag("contoh1"),
        Tag("contoh2")
    ])
    @DisplayName("sample test")
    fun sampleTest(testInfo: TestInfo) { // dengan interface `TestInfo` kita bisa memberikan sekaligus melihat info test yang sedang dijalankan
        println(testInfo.displayName)
        println(testInfo.testClass)
        println(testInfo.tags)
        println(testInfo.testMethod)
    }

}