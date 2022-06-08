package unpar.topcoder.electronicstore_01.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.CheckOutFragmentBinding
import unpar.topcoder.electronicstore_01.model.Address
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.presenter.CheckoutPresenter
import unpar.topcoder.electronicstore_01.presenter.ListPresenter

class CheckoutFragment : Fragment(), ICheckout, View.OnClickListener {
    private lateinit var checkOutBinding: CheckOutFragmentBinding
    private lateinit var presenter: CheckoutPresenter
    private lateinit var adapter: CheckoutAdapter

    // constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        //bind layout
        this.checkOutBinding = CheckOutFragmentBinding.inflate(inflater, container, false)

        //set click listener
        this.checkOutBinding.back.setOnClickListener(this::onClick)
        this.checkOutBinding.chooseAddressBtn.setOnClickListener(this::onClick)
        this.checkOutBinding.payBtn.setOnClickListener(this::onClick)

        // buat, set adapter & presenter untuk tampilkan & operasikan list
        this.presenter = CheckoutPresenter(this)
        this.adapter = CheckoutAdapter(requireActivity(), this.presenter)
        this.checkOutBinding.listItem.adapter = this.adapter

        // listener dari shopping cart fragment u/ terima checked products
        parentFragmentManager.setFragmentResultListener(Page.CHANGE_TO_CHECKOUT_LISTENER, this) {
                _, result ->
            this.presenter.initialProds(Parcels.unwrap<Any>(result.getParcelable("checkedProds")) as ArrayList<ShoppingCartItem>)
        }

        // listener untuk terima default address
        parentFragmentManager.setFragmentResultListener(Page.RECEIVE_DEFAULT_ADDRESS, this) {
                _, result ->
            var defaultAddress = Parcels.unwrap<Any>(result.getParcelable("address")) as Address
            this.checkOutBinding.customerName.text = defaultAddress.getNama()
            this.checkOutBinding.customerContact.text = defaultAddress.getNoHp()
            this.checkOutBinding.customerAddress.text = defaultAddress.getAlamat()
        }

        return this.checkOutBinding.root
    }

    // singleton constructor
    companion object {
        fun getInstance () : CheckoutFragment {
            val instance = CheckoutFragment()
            return instance
        }
    }

    // click listener
    override fun onClick(view: View?) {
        // back button
        if (view == this.checkOutBinding.back) {
            this.moveToPreviousFragment()
        // pay
        } else if (view == this.checkOutBinding.payBtn) {
            if (this.checkOutBinding.customerName.text.toString().equals("") || this.checkOutBinding.customerName.text.toString().length == 0) {
                Snackbar.make(this.checkOutBinding.root, "Select the shipment address", Snackbar.LENGTH_SHORT).show()
            } else {
                this.paymentOK()
            }
        // choose address
        } else if (view == this.checkOutBinding.chooseAddressBtn) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.ADDRESS_MANAGEMENT_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
        }
    }

    // salurkan list hasil operasi ke adapter
    override fun updateCheckoutList(product: ArrayList<ShoppingCartItem>) {
        this.adapter.updateList(product)
    }

    // updating total price after any operations
    override fun updateTotalPrice(total: Int) {
        this.checkOutBinding.totalPrice.text = this.presenter.convertInt(total)
    }

    // check out cart is empty, move to shopping cart fragment
    override fun moveToPreviousFragment() {
        var pg = Bundle()
        pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

        var up = Bundle()
        parentFragmentManager.setFragmentResult(Page.UPDATE_FROM_CHECK_OUT, up)
    }

    // munculin popup payment berhasil & balik ke laman shopping cart
    fun paymentOK() {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.payment_succeed_dialog, null)

        builder.setView(view)
            .setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, id ->
                    var products = Bundle()
                    products.putParcelable("paidProducts", Parcels.wrap(this.presenter.getProducts()))
                    parentFragmentManager.setFragmentResult(Page.DELETE_PAID_PRODUCTS, products)

                    var pg = Bundle()
                    pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
                    parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
                }
            )
        builder.show()
    }
}