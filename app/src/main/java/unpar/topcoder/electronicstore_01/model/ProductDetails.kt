package unpar.topcoder.electronicstore_01.model

//model including constructor
class ProductDetails (private var nama : String, private var kondisi : Integer, private var kategori : String, private var harga : Integer) {
    fun getNama () : String {
        return this.nama
    }

    fun getKondisi() : Integer{
        return this.kondisi
    }

    fun getHarga() : Integer {
        return this.harga
    }

    fun getKategori() : String {
        return this.kategori
    }
}
