<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/user?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false","root","비밀번호");
	PreparedStatement statement = con.prepareStatement("select * from usertable where id=? and pw=?");
	statement.setString(1, request.getParameter("userId"));
	statement.setString(2, request.getParameter("userPassword"));
	System.out.println(statement);
	ResultSet rs = statement.executeQuery();
	if(rs.next()){
		response.getWriter().write(rs.getString(1)+","+rs.getString(2));
	} else{
		response.getWriter().write("error");
	}
	con.close();
%>