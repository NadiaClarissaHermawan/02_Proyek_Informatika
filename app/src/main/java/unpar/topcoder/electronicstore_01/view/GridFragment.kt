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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        this.gridBinding=ProductGridFragmentBinding.inflate(inflater, container, false)

        this.presenter= GridPresenter(this)
        this.adapter=GridAdapter(requireActivity(), this.presenter)
        this.gridBinding.lstProducts.adapter = this.adapter

        this.gridBinding.layoutType.setOnClickListener(this::onClick)
        this.gridBinding.buttonLoadMore.setOnClickListener(this::onClick)

        presenter.updateGrid(this.dataOffset)

        //TODO(bikin listener untuk update product setelah list di update)


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
        if(view == this.gridBinding.layoutType){
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.LIST_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)
        }
        else if(view == this.gridBinding.buttonLoadMore){
            presenter.updateGrid(this.dataOffset)
        }
    }

    //salurkan data yang akan ditampilkan ke adapter & sinkronisasi isi produk ke list fragment
    override fun updateGrid(prods: ArrayList<ProductDetails>,newDataOffset : Int) {
        this.adapter.update(prods)
        this.dataOffset = newDataOffset
        this.listFragment.updateList(prods,newDataOffset)
    }
}