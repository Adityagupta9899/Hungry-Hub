package com.hungryhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hungryhub.service.CustomerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hungryhub.entites.BillGenerate;
import com.hungryhub.entites.CartItem;
import com.hungryhub.entites.CustomCustomerDetail;
import com.hungryhub.entites.Customer;
import com.hungryhub.entites.Order_items;
import com.hungryhub.entites.Orders;
import com.hungryhub.entites.Payment;
import com.hungryhub.entites.Role;
import com.hungryhub.respository.BillGenerteRepositry;
import com.hungryhub.respository.CartItemRepository;
import com.hungryhub.respository.OrderRespositry;
import com.hungryhub.respository.OrderitemsRespository;
import com.hungryhub.respository.PaymentRepository;
import com.hungryhub.respository.RoleRepository;

@Controller
public class HomeControllers {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private OrderitemsRespository orderitemsRespository;

	@Autowired
	private OrderRespositry orderRespositry;

	@Autowired
	private BillGenerteRepositry billGenerteRepositry;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/{custid}")
	public String index(@PathVariable("custid") int custid, Model model) {
		model.addAttribute("custid", custid);
		return "index";
	}

	@GetMapping("/singin")
	public String singin() {
		return "singin";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// For Saving Data in Database
	@PostMapping("/save")
	public String saveCust(@ModelAttribute Customer customer, HttpServletRequest request, Model model,
			HttpSession session) {

		String password = customer.getPassword();
		System.out.println(password + " password");
		customer.setPassword(bCryptPasswordEncoder.encode(password));
		System.out.println(customer.getEmail());
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		customer.setRoles(roles);
		customerService.save(customer);
		System.out.println(bCryptPasswordEncoder.encode(password) + " encode password");

		return "redirect:/singin";

	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		int customerid = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof CustomCustomerDetail) {
			CustomCustomerDetail userDetails = (CustomCustomerDetail) principal;
			Customer customer = userDetails.getCustomer();
			customerid = customer.getID();
			// model.addAttribute("customer", customer);
		}
		return "redirect:/" + customerid;
	}

	@GetMapping("/dashboard/{customerId}")
	public String dashboard(@PathVariable Integer customerId, Model model) {
		Customer customer = customerService.getCustomerById(customerId);

		model.addAttribute("user", customer);
		model.addAttribute("Login", true);
		model.addAttribute("dashboard", true);
		model.addAttribute("custid", customerId);
		return "profile";
	}

	// Login
	@PostMapping("/savelogin")
	public String logincust(@ModelAttribute("customer") Customer customer, HttpSession session) {
		Customer loggedInCustomer = customerService.checkContAndPassword(customer.getCont(), customer.getPassword());
		if (loggedInCustomer != null) {
			session.setAttribute("loggedInCustomer", loggedInCustomer);
			return "redirect:/";
		} else {
			session.setAttribute("msg", "Invalid username or password");
			return "redirect:/loginn";
		}
	}

	@ModelAttribute
	private void userdtls(Model m, HttpSession session) {
		Customer user = (Customer) session.getAttribute("loggedInCustomer");
		m.addAttribute("user", user);
	}

	@PostMapping("/updateProfile")
	public String updateProfile(
			@RequestParam("Cust_name") String custName,
			@RequestParam("email") String email,
			@RequestParam("cont") String cont,
			@RequestParam("Address") String address,
			@RequestParam("password") String password,
			Model model) {

		Customer user = customerService.updateprofile();

		if (user == null) {
			model.addAttribute("error", "User not found");
			return "profile";
		}

		user.setCust_name(custName);
		user.setEmail(email);
		user.setCont(cont);
		user.setAddress(address);
		user.setPassword(password);

		customerService.save(user);

		return "redirect:/profile";
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		// Invalidate session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/loginn";
	}

	@GetMapping("/addtocart/{custid}")
	public String addtoCart(@PathVariable("custid") int custid, Model model) {
		List<CartItem> allmyorderdetails = customerService.getallorderfromshoppingcart(custid);
		model.addAttribute("myorderdetails", allmyorderdetails);
		double totalSubtotal = allmyorderdetails.stream().mapToDouble(CartItem::getSubtotal).sum();
		Long cartitemcount = allmyorderdetails.stream().mapToLong(CartItem::getProduct_id).count();
		model.addAttribute("cartitemcount", cartitemcount);
		model.addAttribute("custid", custid);
		model.addAttribute("totalSubtotal", totalSubtotal);
		model.addAttribute("custid", custid);
		return "addtocart";
	}

	@PostMapping("/saveaddtocart")
	public String CartGet(@ModelAttribute CartItem cartItem, Model model) {
		int custid = cartItem.getCustomer_id();
		int productid = cartItem.getProduct_id();
		CartItem allmyorderdetails = cartItemRepository.finditemfromidandcustomerid(custid, productid);
		if (allmyorderdetails == null == false) {
			int qty = allmyorderdetails.getQty() + cartItem.getQty();
			double subtotal = qty * cartItem.getPrice();
			cartItem.setId(allmyorderdetails.getId());
			cartItem.setQty(qty);
			cartItem.setSubtotal(subtotal);
			cartItem.setCustomer_id(custid);
			cartItemRepository.save(cartItem);

		} else {
			cartItem.setCustomer_id(custid);
			cartItemRepository.save(cartItem);
		}

		System.out.println(cartItem);
		return "redirect:/addtocart/" + custid;
	}

	@GetMapping("/placeorder/{custid}")
	public String getMethodName(@PathVariable("custid") int custid, Model model) {
		Customer cust = customerService.getCustomerById(custid);
		List<CartItem> allmyorderdetails = customerService.getallorderfromshoppingcart(custid);
		double totalSubtotal = allmyorderdetails.stream().mapToDouble(CartItem::getSubtotal).sum();
		Long cartitemcount = allmyorderdetails.stream().mapToLong(CartItem::getProduct_id).count();
		model.addAttribute("cartitemcount", cartitemcount);
		model.addAttribute("myorderdetails", allmyorderdetails);
		model.addAttribute("cust", cust);
		model.addAttribute("custid", custid);
		model.addAttribute("totalSubtotal", totalSubtotal);

		return "placeorder";
	}

	@PostMapping("/saveplaceorderdetails/{custid}")
	public String saveorder(@PathVariable(value = "custid") int id, @ModelAttribute Orders orders,
			HttpServletRequest request, Model model) {
		String product_idarr[] = request.getParameterValues("product_id");
		String product_namearr[] = request.getParameterValues("product_name");
		String pricearr[] = request.getParameterValues("price");
		String qtyarr[] = request.getParameterValues("qty");
		String subtotalarr[] = request.getParameterValues("subtotal");

		System.out.println(product_idarr);
		// orderitemList.addAll(Arrays.asList(product_idarr));
		// orderitemList.addAll(Arrays.asList(product_namearr));
		// orderitemList.addAll(Arrays.asList(pricearr));
		// orderitemList.addAll(Arrays.asList(qtyarr));
		// orderitemList.addAll(Arrays.asList(subtotalarr));
		// orders.setStatus("Pending");
		orders.setAdded_on(LocalDateTime.now());
		Orders order = orderRespositry.save(orders);
		for (int a = 0; a < product_idarr.length; a++) {
			Order_items orderdetail = new Order_items();
			int product_id = Integer.parseInt(product_idarr[a]);
			String product_namestr = product_namearr[a];
			double price = Double.parseDouble(pricearr[a]);
			int qty = Integer.parseInt(qtyarr[a]);
			double subtotal = Double.parseDouble(subtotalarr[a]);
			orderdetail.setProduct_id(product_id);
			orderdetail.setProduct_name(product_namestr);
			orderdetail.setPrice(price);
			orderdetail.setQty(qty);
			orderdetail.setSubtotal(subtotal);
			orderdetail.setOrder_id(order.getId());
			System.out.println(orderdetail);
			orderitemsRespository.save(orderdetail);
		}

		this.customerService.emptycart(id);

		Payment paydetail = new Payment();
		double grantot = Double.parseDouble(orders.getGrand_total());
		paydetail.setAmount(grantot);
		paydetail.setCust_id(id);
		paydetail.setMode(orders.getPayment_method());
		paymentRepository.save(paydetail);

		BillGenerate billdetail = new BillGenerate();
		billdetail.setBill_amount(grantot);
		billdetail.setCustid(id);
		billdetail.setOrderid(order.getId());
		// billdetail.setPayment_status("Postpaid");
		billdetail.setMode(paydetail.getMode());
		billGenerteRepositry.save(billdetail);

		model.addAttribute("orderid", order.getId());
		model.addAttribute("custid", id);
		List<CartItem> allmyorderdetails = customerService.getallorderfromshoppingcart(id);
		Long cartitemcount = allmyorderdetails.stream().mapToLong(CartItem::getProduct_id).count();
		model.addAttribute("cartitemcount", cartitemcount);
		return "Delivery";
	}
}
