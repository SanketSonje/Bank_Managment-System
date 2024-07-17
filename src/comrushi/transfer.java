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
 * Servlet implementation class transfer
 */
public class transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer() {
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
		
		
		int oldbalance=0;
		int	recOldBalance=0;
		int acc =  Integer.parseInt(request.getParameter("acc"));
		int pass = Integer.parseInt(request.getParameter("pass"));
		int bal = Integer.parseInt(request.getParameter("bal"));
		int recacc =  Integer.parseInt(request.getParameter("recacc"));
		
		 Connection con = Dbconnection.connect();
		 
			try {
				PreparedStatement stmt = con.prepareStatement("select * from student where account = ? and pin = ?");
				
				stmt.setInt(1,acc);
				stmt.setInt(2,pass);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					oldbalance = rs.getInt(4);
					 //acc = rs.getInt(2);
				}
				
				PreparedStatement stmt1 = con.prepareStatement("select * from student where account = ?");		
				stmt1.setInt(1,recacc );
				ResultSet rs1 = stmt.executeQuery();
				
				while(rs1.next()){
					recOldBalance = rs1.getInt(4);
					
				}
				
				int old = oldbalance - bal;
				int newb = recOldBalance + bal;
				
			
				PreparedStatement stmt2 = con.prepareStatement("update student set balance = ? where account = ?");
			         
				stmt2.setInt(1,newb );
				stmt2.setInt(2,recacc );
				
				int i = stmt2.executeUpdate();
						
				PreparedStatement stmt3 = con.prepareStatement("update student set balance = ? where account = ?");
		         
				stmt3.setInt(1,old );
				stmt3.setInt(2,acc );
				
				int i1 = stmt3.executeUpdate();
				
				          if(i1>0)
							{
								System.out.println("transfer done successfully !!");
								response.sendRedirect("dashboard.html");
								
							}
							else{
								System.out.println("something went wrong..... ");
							}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			doGet(request, response);
	}
	

}
