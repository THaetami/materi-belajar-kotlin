package cotlin.coroutin

import org.junit.jupiter.api.Test
import java.util.*


/*

*/

class MultipleThread {

    @Test
    fun testMultipleThread() {

        val thread1 = Thread(Runnable {
            println(Date())
            Thread.sleep(2000)
            println("Finish thread 1 : ${Date()}")
        })

        val thread2 = Thread(Runnable {
            println(Date())
            Thread.sleep(2000)
            println("Finish thread 2 : ${Date()}")
        })


        thread1.start()
        thread2.start()

        println("MENUNGGU SELESAI")
        Thread.sleep(3000)
        println("SELESAI")
    }

}