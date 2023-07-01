package Task_part_1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEmployeeById extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_data", "root",
					"root");
			PreparedStatement ps = con.prepareStatement("select * from employee_data where id = ?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
            String fname =  rs.getString("fname");
            String lname =   rs.getString("lname");
            String dob =   rs.getString("dob");
            String gender =    rs.getString("gender");
            String mobile =   rs.getString("mobile");
            String email =    rs.getString("email");
            String password =   rs.getString("password");
            String bloodGroup =   rs.getString("bloodGroup");
            String address =   rs.getString("address");
            String pincode =   rs.getString("pincode");
            
            Registration_entities e = new Registration_entities(id, fname, lname, dob, gender, mobile, email, password,
    				bloodGroup, address, pincode);
            request.setAttribute("empData", e);

			System.out.println(rs);
			System.out.println(rs.getString("id"));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		

		

		RequestDispatcher rd = request.getRequestDispatcher("showSingleData.jsp");
		rd.forward(request, response);

	}

}
