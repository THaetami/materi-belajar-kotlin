package cotlin.coroutin

import kotlinx.coroutines.*
import kotlinx.coroutines.Job
import org.junit.jupiter.api.Test

/*
    * job adalah hasil kembalian dari sebuah coroutine yang
      dijalankan menggunakan function launch
    * dengan object job, kita bisa menjalankan, membatalkan, atau
      menunggu sebuah coroutine
*/

class Job {

    @Test
    fun testJob() {
        runBlocking {
            val job: Job = GlobalScope.launch {
                delay(2_000)
                println("Coroutine Done ${Thread.currentThread().name}")
            }

            job.cancel()
            delay(3_000)

        }
    }

}