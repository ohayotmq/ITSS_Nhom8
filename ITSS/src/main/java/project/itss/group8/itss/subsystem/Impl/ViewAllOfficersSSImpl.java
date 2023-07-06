package project.itss.group8.itss.subsystem.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group8.itss.model.Officer;
import project.itss.group8.itss.model.Worker;
import project.itss.group8.itss.subsystem.ViewAllOfficersSS;
import project.itss.group8.itss.subsystem.ViewAllWorkersSS;
import project.itss.group8.itss.utils.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewAllOfficersSSImpl implements ViewAllOfficersSS {
    private static final Logger logger = LogManager.getLogger(ViewAllOfficersSSImpl.class);
    private static final String getAllOfficersQuery = "select * from officer";

    @Override
    public ObservableList<Officer> getAllOfficers() {
        ObservableList<Officer> allOfficers = FXCollections.observableArrayList();

        try {
            Connection connection = Constant.pool.getConnection();
            // ps - prepared statement
            PreparedStatement ps = connection.prepareStatement(getAllOfficersQuery);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Officer officer = new Officer(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getDouble(7)
                );
                allOfficers.add(officer);
            }
        } catch (Exception e){
            logger.error("Error in getAllOfficers: ", e);
        }

        return allOfficers;
    }
}
