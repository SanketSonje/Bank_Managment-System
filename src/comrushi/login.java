package comrushi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int i=0;
		int balance=0;
		int acc = Integer.parseInt(request.getParameter("acc"));
		int pass = Integer.parseInt(request.getParameter("pass"));
		
		 Connection con = Dbconnection.connect();
		 try {
			PreparedStatement stmt = con.prepareStatement("Select * from student where account=? and pin = ?");
			stmt.setInt(1,acc);
			stmt.setInt(2,pass);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				balance = rs.getInt(4);
				i=1;
			}
			if(i>0){
				info.setBalance(balance);
				info.setAccount(acc);
				info.setPassword(pass);
				response.sendRedirect("dashboard.html");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		
		
		
		
		
	}

}
