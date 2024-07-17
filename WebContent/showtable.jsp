<%@ page import = "comrushi.Dbconnection"  %>
<%@ page import = "comrushi.info"  %>
<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
  width: 50%;
  margin: 20px auto;
  background-color: white; /* Set the background color to purple */
  border-radius: 8px;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}

/* Additional styles for "Your Details" heading */
.your-details-heading {
  text-align: center;
  margin-bottom: 10px;
  font-size: 18px;
  font-weight: bold;
}


</style>
</head>
<body>

<%
	int acc = info.getAccount();
	Connection con = Dbconnection.connect();
	PreparedStatement st = con.prepareStatement("select * from student where account = ?");
	st.setInt(1, acc);
	ResultSet rs = st.executeQuery();
%>
	<center>
	<div class = "your-details-heading"> Your details
	
	</div>
		<table border="3">
			<tr>
			      <th>Name</th>
				<th>Account Number</th>
				<th>Password</th>
				<th>Balance</th>
			</tr>
			
			<% while(rs.next()) { %>
			<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getInt(2)%></td>
				<td><%=rs.getInt(3)%></td>
				<td><%=rs.getInt(4)%></td>
			</tr>
			<% } %>
		</table>	
	</center>
		
</body>
</html>