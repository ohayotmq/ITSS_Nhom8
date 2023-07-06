package project.itss.group11.itss.repository.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Form;
import project.itss.group11.itss.model.LogInfor;
import project.itss.group11.itss.repository.LogInforRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class LogInforRepositoryImpl implements LogInforRepository {
    private static final Logger logger = LogManager.getLogger(LogInforRepositoryImpl.class);
    private static final String q1 = "SELECT id_employee,timestamp,device FROM logcc " +
            "WHERE EXTRACT(DAY FROM timestamp) = '?' " +
            "and EXTRACT(MONTH FROM timestamp) = '?'"+
            "and EXTRACT(YEAR FROM timestamp) = '?'" +
            "and id_employee = ?" +
            "ORDER BY EXTRACT(DAY FROM timestamp)";
    private static final String q2 = "SELECT id_employee,timestamp,device FROM logcc " +
            "WHERE EXTRACT(MONTH FROM timestamp) = ? " +
            "and EXTRACT(YEAR FROM timestamp) = ?" +
            "and id_employee=?";

    private static final String queryCheckDuplicate = "SELECT COUNT(*) FROM logcc WHERE timestamp = ? and id_employee = ? and device = ?";


    private static final String queryUpdate = "update logcc set timestamp = ? , device = ?  where id = ?";
    @Override
    public List<LogInfor> getLogInforByDay(int day,int month,int year,int employee_id) {

        List<LogInfor> logInfors = new ArrayList<>();
        try{
            Connection connection = Constant.pool.getConnection();

            PreparedStatement stmt = connection.prepareStatement(q1);

            stmt.setInt(1,day);
            stmt.setInt(2,month);
            stmt.setInt(3,year);
            stmt.setInt(4,employee_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                LogInfor logInfor = new LogInfor();
                logInfor.setEmployeeID(rs.getInt("id_employee"));
                logInfor.setTimeStamp(rs.getTimestamp("timestamp").toLocalDateTime());
                logInfor.setDevice(rs.getInt("device"));
                logInfors.add(logInfor);
            }
            rs.close();
            stmt.close();
            Constant.pool.releaseConnection(connection);
        }catch (SQLException ex) {
            logger.error("Error when getLogInforByDay: ", ex);
            throw new RuntimeException(ex);
        }
        return logInfors;
    }

    @Override
    public List<LogInfor> getLogInforByMonth(int month,int year, int employee_id) {
        List<LogInfor> logInfors = new ArrayList<>();
        try{
            Connection connection = Constant.pool.getConnection();

            PreparedStatement stmt = connection.prepareStatement(q2);

            stmt.setInt(1,month);
            stmt.setInt(2,year);
            stmt.setInt(3,employee_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                LogInfor logInfor = new LogInfor();
                logInfor.setEmployeeID(rs.getInt("id_employee"));
                logInfor.setTimeStamp(rs.getTimestamp("timestamp").toLocalDateTime());
                logInfor.setDevice(rs.getInt("device"));
                logInfors.add(logInfor);
            }
            rs.close();
            stmt.close();
            Constant.pool.releaseConnection(connection);
        }catch (Exception ex) {
            logger.error("Error when getLogInforByMonth: ", ex);
            throw new RuntimeException(ex);
        }
        logger.info("getLogByMonth: " + logInfors.size());
        return logInfors;
    }

    @Override
    public boolean checkDuplicate(LogInfor logInfor) {
        boolean result = false;
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(queryCheckDuplicate);
            pstmt.setTimestamp(1,Timestamp.valueOf(logInfor.getTimeStamp()));
            pstmt.setInt(2,logInfor.getEmployeeID());
            pstmt.setInt(3,logInfor.getDevice());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                if(resultSet.getInt(1)==1)
                    return true;
            }
        }catch (Exception e){
            logger.error("Error in checkDuplicate: ", e);
        }
        return false;
    }

    @Override
    public int[] importLogCC(List<LogInfor> logInfors) {
        int[] result = new int[0];
        try{
            Connection connection = Constant.pool.getConnection();
            Statement stmt = connection.createStatement();
            for(LogInfor logInfor : logInfors){
                String sqlQuery = "INSERT INTO logcc VALUES('" + logInfor.getId() +
                             "','" + logInfor.getTimeStamp() +
                             "','" + logInfor.getEmployeeID() +
                             "','" + logInfor.getDevice() +
                             "')";
                stmt.addBatch(sqlQuery);
            }
            result = stmt.executeBatch();
        }catch (Exception e){
            logger.error("Error in imoprtLogCC ", e);
        }
        return result;
    }

    public int updateInfor(Form form) {
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(queryUpdate);
            pstmt.setTimestamp(1,Timestamp.valueOf(form.getNewT()));
            pstmt.setInt(2,form.getNewDevice());
            pstmt.setInt(3,form.getIdlog());
            logger.info(form.getIdlog());
            return  pstmt.executeUpdate();
        }catch (Exception e){
            logger.error("Error in updateInfor: ", e);
        }
        return 0;
    }
}
