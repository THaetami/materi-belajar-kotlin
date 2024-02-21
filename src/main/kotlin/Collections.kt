/*
    * collection merupakan sebuah object yang bisa menyimpan kumpulan
      object lain termasuk data class,
    * object turunan collection diantaranya list, set dan map
*/

data class User(val name: String) {
    override fun toString(): String {
        return "hallo $name!!"
    }
}

fun main() {
    /*
        LIST

        * karena setiap object pada kotlin merupakan turunan dari
          kelas Any, variable anyList dibawah ini akan memiliki tipe
          data List<Any>, yang bahkan bisa memasukan sebuah data
          class kedalam list tersebut
        * list yang dibuat dengan function listOf() tidak dpt ditambah,
          dihapus dan diubah item atau nilainya
        * list yang dibuat dengan function mutableListOf(), dapat ditambah,
          dihapus dan diupdate
    */

    // immutable list
    val numberImutableList: List<Int> = listOf(1, 2, 3, 4, 5)

    // list immutable tipe berbeda
    val anyList = listOf('a', "kotlin", 3, false, User("tami"))
    println(anyList[4]) // akses list index 4

    // mutable list
    val mutableList = mutableListOf('a', "kotlin", 3, true, User("tatang"))
    mutableList.add('d') // menambah item
    mutableList.add(1, "sarang") // menambah item pada index ke 1
    mutableList[3] = false // update nilai index 3
    mutableList.removeAt(0) // hapus item index 0
    println(mutableList)

    /*
        perbedaan array dan list
    */
    // array dgn arrayOf, bisa diubah tp tidak bisa ditambah atau dihapus
    val array = arrayOf("kotlin", "java") // array
    array[0] = "dart" // diubah
    // array.add("javascript") // tidak bisa ditambah, akan error

    // list dengan listOf, tidak bisa ditambah, dihapus, dan diupdate
    val list = listOf("Kotlin", "Java")
    // list[0] = "Dart" // error
    //list.add("JavaScript") //error

    // list dengan mutableListOf(), bisa ditambah, dihapus, dan diupdate
    val mutableList1 = mutableListOf("Kotlin", "Java")
    mutableList1[0] = "Dart" // no error
    mutableList1.add("JavaScript") // no error
    mutableList1.removeAt(1) // no error

    // ArrayList alias implementasi interface MutableList dengan function arrayListOf(), bisa ditambah, dihapus, dan diupdate
    val arrayList = arrayListOf("Kotlin", "Java")
    arrayList[0] = "Dart" // no error
    arrayList.add("JavaScript") // no error


    /*
        SET

        * sebuah collection yang hanya dapat menyimpan nilai yang
          unik
        * set yang dibuat dengan function setOf() tidak dpt ditambah,
          dihapus dan diubah item/nilainya
        * set yang dibuat dengan function mutableSetOf(), dapat ditambah,
          dihapus tetapi tidak dpt diupdate/diubah
    */

    // immutable set
    val contohSet = setOf(1, 2, 4, 5, 4, 3)
    println(contohSet) // tidak akan ada angka duplikat

    // mutable set
    val mutableSet = mutableSetOf(1, 2, 4, 2, 1, 5)
    //mutableSet[2] = 6 // tidak bisa mengubah mutableSet
    mutableSet.add(6) // menambah item di akhir set
    mutableSet.remove(1) //menghapus item yang memiliki nilai 1

    val setA = setOf(1, 2, 4, 2, 1, 5)
    val setB = setOf(1, 2, 4, 5)
    val setC = setOf(1, 5, 7)

    println(setA == setB) // akan true karna setA akan membuang angka yang sama
    val union = setA.union(setC) // menggabungkan angka yang tidak sama
    val intersect = setA.intersect(setC) // menggabungkan angka sama yg dimiliki setA dan setC
    val difference = setA.subtract(setC) // Menghasilkan elemen dari setA yang tidak ada di setC

    println(union) // 1 2 3 4 5 7
    println(intersect) // 1 5
    println(difference) // 2 4

    // contoh symentric difference pada set
    val numbers = setOf("one", "two", "three")
    val numbers2 = setOf("three", "four")
    println((numbers - numbers2) union (numbers2 - numbers) ) // [one, two, four]


    /*
        MAP

        * sebuah collection yang dapat menyimpan data dengan format
          key-value
        * map yang dibuat dengan function mapOf() tidak dpt ditambah,
          dihapus dan diubah item/nilainya
        * map yang dibuat dengan function mutableMapOf(), dapat ditambah,
          dihapus dan diupdate/diubah
    */

    // mutable map
    val myMutableMap = mutableMapOf("kunci1" to "nilai1", "kunci2" to "nilai2")
    myMutableMap["kunci3"] = "nilai3" // Menambah item
    myMutableMap.remove("kunci2")     // Menghapus item
    myMutableMap["kunci1"] = "nilaiBaru" // Mengubah nilai


    // immutable map
    val ibuKota = mapOf(
        "Jakarta" to "Indonesia",
        "London" to "England",
        "New Delhi" to "India"
    )

    println(ibuKota["London"]) // England
    println(ibuKota.getValue("Jakarta")) // Indonesia

        /*
            dengan menggunakan simbol [] alias indexing, ketika key
            yang dicari tidak ada, maka outputnya akan null,
            sedangkan menggunakan getValue akan menghasilkan error
        */
        println(ibuKota["Tokyo"]) // null
        // println(ibuKota.getValue("Tokyo") // error

    val mapKeys = ibuKota.keys
    val mapValues = ibuKota.values
    println(mapKeys) // mengambil seluruh key
    println(mapValues) // mengembil seluruh value



}
