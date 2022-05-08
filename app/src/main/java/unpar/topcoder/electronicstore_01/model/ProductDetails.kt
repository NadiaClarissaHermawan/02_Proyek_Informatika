package unpar.topcoder.electronicstore_01.model

//model including constructor
class ProductDetails (private var nama : String, private var kondisi : Int, private var kategori : String, private var harga : String, private var imageSource:String) {
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

    fun getImageSource() : String {
        return this.imageSource
    }
}
