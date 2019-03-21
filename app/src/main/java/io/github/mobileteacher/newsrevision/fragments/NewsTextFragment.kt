package io.github.mobileteacher.newsrevision.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.github.mobileteacher.newsrevision.AddNewsViewModel

import io.github.mobileteacher.newsrevision.R
import kotlinx.android.synthetic.main.fragment_news_text.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NewsTextFragment : Fragment() {

    private lateinit var addNewsViewModel: AddNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            addNewsViewModel = ViewModelProviders.of(it)
                .get(AddNewsViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_news_text, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_button.setOnClickListener {
            // TODO: validar se tiver que validar
            addNewsViewModel.newsText = noticia_edittext.text.toString()

            addNewsViewModel.postNews()
        }

    }


}
