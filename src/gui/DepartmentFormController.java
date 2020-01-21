package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {
	
	private Department entity;
	
	@FXML
	private TextField txtId; 

	@FXML
	private TextField txtName;
	
	@FXML
	private Label lblError; 

	@FXML
	private Button btSave; 

	@FXML
	private Button btCancel;
	
	@FXML
	private void onBtSaveAction() {
		System.out.println("Salvar");
	}

	@FXML
	private void onBtCancelAction() {
		System.out.println("Cancelar");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLenght(txtName, 30);
	}
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade Vazia!");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(String.valueOf(entity.getName()));
	}
}
