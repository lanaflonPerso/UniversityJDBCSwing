/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usb.university.dao;

import com.usb.university.model.Department;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author "INNOVAPPS.CO S.A.S."
 */
public interface DepartmentDAO extends AutoCloseable {

    public void create(Department dept) throws SQLException;

    public void update(String id, Department dept) throws SQLException;

    public void delete(String id) throws SQLException;

    public Department findById(String id) throws SQLException;

    public List<Department> findAll() throws SQLException;

}
