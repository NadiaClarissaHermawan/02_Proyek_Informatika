package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ShoppingCartEntryBinding
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.ShoppingCartPresenter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ShoppingCartAdapter(private var activity: Activity, private var presenter: ShoppingCartPresenter) : BaseAdapter(), View.OnClickListener {
    private lateinit var shoppingCartEntryBinding: ShoppingCartEntryBinding
    private var prods: ArrayList<ProductDetails> = ArrayList()

    override fun getCount() : Int {
        return this.prods.size
    }

    override fun getItem(p0: Int) : ProductDetails {
        return this.prods[p0]
    }

    override fun getItemId(p0: Int) : Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?) : View {
        // bind dengan layout shopping cart entry
        var view: View? = p1
        if(view == null) {
            this.shoppingCartEntryBinding = ShoppingCartEntryBinding.inflate(this.activity.layoutInflater)
            view = this.shoppingCartEntryBinding.root
            view.setTag(this.shoppingCartEntryBinding)
        } else {
            this.shoppingCartEntryBinding = view.tag as ShoppingCartEntryBinding
        }

        // take current prod & show the details to layout
        var currproduct: ProductDetails = this.getItem(p0)
        this.updateLayout(currproduct)

        return this.shoppingCartEntryBinding.root
    }

    // format integer to rupiah
    fun convertInt(price: Int) : String {
        val localeID: Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    // show prods details to layout
    fun updateLayout(currProduct: ProductDetails) {
        this.shoppingCartEntryBinding.productName.text = currProduct.getNama()
        this.shoppingCartEntryBinding.productCategory.text = ""+currProduct.getKategori()
        this.shoppingCartEntryBinding.productCondition.text = ""+currProduct.getKondisi()+"%"
        this.shoppingCartEntryBinding.productPrice.text = this.convertInt(currProduct.getHarga())

        this.shoppingCartEntryBinding.checkbox.setOnClickListener(this::onClick)
        this.shoppingCartEntryBinding.min.setOnClickListener(this::onClick)
        this.shoppingCartEntryBinding.plus.setOnClickListener(this::onClick)
        this.shoppingCartEntryBinding.trashBtn.setOnClickListener(this::onClick)
    }

    override fun onClick(view: View?) {
        if (view == this.shoppingCartEntryBinding.checkbox) {

        } else if (view == this.shoppingCartEntryBinding.min) {

        } else if (view == this.shoppingCartEntryBinding.plus) {

        } else if (view == this.shoppingCartEntryBinding.trashBtn) {

        }
    }

    // terima updated product list dari fragment
    fun updateList(products: ArrayList<ProductDetails>) {
        this.prods = ArrayList()
        this.prods.addAll(products)
        notifyDataSetChanged()
    }
}