package com.simplilearn.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.model.Cart;
import com.simplilearn.model.Product;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddCartDetails
 */
@WebServlet("/add-to-cart")
public class AddCartDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCartDetails() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
				
		Product product1 = new Product();
		product1.setDescription("samsung");
		product1.setPrice(1000);

		Product product2 = new Product();
		product2.setDescription("nokia");
		product2.setPrice(5000);

		Cart cart = new Cart();
		cart.setTotal(6000);

		Set<Product> products = new HashSet<Product>();
		products.add(product1);
		products.add(product2);
		cart.setProducts(products);
		
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = HibernateUtil.buildSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			session.save(cart);
			System.out.println("Before committing transaction");
			tx.commit();
			sessionFactory.close();

			out.println("Cart ID=" + cart.getId());
			out.println("Product-1 ID=" + product1.getId());
			out.println("Product-2 ID=" + product2.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionFactory != null && !sessionFactory.isClosed())
				sessionFactory.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
