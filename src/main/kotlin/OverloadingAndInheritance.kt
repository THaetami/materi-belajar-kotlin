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
      implementasi function dari parent class
 */
open class ParentClass(val name: String, val weight: Double, val age: Int, val isCarnivore: Boolean){
    open fun eat(){
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
        super.eat()
        println("$name sedang memakan ikan !")
    }
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

}