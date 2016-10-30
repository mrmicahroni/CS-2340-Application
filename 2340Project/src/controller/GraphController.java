package controller;

import database.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.Scene;

import model.Location;
import model.Report;
import model.WorkerReport;

/**
 * Controller for the dialog for generating a history graph.
 * @author Alok Tripathy
 *
 */
public class GraphController extends DialogController {

	@FXML
	private TextField longitudeField;
	
	@FXML
	private TextField latitudeField;
	
	@FXML
	private TextField radiusField;
	
	
	@FXML
	public void handleOKPressed() {
		String longitudeString = longitudeField.getText();
		String latitudeString = latitudeField.getText();
		String dummyName = "dumbdumbname";
		Location inputLocation = Location.makeFromString(longitudeString + "\t" + latitudeString + "\t" + dummyName);
		// Will substitute with getNearbyReports(inputLocation) at later date.
		ObservableList<Report> reports = Model.instance().getReports();
		
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
		
		lineChart.setTitle("History Graph");
		
		XYChart.Series<String, Number> virusSeries = new XYChart.Series<>();
		virusSeries.setName("Virus PPM");
		
		for (Report report : reports) {
			if (report instanceof WorkerReport) {
				WorkerReport workerReport = (WorkerReport)report;
				String timestamp = workerReport.getTimestamp().toString();
				double virusPPM = workerReport.getVirusPPM();
				virusSeries.getData().add(new XYChart.Data<>(timestamp, virusPPM));
			}
		}
		
		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(virusSeries);
		
		super.dialogStage.setScene(scene);
		super.dialogStage.show();
	}
    /**
     * Handler for when the cancel button on the register dialog is clicked.
     */
    @FXML
    public void handleCancelPressed() {
        dialogStage.close();
    }
}
