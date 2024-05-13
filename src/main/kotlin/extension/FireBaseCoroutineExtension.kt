package extension

import com.google.api.core.ApiFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> ApiFuture<T>.await(): T = suspendCancellableCoroutine { continuation ->
    this.addListener({
        try {
            continuation.resume(this.get())
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }, MoreExecutors.directExecutor())  // Execute the callback in the thread that completes the future
}
