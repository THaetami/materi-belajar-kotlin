/*
    Overloading, kemampuan membuat function dengan nama yang sama
    dlm sebuah class
*/
class ContohOverloading (private var name: String) {
    fun eat() {
        println("$name makan!")
    }

    fun eat(typeFood: String) {
        println("$name memakan $typeFood!")
    }

    fun eat(typeFood: String, quantity: Double) {
        println("$name memakan $typeFood sebanyak $quantity grams!")
    }

    fun sleep() {
        println("$name tidur!")
    }
}


/*
    * Inheritance, untuk membuat class menjadi super class maka
      diwajibkan menggunakan keyword open, karna secara default
      class berbentuk "final".
    * keyword override untuk mengambil alih atau mengganti
      implementasi function dari parent class, proses ini biasa
      disebut function overriding, kemampuan membuat ulang function
      yang sudah ada di class parent.
 */
open class ParentClass(val name: String, val weight: Double, val age: Int, val isCarnivore: Boolean){
    open fun eat(){ // `open` ditambahkan agar function memiliki kemampuan overriding
        println("$name sedang makan!")
    }

    open fun sleep(){
        println("$name sedang tidur!")
    }
}

class ChildClass(pName: String, pWeight: Double, pAge: Int, pIsCarnivore: Boolean, val furColor: String, val numberOfFeet: Int) : ParentClass(pName, pWeight, pAge, pIsCarnivore) {
    fun playWithHuman() {
        println("$name bermain bersama Manusia !")
    }

    override fun eat(){
        println("$name sedang memakan ikan !")
    }

    override fun sleep() {
        println("$name sedang tidur di bantal !")
    }
}


open class ParentClassWithSuperKeyword(val name: String){
    open fun eat(){
        println("$name sedang makan!")
    }
}

class Cat(pName: String) : ParentClassWithSuperKeyword(pName) {
    override fun eat(){
        super.eat() // dengan keyword `super` kita bisa mengakses function atau properti `eat` yang ada/dimiliki parent class, function sebelum dioverridkan
        println("$name sedang memakan ikan !")
    }
}


// contoh super constructor
open class Customer(val name: String, val type: String, val balance: Long) {
    constructor(name: String, type: String): this(name, type, 0)
    constructor(name: String): this(name, "Standar")
}

// child class tanpa primary constructor
class PremiumCustomer: Customer {
    constructor(name: String): super(name, "Premium")
    constructor(name: String, balance: Long): super(name,"Premium", balance)
}

// child class dengan primary constructor
class ExcecutiveCustomer(name: String, balance: Long): Customer(name, "Executive", balance) {
    constructor(name: String): this(name, 0)
}

fun main() {
    // contoh penggunaan overloading method
    val contohOverloading = ContohOverloading("Tatang Haetami")
    contohOverloading.eat()
    contohOverloading.eat("Nagasari")
    contohOverloading.eat("Buah jeruk", 20.9)

    // contoh penggunaan inheritance
    val childClass = ChildClass("Dicoding Miaw", 3.2, 2, true, "Brown", 4)
    childClass.playWithHuman()
    childClass.eat()
    childClass.sleep()

    val dicodingCat = Cat("Dicoding Miaw")
    dicodingCat.eat()

    // menggunakan super constructor
    val premiumCustomer = PremiumCustomer("tatang")
    println(premiumCustomer.name)

    val excecutiveCustomer = ExcecutiveCustomer("tatang", 10000)
    println(excecutiveCustomer.name)
    println(excecutiveCustomer.balance)

}