package com.stateNetworkConnection.ow2companion

sealed class DataResults<T> {
    class Success<T>(val data: T) : DataResults<T>()
    class Error<T>(val message: String?) : DataResults<T>()
}
