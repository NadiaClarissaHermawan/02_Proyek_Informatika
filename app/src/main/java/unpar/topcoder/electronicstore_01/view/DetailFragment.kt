package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.synnapps.carouselview.ImageListener
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.databinding.ProductDetailFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

//fragment
class DetailFragment : Fragment(), View.OnClickListener{
    private lateinit var detailBinding : ProductDetailFragmentBinding
    private var layout : Int = Page.LIST_PAGE
    //private var imageArray:ArrayList<Int> =  ArrayList()
    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.detailBinding = ProductDetailFragmentBinding.inflate(inflater, container, false)

        //listener dari list & grid layout untuk tampilin detail 1 produk
        parentFragmentManager.setFragmentResultListener(Page.CHANGE_TO_DETAILS_LISTENER, this){
                _, result ->
            this.layout = result.getInt("layout")
            this.updateLayout(Parcels.unwrap<Any>(result.getParcelable("product")) as ProductDetails)
        }

        this.detailBinding.back.setOnClickListener(this::onClick)

        return this.detailBinding.root
    }

    //singleton constructor
    companion object {
        fun getInstance () : DetailFragment {
            val instance = DetailFragment()
            //todo : initialize instance's attributes if needed
            //instance.attr_name = bablabla..
            return instance
        }
    }

    //update details produk ke layar
    fun updateLayout(currentProduct : ProductDetails){
        this.detailBinding.productCategory.text = currentProduct.getKategori()
        this.detailBinding.productCondition.text = ""+currentProduct.getKondisi()+"% new"
        this.detailBinding.productName.text = currentProduct.getNama()
        this.detailBinding.productPrice.text = this.convertInt(currentProduct.getHarga())

        //untuk ngeset image carousel
        var imageArray:ArrayList<Int> =  ArrayList()
        imageArray.add(currentProduct.getImageSource())
        imageArray.add(currentProduct.getImageSource2())
        imageArray.add(currentProduct.getImageSource3())
        var imageListener = ImageListener {position, imageView ->  imageView.setImageResource(imageArray[position])}
        this.detailBinding.carouselView.setImageListener(imageListener)
        this.detailBinding.carouselView.pageCount = imageArray.size
    }

    //format integer to rupiah
    fun convertInt(price : Int): String {
        val localeID : Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    override fun onClick(view: View?) {
        if(view==this.detailBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, this.layout)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)
        }
    }
}