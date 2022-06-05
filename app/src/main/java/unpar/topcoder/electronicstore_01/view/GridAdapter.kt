package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ProductGridEntryBinding
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.GridPresenter
import java.util.*
import java.text.NumberFormat
import kotlin.collections.ArrayList

class GridAdapter(
    private var activity: Activity,
    private var presenter: GridPresenter
) : BaseAdapter(), View.OnClickListener {

    private lateinit var gridItemBinding: ProductGridEntryBinding
    private var prods: MutableList<ProductDetails> = ArrayList()
    private lateinit var currentProduct: ProductDetails

    override fun onClick(v: View?) {
        this.presenter.goDetailsPage(this.currentProduct)
    }

    override fun getCount() : Int {
        return this.prods.size
    }

    override fun getItem(position: Int) : ProductDetails {
        return this.prods[position]
    }

    override fun getItemId(position: Int) : Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) : View {
        var convertView: View?=convertView
        if(convertView==null) {
            this.gridItemBinding = ProductGridEntryBinding.inflate(this.activity.layoutInflater)
            convertView = this.gridItemBinding.root
            convertView.setTag(this.gridItemBinding)
        }
        else {
            this.gridItemBinding= convertView.tag as ProductGridEntryBinding
        }

        this.currentProduct = this.getItem(position)
        this.updateLayout(this.currentProduct)


        return this.gridItemBinding.root
    }


    // format integer to rupiah
    fun convertInt(price: Int) : String {
        val localeID: Locale = Locale("in", "ID")
        val formats = NumberFormat.getCurrencyInstance(localeID)
        return formats.format(price)
    }

    // function update layout (buat recycle)
    fun updateLayout(currentProduct: ProductDetails) {
        this.gridItemBinding.productName.text=this.currentProduct.getNama()
        this.gridItemBinding.productCategory.text="" + this.currentProduct.getKategori()
        this.gridItemBinding.productCondition.text = "" + this.currentProduct.getKondisi() + "%"
        this.gridItemBinding.productPrice.text = this.convertInt(currentProduct.getHarga())
        this.gridItemBinding.productImage.setImageResource(this.currentProduct.getImageSource())
        this.gridItemBinding.root.setOnClickListener{
            this.presenter.goDetailsPage(currentProduct)
        }
    }

    // function untuk update arraylist yang ada di adapter
    fun update(prods: ArrayList<ProductDetails>) {
        this.prods = ArrayList()
        this.prods.addAll(prods)
        this.notifyDataSetChanged()
    }


}