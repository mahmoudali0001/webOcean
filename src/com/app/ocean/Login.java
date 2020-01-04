package com.app.ocean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Login")
public class Login extends HttpServlet
{
    Connection conn = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("user_name");
        String password = request.getParameter("password");


        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:E:/work/IntelliJ IDEA Projects/OceanApp/web/WEB-INF/classes/myDB.db");
//            conn = DriverManager.getConnection("jdbc:sqlite:"+ this.getClass().getResource(  "/myDB.db"));
            ArrayList<User> users = getUsers(conn);

            boolean login = false;
            User user = new User();
            for (int i = 0; i  < users.size(); i++)
            {
                if (username.equals(users.get(i).getUser_name()) && password.equals(users.get(i).getPassword()))
                {
                    login = true;
                    user = users.get(i);
                    break;
                }
            }

            for (int i = 0; i < users.size(); i++)
            {
                if (login == true)
                {
                    request.setAttribute("fullName" , user.getFirst_name() + " " +  user.getLast_name());
                    request.setAttribute("emps" , getEmps(conn));
                    request.setAttribute("id" , getCurrentEmpID(conn));
                    String url = "/emps.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else
                {
                    request.setAttribute("error" , "username or password is wrong , please try again!. ");
                    String url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }
            }


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
                System.out.println(ex.getMessage());
            }
        }

    }

    public ArrayList<User> getUsers(Connection conn) throws SQLException {
        Statement s = conn.createStatement();
        String query = "select * from users;";
        ResultSet rs =  s.executeQuery(query);

        ArrayList<User> users = new ArrayList<>();
        while(rs.next())
        {
            User u = new User();

            u.setUser_id(rs.getInt(1));
            u.setFirst_name(rs.getString(2));
            u.setLast_name(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setBirthday(rs.getString(5));
            u.setPhone_number(rs.getString(6));
            u.setUser_name(rs.getString(7));
            u.setPassword(rs.getString(8));
            u.setConnect_date(rs.getString(9));

            users.add(u);
        }

        return users;
    }

    public int getCurrentEmpID(Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(emp_id) from employees;");
        int id = 0;
        while (resultSet.next())
            id = resultSet.getInt(1)+1;

        return id;
    }

    public ArrayList<Emp> getEmps(Connection conn) throws SQLException {
        Statement s = conn.createStatement();
        String query = "select * from employees";
        ResultSet rs =  s.executeQuery(query);

        ArrayList<Emp> emps = new ArrayList<>();
        while(rs.next())
        {
            Emp emp = new Emp();

            emp.setEmp_id(rs.getInt(1));
            emp.setEmp_name(rs.getString(2));
            emp.setEmp_image(rs.getString(3));
            emp.setBase_salary(rs.getDouble(4));
            emp.setChanging(rs.getDouble(5));
            emp.setAllowance(rs.getDouble(6));
            emp.setSalary(rs.getDouble(7));
            emp.setAdditional(rs.getDouble(8));
            emp.setCompany_insurance(rs.getDouble(9));
            emp.setEmp_insurance(rs.getDouble(10));
            emp.setWork_days(rs.getInt(11));
            emp.setAdvances(rs.getDouble(12));
            emp.setSalary_deduction(rs.getDouble(13));
            emp.setTotal_salary(rs.getDouble(14));
            emp.setDept_location_id(rs.getInt(15));

            emps.add(emp);
        }

        return emps;
    }


}
