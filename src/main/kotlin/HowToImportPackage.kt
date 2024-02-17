import com.dicoding.oop.utils.sayHello
import com.dicoding.oop.utils.PI
import com.dicoding.oop.utils.factorial
import com.dicoding.oop.utils.pow

/*
     jika kita menggunakan seluruh fungsi atau variabel dalam
     package tertentu kita bisa menggunakan tanda bintang (*)
     untuk melakukan impor pada seluruh fungsi dan variabel
     di package tersebut
*/

fun main() {
    sayHello()
    println(PI)
    println(factorial(4.0))
    println(pow(3.4, 2.0))
}