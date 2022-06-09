package unpar.topcoder.electronicstore_01.presenter

import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.view.IAddressManagement

class AddressManagementPresenter(private var ui: IAddressManagement) {
    private var addresses: ArrayList<Address> = ArrayList()
    private lateinit var defaultAddress: Address

    // lanjutkan array of address ke fragment -> adapter
    fun updateToAdapter() {
        this.ui.updateAddress(this.addresses)
    }

    // validate address input
    fun validate(address: Address) : Boolean {
        if (address.getNama() != "" && address.getNama().length > 0) {
            if (address.getNoHp() != "" && address.getNoHp().length >= 7) {
                if (address.getAlamat() != "" && address.getAlamat().length > 0) {
                    return true
                }
            }
        }
        return false
    }

    //set address as default
    fun setDefault(address: Address) {
        var size = this.addresses.size
        for (i in 0..(size - 1)) {
            if (this.addresses[i].getIsDefault() == 1) {
                this.addresses[i].setIsDefault(0)
            }
            if (this.addresses[i].equals(address)) {
                this.addresses[i].setIsDefault(1)
            }
        }
        this.defaultAddress = address
        this.updateToAdapter()
    }

    // add new address
    fun addAddress(address: Address) {
        this.addresses.add(address)
        if (this.addresses.size == 1) {
            this.setDefault(address)
        }
        this.ui.updateAddress(this.addresses)
    }

    //edit address details
    fun editAddress(newAddress: Address, oldAddress: Address) {
        var size = this.addresses.size
        for (i in 0..(size - 1)) {
            if (this.addresses[i].equals(oldAddress)) {
                this.addresses[i].setNama(newAddress.getNama())
                this.addresses[i].setAlamat(newAddress.getAlamat())
                this.addresses[i].setNoHp(newAddress.getNoHp())
                break
            }
        }
        this.updateToAdapter()
    }

    // delete selected address
    fun delete(address: Address) {
        this.addresses.remove(address)
        if (this.addresses.size == 1) {
            this.setDefault(this.addresses[0])
        }
        this.updateToAdapter()
    }

    //return jumlah address yg ada
    fun getSize() : Int {
        return this.addresses.size
    }

    // teruskan address yg ingin diedit ke fragment
    fun callEditPopup(address: Address) {
        this.ui.editAddress(address)
    }

    // return current default address
    fun getDefaultAddress() : Address {
        return this.defaultAddress
    }
}