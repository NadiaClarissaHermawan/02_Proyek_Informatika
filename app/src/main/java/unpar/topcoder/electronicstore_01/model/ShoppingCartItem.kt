package unpar.topcoder.electronicstore_01.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//model including constructor
@Parcelize
class ShoppingCartItem (
    private var product: ProductDetails,
    private var check_status: Int,
    private var quantity: Int
): Parcelable {

    fun getProduct() : ProductDetails {
        return this.product
    }

    fun getCheckStatus() : Int {
        return this.check_status
    }

    fun getQuantity() : Int {
        return this.quantity
    }
}