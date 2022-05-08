package unpar.topcoder.electronicstore_01.presenter

import android.util.Log
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.IProduct

class ListPresenter(private val ui : IProduct) {
    private var products : ArrayList<ProductDetails> = ArrayList()
    private var dataOffset : Int = 0

    //load & update produk u/ disalurkan ke UI (fragment)
    fun updateList() {
        val prods : ArrayList<ProductDetails> = ArrayList()
        for(i in this.dataOffset..(this.dataOffset + 4)) {
            if(i < AllProducts.products.size) {
                if(i == this.dataOffset + 4){
                    this.dataOffset = i + 1
                }
                prods.add(AllProducts.products[i])
            }else {
                this.dataOffset = i + 1
                break
            }
        }
        this.products.addAll(prods)
        this.ui.updateList(this.products)
    }
}