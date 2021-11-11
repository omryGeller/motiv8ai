package com.safelevel.motiv8aiha.network

import org.java_websocket.WebSocket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.handshake.ServerHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.URI

class Motiv8AiWebSocket( private val onMessageReceived: (String?) -> Unit) : WebSocketClient(URI(URL)) {

    override fun onOpen(handshakedata: ServerHandshake?) {
    }

    override fun onMessage(message: String?) {
        onMessageReceived.invoke(message)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
    }

    override fun onError(ex: Exception?) {
    }
    companion object{
        private val URL = "ws://superdo-groceries.herokuapp.com/receive"
    }
}