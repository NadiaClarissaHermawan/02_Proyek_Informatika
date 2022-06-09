package unpar.topcoder.electronicstore_01.presenter

import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.view.ICheckout


class CheckoutPresenter(private val ui: ICheckout) {
    private var products: ArrayList<ShoppingCartItem> = ArrayList()

    // kirim list ke fragment -> adapter u/ dishow
    fun updateToAdapter() {
        this.ui.updateCheckoutList(this.products)
    }

    // calculate the total price
    fun calculatePrice() {
        var size = this.products.size
        var total = 0
        for (i in 0..(size - 1)) {
            total = total + (this.products[i].getQuantity() * this.products[i].getProduct().getHarga())
        }
        this.ui.updateTotalPrice(total)
    }

    //add checked products from shopping cart
    fun initialProds(prods: ArrayList<ShoppingCartItem>) {
        this.products = ArrayList()
        this.products.addAll(prods)
        this.calculatePrice()
        this.updateToAdapter()
    }

    // increase or decrease selected product quantity
    fun operate(operations: Int, product: ShoppingCartItem) {
        var size = this.products.size

        for (i in 0..(size - 1)) {
            if (this.products[i].equals(product)) {
                if (operations == 0) {
                    this.products[i].setQuantity(this.products[i].getQuantity() + 1)
                } else {
                    this.products[i].setQuantity(this.products[i].getQuantity() - 1)
                }
                break
            }
        }
        this.calculatePrice()
        this.updateToAdapter()
    }

    // format integer to rupiah
    fun convertInt(price: Int) : String {
        val localeID: Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    // return the products that have been bought
    fun getProducts() : ArrayList<ShoppingCartItem> {
        return this.products
    }
}