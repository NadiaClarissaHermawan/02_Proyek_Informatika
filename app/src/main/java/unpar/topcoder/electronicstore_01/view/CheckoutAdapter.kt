package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.CheckOutEntryBinding
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.presenter.CheckoutPresenter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutAdapter(
    private var activity: Activity,
    private var presenter: CheckoutPresenter)
    : BaseAdapter() {

    private lateinit var checkoutItemBinding : CheckOutEntryBinding
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
        // bind dengan layout checkout entry
        var view: View? = p1
        if(view == null) {
            this.checkoutItemBinding = CheckOutEntryBinding.inflate(this.activity.layoutInflater)
            view = this.checkoutItemBinding.root
            view.setTag(this.checkoutItemBinding)
        } else {
            this.checkoutItemBinding = view.tag as CheckOutEntryBinding
        }

        // take current prod & show it
        var currproduct: ShoppingCartItem = this.getItem(p0)
        this.updateLayout(currproduct)

        return this.checkoutItemBinding.root
    }

    // terima updated product list dari fragment
    fun updateList(products: ArrayList<ShoppingCartItem>) {
        this.prods = ArrayList()
        this.prods.addAll(products)
        notifyDataSetChanged()
    }

    //show selected products to layout
    fun updateLayout(currProduct: ShoppingCartItem) {
        this.checkoutItemBinding.productName.text = currProduct.getProduct().getNama()
        this.checkoutItemBinding.productPrice.text = this.presenter.convertInt(currProduct.getProduct().getHarga())
        this.checkoutItemBinding.productImage.setImageResource(currProduct.getProduct().getImageSource())
        this.checkoutItemBinding.quantity.text = currProduct.getQuantity().toString()

        this.checkoutItemBinding.min.setOnClickListener {
            this.presenter.operate(1, currProduct)
        }
        this.checkoutItemBinding.plus.setOnClickListener {
            this.presenter.operate(0, currProduct)
        }
    }
}