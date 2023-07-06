package project.itss.group8.itss.subsystem.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.itss.group8.itss.utils.Constant;
import project.itss.group8.itss.model.Worker;
import project.itss.group8.itss.subsystem.ViewAllWorkersSS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllWorkersSSImpl implements ViewAllWorkersSS {
    // SS stands for subsystem
    private static final Logger logger = LogManager.getLogger(ViewAllWorkersSSImpl.class);
    private static final String getAllWorkersQuery = "select * from worker";
    private static final String getWorkerQuery = "select * from worker where workerName = ?";

    @Override
    public ObservableList<Worker> getAllWorkers() {
        ObservableList<Worker> allWorkers = FXCollections.observableArrayList();
        try{
            Connection connection = Constant.pool.getConnection();
            // ps - prepared statement
            PreparedStatement ps = connection.prepareStatement(getAllWorkersQuery);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Worker worker = new Worker(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6),
                        resultSet.getDouble(7)
                );
                allWorkers.add(worker);
            }
        }catch (Exception e){
            logger.error("Error in getAllWorkers: ", e);
        }
        return allWorkers;
    }

    @Override
    public Worker getWorker(String workerName) {
        Worker worker = new Worker(workerName);

        try {
            Connection connection = Constant.pool.getConnection();
            // ps - prepared statement
            PreparedStatement ps = connection.prepareStatement(getWorkerQuery);
            ps.setString(1, workerName);
            // rs - result set
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst() ) {
                return null;
            }

            while (rs.next()) {
                worker.setWorkerID(rs.getString(3));
                worker.setWorkerUnit(rs.getInt(4));
                worker.setWorkMonth(rs.getInt(5));
                worker.setWorkerTotalWorkHour(rs.getDouble(6));
                worker.setWorkerTotalOvertimeHour(rs.getInt(7));
            }

            rs.close();
            ps.close();
            Constant.pool.releaseConnection(connection);
        } catch (SQLException ex) {
            logger.error("getWorker name - " + workerName + " error", ex);
            throw new RuntimeException(ex);
        }

        return worker;
    }
}
