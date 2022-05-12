package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ProductDetails

interface IProduct {

    fun updateList(products : ArrayList<ProductDetails>, newDataOffset : Int)

}