package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ChooseAddressEntryBinding
import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.AddressManagementPresenter

class AddressManagementAdapter (
    private var activity: Activity,
    private var presenter: AddressManagementPresenter

) : BaseAdapter(), View.OnClickListener{
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

        this.currentAddress = this.getItem(position)
        this.updateLayout(this.currentAddress)

        return this.chooseAddressEntryBinding.root
    }

    override fun onClick(v: View?) {

        if(v == this.chooseAddressEntryBinding.editBtn) {
            TODO("on click untuk edit button belum di implement")
        }
        else if (v == this.chooseAddressEntryBinding.trashBtn) {
            TODO("on click untuk delete belum di implement")
        }
    }

    // function update layout (buat recycle)
    fun updateLayout(currentAddress: Address) {
        this.chooseAddressEntryBinding.customerAddress.text = this.currentAddress.getAlamat()
        this.chooseAddressEntryBinding.customerContact.text = this.currentAddress.getNoHp()
        this.chooseAddressEntryBinding.customerName.text = this.currentAddress.getNama()
        this.chooseAddressEntryBinding.editBtn.setOnClickListener(this::onClick)
        this.chooseAddressEntryBinding.trashBtn.setOnClickListener((this::onClick))

    }

    fun update(address: ArrayList<Address>) {
        this.address = ArrayList()
        this.address.addAll(address)
        this.notifyDataSetChanged()
    }
}