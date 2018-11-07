package com.hbjpro.androidvoiceassistant.common.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseClient {
    private var mDatabase: FirebaseDatabase

    init{
        mDatabase = FirebaseDatabase.getInstance()
    }

    fun getDatabaseReference(): DatabaseReference = mDatabase.reference
}