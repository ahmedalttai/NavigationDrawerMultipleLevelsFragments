package me.ahma.navigationdrawermultiplelevelsfragments.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.booklist_row.view.*
import kotlinx.android.synthetic.main.fragment_book_list.view.*
import me.ahma.navigationdrawermultiplelevelsfragments.Base.BaseFragment

import me.ahma.navigationdrawermultiplelevelsfragments.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BookListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BookListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BookListFragment : BaseFragment() {

    override var title = "Books List"

    override var backArrow = false

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        view.recyclyrview_booklistfragment.adapter = adapter

        adapter.clear()

        val titlesArray = resources.getStringArray(R.array.book_titles)
        Log.d("Booklistfragmet","size: ${titlesArray.size} ")
        for (item in titlesArray) {
            adapter.add(BookItem(item))
        }

        adapter.setOnItemClickListener { item, view ->
            val  userItem = item as BookItem

            add(BookDetailsFragment.newInstance(userItem.name))

        }
        return view
    }

    companion object {
        fun newInstance(): BookListFragment {
            return BookListFragment()
        }
    }

    interface BackButtonSupportFragment {
        // return true if your fragment has consumed
        // the back press event, false if you didn't
        fun onBackPressed(): Boolean {
            return true
        }
    }
}

class BookItem(val name:String): Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.name_txtview_booklistrow.text = name

    }

    override fun getLayout(): Int {
        return R.layout.booklist_row
    }
}

