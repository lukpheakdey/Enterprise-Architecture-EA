package edu.mum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.domain.Product;
import edu.mum.repository.ProductRepository;
import edu.mum.repositoryimpl.ProductRepositoryFactory;

/**
 * Servlet implementation class ListProductsServlet
 */
public class ListProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ProductRepository productRepository = ProductRepositoryFactory.getProductRepository();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Product> list = productRepository.getAll();
		request.setAttribute("products",  list);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ListProducts.jsp");
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
