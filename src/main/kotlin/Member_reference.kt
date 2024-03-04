

var message = "Tatang Haetami"

class MyClass {
    var classMessage = "Hello from MyClass"
}

fun main() {
    fun count(valueA: Int, valueB: Int): Int {
        return valueA + valueB
    }
    val sum: (Int, Int) -> Int = ::count
    val hasil = sum(34, 40)
    println(hasil)


    /*
        Function References
    */
    val numbers = 1.rangeTo(10)

    fun isEvenNumbers(number: Int) = number % 2 == 0 // function u/ memeriksa bilangan genap
    val evenNumber = numbers.filter(::isEvenNumbers) // `::` mereferensikan function isEvenNumbers
    println(evenNumber)

    // mereferensikan sebuah extension function
    fun Int.isOddNumbers() = this % 2 != 0
    val oddNumberWithExtensionFunction = numbers.filter(Int::isOddNumbers)
    println(oddNumberWithExtensionFunction)


    /*
        Property References

        selain digunakan untuk mereferensikan sebuah function,
        operator `::` dpt digunakan untuk mereferensikan sebuah
        properti, sehingga kita bisa mengakses apa yang menjadi
        bagian dari properti tersebut seperti nama, fungsi
        setter getter dll
    */

    // contoh menggunakan variable top level
    println(::message.name) // mengambil nama properti | message
    println(::message.get()) // mengambil/membaca nilai dari properti message | Tatang Haetami
    ::message.set("THaetami") // mengganti nilai properti message
    println(::message.get()) // Thaetami


    // contoh menggunakan properi dlm class
    val myObject = MyClass()

    println(myObject::classMessage.name)
    println(myObject::classMessage.get())
    myObject::classMessage.set("Greetings from MyClass")
    println(myObject::classMessage.get())


        /*
            note:

            jika properti menggunakan keyword `val` maka
            fungsi setter tidak akan bisa digunakan
        */


}