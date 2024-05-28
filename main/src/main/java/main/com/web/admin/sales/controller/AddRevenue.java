package main.com.web.admin.sales.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.com.web.admin.reserve.dto.Sales;
import main.com.web.admin.sales.service.AdminSalesService;

/**
 * Servlet implementation class AddRevenue
 */
@WebServlet("/addRevenue")
public class AddRevenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRevenue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        int newRevenue=0;
        try{
        	newRevenue= Integer.parseInt(request.getParameter("revenue"));
        }catch(NumberFormatException e) {
        	newRevenue=1;
        }
        String month=request.getParameter("month");

        AdminSalesService salesService = new AdminSalesService();
        List<Sales> updatedSalesData = salesService.addReservation(month,newRevenue);

        Gson gson = new Gson();
        String jsonSalesData = gson.toJson(updatedSalesData);
        response.getWriter().write(jsonSalesData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
