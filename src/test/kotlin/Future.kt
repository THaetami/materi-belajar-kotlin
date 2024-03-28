package cotlin.coroutin

import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis


/*
    * return value yang dihasilkan Thread yang dibuat dengan
      interface Runnable adalah void (unit), artinya tidak
      mengembalikan data.
    * jika kita ingin mengeksekusi sebuah kode yang
      mengembalikan data, kita bisa menggunakan interface
      Callable, dimana terdapat method call dan return value nya
      adalah generic
    * menggunakan ExecutorService.submit(callable) untuk
      mengeksekusi Callable dan hasilnya adalah Future<T>


    Future :
    * Future merupakan return value untuk eksekusi Callable
    * Dengan future, kita bisa mengecek status apakah proses
      telah selesai, atau bisa mendapatkan data hasil return
      callable, atau bahkan membatalkan proses callable yang
      sedang berjalan.
*/

class Future {

    val executorService = Executors.newFixedThreadPool(10)

    private fun getFoo(): Int {
        Thread.sleep(5000)
        return 10
    }

    private fun getBar(): Int {
        Thread.sleep(5000)
        return 10
    }

    @Test
    fun testNonParallel() { // sequential
        val time = measureTimeMillis {
            val foo = getFoo()
            val bar = getBar()
            val result = foo + bar
            println("total = $result")
        }

        println("total time = $time")
    }

    @Test
    fun testFuture() { // parallel
        val time = measureTimeMillis {
            val foo = executorService.submit(Callable { getFoo() })
            val bar = executorService.submit(Callable { getBar() })

            val total = foo.get() + bar.get() // gunakan fungsi get untuk mengambil hasil future
            println("total is $total")
        }

        println("Time = $time")
    }

}