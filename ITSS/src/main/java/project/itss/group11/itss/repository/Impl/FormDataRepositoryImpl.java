package project.itss.group11.itss.repository.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group11.itss.Until.Constant;
import project.itss.group11.itss.model.Form;
import project.itss.group11.itss.model.FormDatabase;
import project.itss.group11.itss.model.LogInfor;
import project.itss.group11.itss.repository.FormDataRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormDataRepositoryImpl implements FormDataRepository {
    private static final Logger logger = LogManager.getLogger(LogInforRepositoryImpl.class);
    private static final String getFormData = "SELECT * FROM form JOIN logcc ON form.idlog = logcc.id WHERE status = 0";
    private static final String queryUpdate = "update form set status = ? where idform = ?";
    @Override
    public ObservableList<FormDatabase> getFormData() {

        ObservableList<FormDatabase> formDatabases = FXCollections.observableArrayList();
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement stmt = connection.prepareStatement(getFormData);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                FormDatabase formDatabase = new FormDatabase(rs.getInt("idform"),rs.getInt("idlog"),
                        rs.getInt("idnhanvien"),
                        rs.getTimestamp("timestamp").toLocalDateTime(),
                        rs.getTimestamp("newtime").toLocalDateTime(),
                        rs.getInt("device"),
                        rs.getInt("newdevice"));
                formDatabases.add(formDatabase);

                logger.info(formDatabase.getIdlog());
            }
            rs.close();
            stmt.close();
            Constant.pool.releaseConnection(connection);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return formDatabases;
    }

    @Override
    public int updateForm(int formID, int status) {
        try{
            Connection connection = Constant.pool.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(queryUpdate);
            pstmt.setInt(1,status);
            pstmt.setInt(2,formID);
            return  pstmt.executeUpdate();
        }catch (Exception e){
            logger.error("Error in updateInfor: ", e);
        }
        return 0;
    }

}

