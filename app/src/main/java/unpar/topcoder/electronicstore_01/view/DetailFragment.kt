package unpar.topcoder.electronicstore_01.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.synnapps.carouselview.ImageListener
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.databinding.ProductDetailFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails

// fragment
class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var detailBinding: ProductDetailFragmentBinding
    private var layout: Int = Page.LIST_PAGE
    private lateinit var currProductDetails: ProductDetails

    // constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // bind layout
        this.detailBinding = ProductDetailFragmentBinding.inflate(inflater, container, false)

        // listener dari list & grid layout untuk tampilin detail 1 produk
        parentFragmentManager.setFragmentResultListener(Page.CHANGE_TO_DETAILS_LISTENER, this) {
            _, result ->
            this.layout = result.getInt("layout")
            this.currProductDetails = Parcels.unwrap<Any>(result.getParcelable("product")) as ProductDetails
            this.updateLayout()
        }

        //set click listener
        this.detailBinding.back.setOnClickListener(this::onClick)
        this.detailBinding.cart1.setOnClickListener(this::onClick)
        this.detailBinding.add.setOnClickListener(this::onClick)

        return this.detailBinding.root
    }

    // click listener
    override fun onClick(view: View?) {
        if (view==this.detailBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, this.layout)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)

        // ganti ke shopping cart page / tambah barang ke cart
        } else if (view == this.detailBinding.cart1 || view == this.detailBinding.add) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

            var currPg = Bundle()
            currPg.putInt("layout", Page.DETAIL_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_TO_SHOPPING_CART_LISTENER, currPg)

            if (view == this.detailBinding.add) {
                var product = Bundle()
                product.putParcelable("product", Parcels.wrap(this.currProductDetails))
                parentFragmentManager.setFragmentResult(Page.ADD_TO_SHOPPING_CART, product)
            }
        }
    }

    // singleton constructor
    companion object {
        fun getInstance() : DetailFragment {
            val instance = DetailFragment()
            return instance
        }
    }

    // format integer to rupiah
    fun convertInt(price: Int) : String {
        val localeID: Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    // update details produk ke layar
    fun updateLayout() {
        this.detailBinding.productCategory.text = this.currProductDetails.getKategori()
        this.detailBinding.productCondition.text = "" + this.currProductDetails.getKondisi() + "% new"
        this.detailBinding.productName.text = this.currProductDetails.getNama()
        this.detailBinding.productPrice.text = this.convertInt(this.currProductDetails.getHarga())

        // untuk ngeset image carousel
        var imageArray: ArrayList<Int> =  ArrayList()
        imageArray.add(this.currProductDetails.getImageSource())
        imageArray.add(this.currProductDetails.getImageSource2())
        imageArray.add(this.currProductDetails.getImageSource3())
        var imageListener = ImageListener {
                position, imageView ->  imageView.setImageResource(imageArray[position])
        }
        this.detailBinding.carouselView.setImageListener(imageListener)
        this.detailBinding.carouselView.pageCount = imageArray.size
    }
}