/*
    Function Type

    * dengan menggunakan typealias, kita dapat memberikan nama
      alternatif atau alias pada tipe data function yang
       memiliki karakteristik serupa, khususnya terkait tipe
      parameter dan tipe kembalian.
    * anda dapat memberikan nama yang deskriptif pada
      function type menggunakan 'typealias'. kemudian, anda
      bisa menggunakan nama tersebut sebagai "tipe data"
      saat mendeklarasikan fungsi lain,
*/
typealias Arithmatic = (Int, Int) -> Int
typealias Arimatika = ((Int, Int) -> Int)? // menandai sebagai nullable



/*
    Lambda

    * dengan lambda, kita tidak perlu mendeklarasikan tipe data
      untuk nilai kembaliannya/outputnya. karna akan ditentukan
      oleh komplier secara otomatis
    * tidak memiliki keyword fun dan visibilty modifier saat
      dideklarasikan
    * tidak diperlukan keyword 'return' karna komplier akan
      mengembalikan nilai/output dari dalam body.
    * lambda expression dpt digunakan sebagai argumen untuk
      sebuah param dan dpt disimpan dlm variable
    * parameter dari sebuah lambda berada di dalam kurung
      kurawal. untuk membedakannya dengan body, daftar
      parameter yang ada dipisahkan dengan tand ' -> '
*/
val contohLambda = { awal: Int, akhir: Int -> awal * akhir }



/*
    Higher-order Functin

    * sebuah function yang menggunakan function lainnya sebagai
      parameter, menjadikan tipe kembalian, ataupun ke duanya
*/
// dengan 1 parameter
var sum: (Int) -> Int = {value -> value + value}


inline fun printResult(value: Int, sum: (Int) -> Int) {
    /*
        inline adalah fitur dalam kotlin yang memungkinkan anda
        untuk menyertakan seluruh kode dari function atau lambda
        pada saat kompilasi, sehingga mempercepat waktu eksekusi
        program, penggunaan inline juga dpt menyebabkan file
        binary program menjadi lebih besar dan memperlammbat
        waktu kompilasi
    */
    val result = sum(value)
    println(result)
}

fun printName(value: String, name: (String) -> String) {
    println(name(value))
}



/*
    Lambda with receiver, adalah fitur kotlin yang memungkinkan
    kita mendifinisikan ekpresi lambda dengan object penerima
    eksplisit
*/
fun contohLambdaWithReceiver(action: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.action()
    return stringBuilder.toString()
}

// contoh lain lambda with receiver
data class Warga(var name: String = "", var age: Int = 0, var address: String = "")

fun buildWarga(block: Warga.() -> Unit): Warga {
    val warga = Warga()
    warga.block()
    return  warga
}

fun main() {

    // penggunaan Function Type
    val tambah: Arithmatic = { valueA, valueB -> valueA + valueB }
    val multiply: Arithmatic = { valueA, valueB -> valueA * valueB }

    println(tambah(20, 30))
    println(multiply(2, 3))


    // penggunaan lambda
    contohLambda(2, 4)

    /*
        perulangan lambda, forEach adalah extension function
        yang menerapkan lambda
    */
    val ranges = 1.rangeTo(10) step 3
    // ranges.forEach{ value -> // tdk menggunakan index
    ranges.forEachIndexed { index, value ->
        println("value $index is $value")
    }


    // penggunaan higher-order function dengan function sum sebagai argumen
    printResult(123, sum)

    // atau dengan melampirkan isi lambda secara langsung ketika functin printResult() dipanggil
     printResult(12) { value ->
        value + value
     }

    printName("Tatang") { value ->
        val first = "Haetami"
        "$first $value"
    }


    // penggunnaan lambda with receiver
    val message = contohLambdaWithReceiver {
        append("Hello")
        append("Tatang")
        append("Haetami")
    }

    val warga = buildWarga {
        name = "Tatang Hae"
        age = 20
        address = "banten"
    }
    println(message)
    println("Name: ${warga.name}, Age: ${warga.age}, Address: ${warga.address}")
}