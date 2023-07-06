package project.itss.group8.itss.helper.Impl;

import project.itss.group8.itss.helper.IOpenFileLocation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenFileLocation implements IOpenFileLocation {
    @Override
    public void openFileLocation(File folder) {
        try {
            Desktop.getDesktop().open(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}