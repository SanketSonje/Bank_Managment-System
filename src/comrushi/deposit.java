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
 * Servlet implementation class deposit
 */
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deposit() {
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
		int oldbalance=0;
		int acc = 0;
		int pass = Integer.parseInt(request.getParameter("pass"));
		int bal = Integer.parseInt(request.getParameter("bal"));
		 Connection con = Dbconnection.connect();
		 try {
			PreparedStatement stmt = con.prepareStatement("select * from student where pin = ?");
			stmt.setInt(1,pass);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				oldbalance = rs.getInt(4);
				 acc = rs.getInt(2);
			}
			
			int new_balance = oldbalance +bal;
			
PreparedStatement stmt1 = con.prepareStatement("update student set balance = ? where account = ?");
          stmt1.setInt(1,new_balance);
          stmt1.setInt(2,acc);
          
          int i = stmt1.executeUpdate();
          
          if(i>0)
			{
				System.out.println("deposition done successfully !!");
			}
			else{
				System.out.println("something went wrong..... ");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
