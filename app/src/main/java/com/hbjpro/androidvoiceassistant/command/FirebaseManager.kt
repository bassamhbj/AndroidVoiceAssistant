package com.hbjpro.androidvoiceassistant.command

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.hbjpro.androidvoiceassistant.common.firebase.FirebaseClient
import com.hbjpro.androidvoiceassistant.common.data.MessageData

class FirebaseManager {

    private val MESSAGE_LIST_KEY = "message_list"

    fun createNewMessage(message: MessageData, callback: FirebaseCallback<String>){
        FirebaseClient.getDatabaseReference()
                .child(MESSAGE_LIST_KEY)
                .child(message.id)
                .setValue(message)
                .addOnCompleteListener {
                    callback.onSuccess("Message has been created")
                }
                .addOnFailureListener {
                    callback.onError(it.stackTrace.toString())
                }
    }

    fun listenForNewData(callback: FirebaseCallback<List<MessageData?>>){
        FirebaseClient.getDatabaseReference()
                .addValueEventListener(object: ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        callback.onError("Message Data could not be loaded...")
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var list = p0.child(MESSAGE_LIST_KEY).children
                                ?.map { it?.getValue(MessageData::class.java) }

                        callback.onSuccess(list)
                    }
                })
    }

    interface FirebaseCallback<T>{
        fun onSuccess(result: T)
        fun onError(errorMsg: String)
    }
}