package com.greatlearning.resteasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.greatlearning.resteasy.entities.Dish;
import com.greatlearning.resteasy.entities.Order;
import com.greatlearning.resteasy.entities.Orderline;
import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;
import com.greatlearning.resteasy.service.DishService;
import com.greatlearning.resteasy.service.OrderService;
import com.greatlearning.resteasy.service.UserService;
import com.greatlearning.resteasy.service.VendorService;
import com.greatlearning.resteasy.utils.Role;
import com.greatlearning.resteasy.utils.Util;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	private UserService userService = new UserService();
	private VendorService vendorService = new VendorService();
	private DishService dishService = new DishService();
	private OrderService orderService = new OrderService();

	private Order customerOrder = null;
	private User currentUser = null;

	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		Main main = new Main();
		main.runApplication();

	}

	private Scanner scanner = new Scanner(System.in);

	private void runApplication() {
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Welcome to RestEasy!");
		System.out.println("----------------------------------------------------------------------------------------");		
		System.out.println("Are you a Customer(1) or Admin(2)?");

		Role role = getUserRole();

		if (login(role)) {
			if (role.equals(Role.CUSTOMER)) {
				customerOptions();
			} else if (role.equals(Role.ADMIN)) {
				seeAllOrders(null, null);
			}			
		}else {
			System.err.println("Invalid username & password!");
			runApplication();
		}


	}

	public Role getUserRole() {
		int role = scanner.nextInt();

		if (role == 1) {
			return Role.CUSTOMER;
		} else if (role == 2) {
			return Role.ADMIN;
		} else {
			System.err.println("Invalid Input. Please input 1 for Customer or 2 for Admin");
			getUserRole();
		}
		return null;
	}

	private boolean login(Role role) {
		System.out.println("Username:");
		String userName = scanner.next();
		System.out.println("password");
		String password = scanner.next();

		User user = userService.login(userName, password, role);

		if (user != null) {
			currentUser = user;
			return true;
		} else {
			return false;
		}
	}

	public void customerOptions() {
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("1. See all vendors");
		System.out.println("2. Search for dish");
		System.out.println("3. View order");
		System.out.println("4. Logout (will discard any unpaid orders)");
		int option = scanner.nextInt();

		if (option == 1) {
			vendorOptions();	
		}else if (option == 2) {
			searchDishOption();
		} else if (option == 3) { 
			viewOrder(customerOrder, null, null, Role.CUSTOMER );
		}else if (option == 4) {
			logout();
		}	
	}

	private void logout() {
		System.out.println("----------------------------------------------------------------------------------------");
		this.currentUser = null;
		this.customerOrder = null;
		runApplication();
	}

	public void vendorOptions() {
		System.out.println("----------------------------------------------------------------------------------------");
		List<Vendor> vendors = vendorService.getAllVendors();

		for (int i = 0; i < vendors.size(); i++) {
			System.out.println(i+"."+vendors.get(i).getName());
		}
		System.out.println("Enter vendor choice:");
		System.out.println("b:Back");

		String op = scanner.next();
		if(Util.isInteger(op)) {
			int option = Integer.parseInt(op);
			seeVendorDishes(vendors.get(option),null);
		}else {
			if(op.equals("b")) {
				customerOptions();
			}else {
				System.err.println("Invalid option");
				vendorOptions();
			}
		}

	}

	public void seeVendorDishes(Vendor vendor,String sort){
		System.out.println("----------------------------------------------------------------------------------------");
		List<VendorDish> vendorDishs = vendorService.getVendorDishs(vendor,sort); 
		for (int i = 0; i < vendorDishs.size(); i++) {
			System.out.println(i+". "+vendorDishs.get(i).getDish().getName()+"(Rs."+vendorDishs.get(i).getPrice()+")");
		}

		System.out.println("Enter number to add to order");
		System.out.println("a/d:Sort by price");
		System.out.println("b:Back");

		String op= scanner.next();

		if(Util.isInteger(op)) {
			int option = Integer.parseInt(op);
			if(option<vendorDishs.size()) {
				System.out.println("Enter quantity:");
				int quantity = scanner.nextInt();
				if(quantity<=0) {
					System.err.println("Invalid quantity.");
				}else {
					addToOrder(vendorDishs.get(option) ,quantity);
					System.out.println("Dish added to order");
				}		
				seeVendorDishes(vendor, sort);
			}else {
				System.err.println("Invalid option");
				seeVendorDishes(vendor, sort);
			}

		}else {
			if(op.equals("a")) {
				seeVendorDishes(vendor, "asc");
			}else if(op.equals("d")) {
				seeVendorDishes(vendor, "desc");
			}else if(op.equals("b")) {
				vendorOptions();
			}else {
				System.err.println("Invalid option");
				seeVendorDishes(vendor, sort);
			}
		}

	}

	private void searchDishOption() {
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Enter dish to search:");
		String search = scanner.next();
		search(search,null);
	}

	private void search(String search, String sort) {
		System.out.println("----------------------------------------------------------------------------------------");
		List<VendorDish> vendorDishs = dishService.searchDishs(search, sort);
		if(vendorDishs!=null && vendorDishs.size()>0) {
			for (int i = 0; i < vendorDishs.size(); i++) {
				Dish dish = vendorDishs.get(i).getDish();
				Vendor vendor = vendorDishs.get(i).getVendor();
				int price = vendorDishs.get(i).getPrice();
				System.out.println(i+". "+dish.getName()+" / "+vendor.getName()+" / Rs."+price);
			}
			System.out.println("Enter number to add to order");
			System.out.println("a/d:Sort by price");
			System.out.println("b:Back");

			String op= scanner.next();

			if(Util.isInteger(op)) {
				int option = Integer.parseInt(op);
				if(option<vendorDishs.size()) {
					System.out.println("Enter quantity:");
					int quantity = scanner.nextInt();
					if(quantity<=0) {
						System.err.println("Invalid quantity.");
					}else {
						addToOrder(vendorDishs.get(option), quantity);			
						System.out.println("Dish added to order");
					}
					search(search, sort);
					
				}else {
					System.err.println("Invalid option");
					search(search, sort);
				}

			}else { 
				if(op.equals("a")) {
					search(search, "asc");
				}else if(op.equals("d")) {
					search(search, "desc");
				}else if(op.equals("b")) {
					customerOptions();
				}else {
					System.err.println("Invalid option");
					search(search, sort);
				}
			}
		}else {
			System.err.println("*****No results found*****");
			customerOptions();
		}


	}

	private void seeAllOrders(String sort, String field) {
		System.out.println("----------------------------------------------------------------------------------------");
		
		List<Order> orders =  orderService.getAllOrders(sort, field);
		if(orders!=null && orders.size()>0) {
			for (int i = 0; i < orders.size(); i++) {
				Order order = orders.get(i);
				System.out.println(i+". "+order.getId()+" / "+order.getUser().getName()+" / "+order.getOrderDate()+" / "+order.getTotalAmount());
			}

			System.out.println("Enter number to see order");
			System.out.println("a/d:Sort by total amount");
			System.out.println("A/D:Sort by date");
			System.out.println("b:logout");

			String op= scanner.next();

			if(Util.isInteger(op)) {
				int option = Integer.parseInt(op);
				if(option<orders.size()) {
					viewOrder(orders.get(option), sort, field, Role.ADMIN);			
				}else {
					System.err.println("Invalid option");
					seeAllOrders(sort, field);
				}

			}else {
				if(op.equals("a")) {
					seeAllOrders("asc", "totalAmount");
				}else if(op.equals("d")) {
					seeAllOrders("desc", "totalAmount");
				}else if(op.equals("A")) {
					seeAllOrders("asc", "orderDate");
				}else if(op.equals("D")) {
					seeAllOrders("desc", "orderDate");
				}else if(op.equals("b")) {
					logout();
				}else {
					System.err.println("Invalid option");
					seeAllOrders(sort, field);
				}
			}

		}
	}

	private void viewOrder(Order order, String sort, String field, Role role ) {

		System.out.println("----------------------------------------------------------------------------------------");
		if(order!=null) {
			System.out.println("Order Id: "+ order.getId());
			System.out.println("Customer: "+ order.getUser().getName());
			System.out.println("Order Date: "+ order.getOrderDate());
			System.out.println("Total amount: "+ order.getTotalAmount());
			System.out.println();

			if(order.getOrderlines()!=null && order.getOrderlines().size()>0) {

				for (int i = 0; i < order.getOrderlines().size(); i++) {
					Orderline orderline = order.getOrderlines().get(i);
					Dish dish = orderline.getVendorDish().getDish();
					Vendor vendor = orderline.getVendorDish().getVendor();
					int totalPrice = orderline.getQuantity()*orderline.getVendorDish().getPrice();
					System.out.println(i+". "+dish.getName()+" / "+vendor.getName()+" / "+ orderline.getQuantity()+" / "+orderline.getVendorDish().getPrice()+" / "+totalPrice);	
				}
			}
		}else {
			System.err.println("*****Nothing to display*****");
		}

		if(role.equals(Role.ADMIN)) {
			System.out.println("Enter any key to go back");
			scanner.next();
			seeAllOrders(sort, field);			
		}else if(role.equals(Role.CUSTOMER)) {
			System.out.println("p: generate order");
			System.out.println("b: back");
			String op = scanner.next();
			if(op.equals("p")) {
				if(customerOrder!=null) {
					generateOrder();
					System.out.println("Order generated");					
				}else {
					System.err.println("*****Add dishes to order*****");
				}

				customerOptions();
			}else if(op.equals("b")){
				customerOptions();
			}

		}
	}

	private void generateOrder() {
		orderService.generateOrder(customerOrder);
		customerOrder = null;
	}

	private void addToOrder(VendorDish vendorDish, int quantity) {
		
		if(customerOrder==null) {
			customerOrder = new Order();
			customerOrder.setUser(currentUser);
			customerOrder.setOrderlines(new ArrayList<Orderline>());
		}

		boolean flag = false;
		if(customerOrder.getOrderlines()!=null) {
			for(Orderline ool: customerOrder.getOrderlines()) {

				Dish dish = ool.getVendorDish().getDish();
				Vendor vendor = ool.getVendorDish().getVendor();

				if(dish.getId().equals(vendorDish.getDish().getId()) && vendor.getId().equals(vendorDish.getVendor().getId())) {
					int additionalPrice = vendorDish.getPrice()*quantity;
					customerOrder.setTotalAmount(customerOrder.getTotalAmount()+additionalPrice);
					ool.setQuantity(ool.getQuantity()+quantity);
					flag = true;
					break;
				}
			}
		}
		if(!flag) {
			Orderline orderline = new Orderline();
			orderline.setQuantity(quantity);
			orderline.setVendorDish(vendorDish);

			customerOrder.addOrderline(orderline);

			customerOrder.setTotalAmount(customerOrder.getTotalAmount()+(vendorDish.getPrice()*quantity));
		}
	}

}
