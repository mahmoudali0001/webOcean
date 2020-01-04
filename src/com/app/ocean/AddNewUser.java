package com.app.ocean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "AddNewUser")
public class AddNewUser extends HttpServlet
{
    Connection conn = null;
    User user = new User();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        user.setFirst_name(request.getParameter("first_name"));
        user.setLast_name(request.getParameter("last_name"));
        user.setEmail(request.getParameter("email"));
        user.setBirthday(request.getParameter("birthday"));
        user.setPhone_number(request.getParameter("phone_number"));
        user.setUser_name(request.getParameter("user_name"));
        user.setPassword(request.getParameter("password"));
        user.setConnect_date(new Date().toString());


        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:E:/work/IntelliJ IDEA Projects/OceanApp/web/WEB-INF/classes/myDB.db");
//            conn = DriverManager.getConnection("jdbc:sqlite:"+ this.getClass().getResource(  "/myDB.db"));

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(user_id) from users;");

            while (resultSet.next())
                user.setUser_id(resultSet.getInt(1)+1);

            final String SQL = "INSERT INTO USERS  VALUES  (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL);


            ps.setInt(1 ,user.getUser_id());
            ps.setString(2 ,user.getFirst_name());
            ps.setString(3 ,user.getLast_name());
            ps.setString(4 ,user.getEmail());
            ps.setString(5 ,user.getBirthday());
            ps.setString(6 ,user.getPhone_number());
            ps.setString(7 ,user.getUser_name());
            ps.setString(8 ,user.getPassword());
            ps.setString(9 ,user.getConnect_date());

            int i = ps.executeUpdate();

            if (i != 0)
            {
                System.err.println("Record has been inserted");
            } else {
                System.err.println("failed to insert the data");
            }

            resultSet.close();

            request.setAttribute("username" , user.getUser_name());
            request.setAttribute("password" , user.getPassword());
            String url = "/index.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);


        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
            System.err.println(e.getStackTrace());
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

}
