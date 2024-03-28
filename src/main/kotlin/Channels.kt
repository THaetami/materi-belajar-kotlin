import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

/*
    Channel, salah satu konsep yang diperkenalkan kotlin coroutines
    untuk mengatasi komunikasi dan bertukar data antar coroutines.
    Channels menyediakan saluran komunikasi antara producer dan
    consumer dalam lingkup concurrency.

    Channel adalah nilai deferred yang menyediakan cara mudah untuk
    mentransfer nilai tunggal antar coroutine.
*/

fun main() = runBlocking {
    val channel = Channel<Int>(capacity = 3) // Membuat channel dengan kapasitas 3

    // Coroutine produsen
    launch {
        repeat(5) {
            println("Sending $it")
            channel.send(it) // menggunakan fungsi send() untuk mengirimkan hasil komputasi
        }
        // channel.close() // Menutup channel setelah selesai mengirim data
    }

    // Coroutine konsumen
    launch {
        while (true) {
            val element = channel.receive() // Menggunakan fungsi receive untuk membaca/menerima data
            println("Received $element")
        }

    }

    delay(1000) // Menunggu agar coroutines memiliki waktu untuk berjalan
}