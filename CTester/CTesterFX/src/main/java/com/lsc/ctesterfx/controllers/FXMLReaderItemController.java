package com.lsc.ctesterfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.lsc.ctesterfx.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

/**
 * FXML Controller class that manages a specific reader item in the list.
 *
 * @author dma@logossmartcard.com
 */
public class FXMLReaderItemController implements Initializable
{
    private static final Logger LOGGER = Logger.getLogger(FXMLReaderItemController.class);

    // Reader name.
    private String readerName;
    // Index of the reader in the list.
    private int index;

    @FXML
    private JFXButton mReaderButton;
    @FXML
    private Pane mReaderBarSelected;

    /**
     * Initializes the controller class.
     * @param url unused.
     * @param rb unused.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Sets the attributes of this controller.
     *
     * @param name: name of the reader.
     * @param index: index of the reader.
     * @param selected: true if the reader is selected.
     */
    public void setAttributes(String name, int index, boolean selected)
    {
        selectReader(selected);

        this.readerName     = name;
        this.index          = index;

        mReaderButton.setText(readerName);
    }

    @FXML
    private void onClickButton(ActionEvent event)
    {
        selectReader(true);
        Context.newInstance()
                .getMainController().notifyReaderSelected(readerName, index);
    }

    /**
     * Updates the GUI to indicate that this reader has been selected.
     *
     * @param selected: true to select it, false to deselect it.
     */
    public void selectReader(boolean selected)
    {
        if (selected)
        {
            mReaderBarSelected.setStyle("-fx-background-color: #eeeeee");
        }
        else
        {
            mReaderBarSelected.setStyle("-fx-background-color: #333333");
        }
    }

    /**
     * Returns the reader name.
     *
     * @return the reader name.
     */
    public String getName()
    {
        return readerName;
    }
}
