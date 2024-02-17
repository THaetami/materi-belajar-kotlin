import  kotlin.random.Random

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

// property accessor
// membuat function setter dan getter manual
class ContohSeterGetter {
    var name: String = "Dicoding miaw"
        get() {
            println("Fungsi getter terpanggil")
            return  field
        }

        set(value) {
            println("Fungsi setter terpanggil")
            field = value
        }
}

// lateinit keyword yang ditambahkan sebelum variable untuk membuat proses inisialisasi bisa ditunda
lateinit var cont: String

/* lazy keyword digunakan untuk menunda inisialisasi suatu
    variable, akan diinisialisasika ketika benar2 digunakan
    sehingga tidak akan memakan memori diawal ketika program
    berjalan
* */
val contohLazyKey: String by lazy {
    "Tatang Haetami"
}


fun main(args: Array<String>) {
    var name = "Tatang"
    val ahad = 1
    val isnani = 2
    println(contohLazyKey)

    println("Hello my name is $name = ${ahad + isnani}")
    println(name)
    println(if (ahad > isnani) "Always true" else "Always false")

    val text: String? = null
    println(text?.length)

    val str: String? = null
    val textLength = str?.length ?: 7
    println(textLength)

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

    // label keyword "@" untuk menamai suatu loop
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

    val cat = Animal();
    println("Nama: ${cat.name}, Berat: ${cat.weight}, Umur: ${cat.age}, Mamalia: ${cat.isMamal}")
    cat.eat()
    cat.sleep()

    // lateinit keyword yang ditambahkan sebelum variable untuk membuat proses inisialisasi bisa ditunda
    // isInitialized untuk memeriksa apakah variable telah diinisialisasikan

    if (::cont.isInitialized) // variable cont terdapat diatas
        println(cont.length)
    else
        println("not initialized")
    cont  = "Tatang"
    println(cont)











}












