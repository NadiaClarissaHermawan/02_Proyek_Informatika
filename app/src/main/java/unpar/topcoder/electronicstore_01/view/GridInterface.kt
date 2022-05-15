package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.ProductDetails

interface GridInterface {
    fun updateGrid(prods : ArrayList<ProductDetails>, newDataOffset : Int)
    fun moveToDetailsPage(currentProd : ProductDetails)
}