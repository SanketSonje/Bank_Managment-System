package comrushi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Create
 */
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
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
		
		String name = request.getParameter("name");
		int acc = Integer.parseInt(request.getParameter("acc"));
		int pass = Integer.parseInt(request.getParameter("pass"));
		int bal = Integer.parseInt(request.getParameter("bal"));
//		System.out.println(name);
//		System.out.println(acc);
//		System.out.println(pass);
//		System.out.println(bal);

		
		 Connection con = Dbconnection.connect();
			try {
				PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?,?)");
				 stmt.setString(1, name);
				  stmt.setInt(2, acc);
			      stmt.setInt(3, pass);
			      stmt.setInt(4, bal);
			      
			      int i = stmt.executeUpdate();
			      if(i>0){
			    	  response.sendRedirect("dashboard.html");
			      }
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
