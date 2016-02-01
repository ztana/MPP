import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class stField
{
	static TextField fs[];
	static
	{
		fs = new TextField[5];
	}
}

class myLable extends Control
{
	Control instance;
	static int ct =0;
	public myLable(String text) {
		if(text == "T")
		{
			this.instance = new TextField();
			stField.fs[ct++] = (TextField) this.instance;
			//instance.setMinWidth(30);
		}
		else if(text =="S")
		{
			this.instance = new Label();
			instance.setMinWidth(20);
		}
		else
		{
			this.instance = new Label(text);
			instance.setMinWidth(50);
		}
	}
	
}



public class FXLab extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String[] labels = {
				"Name","S","S","S","S","S","S","Street","S","S","S","S","S","S","City","S","S","T","S","T","S","T","S","S",
				"S","S","S","S","S","S","State","S","S","S","S","S","zip",
				"S","S","S","S","S","S","S","S","S","S","S","T","S","T"
				,"S","S","S","S","S","S","S","S","S","S","S","S","S","S","S","S"
				,"S","S","S","S","S","S","S","S","S","S","S","S","S","S","S","S"
				,"S","S","S","S","S","S","S","S","S"
		};
		List<String> ls = Arrays.asList(labels);
		primaryStage.setTitle("Address Form");
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(25, 0, 25, 5));
		pane.getChildren().addAll(
				ls.stream()
				.map(e->new myLable(e))
				.map(e->e.instance)
				.collect(Collectors.toList())
				);
		Button btn = new Button("Sign in");  
        pane.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++)
				{
					if(i<2)
						System.out.println(stField.fs[i].getText());
					else if(i==2)
						System.out.print(stField.fs[i].getText()+",  ");
					else
						System.out.print(stField.fs[i].getText()+"   ");
				}
			}

        });

		Scene scene = new Scene(pane, 500, 175);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

	}

}
