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
import unpar.topcoder.electronicstore_01.databinding.ProductGridFragmentBinding
import unpar.topcoder.electronicstore_01.model.*
import unpar.topcoder.electronicstore_01.presenter.GridPresenter
import unpar.topcoder.electronicstore_01.R



class GridFragment : Fragment(), View.OnClickListener, GridInterface, AdapterView.OnItemSelectedListener {
    private lateinit var gridBinding: ProductGridFragmentBinding
    private lateinit var presenter: GridPresenter
    private lateinit var adapter: GridAdapter
    private lateinit var listFragment: ListFragment
    private var dataOffset: Int = 0

    // view constructor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        // bind
        this.gridBinding=ProductGridFragmentBinding.inflate(inflater, container, false)

        // declare presenter, adapter
        this.presenter= GridPresenter(this)
        this.adapter=GridAdapter(requireActivity(), this.presenter)
        this.gridBinding.lstProducts.adapter = this.adapter

        // setup spinner category
        this.setupSpinner()

        // setup filter
        this.setupFilter()

        // set on click listener
        this.gridBinding.layoutTypeGrid.setOnClickListener(this::onClick)
        this.gridBinding.layoutTypeList.setOnClickListener(this::onClick)
        this.gridBinding.buttonLoadMore.setOnClickListener(this::onClick)

        // add first 5 products
        presenter.updateGrid(this.dataOffset, this.dataOffset + 4)

        // sync product with list layout (listener)
        this.parentFragmentManager.setFragmentResultListener(Page.SYNC_LISTENER, this) {
                _, result ->
            this.gridBinding.dropdownCategory.setSelection(result.getInt("category"))
            this.gridBinding.searchBar.setText(result.getString("keyword").toString())
            this.callUpdateGrid(result.getInt("target"),
                result.getInt("category"), result.getString("keyword").toString())
        }

        return this.gridBinding.root
    }

    // untuk pindah page ke fragment list
    override fun onClick(view: View?) {
        // change to list fragment & sync products
        if (view == this.gridBinding.layoutTypeList) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.LIST_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
            this.listFragment.syncList(
                this.dataOffset,
                this.gridBinding.dropdownCategory.selectedItemPosition,
                this.gridBinding.searchBar.text.toString()
            )

        // ganti ke shopping cart page
        } else if (view == this.gridBinding.cart1) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)

            var currPg = Bundle()
            currPg.putInt("layout", Page.GRID_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_TO_SHOPPING_CART_LISTENER, currPg)

        // load more
        } else if (view == this.gridBinding.buttonLoadMore) {
            val keyword = gridBinding.searchBar.text.toString()
            val category = gridBinding.dropdownCategory.selectedItemPosition
            this.callUpdateGrid(this.dataOffset + 5, category, keyword)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        this.presenter.changeCategoryFilter(p2 - 1, this.dataOffset, this.gridBinding.searchBar.text.toString())
    }

    // salurkan data yang akan ditampilkan ke adapter & sinkronisasi isi produk ke list fragment
    override fun updateGrid(prods: ArrayList<ProductDetails>, newDataOffset: Int) {
        this.adapter.update(prods)
        this.dataOffset = newDataOffset
    }

    override fun moveToDetailsPage(currentProd: ProductDetails) {
        // kirim data clicked product
        var product = Bundle()
        product.putParcelable("product", Parcels.wrap(currentProd))
        product.putInt("layout", Page.GRID_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_TO_DETAILS_LISTENER, product)

        // change page
        var pg = Bundle()
        pg.putInt(Page.PAGE, Page.DETAIL_PAGE)
        parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
    }

    // do nothing
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    // singleton
    companion object {
        fun getInstance(listFragment: ListFragment) : GridFragment {
            val instance = GridFragment()
            instance.listFragment=listFragment
            return instance
        }
    }

    // setup spinner kategori
    fun setupSpinner() {
        val spinnr = this.gridBinding.dropdownCategory
        val spinnerValues = arrayOf<String>("All", "Smartphone", "Tablet", "Watches", "Galaxy Buds")
        val adapter : ArrayAdapter<*> = ArrayAdapter<Any>(requireContext(), R.layout.spinner_layout, spinnerValues)
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        spinnr.adapter = adapter
        spinnr.onItemSelectedListener = this
    }

    // setup text filter listener
    fun setupFilter() {
        this.gridBinding.searchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                val keyword = gridBinding.searchBar.text.toString()
                val category = gridBinding.dropdownCategory.selectedItemPosition
                presenter.changeCategoryFilter(category - 1, dataOffset, keyword)
            }
        })
    }

    // sync product with list fragment, then sync filter & category
    private fun callUpdateGrid(target: Int, category: Int, keyword: String) {
        this.presenter.updateGrid(this.dataOffset, target - 1)
        this.presenter.changeCategoryFilter(category - 1, target, keyword)
    }
}