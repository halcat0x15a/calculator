package calculator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculator extends Application {

	final Label display = new Label();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane panel = new GridPane();
		IntStream.range(0, 9).forEach(
				i -> panel.add(createButton(i + 1), i % 3, 2 - i / 3));
		String[] arithmeticOperations = new String[] { "+", "-", "*", "/" };
		IntStream.range(0, 4).forEach(
				i -> panel.add(createButton(arithmeticOperations[i]), 3, i));
		panel.add(createButton("0"), 0, 3);
		Button equal = new Button("=");
		equal.addEventHandler(ActionEvent.ACTION, e -> {
			Parser parser = new Parser(new StringReader(display.getText()));
			try {
				display.setText(Double.toString(parser.expr()));
			} catch (ParseException ex) {
				display.setText("Error");
			}
		});
		panel.add(equal, 2, 3);
		Button clear = new Button("C");
		clear.addEventHandler(ActionEvent.ACTION, e -> display.setText(""));
		panel.add(clear, 4, 0);
		BorderPane root = new BorderPane();
		root.setTop(display);
		BorderPane.setAlignment(display, Pos.CENTER_RIGHT);
		root.setCenter(panel);
		stage.setScene(new Scene(root));
		stage.show();
	}

	public Button createButton(Object obj) {
		String text = obj.toString();
		Button button = new Button(text);
		button.addEventHandler(ActionEvent.ACTION, e -> {
			display.setText(display.getText() + text);
		});
		return button;
	}

}
