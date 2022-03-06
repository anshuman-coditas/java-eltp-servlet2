package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcContext
 */
//@WebServlet("/JdbcContext")
public class JdbcContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdbcContext() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    String uname=request.getParameter("uname");
	    String pass=request.getParameter("pass");
	
	
	ServletContext app=getServletConfig().getServletContext();
	try{
        Class.forName(app.getInitParameter("driver"));
       Connection con = DriverManager.getConnection(app.getInitParameter("url"), app.getInitParameter("user"), app.getInitParameter("pass1"));
       PreparedStatement ps=con.prepareStatement("select name,password from admin where name=? and password=?");
       ps.setString(1, uname);
       ps.setString(2,pass);
       ResultSet rs=ps.executeQuery();
       if(rs.next()) {
    	   out.println("<h1><center>Logged In Successfully</center></h1>");
       }
       else
    	   out.println("<h1><center>Wrong Entries</center.</h1>");

}catch(Exception e) {
	e.printStackTrace();
}
}
}
