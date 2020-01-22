package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.SellerService;

public class SellerFormController implements Initializable {
	
	private Seller entity;
	private SellerService service;
	
	private List<DataChangeListener> dataChangeListener = new ArrayList<>();
	
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
	private void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula!");
		}
		if (service == null) {
			throw new IllegalStateException("Serviço está nulo!");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		} catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar os dados", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListener) {
			listener.onDataChanged();
		}
	}

	private Seller getFormData() {
		Seller sel = new Seller();
		
		ValidationException exception = new ValidationException("Erro de Validação");
		
		sel.setId(Utils.tryParseToInt(txtId.getText()));
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "O campo não pode ser vazio");
		}
		sel.setName(txtName.getText());
		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		return sel;
	}

	@FXML
	private void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLenght(txtName, 30);
	}
	
	public void setSeller(Seller entity) {
		this.entity = entity;
	}

	public void setSellerService(SellerService service) {
		this.service = service;
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade Vazia!");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(String.valueOf(entity.getName()));
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListener.add(listener);
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		if (fields.contains("name")) {
			lblError.setText(errors.get("name"));
		}
	}
}
