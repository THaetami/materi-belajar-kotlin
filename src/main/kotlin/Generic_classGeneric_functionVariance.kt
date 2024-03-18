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
    generic pada extension function, type param ditulis sebelum
    nama function
 */
//public fun <T> List<T>.slice(indices: Iterable<Int>): List<T>{
//    return indices.mapNotNull { this.getOrNull(it)}
//}
public fun <T> List<T>.slice(indices: Iterable<Int>): List<T> = indices.mapNotNull(this::getOrNull)



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

// untuk contoh variant dan invariant
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
    Variance dalam konteks generic memiliki dua jenis utama:
    covariant dan contravariant.
*/

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

    // menggunakan generic extension function
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
    val covariantAny: Covariant<Any> = covariantString // mensubtitut dari Covariant<String> ke Covariant<Any> karna Any itu parent class dari String
    covariantAny.data()


    // penggunaan contravariant
    val contravarianAny: Contravariant<Any> = Contravariant()
    val contravariantString: Contravariant<String> = contravarianAny // mensubtitut dari Covariant<Any> ke Covariant<String> karna String itu child class dari Any
    contravariantString.sayHello("tatang")

}