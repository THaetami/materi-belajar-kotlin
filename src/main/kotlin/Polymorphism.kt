/*
    Polymorphism

    * kemampuan  sebuah object berubah bentuk menjadi bentuk lain
    * berhubungan erat dengan inheritance alias wajib melakukan
      inheritance
*/
 open class ClassForPolymorphism(val name: String) {
     open fun sayHello(name: String) {
         println("Hello $name, My name ${this.name} | parent class")
     }
 }

// mengextend class ClassForPolymorphism
open class SecondClassForPolymorphism(name: String): ClassForPolymorphism(name) {

    // mengubah function sayHello pada parent class `ClassForPolymorphism`
    final override fun sayHello(name: String) {
        println("hello $name,my name ${this.name} | child class / other class")
    }
}

fun main() {
    var employee: ClassForPolymorphism = ClassForPolymorphism("tatang")
    employee.sayHello("agung")

    /*
        mengubah variable `employee` yang tadinya object dari
        class parent `ClassForPolymorphism` menjadi object dari
        class child `SecondClassForPolymorphism`, kemampuan
        seperti inilah yang disebut polymorphism

        note: variable harus menggunakan `var`
    */
    employee = SecondClassForPolymorphism("subagja")
    employee.sayHello("ronny")
}