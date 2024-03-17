import java.lang.StringBuilder

/*
    SCOPE FUNCTION

    * function yang digunakan untuk mengatur dan memanipulasi
      object dalam block lambda. kotlin menyediakan 5 scope
      function, yaitu `let`, `run`, `with` `apply`, dan `also`.
    * terdapat 2 cara mengakses konteks sebuah object dari dalam
      lambda yang menjadi bagian dari scope function, yaitu:
      diakses sebagai lambda receiver (this) dan
      lambda argument (it)
*/

class Car(var brand: String, var model: String, var year: Int) {
    fun displayInfo() {
        println("Car Information: $brand $model ($year)")
    }
}

class Rakyat(var name: String, var age: Int)

fun main() {

    /*
        Lambda receiver (this)

        * `run`, `with` dan `apply` adalah function scope yang
          menggunakan lambda receiver, dan ketika ingin
          mengakses konteks dari sebuah object. Keyword `this`
          bisa dihilangkan/tidak dituliskan.
        * cara ini memiliki kekurangan, yaitu kita tidak dapat
          membedakan object receiver dengan object yang berada
          dari luar lingkup function tersebut
        * lambda receiver lebih ditujakan untuk operasi object
          itu sendiri, seperti memanggil function dan
          inisialisasi nilai dari anggota yang menjadi bagian
          dari object tersebut
    */

    val message = "Hello Kotlin!"
    val result = with(message) {
        println("value is $this")
        println("with length ${this.length}")
    }

    // Penggunaan run sebagai function scope dengan lambda receiver
    val carRun = Car("Toyota", "Camry", 2022).run {
        brand = "Honda"
        model = "Accord"
        year = 2023
        displayInfo() // Memanggil function dari objek receiver
        this // Mengembalikan objek yang telah diubah
    }

    // Penggunaan with sebagai function scope dengan lambda receiver
    val carWith = with(Car("Ford", "Mustang", 2021)) {
        brand = "Chevrolet"
        model = "Camaro"
        year = 2024
        displayInfo() // Memanggil function dari objek receiver
        this // Mengembalikan objek yang telah diubah
    }

    // Penggunaan apply sebagai function scope dengan lambda receiver
    val carApply = Car("Tesla", "Model 3", 2023).apply {
        brand = "Nissan"
        model = "Altima"
        year = 2025
        displayInfo() // Memanggil function dari objek receiver
    }

    // Kita tidak dapat membedakan objek receiver dengan objek luar lingkup function
    // karena kita tidak menggunakan keyword 'this'

    // Mencetak informasi setelah operasi
    println("Car after run: ${carRun.brand}")
    println("Car after with: ${carWith.model}")
    println("Car after apply: ${carApply.displayInfo()}")


    /*
        Lambda argument (it)

        * function scope `let` dan `also`, menggunakan lambda
          argument untuk mengakses konteks dari sebuah object.
        * nilai argument dapat kita gunakan untuk diproduksi
          atau diinisialisasikan untuk variable lain
        * secara default nama argument tersebut adalah `it`,
          tapi kita dapat mengubahnya.
    */

    // Penggunaan let sebagai function scope dengan lambda argument 'it'
    val carLet = Car("Toyota", "Camry", 2022).let { it ->
        // Menggunakan 'it' untuk mengakses konteks dari objek receiver
        it.year += 1
        it.displayInfo() // Memanggil function dari objek receiver

        // Menggunakan nilai 'it' untuk inisialisasi variabel lain
        val updatedYear = it.year
        updatedYear
    }

    // Penggunaan also sebagai function scope dengan lambda argument 'it'
    val carAlso = Car("Ford", "Mustang", 2021).also { it ->
        // Menggunakan 'it' untuk mengakses konteks dari objek receiver
        it.year += 2
        it.displayInfo() // Memanggil function dari objek receiver
    }

    // Kita dapat mengubah nama argument dari 'it' menjadi sesuatu yang lain
    val carCustomName = Car("Chevrolet", "Camaro", 2023).let { customName ->
        // Menggunakan 'customName' sebagai nama argument yang baru
        customName.year += 3
        customName.displayInfo() // Memanggil function dari objek receiver
        customName.year
    }

    // Mencetak informasi setelah operasi
    println("Car after let: Year $carLet")
    println("Car after also")
    println("Car after let with custom name: Year $carCustomName")

    // contoh lain
    val text = "Heloo"
    text.let {
        val message = "$it lambda argument"
        println(message)
    }


    /*
        PERBEDAAN KELIMA SCOPE FUNCTION
    */
    /*
        apply | lambda receiver (this)

        Menginisialisasi objek dan mengatur propertinya,
        kemudian mengembalikan objek tersebut
    */
    val personApply = Rakyat("John", 25).apply {
        // println("apply: Initializing $name, age $age")
        age += 5
        name = "Updated Haetami"
    }
    println(personApply.name)


    /*
        also | lambda argument (it)

        * melakukan tindakan tambahan pada objek dan
          mengembalikan objek tersebut
        * function also sebaiknya digunakan ketika kita ingin
          menggunakan konteks dari object sebagai argument
          tanpa harus mengubah nilainya
    */
    val personAlso = Rakyat("Jane", 30).also {
        println("also: Initializing ${it.name}, age ${it.age}")
    }
    println(personAlso)


    /*
        let | lambda argument (it)

        Melakukan operasi pada objek dan mengembalikan
        hasil operasi tersebut
    */
    val personLet = Rakyat("Bob", 22).let {
        println("let: Initializing ${it.name}, age ${it.age}")
        "Result: ${it.name} is ${it.age} years old" // return value
    }
    println("let result: $personLet")


        /*
            penggunaan function let akan banyak kita temukan
            pada object bertipe nullable

            degan function let dpt mengurangi operator safe call
            seperti berikut:

            val length = objectName?.length ?: 0 * 2
        */
        val objectNullable: String? = null
        val length = (objectNullable?.takeUnless { it == "null" }?.length ?: 0) + 2
        println("length: $length")
        objectNullable?.let {
            val length = it.length * 2
            val text = "text length $length"
            println(text)
        } ?: run {
                /*
                    elvis operator `?:` dgn function run,
                    digunakan untuk menjalankan logika kode
                    lain jika object bernilai null
                */
            val text = "message is null"
            println(text)
        }



    /*
        run | lambda receiver (this)

        Menjalankan blok kode pada objek dan
        mengembalikan hasil blok kode tersebut, alias sama seperti
        function `let`, yang membedakan run function tidak ada
        parameter pada lambdanya, sehingga kita tidak bisa
        mengakses reference objek menggunakan keyword it tapi
        dengan this
    */
    val personRun = Rakyat("Alice", 28).run {
        println("run: Initializing $name, age $age")
        "Result: $name is $age years old" // return value
    }
    println("run result: $personRun")


    /*
        with | lambda receiver (this)

        Menggunakan objek sebagai receiver untuk blok kode
        dan mengembalikan hasil blok kode tersebut
    */
    val personWithResult = with(Rakyat("Charlie", 35)) {
        println("with: Initializing $name, age $age")
        "Result: $name is $age years old" // return value
    }
    println("with result: $personWithResult")
}

