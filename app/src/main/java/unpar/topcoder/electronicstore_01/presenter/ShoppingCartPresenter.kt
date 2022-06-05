package unpar.topcoder.electronicstore_01.presenter

import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.ICart

class ShoppingCartPresenter(private val ui: ICart) {
    private var products: ArrayList<ProductDetails> = ArrayList()

}