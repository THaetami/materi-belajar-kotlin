/*
    Nested Function

    Function di dalam function tujuannya untuk memisahkan logika
    program dari function utama, selain itu agar kode lebih
    terstruktur dan mudah dibaca
*/

// function inside function
fun functionUtama(valueA: Int, valueB: Int, valueC: Int): Int {

    fun contohInnerFunction(value: Int) {
        // untuk memvalidasi setiap parameter (valueA, valueB dan valueC)
        if (value == 0) {
            throw IllegalArgumentException("value must be better than 0")
        }
    }

    contohInnerFunction(valueA)
    contohInnerFunction(valueB)
    contohInnerFunction(valueC)

    return valueA + valueB + valueC
}



/*
    Recursion / Recursive Function

    * sebuah mekanisme dimana sebuah function digunakan dari
      dalam function itu sendiri, sehingga function dapat
      berjalan beberapa kali.
    * setiap pemanggilan dapat mengembalikan nilai dan digunakan
      sebagai argument untuk pemanggilan function berikutnya
      serta mengembalikan nilai akhir berupa nilai kembalian
      dari setiap pemanggilan function tersebut
*/
fun contohRecursiveFunction(n: Int): Int {
    return if (n == 1) {
        n
    } else {
        n * contohRecursiveFunction(n - 1)
    }
}


tailrec fun factorial(n: Long, result: Long = 1): Long {
    /*
        * tail recursion, memastikan proses sebelumnya telah
          selesai sebelum pemanggilan function berikutnya
          dijalankan, sehingga bisa meminimalisir penumpukan
          frame yang dapat menyebabkan stack overflow karena
          keterbatasan memori stack yang dialokasikan untuk
          program
        * sebuah function yang ditandai modifier tailrec,
          maka function tersebut hanya boleh dipanggil untuk
          dijalankan terakhir dan tidak boleh digunakan
          dalam blok try-catch-finally
    */
    val newResult = n * result
    return if (n == 1L) {
        n
    } else {
        n * factorial(n - 1, newResult)
    }
}

fun main() {
    // penggunaan Nested Function (function inside function)
    println(functionUtama(40, 10, 20))

    // penggunaan recursive function
    println("faktorial dari 6 = ${contohRecursiveFunction(6)}")

    println("faktorial dari 6 = ${factorial(6)}")

}