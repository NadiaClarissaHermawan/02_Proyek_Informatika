package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
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

        presenter.loadData()
        return this.chooseAddressBinding.root
    }

    // singleton
    companion object {
        fun getInstance() : AddressManagementFragment {
            val instance = AddressManagementFragment()
            return instance
        }
    }

    override fun updateAddress(address: ArrayList<Address>) {
        this.adapter.update(address)
    }

    override fun onClick(view: View?) {
        if (view == this.chooseAddressBinding.floatingActionButton) {
            TODO("Not yet implemented")
        } else if (view == this.chooseAddressBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.CHECK_OUT_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
        }
    }
}