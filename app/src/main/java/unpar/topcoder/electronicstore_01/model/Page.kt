package unpar.topcoder.electronicstore_01.model

//a static class --> tidak perlu initiate tapi bisa dipanggil (Page.methodnya)
object Page {
    //a static variable (const), immutable (val)
    const val LIST_PAGE = 0
    const val GRID_PAGE = 1
    const val DETAIL_PAGE = 2
    const val EXIT_PAGE = 999
}