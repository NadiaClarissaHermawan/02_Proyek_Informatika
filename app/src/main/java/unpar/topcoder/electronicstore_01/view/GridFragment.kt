package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.ProductGridFragmentBinding
import unpar.topcoder.electronicstore_01.model.*
import unpar.topcoder.electronicstore_01.presenter.GridPresenter

class GridFragment : Fragment(), View.OnClickListener, GridInterface{
    private lateinit var gridBinding: ProductGridFragmentBinding
    private lateinit var presenter: GridPresenter
    private lateinit var adapter : GridAdapter
    private lateinit var listFragment : ListFragment
    private var dataOffset : Int = 0

    //view constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        //bind
        this.gridBinding=ProductGridFragmentBinding.inflate(inflater, container, false)

        //declare presenter, adapter
        this.presenter= GridPresenter(this)
        this.adapter=GridAdapter(requireActivity(), this.presenter)
        this.gridBinding.lstProducts.adapter = this.adapter

        //set on click listener
        this.gridBinding.layoutType.setOnClickListener(this::onClick)
        this.gridBinding.buttonLoadMore.setOnClickListener(this::onClick)

        //add first 5 products
        presenter.updateGrid(this.dataOffset, this.dataOffset+4)

        //sync product with list layout (listener)
        this.parentFragmentManager.setFragmentResultListener(Page.SYNC_LISTENER, this) {
                _, result ->
            this.callUpdateList(result.getInt("target"))
        }

        return this.gridBinding.root
    }

    //singleton
    companion object{
        fun getInstance(listFragment: ListFragment): GridFragment{
            val instance = GridFragment()
            instance.listFragment=listFragment
            return instance
        }
    }

    //untuk pindah page ke fragment list
    override fun onClick(view : View?) {
        //change page
        if(view == this.gridBinding.layoutType){
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.LIST_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)
        //load more
        }else if(view == this.gridBinding.buttonLoadMore){
            presenter.updateGrid(this.dataOffset, this.dataOffset+4)
        }
    }

    //sync product with list fragment
    private fun callUpdateList(target : Int) {
        this.presenter.updateGrid(this.dataOffset, target-1)
    }

    //salurkan data yang akan ditampilkan ke adapter & sinkronisasi isi produk ke list fragment
    override fun updateGrid(prods: ArrayList<ProductDetails>,newDataOffset : Int) {
        this.adapter.update(prods)
        this.dataOffset = newDataOffset
        this.listFragment.updateList(prods,newDataOffset)
    }
}