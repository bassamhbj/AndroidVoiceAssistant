package com.hbjpro.androidvoiceassistant.command

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hbjpro.androidvoiceassistant.common.firebase.FirebaseClient
import com.hbjpro.androidvoiceassistant.data.MessageData

class FirebaseManager {

    private val MESSAGE_LIST_KEY = "message_list"

    fun createNewMessage(message: MessageData, callback: FirebaseCallback<String, MessageData?>){
        FirebaseClient.getDatabaseReference()
                .child(MESSAGE_LIST_KEY)
                .child(message.id)
                .setValue(message)
                .addOnCompleteListener {
                    callback.onSuccess("")
                }
                .addOnFailureListener {
                    callback.onError("")
                }
    }

    fun listenForNewData(callback: FirebaseCallback<String, MessageData?>){
        FirebaseClient.getDatabaseReference()
                .addValueEventListener(object: ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        callback.onError("")
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var list = p0.child(MESSAGE_LIST_KEY).children
                                ?.map { it?.getValue(MessageData::class.java) }

                        callback.onSuccess(list)
                    }
                })
    }

    interface FirebaseCallback<T, A>{
        fun onSuccess(result: T) { /* Default implementation - Do Nothing */ }
        fun onSuccess(result: List<A>) { /* Default implementation - Do Nothing */ }
        fun onError(errorMsg: String)
    }
}