package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AppEntryPoint implements EntryPoint {

    public void onModuleLoad() {
        // Create main panel
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing(10);
        
        // Title
        HTML title = new HTML("<h1>GWT + Spring Boot Application</h1>");
        mainPanel.add(title);
        
        // Description
        HTML description = new HTML("<p>This demonstrates GWT frontend communicating with Spring Boot backend.</p>");
        mainPanel.add(description);
        
        // Input field
        final TextBox nameField = new TextBox();
        nameField.setText("World");
        nameField.setWidth("200px");
        
        // Button
        final Button sendButton = new Button("Call Backend API");
        sendButton.addStyleName("btn btn-primary");
        
        // Response area
        final HTML responseArea = new HTML();
        
        // Error area
        final Label errorLabel = new Label();
        errorLabel.setStyleName("error");
        
        // Create horizontal panel for input and button
        HorizontalPanel inputPanel = new HorizontalPanel();
        inputPanel.setSpacing(5);
        inputPanel.add(new Label("Name: "));
        inputPanel.add(nameField);
        inputPanel.add(sendButton);
        
        mainPanel.add(inputPanel);
        mainPanel.add(responseArea);
        mainPanel.add(errorLabel);
        
        // Add click handler
        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callBackendApi();
            }
            
            private void callBackendApi() {
                errorLabel.setText("");
                responseArea.setHTML("Loading...");
                
                String url = GWT.getModuleBaseURL() + "api/hello";
                
                RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
                
                try {
                    builder.sendRequest(null, new RequestCallback() {
                        public void onResponseReceived(Request request, Response response) {
                            if (200 == response.getStatusCode()) {
                                responseArea.setHTML("<div style='color: green; font-weight: bold;'>" +
                                    "✓ " + response.getText() + "</div>");
                            } else {
                                showError("HTTP Error: " + response.getStatusCode());
                            }
                        }
                        
                        public void onError(Request request, Throwable exception) {
                            showError("Request failed: " + exception.getMessage());
                        }
                    });
                } catch (RequestException e) {
                    showError("Request exception: " + e.getMessage());
                }
            }
            
            private void showError(String message) {
                errorLabel.setText(message);
                responseArea.setHTML("");
            }
        });
        
        // Add main panel to page
        RootPanel.get().add(mainPanel);
        
        // Focus the input field
        nameField.setFocus(true);
    }
}
