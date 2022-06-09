package unpar.topcoder.electronicstore_01.presenter

import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.view.ICart


class ShoppingCartPresenter(private val ui: ICart) {
    private var products: ArrayList<ShoppingCartItem> = ArrayList()
    private var checked: ArrayList<ShoppingCartItem> = ArrayList()

    // send updated arraylist to fragment -> adapter
    fun updateToAdapter() {
        this.ui.updateCartList(this.products)
    }

    // hitung ulang total harga dari yg di check
    fun calculatePrice() {
        var size: Int = this.checked.size
        var total: Int = 0
        for (i in 0..(size - 1)) {
            total = total + (this.checked[i].getQuantity() * this.checked[i].getProduct().getHarga())
        }
        this.ui.updateTotalPrice(total)
    }

    // add or increase selected product quantity
    fun add(product: ProductDetails) {
        var size = this.products.size
        var indicator = 0

        for (i in 0..(size - 1)) {
            if (this.products[i].getProduct().equals(product)) {
                indicator = 1
                this.products[i].setQuantity(this.products[i].getQuantity() + 1)
                break
            }
        }
        if (indicator == 0) {
            var shoppingCartItem = ShoppingCartItem(product, 1, 0)
            this.products.add(shoppingCartItem)
        }
        this.calculatePrice()
        this.updateToAdapter()
    }

    //delete item from shopping cart
    fun delete(product: ShoppingCartItem) {
        this.products.remove(product)
        this.checked.remove(product)
        this.calculatePrice()
        this.updateToAdapter()
    }

    // delete or decrease selected product quantity
    fun min(product: ShoppingCartItem) {
        var size = this.products.size

        if (product.getQuantity() == 1) {
            this.delete(product)
        } else {
            for (i in 0..(size - 1)) {
                if (this.products[i].equals(product)) {
                    this.products[i].setQuantity(this.products[i].getQuantity() - 1)
                    break
                }
            }
        }
        this.calculatePrice()
        this.updateToAdapter()
    }

    // add item to checked
    fun check(product: ShoppingCartItem) {
        var size = this.products.size
        if (this.checked.contains(product)) {
            this.checked.remove(product)
            for (i in 0..(size - 1)) {
                if (this.products[i].equals(product)) {
                    this.products[i].setCheckStatus(0)
                    break
                }
            }
        } else {
            this.checked.add(product)
            for (i in 0..(size - 1)) {
                if (this.products[i].equals(product)) {
                    this.products[i].setCheckStatus(1)
                    break
                }
            }
        }
        this.calculatePrice()
        this.updateToAdapter()
    }

    // cari tau berapa item yg akan di checkout
    fun getCheckedSize() : Int {
        return this.checked.size
    }

    // ambil seluruh produk yg di check
    fun getChecked() : ArrayList<ShoppingCartItem> {
        return this.checked
    }

    // format integer to rupiah
    fun convertInt(price: Int) : String {
        val localeID: Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    // deleting paid products
    fun deletePaidProds(paidProds: ArrayList<ShoppingCartItem>) {
        var size = paidProds.size
        for (i in 0..(size - 1)) {
            this.products.remove(paidProds[i])
            this.checked.remove(paidProds[i])
        }
        this.calculatePrice()
        this.updateToAdapter()
    }
}