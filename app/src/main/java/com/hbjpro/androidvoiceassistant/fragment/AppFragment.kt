package com.hbjpro.androidvoiceassistant.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbjpro.androidvoiceassistant.R
import com.hbjpro.androidvoiceassistant.presenter.FragmentPresenter
import kotlinx.android.synthetic.main.fragment_app.*

private const val APP_NAME = "APP_NAME"

class AppFragment : Fragment(), FragmentPresenter.ViewListener<Intent> {
    private var appName: String? = null
    private var _presenter = FragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appName = it.getString(APP_NAME)
        }

        _presenter.doExecuteOpenApp(appName.toString(), this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /* --- Override Methods --- */
    override fun onSuccess(result: Intent) {
        //setSnackBar("Starting $appName...")
        activity!!.startActivity(result)
    }

    override fun onError(errorMsg: String) {
        //setSnackBar(errorMsg)
    }

    private fun setSnackBar(text: String){
        Snackbar.make(app_layout, text, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * @param appName Parameter 1.
         * @return A new instance of fragment AppFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(appName: String) =
                AppFragment().apply {
                    arguments = Bundle().apply {
                        putString(APP_NAME, appName)
                    }
                }
    }
}
