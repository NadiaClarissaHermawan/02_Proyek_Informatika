package unpar.topcoder.electronicstore_01.presenter

import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.view.IAddressManagement

class AddressManagementPresenter(private var ui: IAddressManagement) {
    private var address: ArrayList<Address> = ArrayList()

    fun loadData() {
        this.ui.updateAddress(this.address)

    }

    fun addAddress(address: Address) {
        this.address.add(address)
        this.ui.updateAddress(this.address)
    }

}