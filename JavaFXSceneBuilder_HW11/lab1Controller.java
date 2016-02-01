package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class lab1Controller implements EventHandler<ActionEvent> {

	@FXML
	private TextField tf_name; 
	public TextField tf_street;
	public TextField tf_city;
	public TextField tf_state;
	public TextField tf_zip;
	public Button btn_submit;
	public Button btn_next;
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == btn_submit)
		{
			System.out.println(tf_name.getText()+"\n"+tf_street.getText()+"\n"+
					tf_city.getText()+", "+tf_state.getText()+" "+tf_zip.getText());
		}
		if(event.getSource() == btn_next)
		{
			try {
				Stage stage = new Stage();
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("lab2.fxml")));
				 stage.setScene(scene);
				 stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
