package io.github.mobileteacher.newsrevision.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.mobileteacher.newsrevision.R
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import io.github.mobileteacher.newsrevision.AddNewsViewModel
import kotlinx.android.synthetic.main.fragment_news_meta_data.*
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

var newsDateFormat = SimpleDateFormat("yyyy-MM-dd")

class NewsMetaDataFragment : Fragment() {

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


        return inflater.inflate(R.layout.fragment_news_meta_data,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        subscribe()
    }

    private fun setupListeners(){
        date_edittext.setOnClickListener {
            DatePickerFragment().show(childFragmentManager, "DatePicker")
        }

        next_button.setOnClickListener {
            //TODO: validar o formulário

            //salvar os dados no ViewModel
            addNewsViewModel.newsTitle = title_edittext.text.toString()
            addNewsViewModel.newsInformative = informative_edittext.text.toString()
            addNewsViewModel.newsAuthor = author_edittext.text.toString()


            addNewsViewModel.incPage()
        }


    }

    private fun subscribe(){
        addNewsViewModel.date.observe(this,  Observer {
            date_edittext.setText(it)
        })
    }


    class DatePickerFragment : DialogFragment(),
        DatePickerDialog.OnDateSetListener {

        lateinit var addNewsViewModel: AddNewsViewModel
        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)


            //addBillViewModel.localeDate.value = sdf.format(calendar.time)

            addNewsViewModel.date.value = newsDateFormat.format(calendar.time)
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            activity?.let {addNewsViewModel = ViewModelProviders.of(it).get(AddNewsViewModel::class.java)}
            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(activity,
                this,
                year,
                month,
                day
            )
        }
    }


}
