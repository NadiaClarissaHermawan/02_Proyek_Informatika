package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.ProductListFragmentBinding

//fragment
class ListFragment : Fragment() {
    private lateinit var listBinding : ProductListFragmentBinding

    //constructor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        //bind layout
        this.listBinding = ProductListFragmentBinding.inflate(inflater, container, false)

        //todo : buat, set adapter & presenter untuk tampilkan & operasikan list

        return this.listBinding.root
    }

    //singleton constructor
    companion object {
        fun getInstance () : ListFragment {
            val instance = ListFragment()
            //todo : initialize instance's attributes if needed
            //instance.attr_name = bablabla..
            return instance
        }
    }
}