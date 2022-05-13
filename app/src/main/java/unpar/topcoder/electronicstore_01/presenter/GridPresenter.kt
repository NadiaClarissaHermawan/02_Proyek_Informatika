package unpar.topcoder.electronicstore_01.presenter

import android.util.Log
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.GridInterface

class GridPresenter (private var ui:GridInterface){
    private var prods : ArrayList<ProductDetails> = ArrayList() //array berisi semua produk di database

    //load data from AllProducts
    fun updateGrid(offset : Int, target: Int) {
        var dataOffset = offset
        val prods : ArrayList<ProductDetails> = ArrayList()
        if(dataOffset < AllProducts.products.size) {
            for(i in dataOffset..target) {
                if(i < AllProducts.products.size) {
                    prods.add(AllProducts.products[i])
                }else {
                    break
                }
            }
            this.prods.addAll(prods)
            this.ui.updateGrid(this.prods, target+1)
        }
    }
}