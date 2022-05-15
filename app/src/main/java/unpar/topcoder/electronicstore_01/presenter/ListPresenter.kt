package unpar.topcoder.electronicstore_01.presenter

import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.IList

class ListPresenter(private val ui : IList) {
    private var products : ArrayList<ProductDetails> = ArrayList()

    //load & update produk u/ disalurkan ke UI (fragment)
    fun updateList(offset : Int) {
        var dataOffset = offset
        val prods : ArrayList<ProductDetails> = ArrayList()
        if(dataOffset < AllProducts.products.size) {
            for(i in dataOffset..(dataOffset + 4)) {
                if(i < AllProducts.products.size) {
                    if(i == dataOffset + 4){
                        dataOffset = i + 1
                    }
                    prods.add(AllProducts.products[i])
                }else {
                    dataOffset = i
                    break
                }
            }
            this.products.addAll(prods)
            this.ui.updateList(this.products, dataOffset)
        }
    }

    //send current product details to list Fragment -> DetailFragment
    fun goDetailsPage(currentProd : ProductDetails){
        this.ui.moveToDetailsPage(currentProd)
    }
}