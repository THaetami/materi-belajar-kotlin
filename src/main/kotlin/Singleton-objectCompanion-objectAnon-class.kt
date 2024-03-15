/*
    * singleton merupakan konsep pattern yang berguna untuk
      memastikan suatu object hanya memiliki satu instance dengan
      satu titik akses global
    * tidak ada penggunaan constructor dlm pembuatan object
    * tidak perlu membuat instance (inisialisasi) untuk menggunakan
      object sehingga bisa memanggil properti maupun function secara
      langsung
*/

// singleton object
object ContohSingletonObject {
    fun borrowBookById(id: Int) {
        println("book with $id has been borrowed")
    }
}


/*
    Companion Object (Object di dalam Class)

    * dengan keyword companion kita bisa membuat object di dalam
      class (inner object), sehingga kita bisa memanggil function
      atau properti di dalam companion object secara langsung
      tanpa perlu membuat instance
    * companion biasa digunakan  dlm kasus penulisan constant val,
      nilai tetap yang didefinisikan diawal dan tidak dapat diubah
      selama runtime

 */
class ContohCompanionObject {
    companion object {
        const val CONTOH_CONSTANT: Int = 43
        fun myFunction(myNum: Int) {
            println("Function inside companion object ${CONTOH_CONSTANT + myNum} ")
        }


        // perbedaan val constan dan val biasa
        /*
            1. val didefinisikan ketika runtime, sedangkan const val
               harus didefinisikan ketika compile time. jadi, const
               val harus didefinisikan secara langsung, bkn dlm
               bentuk pemanggilan fungsi
            2. val bisa diletakkan dimana saja, sedangkan const val hanya
               bisa dideklarasikan pada top level file atau berada dalam
               object
            3. const val dpt meningkatkan kinerja dgn menghilangkan
               komputasi ketika runtime, sedangkan val perlu dievaluasi
               ketika runtime
        */


        const val LIBRARY_NAME = "Tatang Haetami"
        /*
            nilai inisialisasi harus diketahui pada saat kompilasi
            dan harus berupa nilai konstan yang dapat dievaluasi pada
            waktu kompilasi.
         */
        // const val LIBRARY_NAME_1 = LIBRARY_NAME.lowercase()

        // berbeda dengan val biasa
        val LIBRARY_NAME_2 = LIBRARY_NAME.lowercase()
    }
}


/*
    Anonymous Class,

    ialah class yang tak memiliki nama dan biasanya digunakan
    untuk membuat instance dari suatu class atau interface
    tanpa perlu mendeklarasi kelas baru secara explisit.
    Anon class dapat dibuat dengan keyword 'object'
*/
// untuk contoh anon class
interface IFly {
    fun fly()
}

// function yang menerima parameter bertipe IFly dan memanggil fly()
fun flyWithWings(bird: IFly) {
    bird.fly()
}


/*
    Function Interface

    biasa disebut Singel Abstract Method (SAM) adalah sebuah
    interface yang hanya memiliki satu method abstract
*/
// Deklarasi antarmuka Clickable
fun interface ContohFunctionInterface {
    fun onClick()
}



fun main() {
    // menggunakan singleton object
    ContohSingletonObject.borrowBookById(11)

    // menggunakan companion object
    println(ContohCompanionObject.CONTOH_CONSTANT)
    println(ContohCompanionObject.myFunction(34))


    // Anon Class
    flyWithWings(object : IFly {
        /*
            Di sini, `object : IFly {...}` adalah sintaks untuk
            membuat anonymous class yang mengimplementasikan
            interface IFly. Instance dari anonymous class tersebut
            kemudian diteruskan sebagai parameter ke function
            flyWithWings()
        */
        override fun fly() {
            println("The bird flying")
        }
    })


    // penggunaan function interface
    ContohFunctionInterface {
        println("function interface terpanggil")
    }


}