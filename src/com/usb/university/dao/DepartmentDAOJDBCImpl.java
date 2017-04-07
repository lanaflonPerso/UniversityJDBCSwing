/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usb.university.dao;

import com.usb.university.model.Department;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author "INNOVAPPS.CO S.A.S."
 */
public class DepartmentDAOJDBCImpl implements DepartmentDAO {

    // Not thread-safe
    private Connection con = null;

    // package level access
    DepartmentDAOJDBCImpl() {
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String username = "university";
        String password = "1234";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    @Override
    public void create(Department dept) throws SQLException {
        String query = "INSERT INTO DEPARTMENTS "
                + "(DEPT_NAME, BUILDING, BUDGET) "
                + "VALUES (? , ? , ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, dept.getDeptName());
            stmt.setString(2, dept.getBuilding());
            stmt.setDouble(3, dept.getBudget());
            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Error adding deparment");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error adding department in DAO", se);
        }
    }

    @Override
    public void update(String id, Department dept) throws SQLException {
        String query = "UPDATE DEPARTMENTS "
                + "SET BUILDING= ?, BUDGET= ?"
                + "WHERE DEPT_NAME= ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, dept.getBuilding());
            stmt.setDouble(2, dept.getBudget());
            stmt.setString(3, dept.getDeptName());
            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Error updating department");
            }
        } catch (SQLException se) {
            throw new SQLException("Error updating department in DAO", se);
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        Department dept = findById(id);
        if (dept == null) {
            throw new SQLException("Department dept_name: " + id + " does not exist to delete.");
        }
        String query = "DELETE FROM DEPARTMENTS WHERE DEPT_NAME= ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {  
            stmt.setString(1, id);
            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Error deleting department");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error deleting department in DAO", se);
        }
    }

    @Override
    public Department findById(String id) throws SQLException {
        String query = "SELECT * FROM DEPARTMENTS WHERE DEPT_NAME = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return (new Department(rs.getString("DEPT_NAME"), rs.getString("BUILDING"),
                    rs.getDouble("BUDGET")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error finding department in DAO", se);
        }
    }

    @Override
    public List<Department> findAll() throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM DEPARTMENTS";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<Department> depts = new ArrayList<>();
            // Iterate through the results and create Department objects
            while (rs.next()) {
                depts.add(new Department(rs.getString("DEPT_NAME"), rs.getString("BUILDING"),
                        rs.getDouble("BUDGET")));
            }
            return depts;
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error getting all departments in DAO: " + se.getMessage(), se);
        }
    }

    @Override
    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println("Exception closing Connection: " + se);
        }
    }

}
