package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.ChooseAddressFragmentBinding
import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.presenter.AddressManagementPresenter

class AddressManagementFragment :
    Fragment(),
    View.OnClickListener,
    IAddressManagement
{

    private lateinit var chooseAddressBinding: ChooseAddressFragmentBinding
    private lateinit var presenter: AddressManagementPresenter
    private lateinit var adapter: AddressManagementAdapter

    override fun onClick(v: View?) {
        if (v == this.chooseAddressBinding.floatingActionButton) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        this.chooseAddressBinding = ChooseAddressFragmentBinding.inflate(inflater, container, false)
        this.presenter = AddressManagementPresenter(this)
        this.adapter = AddressManagementAdapter(requireActivity(), this.presenter)
        this.chooseAddressBinding.listAddress.adapter = this.adapter

        this.chooseAddressBinding.floatingActionButton.setOnClickListener(this::onClick)

        presenter.loadData()
        return this.chooseAddressBinding.root
    }

    override fun updateAddress(address: ArrayList<Address>) {
        this.adapter.update(address)
    }
    // singleton
    companion object {
        fun getInstance() : AddressManagementFragment {
            val instance = AddressManagementFragment()
            return instance
        }
    }


}