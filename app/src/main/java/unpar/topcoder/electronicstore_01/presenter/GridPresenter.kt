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
//    private var currProds : ArrayList<ProductDetails> = ArrayList() // array berisi produk yang mau ditampilkan (akan ditampilkan per 4 prod)
//    //pointer kiri dan kanan untuk prods
//    private var leftCounter=0
//    private var rightCounter=3
//    private var endOfList= false // boolean biar kalau produk sudah ditampilkan semua ga bakal ke update

//    //function untuk initial add produk
//    fun loadProducts(prods : ArrayList<ProductDetails>) {
//        this.prods.addAll(prods)
//        for(i in this.leftCounter..this.rightCounter){
//            var tempProduct=prods.get(i)
//            this.currProds.add(tempProduct)
//        }
//
//        this.ui.updateGrid(this.currProds)
//    }
//
//    //function saat tombol add more dipencet
//    fun addProducts(){
//        val sizeProds=this.prods.size
//        //jika masih ada produk yg bisa ditampilin
//        if(rightCounter+4<sizeProds){
//            Log.d("testAddButton","size lebih kecil")
//            this.leftCounter+=4
//            this.rightCounter+=4
//
//            updateCurrProds()
//        }
//        else{
//            Log.d("testAddButton","size lebih besar")
//            //kalau belum semua produk ditampilkan tapi udah diujung banget
//            if(this.endOfList==false){
//                this.leftCounter=this.rightCounter+1
//                this.rightCounter=sizeProds-1
//                this.endOfList=true
//
//                updateCurrProds()
//            }
//        }
//
//    }
//
//    //function nambahin produk
//    fun updateCurrProds(){
//        Log.d("testAddButton",this.leftCounter.toString())
//        Log.d("testAddButton",this.rightCounter.toString())
//        this.currProds.clear() //bersihin currProd
//
//        //masukin 4 barang baru ke currProds sehingga isi currProds jadi 4 produk yg mau ditambah
//        for(i in this.leftCounter..this.rightCounter){
//            var tempProduct=prods.get(i)
//            this.currProds.add(tempProduct)
//        }
//
//        this.ui.updateGrid(this.currProds)
//    }

}