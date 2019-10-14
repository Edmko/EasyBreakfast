package com.example.easybreakfast.ui.main
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.easybreakfast.R
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebViewFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView : WebView=view.findViewById(R.id.web_view)
        val args = WebViewFragmentArgs.fromBundle(arguments!!).myArg
        webView.loadUrl(args)
        cancel.setOnClickListener { v ->v.findNavController().navigateUp()  }
        super.onViewCreated(view, savedInstanceState)
    }
}
