package project.itss.group8.itss.helper.Impl;

import javafx.collections.ObservableList;
import project.itss.group8.itss.helper.ViewAllOfficersHelper;
import project.itss.group8.itss.model.Officer;
import project.itss.group8.itss.subsystem.Impl.ViewAllOfficersSSImpl;
import project.itss.group8.itss.subsystem.Impl.ViewAllWorkersSSImpl;
import project.itss.group8.itss.subsystem.ViewAllOfficersSS;
import project.itss.group8.itss.subsystem.ViewAllWorkersSS;

public class ViewAllOfficersHelperImpl implements ViewAllOfficersHelper {
    // ss - sub-system
    private static final ViewAllOfficersSS allOfficersSS = new ViewAllOfficersSSImpl();

    @Override
    public ObservableList<Officer> officerObservableList() {
        return allOfficersSS.getAllOfficers();
    }
}
