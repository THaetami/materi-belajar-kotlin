/*
    Generics yaitu sebuh konsep yang memungkinkan suatu class atau
    interface menjadi tipe parameter yang dapat digunakan untuk
    berbagai macam tipe data.

    manfaat generic:
    * pengecekan tipe data ketika proses kompilasi
    * sehingga tidak perlu melakukan pengecekan atau konversi tipe
      data secara manual
*/
// contoh class generic
data class Data<T>(val data: T)

// contoh interface generic
interface ListContoh<T> { // <T> disebut type parameter
    operator fun get(index: Int) : T
}

// class yang menggunakan interface generic
class LongList : ListContoh<Long> {
    override fun get(index: Int): Long {
        return this[index]
    }
}

// class generic yang menggunakan interface generic
class ArrayList<T>(vararg items: T) : ListContoh<T> {
    private val element: MutableList<T> = mutableListOf(*items)
    override fun get(index: Int): T {
        return element[index]
    }
}



/*
    Multiple Parameter Type

    parameter type di Generic type boleh lebih dari satu. Namun, harus
    menggunakan nama type yang berbeda, ini sangat berguna ketika kita
    ingin membuat generic parameter type yang banyak
*/
class MyData<T, U>(val firstData: T, val secondData: U) {
    fun printData() {
        return println("Data is $firstData $secondData")
    }

    fun getSecond(): U {
        return secondData
    }

    fun getData(): T {
        return firstData
    }
}



// contoh generic function
class Function(val name: String) {
    fun <T> sayHello(param: T) {
        println("Hello $param, my name is $name")
    }
}


/*
    generic extension function, type param ditulis sebelum
    nama function
 */
// contoh pertama | generic extension function
class DataExtension<T>(val data: T)
fun DataExtension<String>.print() { // extension function `print()`
    val data: String = this.data
    println(data)
}

// contoh kedua | generic extension function
public fun <T> List<T>.slice(indices: Iterable<Int>): List<T> = indices.mapNotNull(this::getOrNull)

//public fun <T> List<T>.slice(indices: Iterable<Int>): List<T>{
//    return indices.mapNotNull { this.getOrNull(it)}
//}



/*
    Constraint Type Parameter

    cara untuk membatasi tipe data yang dapat digunakan sebagai
    argumen generik pada suatu class atau function, sehingga
    memungkinkan kita membuat kode yang lebih aman dan lebih
    spesifik.

    dengan cara menambahkan tanda titik dua (:) setelah tipe
    parameter, kemudian diikuti oleh tipe yang akan dijadikan
    batasan.
*/
// contoh pertama | Constraint Type Parameter
class ListNumber<T : Number> : ListContoh<T>{
    override fun get(index: Int): T {
        return this[index]
    }
}

// untuk contoh constraint type parameter
open class Wargi
class RukunTetangga: Wargi()
class Lurah: Wargi()

// contoh kedua | Constraint Type Parameter
class Kelurahan<T: Wargi>(val wargi: T)

// contoh ketiga | Constraint Type Parameter dengan keyword `where` agar bisa menggunakan lebih dari satu tipe data
interface CanSayHello {
    fun sayHello(name: String)
}

class Camat: Wargi(), CanSayHello {
    override fun sayHello(name: String) {
        println("hell $name, i'm camat")
    }
}
class Kecamatan<T>(val wargi: T) where T : Wargi, T : CanSayHello


// contoh keempat | Constraint Type Parameter dengan keyword `where`
fun <T> List<T>.sumNumber(): T where T : Number, T : Comparable<T> {
    require(this.isNotEmpty()) { "List should not be empty" }

    return when (first()) {
        is Int -> this.sumOf { it as Int } as T
        is Long -> this.sumOf { it as Long } as T
        is Double -> this.sumOf { it as Double } as T
        else -> throw UnsupportedOperationException("Unsupported number type")
    }
}



/*
    Variance

    * variance adalah konsep yang menggambarkan bagaimana sebuah
      tipe yang memiliki subtipe yang sama dan tipe argumennt
      yang berbeda saling berkaitan satu sama lain.
    * variance dibutuhkan ketika kita ingin membuat class atau
      function generic dengan batasan yang tidak akan mengganggu
      dalam penggunaannya.
*/

// untuk contoh variant
abstract class Vehicle(wheel: Int)
class ElectricCar(val speed: Int) : Vehicle(4)
data class MotorCycle(val speed: Int) : Vehicle(2)



/*
    Invariant

    * secara default, saat membuat generic type, sifat parameter
      tersebut adalah invariant
    * invariant artinya tidak boleh disubtitusi dengan subtype
      (child) atau supertype (parent), contohnya ketika kita
      membuat object Contoh<String> (child), maka tidak sama dengan
      Contoh<Any> (parent), begitupun sebaliknya
*/
class Invariant<T>(val data: T)


/*
    Covariant

    * covariant artinya kita bisa melakukan subtitusi dari subtype
      (child) ke supertype (parent), contohnya saat kita membuat
      object (child) Contoh<String>, maka bisa disubtitusi menjadi
      (object parent) Contoh<Any>
    * hanya class generic yang menggunakan generic parameter
      type sebagai return type function
    * untuk membuat covariant, kita perlu menggunakan keyword `out`
*/
class Covariant<out T>(val data: T) {
    fun data(): T {
        /*
            saat membuat class covariant maka kita tidak boleh
            membuat function generic yang menggunakan tipe
            parameter generik sebagai tipe input, karna dapat
            menyebabkan masalah keamanan tipe.
            Kita hanya bisa membuat function yg mengeluarkan output
            alias return value
        */
        return data
    }

    // tidak boleh membuat function dengan input/param generic type
    // fun setData(param: T) {
    //    data = param
    // }
}



/*
    Contravariant

    * contravariant artinya kita bisa melakukan subtitusi dari
      supertype (parent) ke subtype (child), contohnya ketika
      kita membuat object Contoh<Any> (parent), maka bisa
      disubtitusi menjadi Contoh<String> (child)
    * hanya class generic yang menggunakan generic parameter type
      sebagai parameter function
    * untuk membuat contracovariant, kita perlu menggunakan
      keyword `in`
*/
class Contravariant <in T> {

    // tidak boleh membuat function dengan return generic type
    // fun getData(): T {
    //    return data
    // }

     fun sayHello(name: T) {
         return println("hello $name")
     }
}



/*
    Type Projection

    * type projection yaitu menambahkan informasi covariant atau
      contravariant di parameter function, ini memaksa isi function
      untuk melakukan pengecekan
    * jika covariant, kita tidak boleh mengubah data generic object
    * jika contravariant, kita tidak boleh mengambil data generic
      object
*/
class Container<T>(var data: T) // invariant
fun copyContainer(from: Container<out Any>, to: Container<Any>) {
    /*
        dengan adanya `out` maka from adalah covariant sehingga
        tidak bisa mengubah valuenya (variable data)
     */
    to.data = from.data // mensubtitut dari child ke parent `Any`
}



/*
    Star Projection

    sebuah teknik yang digunakan dalam kotlin untuk mengabaikan tipe
    parameter generic pada saat penggunaan tertentu. Ini berguna
    ketika kita ingin menggunakan struktur data generik tanpa harus
    memperhatikan tipe sebenarnya dari tipe parameter generik tersebut

    * star projection bisa dibuat dengan mengganti generic parameter
      type dengan karakter asterik `*`
    * contohnya saat kita hanya ingin mengetahui jumlah/length suatu
      collection tanpa peduli jenis tipe data dalam collection
      tersebut
*/
fun displayLength(array: Array<*>) {
    println("Length Array is ${array.size}")
}



/*
    Comparable interface

    * sebuah interface yang digunakan untuk memberikan kemampuan
      perbandingan object. Ketika sebuah class mengimplement
      interface `Comparable` itu berarti object dari class
      tersebut dapat dibandingkan dengan object lain dari
      tipe yang sama
    * interface `Comparable` memiliki satu method, yaitu
      `compareTo`, yang wajib diimplementkan oleh class
      yang menggunakannya
*/
class ComparableInterface(val name: String, val quantity: Int): Comparable<ComparableInterface> {
    override fun compareTo(other: ComparableInterface): Int { //mengimplement method dari interface Comparable
       // return this.quantity - other.quantity
        return quantity.compareTo(other.quantity)
    }
}


fun main() {
    // penggunaan class generic
    val dataString = Data<String>("tatang")
    val valueString = dataString.data

    val dataInt = Data<Int>(12)
    val valueInt = dataInt.data


    // penggunaan class generic yang menggunakan interface generic
    val longArrayList = ArrayList<Long>(10L, 20L, 30L)
    val firstLong = longArrayList[0]
    println(firstLong)

    val stringArrayList = ArrayList<String>("tatang", "hae", "tami")
    val lastString = stringArrayList[2]
    println(lastString)


    // penggunaan multiple parameter type
    val myData = MyData<String, Int>("tatang", 10)
    println(myData.firstData)
    println(myData.secondData)
    println(myData.printData())


    // menggunakan generic function, tanpa type param juga bisa -> sayHello(12)
    val function = Function("joko")
    function.sayHello<String>("tatang") // function sayHello() dengan String
    function.sayHello<Int>(12) // function sayHello() dengan Int


    // contoh pertama | generic extension function
    val dataExtensionString = DataExtension("data")
    val dataExtensionInt = DataExtension(1)
    // dataExtensionInt.print() // error karna function extension `print()` memiliki type parameter string
    dataExtensionString.print()

    // contoh kedua | generic extension function
    val numbers = (1..100).toList()
    println(numbers.slice(1..10))


    // contoh pertama | Constraint Type Parameter
    val number = ListNumber<Long>()
    val number2 = ListNumber<Int>()
    // val number3 = ListNumber<String>() // akan error karna menggunakan tipe parameter selain number

    // contoh kedua | Contraint Type Parameter
    val penduduk1 = Kelurahan(Wargi())
    val penduduk2 = Kelurahan(RukunTetangga())
    val penduduk3 = Kelurahan(Lurah())
    // val pendukuk4 = Kelurahan("tami") // akan error karna class Kelurahan dibatasi oleh parameter type dari class `Wargi` dan class turunannya

    // contoh ketiga | Constraint Type Parameter dengan keyword where
    val penduduk5 = Kecamatan(Camat())
    // val penduduk6 = Kecamatan(Wargi()) // akan error karna class Kecamatan dibatasi dengan membutuhkan CanSayhello

    // contoh keempat | Constraint Type Parameter dengan keyword where
    val number4 = listOf(1, 2, 3, 4, 5) // berhasil
    val names = listOf("tatang", "haetami") // error
    println(number4.sumNumber())
    // names.sumNumber() // error : inferred type String is not a subtype of Number


    // contoh variant
    val car = ElectricCar(200)
    val motorCycle = MotorCycle(100)
    /*
        variable `vehicle` dapat diisi dengan instance dari
        `ElectricCar` atau `MotorCycle` karena keduaanya adalah
        subtypes dari `Vehicle`. Dengan kata lain, kita bisa
        mengganti nilai variable dari instance `car` ke
        instance `motorCycle`

        note:
        ini adalah contoh variant secara tidak langsung
    */
    var vehicle: Vehicle = car
    vehicle = motorCycle // instance car diganti dgn motorCycle
    println(vehicle.speed)



    // contoh invariant
    val invariantString:  Invariant<String> = Invariant("tatang")
    // val invariantAny: Invariant<Any> = invariantString // error, tidak bisa melakukan seperti `polymorphisme` | tidak bisa mensubtitut


    // penggunaan covariant
    val covariantString = Covariant<String>("tatang")
    val covariantAny: Covariant<Any> = covariantString // mensubtitut dari child Covariant<String> ke parent Covariant<Any>
    covariantAny.data()


    // penggunaan contravariant
    val contravarianAny: Contravariant<Any> = Contravariant()
    val contravariantString: Contravariant<String> = contravarianAny // mensubtitut dari parent Covariant<Any> ke child Covariant<String>
    contravariantString.sayHello("tatang")


    // penggunaan type projection
    val data1 = Container(200)
    val data2: Container<Any> = Container("tatang")
    copyContainer(data1, data2) // argument `data1 = convariant` | `data2 = invariant`
    println("data1 = ${data1.data}, data2 = ${data2.data}")


    // penggunaan star projection
    val arrayInt = arrayOf(1, 2, 3, 4, 5, 6, 7)
    val arrayString = arrayOf("tatang", "hae", "tami")
    displayLength(arrayInt) // function tidak memedulikan tipe data
    displayLength(arrayString) // function tidak memedulikan tipe data


    // penggunaan comparable interface
    val com1 = ComparableInterface("tami", 23)
    val com2 = ComparableInterface("bayu", 20)
    println(com1 > com2)
    println(com1 < com2)
    println(com1 >= com2)
    println(com1 <= com2)


}
