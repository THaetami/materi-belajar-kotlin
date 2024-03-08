import kotlinx.coroutines.*

/*
    Job adalah sebuah hasil dari perintah asynchronous yang
    dijalankan. Job mempresentasikan coroutine yang sebenarnya.
    Job memiliki 3 properti, yakni:

    1. isActive, sebuah properti yang menunjukkan ketika sebuah job
       sedang aktif. Sebuah job bersatus aktif ketika ia sedang
       berjalan ataupun job yang sedang ditangguhkan
       (suspended job).
    2. isCompleted, properti yang menunjukkan ketika sebuah job
       telah selesai alias sudah tidak berjalan lagi. Ini
       berlaku untuk job yang berakhir secara normal, dibatalkan,
       ataupun suatu pengecualian.
    3. isCancelled, properti yang menunjukkan ketika sebuah job
       telah dibatalkan. Job yang dibatalkan juga dianggap
       sebagai Completed job.


    note:
    Cancelling ialah kondisi ketika fungsi cancel() dipanggil
    pada job yang sedang aktif dan memerlukan waktu untuk
    pembatalan tersebut selesai.
*/

//// job dengan fungsi Job()
//fun main() = runBlocking {
//    val job = Job()
//}

// membuat job dengan fungsi launch()
fun main() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        delay(1000L)
        println("Start new job")
    }

    // job.join()
    job.start()

    /*
        Perbedaan join() dan start() adalah, start() akan
        memulai job tanpa harus menunggu job tersebut selesai,
        sedangkan join() akan menunda eksekusi sampai job
        selesai.
    */
    println("Other task")
}
