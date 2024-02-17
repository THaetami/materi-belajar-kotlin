/*
    1. abstract adalah konsep dimana anda menyembunyikan detail
    tertentu dari implementasi suatu object dan hanya
    menunjukkan fungsionalitas yang relevan atau penting bagi
    pengguna object tersebut.

    2. interface merupakan suatu konsep sifat umum yang nantinya
    digunakna oleh suatu class agar dapat memiliki sifat tsb.

    3. tujuan interface ini hanya untuk diimplementasikan oleh
    sebuah class, ketika diimplementasikan harus menggunakan
    keyword override
 */

// contoh abstract class
abstract class ContohAbstractClass(var name: String, var weight: Double, var age: Int, var isMammal: Boolean) {
    fun eat() {
        println("$name sedang makan")
    }

    fun sleep() {
        println("$name sedang tidur")
    }
}

// contoh interface
interface Ifly {
    fun fly()
    val numberOfWings: Int
}

// mengimplementasikan interface
class Bird(override  val numberOfWings: Int) : Ifly {
    override fun fly() {
        if (numberOfWings > 0) println("flying with $numberOfWings wings")
        else println("I flying without wings")
    }
}

// perbedaan abstract class dan interface
/*
    ABSTRACT CLASS
        1. Bisa berisi default value.
        2. Setip member, final secara default. supaya bisa
           dioverride, gunakan keyword abstract (wajib) dan
           open (opsional)
        3. 1 Class hanya bisa extend satu abstract class,
           ditandai dengan ()
        4. digunakan jika tidak tahu implementasi sebagian member

    INTERFACE
        1. Tidak bisa berisi default value
        2. setiap membernya, "abstract" secara default dan harus
           di-override
        3. 1 class bisa implement lebih dari satu interface
        4. digunakan jika tidak tahu implementasi keseluruhan
           member
 */
abstract class Hewan {
    //nilai default tidak diizinkan
    abstract val usia: Int //harus di-overridden
    //nilai default diizinkan
    open val sedangMakan = true //opsional untuk di-overridden
    val sedangBernapas = true //tidak dapat di-overridden
}

interface JBerenang {
    //nilai default tidak diizinkan
    val jumlahKaki: Int //harus di-overridden
    fun berenang() //harus di-overridden
}

interface IMinum {
}

/*
    mengextend abstract class Hewan() dan menimplement
    interface JBerenang, IMinum
 */

class Unta : Hewan(), JBerenang, IMinum {
    override val usia: Int = 7   // properti ini harus ada, coba hapus
    override val sedangMakan = true // properti ini opsional, coba hapus
    override val jumlahKaki = 2 // properti ini harus ada, coba hapus
    override fun berenang() { // metode ini harus ada, coba hapus
        println("Unta bisa berenang")
    }
}

fun main() {
    /*
        tidak bisa membuat object dari abstract class, harus
        diextend terlebih dahulu
    */
    // val animal = ContohAbstractClass("dicoding", 2, 3.4, true)
    val bird = Bird(10)
    println(bird.fly())

    // penggunaan contoh "beda abstract class dan interface
    val unta = Unta()
    unta.berenang()





}