package unpar.topcoder.electronicstore_01.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.ChooseAddressFragmentBinding
import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.presenter.AddressManagementPresenter

class AddressManagementFragment :
    Fragment(),
    View.OnClickListener,
    IAddressManagement
{
    private lateinit var chooseAddressBinding: ChooseAddressFragmentBinding
    private lateinit var presenter: AddressManagementPresenter
    private lateinit var adapter: AddressManagementAdapter
    private lateinit var currAddress: Address

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // binding
        this.chooseAddressBinding = ChooseAddressFragmentBinding.inflate(inflater, container, false)

        // presenter, adapter & binding
        this.presenter = AddressManagementPresenter(this)
        this.adapter = AddressManagementAdapter(requireActivity(), this.presenter)
        this.chooseAddressBinding.listAddress.adapter = this.adapter

        // click listener
        this.chooseAddressBinding.floatingActionButton.setOnClickListener(this::onClick)
        this.chooseAddressBinding.back.setOnClickListener(this::onClick)

        // initialize first batch address data
        this.presenter.updateToAdapter()

        return this.chooseAddressBinding.root
    }

    // singleton
    companion object {
        fun getInstance() : AddressManagementFragment {
            val instance = AddressManagementFragment()
            return instance
        }
    }

    // salurkan array hasil operasi ke adapter untuk di show
    override fun updateAddress(addresses: ArrayList<Address>) {
        this.adapter.update(addresses)
    }

    // tampilkan popup edit address
    override fun editAddress(address: Address) {
        this.currAddress = address
        this.setupPopupAddress(1)
    }

    // click listener action
    override fun onClick(view: View?) {
        if (view == this.chooseAddressBinding.floatingActionButton) {
            this.setupPopupAddress(0)
        } else if (view == this.chooseAddressBinding.back) {
            var currAddress = Bundle()
            if (this.presenter.getSize() > 0) {
                currAddress.putParcelable("address", Parcels.wrap(this.presenter.getDefaultAddress()))
            } else {
                currAddress.putParcelable(
                    "address",
                    Parcels.wrap(Address("", "Please kindly add the shipment address..","",0))
                )
            }
            parentFragmentManager.setFragmentResult(Page.RECEIVE_DEFAULT_ADDRESS, currAddress)

            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.CHECK_OUT_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
        }
    }

    //setup popup
    fun setupPopupAddress(code: Int) {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.address_edit_popup, null)

        if (code == 1) {
            view.findViewById<EditText>(R.id.customer_edit_address).setText(this.currAddress.getAlamat())
            view.findViewById<EditText>(R.id.customer_edit_name).setText(this.currAddress.getNama())
            view.findViewById<EditText>(R.id.customer_edit_contact).setText(this.currAddress.getNoHp())
        }
        builder.setView(view)
            .setPositiveButton("Save",
                DialogInterface.OnClickListener { dialog, id ->
                    // add address
                    val address = view.findViewById<EditText>(R.id.customer_edit_address).text.toString()
                    val name = view.findViewById<EditText>(R.id.customer_edit_name).text.toString()
                    val phone = view.findViewById<EditText>(R.id.customer_edit_contact).text.toString()
                    val res = Address(name, phone, address, 0)
                    if (this.presenter.validate(res)) {
                        if (code == 0)  this.presenter.addAddress(res)
                        else this.presenter.editAddress(res, this.currAddress)

                        if (view.findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.switch_default_btn).isChecked) {
                            if (code == 0) this.presenter.setDefault(res)
                            else this.presenter.setDefault(this.currAddress)
                        }
                        dialog.dismiss()
                    } else {
                        Snackbar.make(
                            this.chooseAddressBinding.root,
                            "Please fill the form.",
                            Snackbar.LENGTH_SHORT).setBackgroundTint(
                                getResources().getColor(R.color.gray)
                                ).setTextColor(getResources().getColor(R.color.dark_blue)).show()
                    }
                })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
        builder.show()
    }
}