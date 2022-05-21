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
        if(dataOffset < AllProducts.products.size && offset != -1) {
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
        Log.d("currProd",currentProd.getNama())
        this.ui.moveToDetailsPage(currentProd)
    }

    //change category
    fun changeCategory (selectedCategory : Int, dataOffset : Int) {
        this.prods.clear()
        //all
        if (selectedCategory == -1) {
            this.prods.addAll(AllProducts.products)
        } else {
            val result = ArrayList<ProductDetails>()
            val size = AllProducts.products.size
            var selection: String = ""

            // Smartphone
            if (selectedCategory == 0) {
                selection = ProductCode.SMARTPHONE
                // Tablet
            } else if (selectedCategory == 1) {
                selection = ProductCode.TABLET
                // Watches
            } else if (selectedCategory == 2) {
                selection = ProductCode.WATCHES
                // Galaxy buds
            } else if (selectedCategory == 3) {
                selection = ProductCode.GALAXYBUDS
            }

            //loop ambil data sesuai kategori
            for (i in 0..(size - 1)) {
                if (AllProducts.products[i].getKategori() == selection) {
                    result.add(AllProducts.products[i])
                }
            }
            this.prods.addAll(result)
        }
        this.ui.updateGrid(this.prods, dataOffset)
    }

    //filter nama produk
    fun search (keyword : String) {
        val result = ArrayList<ProductDetails>()
        val size = AllProducts.products.size

        for (i in 0..(size - 1)) {
            if (AllProducts.products[i].getNama().contains(keyword, ignoreCase = true)) {
                result.add(AllProducts.products[i])
            }
        }
        this.ui.updateGrid(result, -1)
    }
}