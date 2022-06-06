package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ShoppingCartItem

interface ICart {
    fun updateCartList(products: ArrayList<ShoppingCartItem>)
    fun updateTotalPrice(total: Int)
}