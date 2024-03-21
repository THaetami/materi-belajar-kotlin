/*
    1. Extension properties dan Extension Function dalam kotlin
    memungkinkan anda menambahkan properti atau function ke kelas
    tanpa perlu mengubah definisi kelas itu sendiri.

    2. berguna ketika anda ingin menambahkan fungsionalitas atau
    properti tambahan pada class yang tidak anda miliki kontrol
    langsung atas kodenya, misalnya pada class yang berasal dari
    pustaka eksternal

*/

// Deklarasi kelas tanpa properti 'isAdult' dan function greetinng()
class Person(val name: String, val age: Int)

// Mendefinisikan extension property 'isAdult' untuk kelas Person
val Person.isAdult: Boolean
    get() = age >= 18

// mendefinisikan extension function 'greeting' untuk class Person
fun Person.greeting(): String{
    return "Hello, my name is $name"
}

// contoh extension function, menambahkan function firstAndLast() pada object String
fun String.firstAndLast(): Map<String, Char>{
    return mapOf(
        "first" to first(),
        "last" to last()
    )
}



/*
    * contoh lain extension function dari tipe data Object Interger,
    * tipe data pada kotlin object semua karna memiliki method
*/

// extension function
fun Int.plusThree(): Int {
    return this + 3
}

// extension properties
val Int.slice: Int
    get() = this / 2

val String.firstAndLast: Map<String, Char>
    get() = mapOf(
        "first" to first(), "last" to last()
    )


/*
    * infix function = fitur kotlin yg memungkinkan kita untuk
    memanggil function dgn sintaksis infix, yaitu tanpa
    menggunakan tanda kurung atau titik sebagai pemisah antara
    object dan function

    * fitur ini digunakan untuk membuat pemanggilan function
    terlihat lebih mirip operasi antar 2 object

    * syarat menggunakan infix function
        1. Infix function harus merupakan sebuah member function atau extension function.
        2. Harus memiliki satu parameter saja.
        3. Parameter tidak boleh berupa generic dan tidak memiliki nilai default.
*/

// contoh infix function
infix fun Int.sum(value: Int): Int {
    return this + value
}

fun main() {
    // menggunakan extension properties dan function
    val person1 = Person("John", 25)
    val person2 = Person("Alice", 15)
    println("${person1.name} is an adult: ${person1.isAdult}") // Output: John is an adult: true
    println("${person2.name} is an adult: ${person2.isAdult}") // Output: Alice is an adult: false
    println(person1.greeting())

    val hasilExtentionFun = "Bahroel".firstAndLast()
    println(hasilExtentionFun["last"])



    // contoh penggunaan lain | extension function
    println(10.plusThree())
    println(10.slice)
    val hasilExtensionProp = "Tatang haetami".firstAndLast
    println(hasilExtensionProp["first"])




    // contoh penggunakan infix function lain
    val result = 5 sum 3
    println(result)

}
