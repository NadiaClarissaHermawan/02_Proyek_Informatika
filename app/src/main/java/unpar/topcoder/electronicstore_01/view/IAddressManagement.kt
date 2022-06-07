package unpar.topcoder.electronicstore_01.view

import unpar.topcoder.electronicstore_01.model.Address

interface IAddressManagement {
    fun updateAddress(addresses: ArrayList<Address>)
    fun editAddress(address: Address)
}