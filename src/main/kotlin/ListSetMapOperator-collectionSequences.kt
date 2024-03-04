/*
    * collection merupakan sebuah object yang bisa menyimpan kumpulan
      object lain termasuk data class,
    * object turunan collection diantaranya list, set dan map yang
      merupakan jenis collection eager evaluation
    * eager evaluation mengevaluasi seluruh item yang ada pada
      collection
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
    println(contohSet) // [1, 2, 3, 4, 5], tidak akan ada angka duplikat

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



    // OPERATOR COLLECTION
    val numCollection = listOf(1, 2, 3, 4, 5, 6)

    /*
        * FILTER & FILTER NOT, akan menghasilkan list baru dari
          seleksi berdasarkan kondisi yang diberikan
        * filterNot() kebalikan dari filter()
    */
    val hasilFilter = numCollection.filter { it % 2 == 0 } // menyaring bilangan yang habis dibagi 2
    println(hasilFilter) // [2, 4, 5]


    /*
        MAP, akan membuat collection baru sesuai perubahan yang
        akan kita lakukan dari collection sebelumnya
    */
    val hasilMap = numCollection.map { it * 5 } // semua angka numCollection akan dikali 5
    println(hasilMap) // [5, 10, 15, 20, 25, 30]


    /*
        COUNT
    */
    println(numCollection.count()) // 6


    /*
        FIND & firstOrNull, untuk mencari item pada sebuah collection, hasilnya
        adalah item pertama yang sesuai kondisi yang kita tentukan
    */
    val hasilFind = numCollection.find { it % 2 == 1 } // mencari angka ganjil pertama
    println(hasilFind)

    val hasilFirstOrNull = numCollection.firstOrNull { it % 2 == 3 }
    println(hasilFirstOrNull) // null


    /*
        SUM
    */
    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val hasilSum = numberList.sum()


    /*
        SORTED
    */
    val kotlinChar = listOf('k', 'o', 't', 'l', 'i', 'n')
    val ascendingSort = kotlinChar.sorted()
    val descendingSort = kotlinChar.sortedDescending()
    println(ascendingSort)
    println(descendingSort)


    /*
        FOLD
    */
    val fold = numCollection.fold(10) { current, item ->
        /*
            * melakukan perhitungan setiap nilai collection tanpa
              harus melakukan iterasi item
        */
        println("current $current")
        println("item $item")
        current + item
    }
    println(fold)

        /*
            note:
            foldRight() akan melakukan proses iterasi dari indeks terakhir
        */


    /*
        DROP
    */
    val drop = numCollection.drop(3) // memotong collection dari index pertama
    val dropLast = numCollection.dropLast(3) // memotong collection dari index terakhir
    println(drop) // [4, 5, 6]
    println(dropLast) // [1, 2, 3]


    /*
        TAKE
    */
    val take = numCollection.take(3) // mengambil collection dari index pertama
    val takeLast = numCollection.takeLast(3) // mengambil collection dari index terakhir
    println(take) // [1, 2, 3]
    println(takeLast) // [4, 5, 6]


    /*
        SLICE, menyaring item dari posisi tertentu
    */
    val slice = numCollection.slice(2..4) // dari index 2 - 4
    val sliceStep = numCollection.slice(2..4 step 2)
    println(slice) // [3, 4, 5]
    println(sliceStep) // [3, 5]

    val index = listOf(2, 3, 5, 8)
    val total = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val sliceTotal = total.slice(index) // akan berisi collection/variable "total" yang berindex 2, 3, 5, 8
    println(sliceTotal) // [3, 4, 6, 9]


    /*
        CHUNKED, untuk memecah nilai string menjadi beberap bagian
        kecil menjadi array
    */
    val word = "QWERTY"
    val chunked = word.chunked(4)
    println(chunked) // [QWE, RTY]

    val chunkedTransform = word.chunked(3) {
        it.toString().toLowerCase()
    }
    println(chunkedTransform) // [qwe, rty]


    /*
        DISTINCT, menyaring item yang sama di dalam sebuah collection
    */
    val numDistinct = listOf(1, 2, 1, 3, 4, 5, 2, 3, 4, 5)
    val distinct = numDistinct.distinct()
    println(distinct) // [1, 2, 3, 4, 5]

    // contoh distinctBy(), menyaring item yg memiliki panjang yg sama
    val text = listOf("A", "B", "CC", "DD", "EEE", "F", "GGGG")
    val distinctBy = text.distinctBy {
        it.length
    }

    println(distinctBy) // [A, CC, EEE, GGGG]

    // contoh distinc lain
    val items = listOf(
        Item("1", "Kotlin"),
        Item("2", "is"),
        Item("3", "Awesome"),
        Item("3", "as"),
        Item("3", "Programming"),
        Item("3", "Language")
    )

    val distinctItems = items.distinctBy { it.key }
    distinctItems.forEach {
        println("${it.key} with value ${it.value}")
    }



    /*
        SEQUENCES

        * sequence merupakan collection yang bisa dikategorikan ke
          dalam lazy evaluation. lazy evaluation hanya akan
          mengevaluasi item jika benar-benar diperlukan.
        * memungkinkan pemrosesan data yang efesien dan ekonomis
          dalam hal sumber daya, terutama ketika kita hanya perlu
          memproses sebagian kecil dari collection besar atau
          melakukan operasi2 transformasi yang kompleks.
    */

    // membuat sequance dengan asSequence()
    val listForSequence = (1..1000).toList()
    val contohSequence = listForSequence.asSequence().filter { it % 5 == 0 }.map { it * 2 }.first()
    println(contohSequence)

    // membuat sequance dengan sequenceOf()
    val contohSequenceLain = sequenceOf(1, 2, 3, 4, 5)
    val squaredNumbers = contohSequenceLain
        .map { it * it }
        .filter { it > 10 }
    squaredNumbers.forEach { println(it) }

    // membuat sequance dengan generateSequence()
    val sequenceNumber = generateSequence(1) { it + 1 }
    sequenceNumber.take(5).forEach { print("$it ") }
}

data class Item(val key: String, val value: Any)