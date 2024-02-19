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
}