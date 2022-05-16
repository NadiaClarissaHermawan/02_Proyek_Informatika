package unpar.topcoder.electronicstore_01.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//model including constructor
@Parcelize
class ProductDetails (private var nama : String, private var kondisi : Int, private var kategori : String, private var harga : String, private var imageSource:Int, private var imageSource2:Int, private var imageSource3:Int) : Parcelable {
    fun getNama () : String {
        return this.nama
    }

    fun getKondisi() : Int{
        return this.kondisi
    }

    fun getHarga() : String {
        return this.harga
    }

    fun getKategori() : String {
        return this.kategori
    }

    fun getImageSource() : Int {
        return this.imageSource
    }

    fun getImageSource2() : Int {
        return this.imageSource2
    }

    fun getImageSource3() : Int {
        return this.imageSource3
    }

}
