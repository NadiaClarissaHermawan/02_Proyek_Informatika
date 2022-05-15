package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import unpar.topcoder.electronicstore_01.databinding.ProductListEntryBinding
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.ListPresenter

class ListAdapter (private var activity : Activity, private var presenter : ListPresenter) : BaseAdapter(){
    private lateinit var listItemBinding : ProductListEntryBinding
    private var prods : ArrayList<ProductDetails> = ArrayList()

    override fun getCount(): Int {
        return this.prods.size
    }

    override fun getItem(p0: Int): ProductDetails {
        return this.prods[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        //bind dengan layout list entry
        var view : View? = p1
        if(view == null){
            this.listItemBinding = ProductListEntryBinding.inflate(this.activity.layoutInflater)
            view = this.listItemBinding.root
            view.setTag(this.listItemBinding)
        }else{
            this.listItemBinding = view.tag as ProductListEntryBinding
        }

        //take current prod & show the details to layout
        var currproduct : ProductDetails = this.getItem(p0)
        this.updateLayout(currproduct)

        return this.listItemBinding.root
    }

    //terima updated product list dari fragment
    fun updateList(products : ArrayList<ProductDetails>) {
        this.prods = ArrayList()
        this.prods.addAll(products)
        notifyDataSetChanged()
    }

    //show prods details to layout
    fun updateLayout(currProduct : ProductDetails) {
        this.listItemBinding.productName.text = currProduct.getNama()
        this.listItemBinding.productCategory.text = ""+currProduct.getKategori()
        this.listItemBinding.productCondition.text = ""+currProduct.getKondisi()+"%"
        this.listItemBinding.productPrice.text = "Rp "+currProduct.getHarga()
        //set item's click listener
        this.listItemBinding.root.setOnClickListener{this.presenter.goDetailsPage(currProduct)}
    }
}