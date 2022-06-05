package unpar.topcoder.electronicstore_01.presenter

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

    //add or increase selected product quantity
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
            var shoppingCartItem = ShoppingCartItem(product, 1)
            this.products.add(shoppingCartItem)
        }
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

    //delete item from shopping cart
    fun delete(product: ShoppingCartItem) {
        this.products.remove(product)
        this.updateToAdapter()
    }

    //add item to checked
    fun check(product: ShoppingCartItem) {
        if (this.checked.contains(product)) {
            this.checked.remove(product)
        } else {
            this.checked.add(product)
        }
        this.calculatePrice()
    }

    //hitung ulang total harga dari yg di check
    fun calculatePrice() {
        var size: Int = this.checked.size
        var total: Int = 0
        for (i in 0..(size - 1)) {
            total = total + (this.checked[i].getQuantity() * this.checked[i].getProduct().getHarga())
        }
        this.ui.updateTotalPrice(total)
    }

    //clear the checked shopping cart item
    fun clearChecked() {
        this.checked.clear()
    }
}