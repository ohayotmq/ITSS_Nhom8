package project.itss.group8.itss.subsystem;

import javafx.collections.ObservableList;
import project.itss.group8.itss.model.Worker;

public interface ViewAllWorkersSS {
    // SS stands for subsystem
    ObservableList<Worker> getAllWorkers();
    Worker getWorker(String workerID);
}
