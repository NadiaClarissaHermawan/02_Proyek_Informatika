package unpar.topcoder.electronicstore_01.model

import unpar.topcoder.electronicstore_01.R

object  AllProducts {
    val products = arrayOf<ProductDetails>(
        ProductDetails("Galaxy Note20 Ultra 5G", ProductCode.PNEW, ProductCode.SMARTPHONE, "18.999.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy A53 5G", ProductCode.PMEDIUM, ProductCode.SMARTPHONE, "5.999.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy Tab S8+ 5G", ProductCode.PHIGH, ProductCode.TABLET, "16.999.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy Tab S8+ (Wi-Fi)", ProductCode.PMEDIUM, ProductCode.TABLET, "9.999.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy Watch4 LTE (40mm)", ProductCode.PLOW, ProductCode.WATCHES, "3.999.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy Watch Active2", ProductCode.PHIGH, ProductCode.WATCHES, "4.399.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3),
        ProductDetails("Galaxy Buds2", ProductCode.PMEDIUM, ProductCode.GALAXYBUDS, "1.699.000", R.drawable.test_image, R.drawable.test_image2, R.drawable.test_image3)
    )
}

