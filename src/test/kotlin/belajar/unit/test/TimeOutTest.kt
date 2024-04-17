package belajar.unit.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

class TimeOutTest {

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS) // test berjalan lebih dari 5 detik maka digagalkan
    fun testSlow() {
        Thread.sleep(10000)
    }

}