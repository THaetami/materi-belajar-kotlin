/*
    Special Class yang disediakan oleh kotlin:
    1. Data class dirancang khusus untuk menyimpan data. yang memiliki
       function bawaan, yaitu toString(), equals(), copy(), componentN(),
       dan hasCode()
    2. Nested class, membuat class di dalam class tanpa akses ke class induk
    3. Inner Class, membuat class di dalam class dengan akses ke class induk
    4. Enum Class, tipe data dengan sekumpulan nilai konstan yang telah ditentukan
    5. Sealed Class, digunakan untuk pembatasan heirarki class
*/


/*
    hal yang perlu diperhatikan dlm membuat data class:
    1. constructor utama pada class tsb harus memiliki setidaknya
       1 parameter
    2. semua constructor utama perlu dideklarasikan sebagai val atau var
    3. modifier dari sebuah class tidak bisa abstract, open, sealed atau inner
*/


// data class
data class ContohDataClass(val name: String, val age: Int) {

    /*
        fungsi toString() otomatis dimiliki oleh data class,
        dengan toString() object dpt dikonversikan ke dlm
        bentuk yang dpt dicetak atau ditampilkan secara mudah
        dibaca
    */
    override fun toString(): String {
        return "isi object = $name dan $age"
    }

    fun intro() {
        println("my name is $name, i am $age years old")
    }
}


/*
    * nested class adalah class di dalam class
    * apabila suatu clas memang hanya digunakan oleh satu class
      saja dan keduannya memiliki hubungan yang erat, lebih baik
      dibuat nested class untuk meningkatkan enkapsulasi dan kode
      lebih bersih
*/

// nested class
class House {
    // properti atau anggota outer class
    val buildingArea = 100
    val totalRooms = 4

    /*
        * keyword inner ditambahkan agar inner class dpt
          mengakses Outer Class alias House() class.
        * jika nama properti Outer dan Inner class memiliki
          nama yang sama, maka gunakan
          qualifield this -> " this@NamaClass.namaProperti "
    */
    inner class Room {
        // properti atau anggota inner class
        val totalRooms = 10
        fun measureRoomArea() {
            //inner class dpt mengakases properti outer class
            println(buildingArea/this@House.totalRooms)
        }
    }
}


/*
    Enum Class,

    * salah fitur yang bisa digunakan untuk menyimpan
      kumpulan object yang telah didefinisikan menjadi tipe data
      konstanta.
    * enum berguna untuk meminimalisir nilai variable tertukar
      dengan nilai variable lain, meminimalisir kesalahan
      pengetikan
    * object yang berada di dalam Enum secara implisit bersifat
      static dan final sehingga kita tidak dapat mengubahnya
      setelah dideklarasikan.
*/
//contoh enum dengan constructor
enum class Color(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}


/*
    Sealed class

    * untuk pembatasan hierarki class
    * semua turunannya harus sesuai dengan yang didefinisikan
      di dalam class tersebut.
    * mirip enum tapi jauh lebih fleksibel. enum hanya berisi
      satu object atau instance yang sama per class, sedang
      sealed class dpt berisi beberap object yang berbeda
      untuk memberikan info lebih
*/
sealed class Result {
    sealed class Success (val data: Any): Result()
    data class Error(val message: String): Result()
    object Loading: Result()
}


fun main() {
    val dataClass = ContohDataClass("tatang", 18)

    dataClass.intro()

    // hasil menggunakan toString()
    println(dataClass)

    /*
        fungsi equals() biasa digunakan untuk melakukan
        komparasi konten antara 2 object
    */
    val dataKomparasi1 = ContohDataClass("tatang", 18)
    val dataKomparasi2 = ContohDataClass("tatang", 25)

    println(dataClass.equals(dataKomparasi1)) // hasil true, karna object dataClass dan dataKomparasi memiliki nilai sama
    println(dataClass.equals(dataKomparasi2)) // false


    /*
        1. fungsi copy(), dpt digunakan untuk menyalin sebuah object
           dengan sangat mudah
        2. selain itu dapat mengcopy sekaligus memodifikasi atau
           mengubah nilai properti
    */
    val dataCopy = dataClass.copy()
    println(dataCopy)

    val dataCopyUpdate = dataClass.copy(age = 90, name = "Bayu") // mengcopy dan mengupdate
    println(dataCopyUpdate)


    /*
        Destructuring Declaration

        fungsi componentN(), biasa digunakan untuk menguraikan
        sebuah object menjadi beberapa properti yang dimilikinya
    */
    val component1 = dataClass.component1()
    val compenent2 = dataClass.component2()
    println(component1)
    println(compenent2)

    // Destructuring Declaration bisa juga dgn cara berikut
    val (name, age) = dataClass
    println(name)
    println(age)

    // menggunakan nested class
    val house = House()
    val room = house.Room()
    room.measureRoomArea()

    /*
        Enum

        fungsi values() untuk mendapatkan daftar object Enum,
        selain itu bisa menggunakan enumValues()
    */

    // val colors: Array<Color> = Color.values()
    val colors: Array<Color> = enumValues()
    colors.forEach { color ->
        println("${color.name} = ${color.value.toString(16)}")
    }


    /*
        valueOf() untuk mendapatkan nama dari object Enum,
        selain itu bisa menggunakan enumValueOf()
     */
    // val colorName: Color = enumValueOf("RED")
    val colorName: Color = Color.valueOf("RED")
    println("color is $colorName")
    println("color value is ${colorName.value.toString(16)}")


    // penggunaan sealed class
    val result: Result = Result.Error("Opps")
    when(result) {
        //jika salah satu kondisi dihapus, kode akan error
        is Result.Success -> {
            println("Success: ${result.data}")
        }
        is Result.Error -> {
            println("Error: ${result.message}")
        }
        is Result.Loading -> {
            println("Loading...")
        }
    }
}