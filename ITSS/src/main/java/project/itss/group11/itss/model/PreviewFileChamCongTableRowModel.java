package project.itss.group11.itss.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class PreviewFileChamCongTableRowModel {
	private final SimpleIntegerProperty id;
	private final ObjectProperty<LocalDateTime> timestamp = new SimpleObjectProperty<LocalDateTime>();
	private final SimpleIntegerProperty device;
	private final SimpleStringProperty name;
	private final SimpleStringProperty role;
	private final SimpleIntegerProperty unit;
	private final ObjectProperty<LocalDate> birtdate = new SimpleObjectProperty<LocalDate>();;
	private final SimpleStringProperty gender;
	private CheckBox selectCheckBox;
	
	public CheckBox getSelectCheckBox() {
		return selectCheckBox;
	}

	public void setSelectCheckBox(CheckBox selectCheckBox) {
		this.selectCheckBox = selectCheckBox;
	}

	// special getter
	public int getId() {
		return id.get();
	}

	public LocalDateTime getTimestamp() {
		return timestamp.get();
	}

	public int getDevice() {
		return device.get();
	}
	
	public String getName() {
		return name.get();
	}

	public String getRole() {
		return role.get();
	}

	public int getUnit() {
		return unit.get();
	}

	public LocalDate getBirtdate() {
		return birtdate.get();
	}

	public String getGender() {
		return gender.get();
	}

	public PreviewFileChamCongTableRowModel(int id, LocalDateTime timestamp, int device, String name, String role, int unit, LocalDate birtdate, String gender, Boolean isValid) {
		this.id = new SimpleIntegerProperty(id);
		this.timestamp.set(timestamp);
		this.device = new SimpleIntegerProperty(device);
		this.name = new SimpleStringProperty(name);
		this.role = new SimpleStringProperty(role);
		this.unit = new SimpleIntegerProperty(unit);
		this.gender = new SimpleStringProperty(gender);
		this.birtdate.set(birtdate);
		selectCheckBox = new CheckBox();
		if(!isValid) {
			selectCheckBox.setDisable(true);
			selectCheckBox.setText("ID does not exist");
		}
	}

}
