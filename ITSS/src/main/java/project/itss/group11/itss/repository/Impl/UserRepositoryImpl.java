package project.itss.group11.itss.repository.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group11.itss.Until.ConnectionPool;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Account;
import project.itss.group11.itss.repository.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String getPassQuery = "select password from users where id = ?";
    @Override
    public String getPass(String username) {
        String pass = null;
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement stmt = connection.prepareStatement(getPassQuery);
            stmt.setInt(1,Integer.parseInt(username));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                pass = rs.getString(1);
            }
            rs.close();
            stmt.close();
            Constant.pool.releaseConnection(connection);
        }catch (SQLException ex) {
            logger.error("User: " + username + " - Error when get pass: ", ex);
            throw new RuntimeException(ex);
        }
        return pass;
    }


}
