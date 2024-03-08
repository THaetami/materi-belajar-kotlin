import kotlinx.coroutines.*

/*
    * Coroutine dispatcher adalah komponen dalam kotlin
      coroutines yang bertanggung jawab untuk menentukan
      bagaimana dan dimana coroutine akan dijalankan.

    * Berikut jenis2 dispatcher yang dimiliki kotlin coroutine

      1. `Dispacthers.Default`, dispatcher dasar yang digunakan
         oleh semua standar builders seperti launch, async, dll
         jika tidak ada dispatcher lain yang ditentukan.

      2. `Dispatchers.IO`, Dispatcher ini cocok untuk operasi
         IO-bound seperti pembacaan atau penulisan ke sistem
         file atau panggilan jaringan. Menggunakan pool thread
         khusus untuk operasi IO.

      3. `Dispatchers.Main`, Dispatcher ini digunakan untuk
         menjalankan coroutine di thread utama, yang umumnya
         digunakan untuk pembaruan antarmuka pengguna (UI). Pada
         platform Android, ini dapat digunakan untuk memastikan
         bahwa operasi UI.

      4. `Dispatchers.Unconfined`, Dispatcher ini membuat
         coroutine berjalan tanpa batasan pada thread tertentu.
         coroutine akan mulai di thread yang memanggilnya, tetapi
         dapat pindah ke thread lain selama eksekusi.
*/


fun main() = runBlocking<Unit> {

    // contoh dispatcher unconfined
    launch(Dispatchers.Unconfined) {
        /*
            coroutine dimulai dari main thread, kemudian tertunda
            oleh fungsi delay, setelah itu coroutine dilanjutkan
            kembali pada thread DefaultExecutor
        */
        println("Starting in ${Thread.currentThread().name}")
        delay(1000L)
        println("Resuming in ${Thread.currentThread().name}")
    }.start()


    // singel thread context menggunakan newSingleThreadContext()
    val dispatcher = newSingleThreadContext("myThread")
    launch(dispatcher) {
        /*
            single thread context, dispather ini menjamin bahwa
            setiap saat coroutine akan dijalankan pada thread
            yang kita tentukan

            * pada contoh ini, walaupun sudah menjalankan fungsi
              delay, coroutine akan tetap berjalan pada myThread
        */
        println("Starting single thread context in ${Thread.currentThread().name}")
        delay(1000L)
        println("Resuming single thread context in ${Thread.currentThread().name}")
    }.start()



    /*
        * Thread Pull, menggunakan fungsi newFixedThreadPoolContetx()
        * Thread Pull, sebuah dispatcher yang memiliki kumpulan
          thread, ia akan memulai dan melanjutkan coroutine disalah
          satu thread yang tersedia pada kumpulan tersebut dan
          runtime akan mentukan thread mana yang tersedia, serta
          bagaimana proses distribusi bebannya.
    */
    val threadPullDispatcher = newFixedThreadPoolContext(3, "myPool")
    launch(threadPullDispatcher) {
        /*
            pada contoh ini, menetapkan thread `myPool` sebanyak
            3 thread, dan runtime otomatis menentukan pada thread
            mana coroutine akan dijalankan dan dilanjutkan
        */
        println("Starting thread full in ${Thread.currentThread().name}")
        delay(1000L)
        println("Resuming thread full in ${Thread.currentThread().name}")
    }.start()
}