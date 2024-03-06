import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*
    Coroutines ialah mekanisme di dalam bahasa kotlin yang
    digunakan untuk mengelola concurrency atau proses bersamaan.
    mereka memberikan cara ringkaas dan efisien untuk melakukan
    operasi asinkron dan concurrency tanpa harus mengandalkan
    callback atau melibatkan pembuatan banyak thread.

    * Coroutines hanyalah library lain yang disediakan oleh
    JetBrains, untuk itu agar bisa menggunakannya kita perlu
    menambahkan dependensi pada file build.gradle.kts
*/


suspend fun getCapital(): Int {
    delay(1000L)
    return 5000
}

suspend fun getIncome(): Int {
    delay(2000L)
    return 34000
}

fun main() = runBlocking{
    /*
        runBlocking, function ini dibuat untuk menjembatani
        blocking code menjadi kode yang dapat ditangguhkan.
        runBlocking akan memblokir sebuah thread yang sedang
        berjalan hingga eksekusi corountine selesai. Selain
        pada function main() kita juga dapat menerapkan
        pada unit test
    */
    launch {
        /*
            function launch digunakan untuk memulai sebuah
            coroutines yang tidak menghasilkan nilai kembali
            (return value). launch akan menghasilkan `job`
            yang bisa digunakan untuk membatalkan eksekusi
        */
        delay(1000L)
        println("Coroutines!")
    }
    println("Hello,")
    delay(1000L)

    /*
        perbedaan menggunakan builder `async` dan tidak
    */

    val timeOne = measureTimeMillis {
        /*
            tidak menggunakan bilder `async` alias dengan
            pendekatan sequintial atau sering disebut dengan
            synchronous, dimana tugas-tugas atau operasi-
            operasi dilakukan secara berurutan satu per satu
        */
        val capital = getCapital()
        val income = getIncome()
        println("Your profit is ${income - capital}")
    }

    // menggunakan builder `async`
    val timeTwo = measureTimeMillis {
        /*
            `async` fungsi ini digunakan untuk memulai sebuah
            coroutine yang akan mengembalikan deferred yang
            berisi hasil atau exception. Lalu untuk mengakses
            hasil tersebut, kita perlu menggunakan fungsi
            `await`
        */
        val capital = async { getCapital() }
        val income = async { getIncome() }
        println("Your profit is ${income.await() - capital.await()}")
    }

    /*
        hasilnya fungsi yang menggunakan `async` membutuhkan
        waktu yang lebih singkat dari kode yang tidak menggunakan
    */
    println("Completed in $timeOne ms vs $timeTwo ms")
}