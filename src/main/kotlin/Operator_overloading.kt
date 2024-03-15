/*
    Operator Overloading

    * kemampuan membuat function dari operator-operator seperti
      operator aritmatika, shingga kita bisa melakukan operasi
      apapun di object seperti layaknya tipe data integer
    * untuk membuat operator overloading dibutuhkan keyword
      `operator` sebelum declaration function
*/
data class Fruits(val total: Int) {
    operator fun plus(fruits: Fruits): Fruits {
        return Fruits(total + fruits.total)
    }
    
    operator fun minus(fruits: Fruits): Fruits {
        return Fruits(total - fruits.total)
    }

    /*
       note:
       plus dan minus adalah contoh function yang dimiliki
       kotlin untuk operasi aritmatika

       a + b ==> a.plus(b)
       a - b ==> a.minus(b)
       a * b ==> a.times(b)
       a / b ==> a.div(b)
       a..b ==> a.rangeTo(b)
   */
}

fun main() {
    val fruits1 = Fruits(70)
    val fruits2 = Fruits(100)
    val fruits3 = fruits2 + fruits1 // tanpa keyword operator, tanda `+` tidak berfungsi
    val fruits4 = fruits1 - fruits2
    println(fruits3)
    println(fruits4.total)
}
