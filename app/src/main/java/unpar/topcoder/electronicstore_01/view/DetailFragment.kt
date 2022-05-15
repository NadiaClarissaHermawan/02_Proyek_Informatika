package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.databinding.ProductDetailFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails

//fragment
class DetailFragment : Fragment(), View.OnClickListener{
    private lateinit var detailBinding : ProductDetailFragmentBinding
    private var layout : Int = Page.LIST_PAGE

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
        this.detailBinding.productCondition.text = ""+currentProduct.getKondisi()
        //this.detailBinding.productDescription.text = currentProduct.getDesc()
        this.detailBinding.productImage.setImageResource(currentProduct.getImageSource())
        this.detailBinding.productName.text = currentProduct.getNama()
        this.detailBinding.productPrice.text = "Rp "+currentProduct.getHarga()
    }

    override fun onClick(view: View?) {
        if(view==this.detailBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, this.layout)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)
        }
    }
}