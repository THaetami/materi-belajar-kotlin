/*
    Generics yaitu sebuh konsep yang memungkinkan suatu class atau
    interface menjadi tipe parameter yang dapat digunakan untuk
    berbagai macam tipe data.
*/
// interface generic
interface ListContoh<T> { // <T> disebut type parameter
    operator fun get(index: Int) : T
}

class LongList : ListContoh<Long> { // class yang menggunakan interface generic
    override fun get(index: Int): Long {
        return this[index]
    }
}

// class generic yang menggunakan interface generic
class ArrayList<T> : ListContoh<T> {
    override fun get(index: Int): T {
        return this[index]
    }
}


// generic pada extension function, type param ditulis sebelum nama function
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
class ListNumber<T : Number> : ListContoh<T>{
    override fun get(index: Int): T {
        return this[index]
    }
}

// contoh lain
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
abstract class Vehicle(wheel: Int)
class ElectricCar(speed: Int) : Vehicle(4)
data class MotorCycle(val speed: Int) : Vehicle(2)

/*
    Class generic yang mempunyai variance terdiri dari 2, yakni
*/


open class Binatang
class Kucing : Binatang()

// contoh Covariant
class Kandang<out T : Binatang>(private val hewan: T) {
    fun dapatkanHewan(): T {
        return hewan
    }
}

// contoh contravariant
class PenjagaKandang<in T : Binatang> {
    fun urusKandang(hewan: T) {
        // Melakukan sesuatu dengan kandang yang berisi hewan
    }
}

fun main() {
    // menggunakan class generic
    val longArrayList = ArrayList<Long>()
    // val firstLong = longArrayList[0]

    // menggunakan generic extension function
    val numbers = (1..100).toList()
    println(numbers.slice(1..10))

    // menggunakan class dengan constraint type
    val number = ListNumber<Long>()
    val number2 = ListNumber<Int>()
    // val number3 = ListNumber<String>() // akan error karna menggunakan tipe parameter selain number

    val number4 = listOf(1, 2, 3, 4, 5)
    println(number4.sumNumber())

    val names = listOf("dicoding", "academy")
    // names.sumNumber() // error : inferred type String is not a subtype of Number


    // materi variance
    val car = ElectricCar(200)
    val motorCycle = MotorCycle(100)
    /*
        variable `vehicle` dapat diisi dengan instance dari
        `ElectricCar` atau `MotorCycle` karena keduaanya adalah
        subtypes dari `Vehicle`. Dengan kata lain, kita bisa
        mengganti nilai variable dari instance `car` ke
        instance `motorCycle`
    */
    var vehicle: Vehicle = car
    vehicle = motorCycle // instance car diganti dgn motorCycle
    println(vehicle.speed)

    val carList = listOf(ElectricCar(2), MotorCycle(4))
    val vehicleList = carList


    val kandangKucing: Kandang<Kucing> = Kandang(Kucing())
    val kandangBinatang: Kandang<Binatang> = kandangKucing //


    val penjagaBinatang: PenjagaKandang<Binatang> = PenjagaKandang<Binatang>()
    val penjagaKucing: PenjagaKandang<Kucing> = penjagaBinatang // Contravariant, dapat di-substitute
}