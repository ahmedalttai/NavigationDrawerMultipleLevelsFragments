package me.ahma.navigationdrawermultiplelevelsfragments.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book_details.*
import kotlinx.android.synthetic.main.fragment_book_details.view.*
import me.ahma.navigationdrawermultiplelevelsfragments.Base.BaseFragment

import me.ahma.navigationdrawermultiplelevelsfragments.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BookDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BookDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BookDetailsFragment : BaseFragment() {

    override var title = "Placeholder"

    override var backArrow = true

    //2
    companion object {

        fun newInstance(title:String): BookDetailsFragment {
            var new = BookDetailsFragment()
            new.title= title
            return new
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_details, container, false)
        view.book_details_text.text =  title + " is a great book because of the blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah "
        return view
    }

}