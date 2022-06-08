package unpar.topcoder.electronicstore_01.model

 // a static class --> tidak perlu initiate tapi bisa dipanggil (Page.methodnya)
object Page {
    //a static variable (const), immutable (val)
    const val LIST_PAGE = 0
    const val GRID_PAGE = 1
    const val DETAIL_PAGE = 2
    const val SHOPPING_CART_PAGE = 3
    const val CHECK_OUT_PAGE = 4
    const val ADDRESS_MANAGEMENT_PAGE = 5
    const val EXIT_PAGE = 999
    const val CHANGE_PAGE_LISTENER = "changePage"
    const val CHANGE_TO_DETAILS_LISTENER = "changeToDetails"
    const val CHANGE_TO_SHOPPING_CART_LISTENER = "changeToShoppingCart"
    const val CHANGE_TO_CHECKOUT_LISTENER = "changeToCheckout"
    const val ADD_TO_SHOPPING_CART = "addToShoppingCart"
    const val UPDATE_FROM_CHECK_OUT = "updateCheckout"
    const val DELETE_PAID_PRODUCTS = "deletePaidProds"
    const val RECEIVE_DEFAULT_ADDRESS = "receiveAddress"
    const val SYNC_LISTENER = "sync"
    const val PAGE = "page"
}