package com.example.client;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;

public class FormField extends Composite {
    private String label;
    private Widget box;
    private Label errorLabel;

    private VerticalPanel mainPanel;
    public FormField(String label, Widget box) {
        this.label = label;
        this.box = box;

        mainPanel = new VerticalPanel();
        mainPanel.setSpacing(15);
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        Label labelWidget = new Label(label);
        errorLabel = new Label();

        mainPanel.add(labelWidget);
        mainPanel.add(box);
        mainPanel.add(errorLabel);

        initWidget(mainPanel);
    }

    public void showError(String error) {
        errorLabel.setText(error);
        errorLabel.setVisible(true);
        errorLabel.setStyleName("error");
    }

    public void clearError() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }

    public Widget getInputWidget() {
        return box;
    }

    // Get the text value (for TextBox, PasswordTextBox)
    public String getText() {
        if (box instanceof HasText) {
            return ((HasText) box).getText();
        }
        return "";
    }

    // Set the text value
    public void setText(String text) {
        if (box instanceof HasText) {
            ((HasText) box).setText(text);
        }
    }

    // Set focus
    public void setFocus(boolean focused) {
        if (box instanceof Focusable) {
            ((Focusable) box).setFocus(focused);
        }
    }

    // Set width
    public void setWidth(String width) {
        box.setWidth(width);
    }

    // Add value change handler (for real-time validation)
    public void addValueChangeHandler(ValueChangeHandler<String> handler) {
        if (box instanceof HasValueChangeHandlers) {
            ((HasValueChangeHandlers<String>) box).addValueChangeHandler(handler);
        }
    }

    // For ListBox operations
    public void addItem(String item) {
        if (box instanceof ListBox) {
            ((ListBox) box).addItem(item);
        }
    }

    public void addItem(String item, String value) {
        if (box instanceof ListBox) {
            ((ListBox) box).addItem(item, value);
        }
    }

    public int getSelectedIndex() {
        if (box instanceof ListBox) {
            return ((ListBox) box).getSelectedIndex();
        }
        return -1;
    }

    public String getSelectedValue() {
        if (box instanceof ListBox) {
            return ((ListBox) box).getSelectedValue();
        }
        return "";
    }

    public void setSelectedIndex(int index) {
        if (box instanceof ListBox) {
            ((ListBox) box).setSelectedIndex(index);
        }
    }
}
