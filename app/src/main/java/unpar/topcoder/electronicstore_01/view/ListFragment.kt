package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.ProductListFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.presenter.ListPresenter

//fragment
class ListFragment : Fragment(), View.OnClickListener {
    private lateinit var listBinding : ProductListFragmentBinding
    private lateinit var presenter : ListPresenter
    private lateinit var adapter : ListAdapter

    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.listBinding = ProductListFragmentBinding.inflate(inflater, container, false)

        //buat, set adapter & presenter untuk tampilkan & operasikan list
        this.presenter = ListPresenter()
        this.adapter = ListAdapter(requireActivity(), this.presenter)
        this.listBinding.lstProducts.adapter = this.adapter

        //set click listener
        this.listBinding.layoutType.setOnClickListener(this::onClick)

        return this.listBinding.root
    }

    //singleton constructor
    companion object {
        fun getInstance () : ListFragment {
            val instance = ListFragment()
            //todo : initialize instance's attributes if needed
            //instance.attr_name = bablabla..
            return instance
        }
    }

    //onclick listener method
    override fun onClick(view: View?) {
        //ganti ke grid frag
        if(view == this.listBinding.layoutType){
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.GRID_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
        }
    }
}