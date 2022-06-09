package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ChooseAddressEntryBinding
import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.presenter.AddressManagementPresenter

class AddressManagementAdapter (
    private var activity: Activity,
    private var presenter: AddressManagementPresenter

) : BaseAdapter() {
    private  lateinit var chooseAddressEntryBinding: ChooseAddressEntryBinding
    private var address: MutableList<Address> = ArrayList()
    private lateinit var currentAddress: Address

    override fun getCount(): Int {
        return this.address.size
    }

    override fun getItem(position: Int): Address {
        return this.address[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        if (convertView == null) {
            this.chooseAddressEntryBinding =
                ChooseAddressEntryBinding.inflate(this.activity.layoutInflater)
            convertView = this.chooseAddressEntryBinding.root
            convertView.setTag(this.chooseAddressEntryBinding)
        } else {
            this.chooseAddressEntryBinding = convertView.tag as ChooseAddressEntryBinding
        }

        //get the current item
        this.currentAddress = this.getItem(position)
        this.updateLayout(this.currentAddress)

        return this.chooseAddressEntryBinding.root
    }

    // function update layout (buat recycle)
    fun updateLayout(currentAddress: Address) {
        this.chooseAddressEntryBinding.customerAddress.text = this.currentAddress.getAlamat()
        this.chooseAddressEntryBinding.customerContact.text = this.currentAddress.getNoHp()
        this.chooseAddressEntryBinding.customerName.text = this.currentAddress.getNama()

        if (this.currentAddress.getIsDefault() == 1) {
            this.chooseAddressEntryBinding.defaultSign.visibility = View.VISIBLE
        } else {
            this.chooseAddressEntryBinding.defaultSign.visibility = View.INVISIBLE
        }

        this.chooseAddressEntryBinding.editBtn.setOnClickListener {
            this.presenter.callEditPopup(currentAddress)
        }
        this.chooseAddressEntryBinding.trashBtn.setOnClickListener {
            this.presenter.delete(currentAddress)
        }

    }

    fun update(address: ArrayList<Address>) {
        this.address = ArrayList()
        this.address.addAll(address)
        this.notifyDataSetChanged()
    }
}