package unpar.topcoder.electronicstore_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import unpar.topcoder.electronicstore_01.view.ListFragment
import unpar.topcoder.electronicstore_01.databinding.ActivityMainBinding
import unpar.topcoder.electronicstore_01.model.Page
import unpar.topcoder.electronicstore_01.view.DetailFragment
import unpar.topcoder.electronicstore_01.view.GridFragment

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var arrFragment : Array<Fragment>
    private var currentFragment : Int = Page.LIST_PAGE
    private lateinit var listFragment : Fragment
    private lateinit var gridFragment : Fragment
    private lateinit var detailFragment : Fragment

    //constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        //bind layout
        super.onCreate(savedInstanceState)
        this.mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.mainBinding.root)

        //initiate fragments & masukkin ke array
        this.listFragment = ListFragment.getInstance()
        this.gridFragment = GridFragment.getInstance()
        this.detailFragment = DetailFragment.getInstance()
        this.arrFragment = arrayOf(this.listFragment, this.gridFragment, this.detailFragment)

        //initialize frag manager & set main page
        this.fragmentManager = supportFragmentManager
        this.changePage(Page.LIST_PAGE)
    }

    //method untuk ganti halaman
    fun changePage(page : Int) {
        if(page == Page.EXIT_PAGE){
            this.exitApp()
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