package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{
	
	@FXML	
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void OnMenuItemSellerAction() {
		System.out.println("Vendedores");
	}

	@FXML
	public void OnMenuItemDepartmentAction() {
		System.out.println("Departamentos");
	}

	@FXML
	public void OnMenuItemAboutAction() {
		System.out.println("Sobre");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
