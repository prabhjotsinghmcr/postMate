package com.barclays.postMate.controller;

import com.barclays.postMate.AlertHelper;
import com.barclays.postMate.dto.Header;
import com.barclays.postMate.service.RestService;
import com.oracle.javafx.jmx.json.JSONReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.*;
import java.nio.file.Files;


public class MainController {

    @FXML
    private TextField urlText;
    @FXML
    private StyleClassedTextArea bodyTextBox;
    @FXML
    private StyleClassedTextArea postRequestResponseTestArea;
    @FXML
    private Button submitButtonGetReq;
    @FXML
    private Button submitButtonPostReq;
    @FXML
    private Button submitButtonPutReq;
    @FXML
    private Button submitButtonDeleteReq;
    @FXML
    private Label responseStatusValueGet;
    @FXML
    private Label responseStatusValuePost;
    @FXML
    private StyleClassedTextArea postReqBodyText;
    @FXML
    private TabPane postReqTabPane;
    @FXML
    private Tab postReqResponseTab;
    @FXML
    private Button jsonUploadButton;
    @FXML
    private Label jsonFileNameLabel;
    @FXML
    private TableView<Header> postHeaderTableView;
    @FXML
    public void handleGetRequest(){
        Window owner = submitButtonGetReq.getScene().getWindow();
        if(urlText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter API URL");
            return;
        }
        String httpResponse = RestService.getInstance().processGetRequest(urlText.getText());
//        String contentEncoding = null!=httpResponse.getEntity().getContentEncoding() ? httpResponse.getEntity().getContentEncoding().toString() : null;
//        String response = null;
//        try {
//            response = EntityUtils.toString(httpResponse.getEntity(),contentEncoding);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bodyTextBox.replaceText(0,bodyTextBox.getText().length(),httpResponse);
        responseStatusValueGet.setText(httpResponse);
    }

    @FXML
    public void handlePostRequest(){
        Window owner = submitButtonGetReq.getScene().getWindow();
        if(urlText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter API URL");
            return;
        }
        String response = RestService.getInstance().postRequest(urlText.getText(),postReqBodyText.getText());
        SingleSelectionModel<Tab> singleSelectionModel = postReqTabPane.getSelectionModel();
        singleSelectionModel.select(postReqResponseTab);
        responseStatusValuePost.setText(response);
        postRequestResponseTestArea.replaceText(0,0,response);

    }

    @FXML
    public void handleDeleteRequest(){
        Window owner = submitButtonGetReq.getScene().getWindow();
        if(urlText.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter API URL");
            return;
        }

    }

    @FXML
    public void uploadJson(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(jsonUploadButton.getScene().getWindow());
        if (null !=file && file.exists()){
            String extension = file.getName().substring(file.getName().lastIndexOf("."));
            if (extension.equals(".json")){

                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    postReqBodyText.clear();
                    bufferedReader.lines().forEach(postReqBodyText::appendText);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("File Choosen:"+ file.getAbsolutePath());
        jsonFileNameLabel.setText(file.getAbsolutePath());

    }

    public void popTalble(){
         final ObservableList<Header> data = FXCollections.observableArrayList();
         data.add(new Header("key","value","Des"));
        postHeaderTableView.setEditable(true);

        TableColumn keyCol = new TableColumn("Key");
        keyCol.setCellValueFactory(new PropertyValueFactory<Header,String>("key"));

        TableColumn valueCol = new TableColumn("Value");
        valueCol.setCellValueFactory(
                new PropertyValueFactory<Header,String>("value")
        );

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setMinWidth(200);
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<Header,String>("description")
        );
        postHeaderTableView.setItems(data);
        postHeaderTableView.getColumns().addAll(keyCol, valueCol, descriptionCol);

    }

}
