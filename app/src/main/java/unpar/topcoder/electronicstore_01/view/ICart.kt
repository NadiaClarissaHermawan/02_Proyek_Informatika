package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ProductDetails

interface ICart {
    fun updateCartList(products: ArrayList<ProductDetails>, newDataOffset: Int)
}