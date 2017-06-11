# BookSale
Final project of E-Business class.

Environment:
	NetBeans 8.2
  
Database: 
	Derby
  
MVC Pattern
1.Bean: 
	1.User:
		contains all user related information (userId, userName, email, password)
	2.Production:
		contains all product related information (pid, name, author, type, url, description, price, inventory, seller)
	3.Record:
		contains all shopping cart records information (id, productName, author, url, productCount, userName, cost(price of the book))

2.Dao:
	1.UserService: 
		when users register, their information will be inserted into database
		when users log in, whether their usernames and passwords are correct is looked up
	2.ProductionService:
		when users choose a book type, this type related books will show up
		after purchasing the products, update the inventory
	3.dbConnection: 
		connect to the database
		execute search, delete, update, and insert queries
	4.VisitorService: 
		deal with the case when a user has not sign in (for shopping cart)
	5.shoppingService:
		when user has signed in, look up his shopping cart
		add items, delete the items, update the quantity of items he wants to buy
	6.RecordService:
		pay
		remove the items from shopping cart records

3.Servlet:
	1.LoginServlet:
		check if the inputs are legitimate
		redirect to login page if log in failed
		redirect to index if log in succeeded
	2.logoutServlet:
		users can log out at any time
	3.RegisterServlet:
		check if the inputs are legitimate (password and confirm password can match, email contains ‘@’, etc.)
		check if the username has been taken
		redirect to register page if register failed
		redirect to login page if register succeeded
	4.GetBookList:
		get the type, and redirect to the books list page
	5.GetSingleBook:
		get the book information, and redirect to the book’s page
	6.shoppingCart:
		add the items to the shopping cart (no matter whether he has signed in or not)
	7.productInfor:
		When people want to know the items that are added to the shopping cart, it will go to this servlet
	8.deleteProduct:
		delete the item in the shoppingCart
	9.updateProduction:
		check if the quantity user wants to buy exceeds the inventory, if so transaction will be prohibited, otherwise will redirect to order has been placed page


Explore our website:
1.You can find different types of books through navigation.
2.You can view details of each book by clicking on it.
3.You can add the book to cart if you like it no matter if you have signed in or not. 
4.You can view your shopping cart. It doesn’t matter if you haven’t signed in.
5.You can remove the item from your cart if you changed your mind.
6.You can add more quantities of one item by clicking the book’s name or picture. You will be guided to the book’s page again, and you can add more quantities on that page.
7.If you do not have an account, you can register for one, and then sign in.
8.After signing in, you are allowed to check out! If the number of the book you want to buy exceed the inventory,you cannot finish your purchase.
