package unpar.topcoder.electronicstore_01.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//model including constructor
@Parcelize
class ProductDetails (private var nama : String, private var kondisi : Int, private var kategori : String, private var harga : String, private var imageSource:Int) : Parcelable {
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
}
