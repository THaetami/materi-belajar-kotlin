import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/*
    Deferred
    * fungsi `async` akan mengembalikan nilai deffered berupa hasil
      atau exception.
    * deferred yaitu nilai tangguhan yang dihasilkan dari proses
      coroutinis
    * deferred dapat dibuat secara manual.
    * deffered memiliki life cyle yang sama seperti job, bedanya
      hanya pada tipe yang hasil yang diberikan.
    * hasil deferred tersedia ketika mencapai completed dan dapat
      diakses dengan fungsi await.
    * diferred akan mengirimkan pengecualian jika ia gagal, dan
      kita bisa mengakses nilai pengecualian tersebut dengan
      fungsi `getCompletionExceptionOrNull`
*/

fun main() = runBlocking {
    val capital = async { getCapital() }
    val income = async { getIncome() }
    println("Your profit is ${income.await() - capital.await()}")
}