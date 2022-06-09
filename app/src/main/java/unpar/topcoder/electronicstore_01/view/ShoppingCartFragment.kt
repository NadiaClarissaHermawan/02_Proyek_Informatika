package unpar.topcoder.electronicstore_01.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlin.collections.ArrayList
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.databinding.ShoppingCartFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.model.ShoppingCartItem
import unpar.topcoder.electronicstore_01.presenter.ShoppingCartPresenter
import unpar.topcoder.electronicstore_01.R

class ShoppingCartFragment : Fragment(), ICart, View.OnClickListener {
    private lateinit var shoppingCartBinding: ShoppingCartFragmentBinding
    private lateinit var presenter: ShoppingCartPresenter
    private lateinit var adapter: ShoppingCartAdapter
    private var layout: Int = Page.LIST_PAGE

    // constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // bind layout
        this.shoppingCartBinding = ShoppingCartFragmentBinding.inflate(inflater, container, false)

        // buat, set adapter & presenter untuk tampilkan & operasikan list
        this.presenter = ShoppingCartPresenter(this)
        this.adapter = ShoppingCartAdapter(requireActivity(), this.presenter)
        this.shoppingCartBinding.listItem.adapter = this.adapter

        // set click listener
        this.shoppingCartBinding.back.setOnClickListener(this::onClick)
        this.shoppingCartBinding.checkoutBtn.setOnClickListener(this::onClick)

        // listener dari list, grid & detail layout untuk catat target page 'back' button
        parentFragmentManager.setFragmentResultListener(Page.CHANGE_TO_SHOPPING_CART_LISTENER, this) {
            _, result ->
            this.layout = result.getInt("layout")
        }

        // listener add button dari detailFragment
        parentFragmentManager.setFragmentResultListener(Page.ADD_TO_SHOPPING_CART, this) {
            _, result ->
            var product = Parcels.unwrap<Any>(result.getParcelable("product")) as ProductDetails
            this.addProduct(product)
        }

        // listener update checked
        parentFragmentManager.setFragmentResultListener(Page.UPDATE_FROM_CHECK_OUT, this) {
                _, result ->
            this.presenter.updateToAdapter()
            this.presenter.calculatePrice()
        }

        // listener dari confirm payment, delete products yg sdh di bayar
        parentFragmentManager.setFragmentResultListener(Page.DELETE_PAID_PRODUCTS, this) {
                _, result ->
            var products = Parcels.unwrap<Any>(result.getParcelable("paidProducts")) as ArrayList<ShoppingCartItem>
            this.presenter.deletePaidProds(products)
        }

        return this.shoppingCartBinding.root
    }

    //click listener action
    override fun onClick(view: View?) {
        // kembali ke page sblmnya
        if (view == this.shoppingCartBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, this.layout)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

        // ganti ke halaman konfirmasi pembayaran
        } else if (view == this.shoppingCartBinding.checkoutBtn) {
            if (this.presenter.getCheckedSize() > 0) {
                var pg = Bundle()
                pg.putInt(Page.PAGE, Page.CHECK_OUT_PAGE)
                parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER,pg)

                var checkout = Bundle()
                checkout.putParcelable("checkedProds", Parcels.wrap(this.presenter.getChecked()))
                parentFragmentManager.setFragmentResult(Page.CHANGE_TO_CHECKOUT_LISTENER, checkout)
            } else {
                Snackbar.make(
                    this.shoppingCartBinding.root,
                    "Please check any product to continue",
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(
                    getResources().getColor(R.color.white)
                ).setTextColor(getResources().getColor(R.color.dark_blue)).show()
            }
        }
    }

    // salurkan list of shopping cart item dari presenter ke adapter
    override fun updateCartList(products: ArrayList<ShoppingCartItem>) {
        this.adapter.updateList(products)
    }

    // update total harga dari checked items
    override fun updateTotalPrice(total: Int) {
        this.shoppingCartBinding.totalPrice.text = this.presenter.convertInt(total)
    }

    // singleton constructor
    companion object {
        fun getInstance () : ShoppingCartFragment {
            val instance = ShoppingCartFragment()
            return instance
        }
    }

    // nambahin produk ke shopping cart
    fun addProduct(product: ProductDetails) {
        this.presenter.add(product)
    }
}