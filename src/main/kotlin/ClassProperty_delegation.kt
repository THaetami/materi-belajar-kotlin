import kotlin.reflect.KProperty

/*
    Delegation

    * delegation sederhananya adalah proses meneruskan propertis
      atau function ke object lain
*/
// contoh pertama | delegation
interface Base {
    fun sayHello(name: String)
    fun sayGoodBy(name: String)
}

class MyBase: Base {
    override fun sayHello(name: String) {
        println("Hello $name")
    }

    override fun sayGoodBy(name: String) {
        println("Good by $name")
    }
}

// membuat delegation dengan manual
//class MyBaseDelegate(val base: Base): Base {
//    override fun sayHello(name: String) {
//        base.sayHello(name)
//    }
//
//    override fun sayGoodBy(name: String) {
//        base.sayGoodBy(name)
//    }
//}

// membuat delegation otomatis dengan bantuan keyword `by`
class MyBaseDelegate(val base: Base): Base by base // semua function didelegasikan

// contoh salah satu function saja yang didelegasikan, yakni function sayHello() yang tidak didelegasikan
//class MyBaseDelegate(val base: Base): Base by base {
//    override fun sayHello(name: String) {
//        println("Hello $name")
//    }
//}


// contoh dua, membuat delegation untuk setter dan getter
class ContohClassDelegation {
    // variable value bertipe "Any" agar bisa menggunakan berbagai tipe data
    private var value: Any = "Default"

    operator fun getValue(classRef: Any?, property: KProperty<*> ) : Any {
        println("Fungsi ini sama seperti getter untuk properti ${property.name} pada class $classRef")
        return value
    }

    operator fun setValue(classRef: Any?, property: KProperty<*>, newValue: Any){
        println("Fungsi ini sama seperti setter untuk properti ${property.name} pada class $classRef")
        println("Nilai ${property.name} dari: $value akan berubah menjadi $newValue")
        value = newValue
    }
}

class ContohClassPenerimaDelegationPertama {
    var name: Any by ContohClassDelegation()
    var age: Any by ContohClassDelegation()
}

class ContohClassPenerimaDelegationKedua {
    var name: Any by ContohClassDelegation()
}

class ContohClassPenerimaDelegationKetiga {
    var name: Any by ContohClassDelegation()
}

// contoh primary constructor
class ContohPrimaryConstructor (val name: String, val weight: Double, val age: Int, val isMammal: Boolean)

// contoh class dengan init block
class ContohClassWithInit (pName: String, pWeight: Double, pAge: Int, pIsMammal: Boolean) {
    val name: String
    val weight: Double
    val age: Int
    val isMammal: Boolean
    var isEat: Boolean

    /*
        init memiliki beberapa fungsi:
        1. membantu memvalidasi sebuah nilai properti sebelum
           sebelum diinisialisasikan
     */
    init {
        this.weight = if (pWeight < 0) 0.1 else pWeight
        this.age = if (pAge > 1) 1 else pAge
        this.name = pName
        this.isMammal = pIsMammal
        this.isEat = false
    }

    /*
        secondary constructor, digunakan ketika kita ingin
        menginisialisasikan sebuah kelas dengan cara yang lain
     */
    constructor(name: String, weight: Double, age: Int, isMammal: Boolean, isEat: Boolean) : this(name, weight, age, isMammal) {
        this.isEat = isEat
    }
}


fun main() {
    // menggunakan contoh pertama | delegation
    val base = MyBase()
    base.sayHello("Eko");
    val baseDelegate =MyBaseDelegate(base)
    baseDelegate.sayHello("tatang")
    baseDelegate.sayGoodBy("Roni")

    // menggunakan contoh dua | delegation
    val contohPertama = ContohClassPenerimaDelegationPertama();
    contohPertama.name = "Dimas"
    contohPertama.age = 26
    println("contoh pertama penerima delegation class, dengan nama: ${contohPertama.name} dan umur: ${contohPertama.age}")

    val contohKedua = ContohClassPenerimaDelegationKedua();
    contohKedua.name = "Kanjeng"
    println("contoh kedua penerima delegation class: ${contohKedua.name}")

    val contohKetiga = ContohClassPenerimaDelegationKetiga();
    contohKetiga.name = "Pranowo"
    println("contoh ketiga penerima delegation class: ${contohKetiga.name}")


    // contoh penggunaan primary constructor
    val contohPrimary = ContohPrimaryConstructor("Boby", 3.4, 40, true)
    println("Nama: ${contohPrimary.name}, Berat: ${contohPrimary.weight}, Umur: ${contohPrimary.age}, Mamalia ${contohPrimary.isMammal}")

    // contoh penggunaan class dengan init block
    val contohInit = ContohClassWithInit("Alex", 0.0, 30, false)
    println("Nama: ${contohInit.name}, Berat: ${contohInit.weight}, Umur: ${contohInit.age}, Mamalia ${contohInit.isMammal}")
    println(contohInit.isEat)



}