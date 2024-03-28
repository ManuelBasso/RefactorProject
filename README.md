"Write a program in Java using Spring/Springboot and all additional libraries to manage a car dealership.

The car dealership will have simple users (customers) who can place an order or purchase a model already available. Or rent a vehicle. 
It will have administration users (admins) who can add vehicles already available, used vehicles, orderable vehicles, and rentable vehicles, and can manage sales, rentals, and orders. Then there will be sellers who will manage sales and rentals.

For each customer, we will have a series of attributes:

Name
Surname
Phone
Email
Password


For each administrator, we will have a series of attributes:

Name
Surname
Email
Password


For each seller, we will have a series of attributes:

Name
Surname
Phone
Email
Password


Vehicles can be of different types: cars, motorcycles, scooters, vans. For each vehicle, we will have different attributes:

Brand
Model
Displacement
Color
Power
Gearbox type
Year of registration
Fuel type
Price
Possible discount on the list price
accessories
Flag identifying if the vehicle is new or used
Flag identifying if the vehicle is available, ordered, rent, or no longer available


For each order/purchase, we will have:

Deposit
Paid flag
Order status
Ordered/purchased vehicle


For a rent, we will have:

Rent start date
Rent end date
Daily rent cost
Total rent cost
Paid flag
Rented vehicle


A customer can:

Create an order from a vehicle
View their orders
Delete an order
Create a purchase from a vehicle
View their purchases
Create a rent
View their rents
Delete a rent
Delete their account
Modify user data
Search for a vehicle according to a criteria (price, color, brand, model, etc.)
Get details of a specific vehicle


An admin can:

Add a vehicle
Modify a vehicle
Delete a vehicle
Change the status of a vehicle
Create an order for a user
Delete an order for a user
Modify an order for a user
Create a rent for a user
Delete a rent for a user
Modify a rent for a user
Create a purchase for a user
Delete a purchase for a user
Modify a purchase for a user
Check how many sales a seller has made in a specific period of time
Check how much money a seller has generated in a specific period of time
Check the salon's earnings in a specific period
Check currently orderable/purchasable/unavailable/new/used vehicles
Delete a user
Modify a user
Delete a seller
Modify a seller
Get the best-selling vehicle in a given period
Get the highest-value vehicle sold up to that moment
Get the most searched/ordered vehicle


A seller can:

Get details of a specific vehicle
Create an order from an orderable vehicle
Delete a created order
Modify a created order
Check the status of a specific order
Update the status of a specific order
Check all orders filtered by a status
Create a rent
Delete a rent
Modify a rent
The system must also allow login and registration of users through two specific routes."
