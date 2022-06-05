package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ProductDetails

interface IList {
    fun updateList(products: ArrayList<ProductDetails>, newDataOffset: Int)
    fun moveToDetailsPage(currentProd: ProductDetails)
}