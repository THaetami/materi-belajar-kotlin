/*
    Exception

    * error dalam kotlin direpresentasikan dengan Exception,
      dimana semua tipe data error pasti class turunan dari
      Throwable
    * membuat error dengan keyword `throw` diikuti object errornya
*/
// membuat exception
class ValidationException(message: String): Throwable(message)

fun sayHello(name: String) {
    if (name.isBlank()) {
        throw ValidationException("Name is blank")
    }
    println("Hello $name")
}

fun main() {
    // penggunaan sayHello() | membuat exception
    sayHello("") // error 'name is blank'


    /*
        try-catch
    */
    val someNullValue: String? = null
    lateinit var someMustNotNullValue: String

    try {
        someMustNotNullValue = someNullValue!! // `!!` (non-null assertion operator) mengasumsikan jika bernilai null maka error NullPointerException
        println(someMustNotNullValue)
    } catch (e: Exception) {
        someMustNotNullValue = "Nilai String Null"
        println(someMustNotNullValue)
    }


    /*
        try-catch-finally

        finally akan dieksekusi setelah program keluar dari blok
        try ataupun catch. Bahkan finally juga tereksekusi ketika
        terjadi exception yang tidak terduga. Exception tidak
        terduga terjadi ketika kita menggunakan
        NullPointerException pada catch namun exception yang
        terjadi adalah NumberFormatException
    */
    try {
        someMustNotNullValue = someNullValue!!
    } catch (e: Exception) {
        someMustNotNullValue = "Nilai String Null"
    } finally {
        println(someMustNotNullValue)
    }


    /*
        Multiple Catch

        pada catch kita dapat secara spesifik memilih tipe exception
        apa yang ingin ditangani. Multiple catch memungkinkan untuk
        penanganan exception dapat ditangani lebih dari satu tipe
        exception. Hal ini sangat berguna ketika kita ingin menangani
        setiap tipe exception dengan perlakuan yang berbeda.
    */
    val someStringValue: String? = null
    var someIntValue: Int = 0
    try {
        someIntValue = someStringValue!!.toInt()
    } catch (e: NullPointerException) {
        someIntValue = 0
    } catch (e: NumberFormatException) {
        someIntValue = -1
    } finally {
        when(someIntValue){
            0 -> println("Catch block NullPointerException terpanggil !")
            -1 -> println("Catch block NumberFormatException terpanggil !")
            else -> println(someIntValue)
        }
    }
}