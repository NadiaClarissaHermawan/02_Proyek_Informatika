package unpar.topcoder.electronicstore_01.model

//model including constructor
class ProductDetails (private var nama : String, private var kondisi : Int, private var kategori : String, private var harga : Int, private var imageSource:Int) {
    fun getNama () : String {
        return this.nama
    }

    fun getKondisi() : Int{
        return this.kondisi
    }

    fun getHarga() : Int {
        return this.harga
    }

    fun getKategori() : String {
        return this.kategori
    }

    fun getImageSource() : Int {
        return this.imageSource
    }
}
