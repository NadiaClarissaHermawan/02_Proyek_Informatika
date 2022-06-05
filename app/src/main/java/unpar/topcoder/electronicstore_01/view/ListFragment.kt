package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import org.parceler.Parcels
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.ProductListFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.ListPresenter

// fragment
class ListFragment : Fragment(), IList, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var listBinding: ProductListFragmentBinding
    private lateinit var presenter: ListPresenter
    private lateinit var adapter: ListAdapter
    private var dataOffset: Int = 0
    private var sortByName: Int = 0
    private var sortByPrice: Int = 0
    private var sortByCondition: Int = 0

    // constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // bind layout
        this.listBinding = ProductListFragmentBinding.inflate(inflater, container, false)

        // buat, set adapter & presenter untuk tampilkan & operasikan list
        this.presenter = ListPresenter(this)
        this.adapter = ListAdapter(requireActivity(), this.presenter)
        this.listBinding.lstProducts.adapter = this.adapter

        // setup change category spinner
        this.setupSpinner()

        // setup text filter listener
        this.setupFilter()

        // set click listener
        this.listBinding.layoutTypeGrid.setOnClickListener(this::onClick)
        this.listBinding.buttonLoadMore.setOnClickListener(this::onClick)
        this.listBinding.productName.setOnClickListener(this::onClick)
        this.listBinding.productPrice.setOnClickListener(this::onClick)
        this.listBinding.productCondition.setOnClickListener(this::onClick)
        this.listBinding.cart1.setOnClickListener(this::onClick)

        // initial products
        this.callUpdateList(this.dataOffset, this.dataOffset + 4)

        return this.listBinding.root
    }

    // salurkan updated product list dari presenter ke adapter untuk ditampilkan
    override fun updateList(products: ArrayList<ProductDetails>, newDataOffset: Int) {
        this.adapter.updateList(products)
        if (newDataOffset != -1) {
            this.dataOffset = newDataOffset
        }
    }

    // load & update product list, then sync with filters
    private fun callUpdateList(offset: Int, target: Int) {
        this.presenter.updateList(offset, target)
        val keyword = listBinding.searchBar.text.toString()
        val category = listBinding.dropdownCategory.selectedItemPosition
        this.presenter.changeCategoryFilter(category - 1, target + 1, keyword)
    }

    // onclick listener method
    override fun onClick(view: View?) {
        // ganti ke grid frag & sync product
        if (view == this.listBinding.layoutTypeGrid) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.GRID_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

            var target = Bundle()
            target.putInt("target", this.dataOffset)
            target.putInt("category", this.listBinding.dropdownCategory.selectedItemPosition)
            target.putString("keyword", this.listBinding.searchBar.text.toString())
            parentFragmentManager.setFragmentResult(Page.SYNC_LISTENER, target)

        // ganti ke shopping cart page
        } else if (view == this.listBinding.cart1) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

            var currPg = Bundle()
            currPg.putInt("layout", Page.LIST_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_TO_SHOPPING_CART_LISTENER, currPg)

        // load more
        } else if (view == this.listBinding.buttonLoadMore) {
            this.callUpdateList(this.dataOffset, this.dataOffset + 4)

        // sort product's name
        } else if (view == this.listBinding.productName) {
            if (this.sortByName == 0) {
                this.presenter.sorting(0, 0)
                this.sortByName = 1
            } else {
                this.presenter.sorting(0, 1)
                this.sortByName = 0
            }

        // sort product's price
        } else if (view == this.listBinding.productPrice) {
            if (this.sortByPrice == 0) {
                this.presenter.sorting(1, 0)
                this.sortByPrice = 1
            } else {
                this.presenter.sorting(1, 1)
                this.sortByPrice = 0
            }

        // sort product's condition
        } else if (view == this.listBinding.productCondition) {
            if (this.sortByCondition == 0) {
                this.presenter.sorting(2, 0)
                this.sortByCondition = 1
            } else {
                this.presenter.sorting(2, 1)
                this.sortByCondition = 0
            }
        }
    }

    // select listener untuk spinner
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        this.presenter.changeCategoryFilter(p2 - 1, this.dataOffset, this.listBinding.searchBar.text.toString())
    }

    // do nothing
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    // move to detailsFragment
    override fun moveToDetailsPage(currentProd: ProductDetails) {
        // kirim data clicked product
        var product = Bundle()
        product.putParcelable("product", Parcels.wrap(currentProd))
        product.putInt("layout", Page.LIST_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_TO_DETAILS_LISTENER, product)

        //change page
        var pg = Bundle()
        pg.putInt(Page.PAGE, Page.DETAIL_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
    }

    // singleton constructor
    companion object {
        fun getInstance () : ListFragment {
            val instance = ListFragment()
            return instance
        }
    }

    // setup spinner kategori
    fun setupSpinner() {
        val spinnr = this.listBinding.dropdownCategory
        val spinnerValues = arrayOf<String>("All", "Smartphone", "Tablet", "Watches", "Galaxy Buds")
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any>(requireContext(), R.layout.spinner_layout, spinnerValues)
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        spinnr.adapter = adapter
        spinnr.onItemSelectedListener = this
    }

    // setup text filter listener
    fun setupFilter() {
        this.listBinding.searchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                val keyword = listBinding.searchBar.text.toString()
                val category = listBinding.dropdownCategory.selectedItemPosition
                presenter.changeCategoryFilter(category - 1, dataOffset, keyword)
            }
        })
    }

    // sync product from grid fragment
    fun syncList(newDataOffset: Int, category: Int, keyword: String) {
        this.listBinding.dropdownCategory.setSelection(category)
        this.listBinding.searchBar.setText(keyword)
        this.callUpdateList(this.dataOffset, newDataOffset - 1)
        this.dataOffset = newDataOffset
    }
}