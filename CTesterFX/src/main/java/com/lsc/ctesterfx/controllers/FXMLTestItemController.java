package com.lsc.ctesterfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.lsc.ctesterfx.constants.Tooltips;
import com.lsc.ctesterfx.dao.Test;
import com.lsc.ctesterfx.task.CompilationTask;
import com.lsc.ctesterfx.task.ExecutionTask;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Pair;

/**
 * FXML Controller class of every test item in the VBox.
 *
 * @author dma@logossmartcard.com
 */
public class FXMLTestItemController implements Initializable
{
    private enum TEST_STATE {
        WAITING,
        NOT_COMPILED,
        COMPILING,
        COMPILATION_OK,
        COMPILATION_FAILED,
        RUNNING,
        EXECUTION_OK,
        EXECUTION_FAILED
    }

    @FXML
    private JFXCheckBox mTestNameCheckbox;

    @FXML
    private JFXButton mRunTestButton;
    @FXML
    private JFXButton mRemoveTestButton;
    @FXML
    private JFXButton mTestStatusButton;

    private Test mTest;

    private int mIndex;

    private FXMLMainController mMainController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        _setupTooltips();
    }

    /**
     * Sets up all the buttons' tooltips.
     */
    private void _setupTooltips()
    {
        mRunTestButton.setTooltip(Tooltips.create(Tooltips.RUN_TEST));
        mRemoveTestButton.setTooltip(Tooltips.create(Tooltips.REMOVE_TEST));
    }

    @FXML
    private void onClickRunTestButton(ActionEvent event)
    {
        // Create an executor and the compilation task.
        ExecutorService compilationExecutor = Executors.newFixedThreadPool(1);
        CompilationTask compilationTask = new CompilationTask(mTest);
        // Set a listener that will be triggered when the task is finished.
        compilationTask.setOnSucceeded((Event e) ->
        {
            // Get the result.
            Pair<Object, Method> result = (Pair<Object, Method>) compilationTask.getValue();

            // Check if it's succesful.
            if (result != null)
            {
                setState(TEST_STATE.COMPILATION_OK);

                // Do the same process but this time for the execution of the test.
                ExecutorService executionExecutor = Executors.newFixedThreadPool(1);
                ExecutionTask executionTask = new ExecutionTask(result.getKey(), result.getValue());
                executionTask.setOnSucceeded((Event ev) ->
                {
                    if ((boolean) executionTask.getValue())
                    {
                        setState(TEST_STATE.EXECUTION_OK);
                    }
                    else
                    {
                        setState(TEST_STATE.EXECUTION_FAILED);
                    }
                });

                setState(TEST_STATE.RUNNING);
                // Start the execution.
                executionExecutor.execute(executionTask);
                executionExecutor.shutdown();
            }
        });

        // Update the status image.
        setState(TEST_STATE.COMPILING);
        // Start the compilation.
        compilationExecutor.execute(compilationTask);
        compilationExecutor.shutdown();
    }

    @FXML
    private void onClickRemoveTestButton(ActionEvent event)
    {
        mMainController.removeTestAtIndex(mIndex);
    }

    /**
     * Method that updates the GUI with the new state.
     *
     * @param state: new state of the test.
     */
    private void setState(TEST_STATE state)
    {
        switch (state) {
            case NOT_COMPILED:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: #dddddd; -fx-text-fill: black");
                mTestStatusButton.setText("Not compiled");
                break;

            case COMPILING:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: #eeeeee; -fx-text-fill: black");
                mTestStatusButton.setText("Compiling");
                break;

            case RUNNING:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: #eeeeee; -fx-text-fill: black");
                mTestStatusButton.setText("Running");
                break;

            case COMPILATION_OK:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: #aeffad; -fx-text-fill: black");
                mTestStatusButton.setText("Compiled");
                break;

            case COMPILATION_FAILED:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: red; -fx-text-fill: white");
                mTestStatusButton.setText("Compilation error");
                break;

            case EXECUTION_OK:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: #42ff3f; -fx-text-fill: black");
                mTestStatusButton.setText("Succesful");
                break;

            case EXECUTION_FAILED:
                mTestStatusButton.setStyle("-fx-background-radius: 32; -fx-background-color: red; -fx-text-fill: white");
                mTestStatusButton.setText("Failed");
                break;
        }
    }

    /**
     * Sets only the index of the test in the list.
     *
     * @param index: index of the test in the list.
     */
    public void setAttributes(int index)
    {
        mIndex = index;
    }

    /**
     * Sets the different attributes needed to initialize the test item.
     *
     * @param file: name of the test file.
     * @param controller: controller that has to be notified if something happens.
     * @param index: index in the list.
     */
    public void setAttributes(File file, FXMLMainController controller, int index)
    {
        mTest = new Test(file);

        mMainController = controller;

        mIndex = index;

        mTestNameCheckbox.setMnemonicParsing(false);
        mTestNameCheckbox.setText(mTest.getName());
    }

    /**
     * Selects or deselects the checkbox.
     *
     * @param selected: the checkbox has to be selected if true. Deselected otherwise.
     */
    public void select(boolean selected)
    {
        mTestNameCheckbox.setSelected(selected);
    }
}
