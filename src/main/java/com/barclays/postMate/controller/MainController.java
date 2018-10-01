package com.barclays.postMate.controller;

import com.barclays.postMate.AlertHelper;
import com.barclays.postMate.service.RestService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import org.fxmisc.richtext.StyleClassedTextArea;


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

}
