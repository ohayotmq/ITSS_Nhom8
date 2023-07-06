package project.itss.group8.itss.helper.Impl;

import javafx.collections.ObservableList;
import project.itss.group8.itss.helper.ViewAllWorkersHelper;
import project.itss.group8.itss.model.Worker;
import project.itss.group8.itss.subsystem.Impl.ViewAllWorkersSSImpl;
import project.itss.group8.itss.subsystem.ViewAllWorkersSS;

public class ViewAllWorkersHelperImpl implements ViewAllWorkersHelper {
    // ss - sub-system
    private static final ViewAllWorkersSS allWorkersSS = new ViewAllWorkersSSImpl();

    @Override
    public ObservableList<Worker> workerObservableList() {
        return allWorkersSS.getAllWorkers();
    }
}
