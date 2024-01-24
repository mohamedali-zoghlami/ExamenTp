package com.gl4.examentp

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}