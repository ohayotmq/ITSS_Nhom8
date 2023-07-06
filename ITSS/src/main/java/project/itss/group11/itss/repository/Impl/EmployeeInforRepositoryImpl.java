package project.itss.group11.itss.repository.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Employee;
import project.itss.group11.itss.repository.EmployeeInforRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInforRepositoryImpl implements EmployeeInforRepository {

    private static final Logger logger = LogManager.getLogger(EmployeeInforRepositoryImpl.class);
    private static final String getEmployeeInforQuery = "select * from employee where id = ?";
    private static final String getEmployeeUnitInforQuery = "select * from employee";
    @Override
    public ObservableList<Employee> getEmployeeUnitInfor(int unitID) {
        ObservableList<Employee> listEmployee = FXCollections.observableArrayList();
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(getEmployeeUnitInforQuery);
            //pstmt.setInt(1,unitID);
            logger.info("Unit ID: " + unitID);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee(resultSet.getInt(1),resultSet.getString(2), resultSet.getTimestamp(3).toLocalDateTime().toLocalDate(), resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6));
                listEmployee.add(employee);
            }
        }catch (Exception e){
            logger.error("Error in getEmployeeUnitInfor: ", e);
        }
        return listEmployee;
    }

    @Override
    public Employee getInforUser(int id) {
        Employee employee = new Employee(id);
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement stmt = connection.prepareStatement(getEmployeeInforQuery);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst() ) {    
                return null; 
            } 
            while (rs.next()){
                employee.setName(rs.getString(2));
                employee.setBirthDate( rs.getDate(3).toLocalDate());
                employee.setUnit(rs.getInt(4));
                employee.setRole(rs.getInt(5));
                employee.setGender(rs.getInt(6));
            }
            rs.close();
            stmt.close();
            Constant.pool.releaseConnection(connection);
        }catch (SQLException ex) {
            logger.error("getInforUser id: " + id + " error", ex);
            throw new RuntimeException(ex);
        }
        return employee;
    }

}
