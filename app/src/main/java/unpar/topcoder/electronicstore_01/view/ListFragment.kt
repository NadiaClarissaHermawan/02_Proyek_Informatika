package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.databinding.ProductListFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.ListPresenter

//fragment
class ListFragment : Fragment(), IList,View.OnClickListener {
    private lateinit var listBinding : ProductListFragmentBinding
    private lateinit var presenter : ListPresenter
    private lateinit var adapter : ListAdapter
    private var dataOffset : Int = 0

    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.listBinding = ProductListFragmentBinding.inflate(inflater, container, false)

        //buat, set adapter & presenter untuk tampilkan & operasikan list
        this.presenter = ListPresenter(this)
        this.adapter = ListAdapter(requireActivity(), this.presenter)
        this.listBinding.lstProducts.adapter = this.adapter

        //set click listener
        this.listBinding.layoutTypeGrid.setOnClickListener(this::onClick)
        this.listBinding.layoutTypeList.setOnClickListener(this::onClick)
        this.listBinding.buttonLoadMore.setOnClickListener(this::onClick)

        //initial products
        this.callUpdateList()

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
        if(view == this.listBinding.layoutTypeGrid){
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.GRID_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

            var target = Bundle()
            target.putInt("target", this.dataOffset)
            parentFragmentManager.setFragmentResult(Page.SYNC_LISTENER, target)
        //load more
        }
        else if(view == this.listBinding.layoutTypeList){

        }
        else if(view == this.listBinding.buttonLoadMore) {
            this.callUpdateList()
        }
    }

    //load & update product list
    private fun callUpdateList() {
        this.presenter.updateList(this.dataOffset)
    }

    //salurkan updated product list dari presenter ke adapter untuk ditampilkan
    override fun updateList(products: ArrayList<ProductDetails>, newDataOffset : Int) {
        this.adapter.updateList(products)
        this.dataOffset = newDataOffset
    }

    //move to detailsFragment
    override fun moveToDetailsPage(currentProd: ProductDetails) {
        //kirim data clicked product
        var product = Bundle()
        product.putParcelable("product", Parcels.wrap(currentProd))
        product.putInt("layout", Page.LIST_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_TO_DETAILS_LISTENER, product)

        //change page
        var pg = Bundle()
        pg.putInt(Page.PAGE, Page.DETAIL_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
    }
}