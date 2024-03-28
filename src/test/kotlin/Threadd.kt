package cotlin.coroutin

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.concurrent.thread

/*
    Thread

    * untuk membuat thread, kita bias menggunakan interface
      Runnable sebagai kode program yang akan dieksekusi, lalu
      menggunakan method Thread.start() untuk menjalankan
      Thread tersebut.
    * Thread akan berjalan secara paralel, sehingga tidak
      akan ditunggu oleh Thread utama
    * kotlin memiliki helper function `thread()` jika kita
      ingin membuat thread lebih singkat dan mudah
*/

class Threadd {

    @Test
    fun testThreadName() {
        val threadName = Thread.currentThread().name
        println("Running in thread $threadName")
    }

    @Test
    fun testThreadRunnable() {
        // thread menggunakan interface Runnable
        val runnable = Runnable {
            println(Date())
            Thread.sleep(2000)
            println("Finish : ${Date()}")
        }

        val thread = Thread(runnable)
        thread.start()

        println("MENUNGGU SELESAI")
        Thread.sleep(3000)
        println("SELESAI")
    }

    @Test
    fun testThreadWithHelperFunction() {
        thread(start = true) {
            println(Date())
            Thread.sleep(2000)
            println("Finish : ${Date()}")
        }

        println("MENUNGGU SELESAI")
        Thread.sleep(3000)
        println("SELESAI")
    }

}

