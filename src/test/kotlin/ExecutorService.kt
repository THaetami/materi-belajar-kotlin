package cotlin.coroutin

import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors


/*
    Executor service

    * adalah fitur di JVM yang bisa digunakan untuk manajemen
      Thread
    * lebih direkomendasikan menggunakan ExecutorService dari
      pada Thread secara manual
    * ExecutorService adalah sebuah interface, untuk membuat
      objectnya, kita bisa menggunakan class Executors,

    Berikut beberapa jenis helper function ExecutorService:
    1. newSingleThreadExecutor => membuat ExecutorService
       dengan 1 Thread
    2. newFixedThreadPool(int) => membuat ExecutorService
       dengan n Thread
    3. newChachedThreadPool() => membuat ExecutorService
       dengan Thread akan meningkat sesuai kebutuhan


    Threadpool

    * implementasi ExecutorService yang terdapat di class Executor
      adalah class ThreadPoolExecutor
    * di dalam ThreadPool terdapat data queue (antrian) tempat
      menyimpan semua proses sebelum di eksekusi oleh Thread
      yang tersedia di ThreadPool, sehingga kita bisa
      mengeksekusi sebanyak-banyaknya Runnable.
    * Runnable yang tidak dieksekusi akan menunggu di queue sampai
      Thread sudah selesai mengeksekusi Runnable yang lain
*/

class ExecutorService {

    @Test
    fun testSingleThreadPool() {
        // mengeksekusi satu per satu
        val executorService = Executors.newSingleThreadExecutor()
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("selesai memasukan runnable $it")
        }

        println("MENUNGGU")
        Thread.sleep(11000)
        println("SELESAI")
    }

    @Test
    fun testFixedThreadPool() {
        // mengeksekusi tiap nThread, dlm contoh ini 10 thread dieksekusi tiap 3 thread
        val executorService = Executors.newFixedThreadPool(3)
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("selesai memasukan runnable $it")
        }

        println("MENUNGGU")
        Thread.sleep(11000)
        println("SELESAI")
    }

    @Test
    fun testCachedThreadPool() {
        // mengeksekusi semua thread yang ada secara sekaligus, dlm contoh ini 10 thread
        val executorService = Executors.newCachedThreadPool()
        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("selesai memasukan runnable $it")
        }

        println("MENUNGGU")
        Thread.sleep(11000)
        println("SELESAI")
    }

}