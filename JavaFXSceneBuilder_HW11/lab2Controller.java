package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class lab2Controller implements EventHandler<ActionEvent> {

	public Button btn_cl;
	public Button btn_rl;
	public Button btn_rd;
	public TextField tf_input;
	public TextField tf_output;
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == btn_cl)
		{
			tf_output.setText(String.format("%s", tf_input.getText().length()));
		}
		else if (event.getSource() == btn_rl)
		{
			tf_output.setText(
					new StringBuilder(tf_input.getText()).reverse().toString()
						);
		}
		else
		{
			String onR = "";
			String si = tf_input.getText();
			for(int i=0;i<si.length();i++)
			{
				if(!onR.contains(""+si.charAt(i)))
				{
					onR += si.charAt(i);
				}
			}
			tf_output.setText(onR);
		}
	}

}
