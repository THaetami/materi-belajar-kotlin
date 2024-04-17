package belajar.unit.test

import org.junit.jupiter.api.Nested
import kotlin.test.Test

class NestedTest {

    @Test
    fun test1() {

    }

    @Nested // dengan `@Nested' maka kita bisa membuat inner class bisa digunakan
    inner class MyNestedTest {

        @Test
        fun test1() {

        }

    }

}