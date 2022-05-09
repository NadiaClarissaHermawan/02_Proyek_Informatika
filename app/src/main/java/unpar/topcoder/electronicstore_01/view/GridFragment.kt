package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.ProductGridFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductCode
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.GridPresenter

class GridFragment : Fragment(), View.OnClickListener, GridInterface{
    private lateinit var gridBinding: ProductGridFragmentBinding
    private lateinit var presenter: GridPresenter
    private lateinit var adapter : GridAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        this.gridBinding=ProductGridFragmentBinding.inflate(inflater, container, false)

        this.presenter= GridPresenter(this)
        this.adapter=GridAdapter(requireActivity(), this.presenter)
        this.gridBinding.lstProducts.adapter = this.adapter

        this.gridBinding.layoutType.setOnClickListener(this::onClick)
        this.gridBinding.buttonLoadMore.setOnClickListener(this::onClick)

        //masukin list produk ke adapter
        var p1 : ProductDetails = ProductDetails("produk 1", ProductCode.PMEDIUM, ProductCode.GALAXYBUDS,"50.000", R.drawable.test_image)
        var p2 : ProductDetails = ProductDetails("produk 2", ProductCode.PMEDIUM, ProductCode.SMARTPHONE,"50.000", R.drawable.test_image)
        var p3 : ProductDetails = ProductDetails("produk 3", ProductCode.PMEDIUM, ProductCode.WATCHES,"50.000",R.drawable.test_image)
        var p4 : ProductDetails = ProductDetails("produk 4", ProductCode.PHIGH, ProductCode.SMARTPHONE,"50.000", R.drawable.test_image)
        var p5 : ProductDetails = ProductDetails("produk 5", ProductCode.PMEDIUM, ProductCode.GALAXYBUDS,"50.000", R.drawable.test_image)
        var p6 : ProductDetails = ProductDetails("produk 6", ProductCode.PNEW, ProductCode.WATCHES,"50.000", R.drawable.test_image)
        var p7 : ProductDetails = ProductDetails("produk 7", ProductCode.PLOW, ProductCode.GALAXYBUDS,"50.000", R.drawable.test_image)
        var p8 : ProductDetails = ProductDetails("produk 8", ProductCode.PNEW, ProductCode.TABLET,"50.000", R.drawable.test_image)
        var p9 : ProductDetails = ProductDetails("produk 9", ProductCode.PNEW, ProductCode.TABLET,"50.000", R.drawable.test_image)
        var p10 : ProductDetails = ProductDetails("produk 10", ProductCode.PLOW, ProductCode.WATCHES,"50.000", R.drawable.test_image)
        var prods = arrayListOf(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)

        presenter.loadProducts(prods)

        return this.gridBinding.root

    }

    //singleton
    companion object{
        fun getInstance(): GridFragment{
            val instance= GridFragment()
            return instance
        }
    }

    //untuk pindah page ke fragment list
    override fun onClick(view: View?) {
        if(view== this.gridBinding.layoutType){
            var pg=Bundle()
            pg.putInt(Page.PAGE, Page.LIST_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)
        }
        else if(view == this.gridBinding.buttonLoadMore){
            presenter.addProducts()
        }
    }

    //interface function untuk balikin hasil presenter
    override fun updateGrid(prods: ArrayList<ProductDetails>) {
        this.adapter.update(prods)
    }
}