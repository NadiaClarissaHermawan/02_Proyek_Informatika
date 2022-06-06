package unpar.topcoder.electronicstore_01.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//model including constructor
@Parcelize
class ShoppingCartItem (
    private var product: ProductDetails,
    private var quantity: Int
): Parcelable {

    fun getProduct() : ProductDetails {
        return this.product
    }

    fun getQuantity() : Int {
        return this.quantity
    }

    fun setQuantity(num: Int) {
        this.quantity = num
    }
}