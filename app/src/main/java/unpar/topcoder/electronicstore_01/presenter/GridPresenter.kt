package unpar.topcoder.electronicstore_01.presenter

import android.util.Log
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductCode
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

    fun goDetailsPage(currentProd : ProductDetails){
        this.ui.moveToDetailsPage(currentProd)
    }

    //change category & filter sync
    fun changeCategoryFilter (selectedCategory : Int, dataOffset : Int, filter : String) {
        val result = ArrayList<ProductDetails>()
        val size = AllProducts.products.size

        //all
        if (selectedCategory == -1) {
            for (i in 0..dataOffset-1) {
                if (i < size &&  AllProducts.products[i].getNama().contains(filter, ignoreCase = true)) result.add(AllProducts.products[i])
                else if (i > size) break
            }
        } else {
            var selection: String = ""

            // Smartphone
            if (selectedCategory == 0) selection = ProductCode.SMARTPHONE
            // Tablet
            else if (selectedCategory == 1) selection = ProductCode.TABLET
            // Watches
            else if (selectedCategory == 2) selection = ProductCode.WATCHES
            // Galaxy buds
            else if (selectedCategory == 3) selection = ProductCode.GALAXYBUDS

            //loop ambil data sesuai kategori
            for (i in 0..dataOffset-1) {
                if (i < size && AllProducts.products[i].getKategori() == selection && AllProducts.products[i].getNama().contains(filter, ignoreCase = true)) {
                    result.add(AllProducts.products[i])
                } else if (i > size) break
            }
        }
        this.prods.clear()
        this.prods.addAll(result)
        this.ui.updateGrid(this.prods, dataOffset)
    }
}