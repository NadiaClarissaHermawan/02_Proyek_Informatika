package unpar.topcoder.electronicstore_01.presenter

import android.util.Log
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.GridInterface

class GridPresenter (private var ui:GridInterface){
    private var prods : ArrayList<ProductDetails> = ArrayList() //array berisi semua produk di database


    fun updateGrid(offset : Int) {
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
                    dataOffset = i + 1
                    break
                }
            }
            this.prods.addAll(prods)
            this.ui.updateGrid(this.prods, dataOffset)
        }
    }

    fun goDetailsPage(currentProd : ProductDetails){
        this.ui.moveToDetailsPage(currentProd)
    }

}