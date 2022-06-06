package unpar.topcoder.electronicstore_01.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(private var nama: String,
              private var no_hp:String,
              private var alamat: String,
              private var isDefault: Boolean
) : Parcelable {
    fun getNama() : String{
        return this.nama;
    }

    fun getNoHp() : String{
        return this.no_hp;
    }

    fun getAlamat() : String{
        return this.alamat;
    }

    fun getIsDefault() : Boolean{
        return this.isDefault;
    }

    fun setNama(nama: String) {
        this.nama = nama
    }

    fun setNoHp(no_hp: String) {
        this.no_hp = no_hp
    }

    fun setAlamat(alamat: String) {
        this.alamat = alamat
    }

    fun setIsDefault(isDefault: Boolean) {
        this.isDefault = isDefault
    }
}