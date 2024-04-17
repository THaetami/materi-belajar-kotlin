package belajar.unit.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.concurrent.TimeUnit

/*
    Paralel test

    * secara default Junit akan manjalankan test
      secara sequintial (satu per satu)
    * berikut langkah2 untuk mengaktifkan test
      secara paralel

      1. membuat file junit-platform.properties di resource,
         kemudian tambahakan value:
            junit.jupiter.execution.parallel.enabled=true
      2. tambahkan tag `Execution` dengan execution mode = concurrent
*/
@Execution(ExecutionMode.CONCURRENT)
class ParalelTest {

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testSlow1() {
        Thread.sleep(3000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testSlow2() {
        Thread.sleep(3000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun testSlow3() {
        Thread.sleep(3000)
    }

}