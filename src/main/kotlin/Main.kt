import com.dicoding.oop.utils.sayHello
import  kotlin.random.Random

/*
    * lateinit keyword yang ditambahkan sebelum variable
      untuk membuat proses inisialisasi bisa ditunda
    * lateinit hanya bisa digunakan pada variable `var`
 */
lateinit var cont: String

/*
    lazy keyword digunakan untuk menunda inisialisasi suatu
    variable, akan diinisialisasika ketika benar2 digunakan
    sehingga tidak akan memakan memori diawal ketika program
    berjalan
*/
val contohLazyKey: String by lazy {
    "Tatang Haetami"
}


// Class
class Animal() {
    var name: String = "Kucing"
    var weight: Double = 3.2
    var age: Int = 2
    var isMamal: Boolean = true

    fun eat() {
        println("$name makan")
    }

    fun sleep() {
        println("$name tidur")
    }
}


/*
    Property accessor

    membuat function setter dan getter manual,
    merupakan langkah untuk menerapkan prinsip enkapsulasi,
    dengan getter dan setter kita dapat mengontrol akses
    ke properti private tersebut, Ini membantu menjaga
    keamanan dan integritas data, serta mencegah perubahan
    yang tidak diinginkan.
*/
class ContohSeterGetter {
    private var name: String = "Dicoding miaw"
        get() {
            println("Fungsi getter terpanggil")
            return  field
        }

        set(value) { // properti dengan keyword `val` tidak bisa membuat setter
            println("Fungsi setter terpanggil")
            if (value.isNotBlank()) {
                field = value
            }
        }
}

// mencegah null pointer exception
data class Friend(val name: String)
fun cegahNullException(friend: Friend) {

    /*
        dengan if
    */
    // if (friend != null) {
    //    println("Hello ${friend.name}")
    // }

    /*
        dengan `?`
    */
    // println("Hello ${friend?.name}")

    /*
        dengan elvis operator `?:`
    */
    // val name = friend?.name ?: "Friend"

    /*
        dengan non-null assertion operator `!!`
    */
    val notNullFriend = friend!!
    val name = notNullFriend.name
    println("hello $name")
}

fun main(args: Array<String>) {
    // menggunakan lazy keyword
    println(contohLazyKey)

    /*
        Lateinit

        * lateinit keyword yang ditambahkan sebelum variable
          untuk membuat proses inisialisasi bisa ditunda
        * isInitialized untuk memeriksa apakah variable telah
          diinisialisasikan
    */
    if (::cont.isInitialized) // variable cont terdapat diatas
        println(cont.length)
    else
        println("not initialized")
    cont  = "Tatang"
    println(cont)


    // penggunaan class
    val cat = Animal();
    println("Nama: ${cat.name}, Berat: ${cat.weight}, Umur: ${cat.age}, Mamalia: ${cat.isMamal}")
    cat.eat()
    cat.sleep()


    // string
    var name = "Tatang"
    val ahad = 1
    val isnani = 2
    println("Hello my name is $name = ${ahad + isnani}")
    println(name)
    println(if (ahad > isnani) "Always true" else "Always false")


    // penggunaan cegahNullException() | mencegah null pointer exception
    cegahNullException(Friend("tatang"))
    // cegahNullException(null) // error


    // function
    fun setUser(name: String, age: Int): String {
        return "Your name is $name, and you $age years old"
    }

    // function yang tidak memiliki output menggunakan Unit keyword
    fun printUser(name: String): Unit {
        println("Your name is $name")
    }

    val user = setUser("tami", 26)
    val printUser = printUser("Tatang")
    println(user)
    println(printUser)


    // when expression
    val value = 9
    val stringOfValue = when(value) {
        6 -> "value is 6"
        7 -> "value is 7"
        8 -> "value is 8"
        else -> "value cannot be reached"
    }
    println(stringOfValue)

    val angka = 90
    val jarak = 10..40
    when(angka) {
        in jarak -> println("value is in the range")
        !in jarak -> println("value is outside the range")
    }

    // menangkap subjek dari when expression di dalam sebuah variable
    fun getRegisterNumber() = Random.nextInt(100)
    val registerNumber = when(val regis = getRegisterNumber()){
        in 1..50 -> 50 * regis.toInt()
        in 51..100 -> 100 * regis.toInt()
        else -> regis
    }


    // while
    var counter = 2
    while (counter <= 7) {
        println("looping while ${counter++}")
    }


    // do while
    var conters = 8
    do {
        println("looping do while ${conters++}")
    } while (conters <= 7)


    // range
    var rangeInt = 1..10
    println(rangeInt.step) // jarak 2 nilai range

    // mengubah nilai step dari range
    val rangeInts = 1..10 step 2
    rangeInts.forEach{value ->
        print("$value ")
    }
    println(rangeInts.step)


    // looping for
    for ((index, value) in rangeInts.withIndex()) {
        println("value $value with index $index")
    }

    // continue and break keyword
    val listOfInt = listOf(1, 2, 3, null, 5, null, 7)
    for (i in listOfInt) {
        if (i == null) continue // break
        print(i)
    }


    // label, dengan keyword "@" untuk menamai suatu loop atau expression
    loop@ for (i in 1..10) {
        println("Outside loop")

        for (j in 1..10) {
            println("inside loop")
            if (j > 5) break@loop
        }
    }
    for (i in 1..3) {
        for (j in 1..i) {
            print(j)
        }
    }




    // soal list collection
    val total = listOf(1, 2, 3, 4, 5, 6)
    val result1 = total.take(5)
    println(result1) // [1, 2, 3, 4, 5]
    val result2 = result1.takeLast(4)
    println(result2) // [2, 3, 4, 5]
    val result3 = result2.drop(1)
    println(result3) // [3, 4, 5]
    val result4 = result3.dropLast(2)
    println(result4) // [3]

}












