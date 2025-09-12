package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * This demonstrates various GWT UI components and concepts.
 */
public class AppEntryPoint implements EntryPoint {

    // UI Components - declared as instance variables for access across methods
    private FormField nameFormField;
    private TextBox emailField;
    private PasswordTextBox passwordField;
    private PasswordTextBox confirmPasswordField;
    private ListBox countryList;
    private CheckBox termsCheckbox;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private Button submitButton;
    private Button clearButton;
    private HTML responseArea;
    private Label errorLabel;
    private VerticalPanel mainPanel;

    public void onModuleLoad() {
        createUI();
        setupEventHandlers();
        addToPage();
    }

    /**
     * Step 1: Create all UI components
     * This demonstrates GWT's widget system
     */
    private void createUI() {
        // Main container - VerticalPanel stacks widgets vertically
        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(15);
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        
        // Title with styling
        HTML title = new HTML("<h1 style='color: #2c3e50; text-align: center;'>User Registration Form</h1>");
        mainPanel.add(title);
        
        // Create form fields
        createFormFields();
        
        // Create buttons
        createButtons();
        
        // Create response areas
        createResponseAreas();
    }

    /**
     * Step 2: Create individual form components
     * Learning: Different GWT input widgets
     */
    private void createFormFields() {

        // FormField nameField = new FormField("Full Name:", new TextBox());

        // Name field
        nameFormField = new FormField("Full Name:", new TextBox());
        nameFormField.setWidth("300px");
        nameFormField.setText("Enter your full name");
        
        // Email field
        emailField = new TextBox();
        emailField.setWidth("300px");
        emailField.setText("Enter your email address");
        
        // Password fields
        passwordField = new PasswordTextBox();
        passwordField.setWidth("300px");
        passwordField.setText("Enter password");
        
        confirmPasswordField = new PasswordTextBox();
        confirmPasswordField.setWidth("300px");
        confirmPasswordField.setText("Confirm password");
        
        // Country dropdown
        countryList = new ListBox();
        countryList.setWidth("300px");
        countryList.addItem("Select Country", "");
        countryList.addItem("United States", "US");
        countryList.addItem("Canada", "CA");
        countryList.addItem("United Kingdom", "UK");
        countryList.addItem("India", "IN");
        countryList.addItem("Australia", "AU");
        
        // Gender radio buttons (must be in same group)
        maleRadio = new RadioButton("gender", "Male");
        femaleRadio = new RadioButton("gender", "Female");
        
        // Terms checkbox
        termsCheckbox = new CheckBox("I agree to the terms and conditions");
    }

    /**
     * Step 3: Create buttons with different styles
     * Learning: Button styling and grouping
     */
    private void createButtons() {
        submitButton = new Button("Submit Registration");
        submitButton.addStyleName("btn btn-primary");
        submitButton.setWidth("150px");
        
        clearButton = new Button("Clear Form");
        clearButton.addStyleName("btn btn-secondary");
        clearButton.setWidth("150px");
    }

    /**
     * Step 4: Create response areas
     * Learning: Different ways to display text
     */
    private void createResponseAreas() {
        responseArea = new HTML();
        responseArea.setWidth("400px");
        
        errorLabel = new Label();
        errorLabel.setStyleName("error");
        errorLabel.setWidth("400px");
    }

    /**
     * Step 5: Set up event handlers
     * Learning: GWT event system
     */
    private void setupEventHandlers() {
        // Submit button click handler
        submitButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                handleSubmit();
            }
        });
        
        // Clear button click handler
        clearButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clearForm();
            }
        });
        
//        // Real-time validation on name field
//        nameField.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                validateName();
//            }
//        });
//
//        // Real-time validation on email field
//        emailField.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                validateEmail();
//            }
//        });
//
//        // Password confirmation validation
//        confirmPasswordField.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                validatePasswordMatch();
//            }
//        });
    }

    /**
     * Step 6: Build the complete UI layout
     * Learning: GWT layout panels and organization
     */
    private void addToPage() {
        // Create a form panel to organize fields
        FormPanel formPanel = new FormPanel();
        VerticalPanel formContent = new VerticalPanel();
        formContent.setSpacing(10);
        formContent.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        
        // Add form fields with labels
        formContent.add(nameFormField);
        formContent.add(createFieldWithLabel("Email:", emailField));
        formContent.add(createFieldWithLabel("Password:", passwordField));
        formContent.add(createFieldWithLabel("Confirm Password:", confirmPasswordField));
        formContent.add(createFieldWithLabel("Country:", countryList));
        
        // Add gender selection
        VerticalPanel genderPanel = new VerticalPanel();
        genderPanel.add(new Label("Gender:"));
        HorizontalPanel radioPanel = new HorizontalPanel();
        radioPanel.setSpacing(10);
        radioPanel.add(maleRadio);
        radioPanel.add(femaleRadio);
        genderPanel.add(radioPanel);
        formContent.add(genderPanel);
        
        // Add terms checkbox
        formContent.add(termsCheckbox);
        
        // Add buttons
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setSpacing(10);
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        formContent.add(buttonPanel);
        
        formPanel.add(formContent);
        mainPanel.add(formPanel);
        
        // Add response areas
        mainPanel.add(responseArea);
        mainPanel.add(errorLabel);
        
        // Add to page
        RootPanel.get().add(mainPanel);
        
        // Focus first field
        nameFormField.setFocus(true);
    }

    /**
     * Helper method to create labeled fields
     * Learning: Reusable UI components
     */
    private VerticalPanel createFieldWithLabel(String labelText, Widget widget) {
        VerticalPanel fieldPanel = new VerticalPanel();
        Label label = new Label(labelText);
        label.addStyleName("field-label");
        fieldPanel.add(label);
        fieldPanel.add(widget);
        return fieldPanel;
    }

    /**
     * Step 7: Form validation
     * Learning: Client-side validation in GWT
     */
    private boolean validateForm() {
        clearErrors();
        boolean isValid = true;
        
        // Name validation
        if (nameFormField.getText().trim().isEmpty()) {
            nameFormField.showError("Name is required");
            isValid = false;
        } else if (nameFormField.getText().trim().length() < 2) {
            nameFormField.showError("Name must be at least 2 characters");
            isValid = false;
        }
        
        // Email validation
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showFieldError(emailField, "Email is required");
            isValid = false;
        } else if (!email.contains("@") || !email.contains(".")) {
            showFieldError(emailField, "Please enter a valid email address");
            isValid = false;
        }
        
        // Password validation
        String password = passwordField.getText();
        if (password.isEmpty()) {
            showFieldError(passwordField, "Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            showFieldError(passwordField, "Password must be at least 6 characters");
            isValid = false;
        }
        
        // Password confirmation
        if (!password.equals(confirmPasswordField.getText())) {
            showFieldError(confirmPasswordField, "Passwords do not match");
            isValid = false;
        }
        
        // Country validation
        if (countryList.getSelectedIndex() == 0) {
            showFieldError(countryList, "Please select a country");
            isValid = false;
        }
        
        // Terms validation
        if (!termsCheckbox.getValue()) {
            showError("You must agree to the terms and conditions");
            isValid = false;
        }
        
        return isValid;
    }

    /**
     * Real-time validation methods
     * Learning: Event-driven validation
     */
    private void validateName() {
        String name = nameFormField.getText().trim();
        if (!name.isEmpty() && name.length() < 2) {
            showFieldError(nameFormField, "Name must be at least 2 characters");
        } else {
            clearFieldError(nameFormField);
        }
    }

    private void validateEmail() {
        String email = emailField.getText().trim();
        if (!email.isEmpty() && (!email.contains("@") || !email.contains("."))) {
            showFieldError(emailField, "Please enter a valid email address");
        } else {
            clearFieldError(emailField);
        }
    }

    private void validatePasswordMatch() {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (!confirmPassword.isEmpty() && !password.equals(confirmPassword)) {
            showFieldError(confirmPasswordField, "Passwords do not match");
        } else {
            clearFieldError(confirmPasswordField);
        }
    }

    /**
     * Step 8: Handle form submission
     * Learning: Data collection and API communication
     */
    private void handleSubmit() {
        if (!validateForm()) {
            return;
        }
        
        // Collect form data
        String formData = collectFormData();
        
        // Show loading state
        submitButton.setEnabled(false);
        submitButton.setText("Submitting...");
        responseArea.setHTML("Submitting registration...");
        
        // Simulate API call (we'll use the existing backend)
        callBackendApi(formData);
    }

    /**
     * Collect form data into a structured format
     * Learning: Data handling in GWT
     */
    private String collectFormData() {
        StringBuilder data = new StringBuilder();
        data.append("Name: ").append(nameFormField.getText().trim()).append("\n");
        data.append("Email: ").append(emailField.getText().trim()).append("\n");
        data.append("Country: ").append(countryList.getSelectedValue()).append("\n");
        data.append("Gender: ").append(maleRadio.getValue() ? "Male" : "Female").append("\n");
        data.append("Terms Accepted: ").append(termsCheckbox.getValue() ? "Yes" : "No");
        return data.toString();
    }

    /**
     * Call backend API with form data
     * Learning: HTTP communication in GWT
     */
    private void callBackendApi(String formData) {
        String url = GWT.getModuleBaseURL() + "api/hello";
        
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        
        try {
            builder.sendRequest(null, new RequestCallback() {
                public void onResponseReceived(Request request, Response response) {
                    submitButton.setEnabled(true);
                    submitButton.setText("Submit Registration");
                    
                    if (200 == response.getStatusCode()) {
                        showSuccess("Registration submitted successfully!\n\n" + formData);
                    } else {
                        showError("Server error: " + response.getStatusCode());
                    }
                }
                
                public void onError(Request request, Throwable exception) {
                    submitButton.setEnabled(true);
                    submitButton.setText("Submit Registration");
                    showError("Request failed: " + exception.getMessage());
                }
            });
        } catch (RequestException e) {
            submitButton.setEnabled(true);
            submitButton.setText("Submit Registration");
            showError("Request exception: " + e.getMessage());
        }
    }

    /**
     * Clear the form
     * Learning: Widget state management
     */
    private void clearForm() {
        nameFormField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        countryList.setSelectedIndex(0);
        maleRadio.setValue(false);
        femaleRadio.setValue(false);
        termsCheckbox.setValue(false);
        clearErrors();
        responseArea.setHTML("");
        nameFormField.setFocus(true);
    }

    /**
     * Error handling methods
     * Learning: User feedback in GWT
     */
    private void showFieldError(Widget widget, String message) {
        widget.addStyleName("field-error");
        // You could also show tooltips or inline error messages
    }

    private void clearFieldError(Widget widget) {
        widget.removeStyleName("field-error");
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    private void showSuccess(String message) {
        responseArea.setHTML("<div style='color: green; font-weight: bold; padding: 10px; border: 1px solid green; border-radius: 4px; background-color: #f0fff0;'>" +
            "✓ " + message.replace("\n", "<br/>") + "</div>");
        errorLabel.setVisible(false);
    }

    private void clearErrors() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
        // Clear field errors
        nameFormField.removeStyleName("field-error");
        emailField.removeStyleName("field-error");
        passwordField.removeStyleName("field-error");
        confirmPasswordField.removeStyleName("field-error");
        countryList.removeStyleName("field-error");
    }
}
