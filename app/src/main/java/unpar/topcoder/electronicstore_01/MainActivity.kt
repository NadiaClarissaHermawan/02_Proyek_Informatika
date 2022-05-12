package unpar.topcoder.electronicstore_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import unpar.topcoder.electronicstore_01.view.ListFragment
import unpar.topcoder.electronicstore_01.databinding.ActivityMainBinding
import unpar.topcoder.electronicstore_01.model.AllProducts
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.model.Update
import unpar.topcoder.electronicstore_01.view.DetailFragment
import unpar.topcoder.electronicstore_01.view.GridFragment

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var arrFragment : Array<Fragment>
    private var currentFragment : Int = Page.LIST_PAGE
    private lateinit var listFragment : ListFragment
    private lateinit var gridFragment : GridFragment
    private lateinit var detailFragment : Fragment

    //constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        //bind layout
        super.onCreate(savedInstanceState)
        this.mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.mainBinding.root)

        //initiate fragments & masukkin ke array
        this.listFragment = ListFragment.getInstance()
        this.gridFragment = GridFragment.getInstance(this.listFragment)
        this.detailFragment = DetailFragment.getInstance()
        this.arrFragment = arrayOf(this.listFragment, this.gridFragment, this.detailFragment)

        //initialize frag manager & set main page
        this.fragmentManager = supportFragmentManager

        this.changePage(Page.LIST_PAGE)


        //updating list fragment abis grid terupdate ATAU updating grid fragment abis list ke update
        this.fragmentManager.setFragmentResultListener(Update.UPDATE, this) {
            _, result ->
            var type = result.getString(Update.TYPE)
            var offset = result.getInt(Update.OFFSET)
            if(type==Update.GRID) {
                //ceritanya disini mau dibuat bundle yg isinya offset yg dikirim ke grid fragment tapi masih belum dicoba
                var bundle : Bundle = Bundle()
                bundle.putInt(Update.UPDATE,offset)
                this.fragmentTransaction = this.fragmentManager.beginTransaction()
                this.gridFragment.setArguments(bundle)


            }
            else if(type==Update.LIST) {

            }
        }

        //change page listener
        this.fragmentManager.setFragmentResultListener(Page.CHANGE_PAGE_LISTENER, this) {
                _, result ->
            var page = result.getInt(Page.PAGE)
            this.changePage(page)
        }

    }

    //method untuk ganti halaman
    fun changePage(page : Int) {
        //exit
        if(page == Page.EXIT_PAGE){
            this.exitApp()
        //change page
        }else{
            this.fragmentTransaction = this.fragmentManager.beginTransaction()
            if(this.currentFragment != page){
                this.fragmentTransaction.hide(arrFragment[this.currentFragment])
            }
            if(this.arrFragment[page].isAdded){
                this.fragmentTransaction.show(this.arrFragment[page])
            }else{
                this.fragmentTransaction.add(R.id.fragment_container, this.arrFragment[page])
            }
            this.fragmentTransaction.commit()
            this.currentFragment = page
        }
    }

    //exit applications
    fun exitApp() {
        moveTaskToBack(true)
        finish()
    }
}