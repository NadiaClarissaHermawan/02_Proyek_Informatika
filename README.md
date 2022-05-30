# 02_Proyek_Informatika

## Anggota Kelompok B :

- Nadia Clarissa Hermawan        6181901013
- Reynaldi Lukas Yudawinata      6181901038
- Thomas Christsian Haeryono     2016730027

## Project Details :

A Challenge from [Topcoder 1](https://www.topcoder.com/challenges/b8ac049a-6b43-490f-a0c8-ad5aaea02d45) and [Topcoder 2](https://www.topcoder.com/challenges/66db72db-a286-42d1-a222-6a1aad75dbe1) website to create an android app in Kotlin to sell second-hand electronic products which are not needed by their owners. This application can show the name, category, thumbnail, price, and condition of the product being sold. Then the name, product, and condition can be sorted ascending/descending. Users can also search for the desired product name in the search field and can filter products by category. When the user presses a product, it will move to the product page which shows the image, name, price, category, condition and there is a button to add to the cart.

## Project Requirements : 
1. Products List Screen
   - We need to display the Products in list mode or tiles mode.
   - we need to display product name, product category, product thumbnails, product price, and product Condition.
   - The product name, product condition, product price should be sortable, such as click the 'Product name' header to sort products by product name ascending/descending order.
   - Show only 5 products when the screen loads.
   - A LOAD MOER button, clicking on it will load next 5 courses until no more products left.
   - Click on the product name of a row in list mode or a square in tiles mode, it will go to product details screen.
   - Provide a search box to search out for the product name.
   - Provide a select to filter products based on category.
  
2. Product Details Screen
   - Product name
   - Product images: show 3 images for a product and show them as carousel
   - Product category
   - Product price
   - Product condition: almost new, 90% new, etc.
   - A button to add this to shopping cart. For now, just show a toast how many items are in the cart and what the items are.
   - A back(<) button to return to products list screen
   
3. Shopping Cart screen
We need to display the Shopping cart after user click the Add to Cart button. See the screen design:
   - Click + icon can add one to cart, and click - icon can minus one to cart.
   - We can choose which prodcuts to purchase by click the circle icon before the product image.
   - Total section shows the total amount of the selected products.
   - Click Purchase button will go to fill order screen.

4. Fill Order screen
This screen is shown after user click purchase button in shopping car screen. 
   - Show the address information as shown in screenshot.
   - Click > will go to manage address screen.
   - Show the products we buy: product name, price, and amount.
   - The amount can be changed by +, - icons.
   - Show Total Amount to pay on the left side of the row for Pay button.
   - Click Pay button will finish the purchase process and will go to Orders screen.

5. Address manage Screen
This screen is used to manage delivery address. see screen design here:
   - click '+ Add Address' to add a new address.
   - click DELETE to remove an address.
   - click edit icon to edit address.

6. Add/Edit screen
This screen is for adding/editing address. see screen design here:
   - input name, mobile, address details. All fields are required.
   - Toggle 'Set as Default address' to save it as default or not.
   - click Save button to save.
   
## Tech Stack:
- Android Studio
- Android 9+
- Kotlin
- Android phone: Samsung Galaxy S21, OnePlus 9 series

## How To Run the Project:
Work in progress.. please wait

