package project.itss.group11.itss.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.itss.group11.itss.model.LogInfor;
import project.itss.group11.itss.repository.Impl.EmployeeInforRepositoryImpl;
import project.itss.group11.itss.repository.Impl.LogInforRepositoryImpl;
import project.itss.group11.itss.repository.EmployeeInforRepository;
import project.itss.group11.itss.repository.LogInforRepository;

public class ImportFileChamCongModel {
	private ArrayList<LogInfor> writeToDBList = new ArrayList<LogInfor>();
	private ArrayList<LogInfor> inputList = new ArrayList<LogInfor>();
	private ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
	private ArrayList<Boolean> isDuplicate = new ArrayList<Boolean>();
	private ObservableList<PreviewFileChamCongTableRowModel> tableRows = FXCollections.observableArrayList();
	private EmployeeInforRepository employeeInforRepository = new EmployeeInforRepositoryImpl();
	private LogInforRepository logInforRepository = new LogInforRepositoryImpl();
	public ObservableList<PreviewFileChamCongTableRowModel> getTableRows() {
		return tableRows;
	}

	public ArrayList<LogInfor> getInputList() {
		return inputList;
	}

	public ArrayList<Boolean> getIsDuplicate() {
		return isDuplicate;
	}

	public ImportFileChamCongModel() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean importLogInforList(ArrayList<Boolean> isSelected) {
		for(int i=0; i<isSelected.size(); i++) {
			if(isSelected.get(i))
				writeToDBList.add(inputList.get(i));
		}
		int isSuccess[] = logInforRepository.importLogCC(writeToDBList);
		for(int i: isSuccess)
			if(i<0)
				return false;
		return true;
	}
	
	
	public void checkDuplicate() {
		for(LogInfor logInfor: inputList) {
			if(logInforRepository.checkDuplicate(logInfor)) {
				isDuplicate.add(true);
				System.out.println("Is dup");
			}
			else {
				isDuplicate.add(false);
				System.out.println("Is not dup");
			}
		}
	}
	
	public void inputRows(List<String[]> rows) {
		for(String[] row: rows) {
//			System.out.println("Input a row");
			
			int id = Integer.parseInt(row[0]);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime timeStamp = LocalDateTime.parse(row[1], formatter);
			int device = Integer.parseInt(row[2]);
			
			LogInfor logInfor = new LogInfor();
			logInfor.setEmployeeID(id);
			logInfor.setTimeStamp(timeStamp);
			logInfor.setDevice(device);
			inputList.add(logInfor);
			
			// Lay thong tin tu he thong qlns
			Employee employee = employeeInforRepository.getInforUser(id);
			String name = null;
			String role = null;
			int unit = -1;
			String gender = null;
		    LocalDate birthDate = null;
		    Boolean isValid = false;
			if(employee != null) {
				name = employee.getName();
				
				switch(employee.getRole()) {
				case 1:
					role = "QLNS";	break;
				case 2:
					role = "Trưởng đơn vị";	break;
				case 3:
					role = "Nhân viên";	break;
				}
				
				unit = employee.getUnit();
				
				if(employee.getGender()==0) 
					gender = "Nữ";
				else
					gender = "Nam";
				
				birthDate = employee.getBirthDate();
				
				isValid = true;
			}
			PreviewFileChamCongTableRowModel tableRow = new PreviewFileChamCongTableRowModel(id, timeStamp, device, name, role, unit, birthDate, gender, isValid);
			tableRows.add(tableRow);
				
//			System.out.println("Inputed a row");
		}
	}
}
