package co.zia.khalid.mockwebserver_sample

import androidx.lifecycle.Observer

open class Event<out T>(val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotConsumed(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peek() = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotConsumed()?.let {
            onEventUnhandledContent(it)
        }
    }
}