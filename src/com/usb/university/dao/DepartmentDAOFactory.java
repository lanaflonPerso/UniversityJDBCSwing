/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usb.university.dao;

/**
 *
 * @author "INNOVAPPS.CO S.A.S."
 */
public class DepartmentDAOFactory {
    public DepartmentDAO createDeparmentDAO() {
        return new DepartmentDAOJDBCImpl();
    }
}
