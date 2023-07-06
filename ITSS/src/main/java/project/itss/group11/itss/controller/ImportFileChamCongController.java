package project.itss.group11.itss.controller;

import project.itss.group11.itss.view.ImportFileChamCongView;
import project.itss.group11.itss.view.QLNSView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javafx.scene.input.MouseEvent;
import project.itss.group11.itss.controller.TemplateController;
import project.itss.group11.itss.model.ImportFileChamCongModel;
import project.itss.group11.itss.view.TemplateView;

public class ImportFileChamCongController{
	private ImportFileChamCongView importFileChamCongView;
	private ImportFileChamCongModel importFileChamCongModel;
	public ImportFileChamCongController(ImportFileChamCongView importFileChamCongView) {
		this.importFileChamCongView = importFileChamCongView;
	}
	
	public void handleCsvInput(File file) {
		System.out.println("Handle csv input");
		importFileChamCongModel = new ImportFileChamCongModel();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (file != null) {
			 //openFile(file);
			CSVReader reader = new CSVReader(fileReader);
			List<String[]> rows = null;
			try {
				rows = reader.readAll();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			importFileChamCongModel.inputRows(rows);
			importFileChamCongModel.checkDuplicate();
			System.out.println("Handled csv input");
		}
	}
	
	public void handleShowTable() {
		importFileChamCongView.showTable(importFileChamCongModel.getTableRows(), importFileChamCongModel.getIsDuplicate());
	}
	
	public Boolean handleImport(ArrayList<Boolean> isSelected) {
		return(importFileChamCongModel.importLogInforList(isSelected));
	}
}
