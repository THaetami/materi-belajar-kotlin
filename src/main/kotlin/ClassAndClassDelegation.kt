import kotlin.reflect.KProperty

class ContohClassDelegation {
    // variable value bisa diganti tipe data "any" agar bisa menggunakan berbagai tipe data
    private var value: String = "Default"

    operator fun getValue(classRef: Any?, property: KProperty<*> ) : String {
        println("Fungsi ini sama seperti getter untuk properti ${property.name} pada class $classRef")
        return value
    }

    operator fun setValue(classRef: Any?, property: KProperty<*>, newValue: String){
        println("Fungsi ini sama seperti setter untuk properti ${property.name} pada class $classRef")
        println("Nilai ${property.name} dari: $value akan berubah menjadi $newValue")
        value = newValue
    }
}

class ContohClassPenerimaDelegationPertama {
    var name: String by ContohClassDelegation()
}

class ContohClassPenerimaDelegationKedua {
    var name: String by ContohClassDelegation()
}

class ContohClassPenerimaDelegationKetiga {
    var name: String by ContohClassDelegation()
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
    val contohPertama = ContohClassPenerimaDelegationPertama();
    contohPertama.name = "Dimas"
    println(contohPertama.name)

    val contohKedua = ContohClassPenerimaDelegationKedua();
    contohKedua.name = "Kanjeng"
    println(contohKedua.name)

    val contohKetiga = ContohClassPenerimaDelegationKetiga();
    contohKedua.name = "Pranowo"
    println(contohKetiga.name)

    // contoh penggunaan primary constructor
    val contohPrimary = ContohPrimaryConstructor("Boby", 3.4, 40, true)
    println("Nama: ${contohPrimary.name}, Berat: ${contohPrimary.weight}, Umur: ${contohPrimary.age}, Mamalia ${contohPrimary.isMammal}")

    // contoh penggunaan class dengan init block
    val contohInit = ContohClassWithInit("Alex", 0.0, 30, false)
    println("Nama: ${contohInit.name}, Berat: ${contohInit.weight}, Umur: ${contohInit.age}, Mamalia ${contohInit.isMammal}")
    println(contohInit.isEat)



}