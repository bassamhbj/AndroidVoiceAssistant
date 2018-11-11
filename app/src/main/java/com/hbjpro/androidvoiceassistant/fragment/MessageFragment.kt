package com.hbjpro.androidvoiceassistant.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.adapter.MessageAdapter
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.common.data.MessageData
import com.hbjpro.androidvoiceassistant.presenter.FragmentPresenter
import kotlinx.android.synthetic.main.fragment_message.*

private const val MESSAGE = "message_text"
private const val IS_CREATE = "is_create"

class MessageFragment : Fragment() {

    private var message: String = ""
    private var isCreateMessage: Boolean = false
    private var _presenter = FragmentPresenter()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE)
            isCreateMessage = it.getBoolean(IS_CREATE)
        }

        initFirebase()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_message, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewMessage)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun initFirebase(){
        if(isCreateMessage){
            doCreateMessage()
        }

        doListenMessage()
    }

    private fun doCreateMessage(){
        var messageData = MessageData("message${System.currentTimeMillis()}", message)
        _presenter.doExecuteCreateNewMessage(messageData, object: FragmentPresenter.ViewListener<String>{
            override fun onSuccess(result: String) {
                setSnackBar(result)
            }

            override fun onError(errorMsg: String) {
                setSnackBar(errorMsg)
            }
        })
    }

    private fun doListenMessage(){
        _presenter.doExecuteMessageListener( object: FragmentPresenter.ViewListener<List<MessageData?>>{
            override fun onSuccess(result: List<MessageData?>) {
                recyclerView.layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = MessageAdapter(result, activity!!.applicationContext)
                setSnackBar("Message data has been updated")
            }

            override fun onError(errorMsg: String) {
                setSnackBar(errorMsg)
            }
        })
    }

    private fun setSnackBar(text: String){
        Snackbar.make(message_layout, text, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * @param message Parameter 1.
         * @param isCreateMessage Parameter 2.
         * @return A new instance of fragment MessageFragment.
         */
        @JvmStatic
        fun newInstance(message: String, isCreateMessage: Boolean) =
                MessageFragment().apply {
                    arguments = Bundle().apply {
                        putString(MESSAGE, message)
                        putBoolean(IS_CREATE, isCreateMessage)
                    }
                }
    }
}
