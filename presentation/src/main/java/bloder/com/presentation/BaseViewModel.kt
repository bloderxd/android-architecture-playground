package bloder.com.presentation

import android.arch.lifecycle.ViewModel
import bloder.com.concurrency.coroutines
import bloder.com.concurrency.coroutines.CoroutinesModule
import kotlinx.coroutines.cancelChildren

open class BaseViewModel : ViewModel(), CoroutinesModule by coroutines() {

    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }
}