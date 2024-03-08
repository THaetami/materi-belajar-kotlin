import kotlinx.coroutines.*

@InternalCoroutinesApi
fun main() = runBlocking {
    val job = launch {
        delay(5000L)
        println("Start new job!")
    }

    delay(2000L)
    job.cancel(cause = CancellationException("times is up!"))
    println("Cancelling job...")
    if (job.isCancelled) {
        println("Job is cancelled because  ${job.getCancellationException().message}")
    }
}