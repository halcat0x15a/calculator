package calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.StringReader;

public class Calculator extends Application {

	@FXML
	private TextField display;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(
				"calculator.fxml"))));
		stage.show();
	}

	@FXML
	protected void input(ActionEvent event) {
		Button source = (Button) event.getSource();
		display.setText(display.getText() + source.getText());
	}

	@FXML
	protected void clear(ActionEvent event) {
		display.setText("");
	}

	@FXML
	protected void calc(ActionEvent event) {
		Parser parser = new Parser(new StringReader(display.getText()));
		try {
			display.setText(parser.expr().toString());
		} catch (Throwable ex) {
			display.setText(ex.getMessage());
		}
	}

}
