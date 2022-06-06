package unpar.topcoder.electronicstore_01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import unpar.topcoder.electronicstore_01.databinding.CheckOutFragmentBinding
import unpar.topcoder.electronicstore_01.model.Page

class CheckoutFragment : Fragment(), View.OnClickListener {
    private lateinit var checkOutBinding: CheckOutFragmentBinding

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

        return this.checkOutBinding.root
    }

    // singleton constructor
    companion object {
        fun getInstance () : CheckoutFragment {
            val instance = CheckoutFragment()
            return instance
        }
    }

    override fun onClick(view: View?) {
        if (view == this.checkOutBinding.back) {
            var pg = Bundle()
            pg.putInt(Page.PAGE, Page.SHOPPING_CART_PAGE)
            parentFragmentManager.setFragmentResult(Page.CHANGE_PAGE_LISTENER, pg)
        }
    }
}