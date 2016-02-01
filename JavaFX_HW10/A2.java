import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class A2 extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("String Utility");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Button cl = new Button("Count Letters");
        Button rl = new Button("Reverse Letters");
        Button rd = new Button("Remove Duplicates");
        Label li = new Label("input");
        Label lo = new Label("output");
        TextField ti = new TextField();
        TextField to = new TextField();
        
      //  grid.setGridLinesVisible(true);
        
        cl.setMinWidth(200);
        rl.setMinWidth(200);
        rd.setMinWidth(200);
        cl.setMinHeight(40);
        rl.setMinHeight(40);
        rd.setMinHeight(40);
        //grid.add(hbBtn, 1, 4);


        grid.add(cl, 0, 0);
        grid.add(rl, 0, 2);
        grid.add(rd, 0, 4);
        
        grid.add(li, 1, 0);
        grid.add(ti, 1, 1);
        grid.add(lo, 1, 2);
        grid.add(to, 1, 3);
        
        cl.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				to.setText(
					String.format("%s", ti.getText().length())
						);
			}});
        rl.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				to.setText(
					new StringBuilder(ti.getText()).reverse().toString()
						);
			}});
        rd.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String onR = "";
				String si = ti.getText();
				for(int i=0;i<si.length();i++)
				{
					if(!onR.contains(""+si.charAt(i)))
					{
						onR += si.charAt(i);
					}
				}
				to.setText(onR);
			}});
        
        Scene scene = new Scene(grid, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        
        
	}

}
