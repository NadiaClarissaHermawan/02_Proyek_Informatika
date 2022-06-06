package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ShoppingCartItem

interface ICheckout {
    fun updateCheckoutList(product: ArrayList<ShoppingCartItem>)
    fun updateTotalPrice(total: Int)
}