package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ShoppingCartEntryBinding
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.presenter.ShoppingCartPresenter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ShoppingCartAdapter(private var activity: Activity, private var presenter: ShoppingCartPresenter) : BaseAdapter() {
    private lateinit var shoppingCartEntryBinding: ShoppingCartEntryBinding
    private var prods: ArrayList<ShoppingCartItem> = ArrayList()

    override fun getCount() : Int {
        return this.prods.size
    }

    override fun getItem(p0: Int) : ShoppingCartItem {
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
        var currproduct: ShoppingCartItem = this.getItem(p0)
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
    fun updateLayout(currProduct: ShoppingCartItem) {
        this.shoppingCartEntryBinding.productName.text = currProduct.getProduct().getNama()
        this.shoppingCartEntryBinding.productCategory.text = ""+currProduct.getProduct().getKategori()
        this.shoppingCartEntryBinding.productCondition.text = ""+currProduct.getProduct().getKondisi()+"%"
        this.shoppingCartEntryBinding.productPrice.text = this.convertInt(currProduct.getProduct().getHarga())
        this.shoppingCartEntryBinding.productImage.setImageResource(currProduct.getProduct().getImageSource())
        this.shoppingCartEntryBinding.quantity.text = currProduct.getQuantity().toString()

        this.shoppingCartEntryBinding.checkbox.setOnClickListener {
            this.presenter.check(currProduct)
        }
        this.shoppingCartEntryBinding.min.setOnClickListener {
            this.presenter.min(currProduct)
        }
        this.shoppingCartEntryBinding.plus.setOnClickListener {
            this.presenter.add(currProduct.getProduct())
        }
        this.shoppingCartEntryBinding.trashBtn.setOnClickListener {
            this.presenter.delete(currProduct)
        }
    }

    // terima updated product list dari fragment
    fun updateList(products: ArrayList<ShoppingCartItem>) {
        this.prods = ArrayList()
        this.prods.addAll(products)
        notifyDataSetChanged()
    }
}