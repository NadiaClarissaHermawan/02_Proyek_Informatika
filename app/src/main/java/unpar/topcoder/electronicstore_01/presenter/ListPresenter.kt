package unpar.topcoder.electronicstore_01.presenter

import android.util.Log
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.ProductCode
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.view.IList

class ListPresenter (private val ui : IList) {
    private var products : ArrayList<ProductDetails> = ArrayList()

    //load & update produk u/ disalurkan ke UI (fragment)
    fun updateList (offset : Int) {
        Log.e("DEBUG", "offset: "+offset )
        var dataOffset = offset
        val prods : ArrayList<ProductDetails> = ArrayList()
        if (dataOffset < AllProducts.products.size && offset != -1) {
            for (i in dataOffset..(dataOffset + 4)) {
                if (i < AllProducts.products.size) {
                    if (i == dataOffset + 4){
                        dataOffset = i + 1
                    }
                    prods.add(AllProducts.products[i])
                } else {
                    dataOffset = i
                    break
                }
            }
            this.products.addAll(prods)
            this.ui.updateList(this.products, dataOffset)
        } else if (offset == - 1) {
            this.ui.updateList(this.products, dataOffset)
        }
    }

    //send current product details to list Fragment -> DetailFragment
    fun goDetailsPage (currentProd : ProductDetails){
        this.ui.moveToDetailsPage(currentProd)
    }

    //change category
    fun changeCategory (selectedCategory : Int, dataOffset : Int) {
        //all
        if (selectedCategory == -1) {
            this.updateList(-1)
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
            this.ui.updateList(result, dataOffset)
        }
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
        this.ui.updateList(result, -1)
    }

    //sorting
    fun sorting (attr : Int, mode : Int) {
        //by name
        if (attr == 0) {
            if (mode == 0) {
                this.processSortingResult(this.products.sortedBy { it.getNama() })
            } else {
                this.processSortingResult(this.products.sortedByDescending { it.getNama() })
            }
        //by price
        } else if (attr == 1) {
            if (mode == 0) {
                this.processSortingResult(this.products.sortedBy { it.getHarga() })
            } else {
                this.processSortingResult(this.products.sortedByDescending { it.getHarga() })
            }
        //by conditions
        } else {
            if (mode == 0) {
                this.processSortingResult(this.products.sortedBy { it.getKondisi() })
            } else {
                this.processSortingResult(this.products.sortedByDescending { it.getKondisi() })
            }
        }
    }

    //process sort result
    fun processSortingResult (result : List<ProductDetails>) {
        this.products.clear()
        this.products.addAll(result)
        this.ui.updateList(this.products, -1)
    }
}