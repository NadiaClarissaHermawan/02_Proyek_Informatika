package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.ProductDetailFragmentBinding

//fragment
class DetailFragment : Fragment(){
    private lateinit var detailBinding : ProductDetailFragmentBinding

    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.detailBinding = ProductDetailFragmentBinding.inflate(inflater, container, false)

        //todo : buat, set adapter & presenter untuk tampilkan & operasikan grid

        return this.detailBinding.root
    }

    //singleton constructor
    companion object {
        fun getInstance () : DetailFragment {
            val instance = DetailFragment()
            //todo : initialize instance's attributes if needed
            //instance.attr_name = bablabla..
            return instance
        }
    }
}