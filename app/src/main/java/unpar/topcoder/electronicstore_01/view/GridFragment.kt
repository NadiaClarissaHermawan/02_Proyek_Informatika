package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.ProductGridFragmentBinding

//fragment
class GridFragment : Fragment(){
    private lateinit var gridBinding : ProductGridFragmentBinding

    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.gridBinding = ProductGridFragmentBinding.inflate(inflater, container, false)

        //todo : buat, set adapter & presenter untuk tampilkan & operasikan grid

        return this.gridBinding.root
    }

    //singleton constructor
    companion object {
        fun getInstance () : GridFragment {
            val instance = GridFragment()
            //todo : initialize instance's attributes if needed
            //instance.attr_name = bablabla..
            return instance
        }
    }
}