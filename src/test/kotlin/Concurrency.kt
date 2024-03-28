package cotlin.coroutin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*

/*
    Pendahuluan

    * parallel menjalankan beberapa pekerjaan secara bersamaan
    * concurrency menjalankan beberapa pekerjaan secara
      bergantian
    * parallel biasanya membutuhkan banyak Thread, sedangkan
      concurrency hanya membutuhkan sedikit Thread
*/

/*
    Coroutine

    * coroutine sebenarnya dieksekusi di dalam thread. namun dengan
      coroutine sebuah thread bisa memiliki kemampuan untuk
      menjalankan beberapa coroutine secara bergantian (concurrent)
    * kelebihan coroutine dari thread adalah murah dan cepat.
      membuat ribuan bahkan jutaan coroutine secara cepat dan murah
      tanpa takut kelebihan memory


    Beberapa hal yang terdapat di coroutine:

    1. Suspend Function
       * sebuah function dimana kita bisa menangguhkan waktu
         eksekusinya tanpa harus mem-blok thread yang sedang
         menjalankan function tersebut
       * syarat menjalankan suspend function di kotlin adalah,
         harus dipanggil dari suspend function lainnya.
       * saat membuat/memulai coroutine, secara otomatis bisa
         memanggil suspend function




*/

class Concurrency {

    private suspend fun helloWord() {
        println("Hello World : ${Date()} : ${Thread.currentThread().name}")
        delay(2000)
        println("Hello World : ${Date()} : ${Thread.currentThread().name}")
    }

    @Test
    fun testSuspendFunction() {
        runBlocking {
            /*
                runBlocking untuk memulai/membuat coroutine,
                sebaiknya runBlocking hanya digunakan untuk
                kebutuhan pengujian, dalam aplikasi nyata
                kita menggunakan pendekatan non-blocking
                seperti `launch` dan `async`
            */
            helloWord()
        }
    }



    /*
       Membuat coroutine:

       * coroutine tidak bisa berjalan sendiri, dia perlu
         berjalan di dalam sebuah scope
       * salah satu scope yang bisa kita gunakan adalah
         GlobalScope
       * untuk membuat coroutine, kita bisa menggunakan method
         launch()
    */
    suspend fun hello() {
        delay(1000)
        println("hello world")
    }

    // contoh membuat coroutine
    @Test
    fun testCoroutine() {
        GlobalScope.launch {
            hello()
        }
        println("MENUNGGU")
        runBlocking {
            delay(2000)
        }
        println("SELESAI")
    }

}