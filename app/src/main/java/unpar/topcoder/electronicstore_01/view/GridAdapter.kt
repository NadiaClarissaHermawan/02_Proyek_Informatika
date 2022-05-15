package unpar.topcoder.electronicstore_01.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.R
import unpar.topcoder.electronicstore_01.databinding.ProductGridEntryBinding
import unpar.topcoder.electronicstore_01.model.ProductDetails
import unpar.topcoder.electronicstore_01.presenter.GridPresenter

class GridAdapter (private var activity: Activity, private var presenter: GridPresenter): BaseAdapter(), View.OnClickListener{
    private lateinit var gridItemBinding: ProductGridEntryBinding
    private var prods: MutableList<ProductDetails> = ArrayList()
    private lateinit var currentProduct: ProductDetails

    override fun getCount(): Int {
        return this.prods.size
    }

    override fun getItem(position: Int): ProductDetails {
        return this.prods[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View?=convertView
        if(convertView==null){
            this.gridItemBinding = ProductGridEntryBinding.inflate(this.activity.layoutInflater)
            convertView = this.gridItemBinding.root
            convertView.setTag(this.gridItemBinding)
        }
        else{
            this.gridItemBinding= convertView.tag as ProductGridEntryBinding
        }

        this.currentProduct = this.getItem(position)
        this.updateLayout(this.currentProduct)

        convertView.setOnClickListener(this::onClick)

        return this.gridItemBinding.root
    }

    //function update layout (buat recycle)
    fun updateLayout(currentProduct : ProductDetails){
        this.gridItemBinding.productName.text=this.currentProduct.getNama()
        this.gridItemBinding.productCategory.text=""+this.currentProduct.getKategori()
        this.gridItemBinding.productCondition.text = ""+this.currentProduct.getKondisi()+"%"
        this.gridItemBinding.productPrice.text = ""+this.currentProduct.getHarga()
        this.gridItemBinding.productImage.setImageResource(this.currentProduct.getImageSource())
    }

    override fun onClick(v: View?) {
        this.presenter.goDetailsPage(this.currentProduct)
    }

    //function untuk update arraylist yang ada di adapter
    fun update(prods: ArrayList<ProductDetails>){
        this.prods = ArrayList()
        this.prods.addAll(prods)
        this.notifyDataSetChanged()
    }
}