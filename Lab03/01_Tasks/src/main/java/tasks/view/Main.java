package tasks.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tasks.controller.Controller;
import tasks.controller.Notificator;
import tasks.model.ArrayTaskList;
import tasks.services.TaskIO;
import tasks.services.TasksService;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;
    private static final int defaultwidth = 820;
    private static final int defaultHeight = 520;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private ArrayTaskList savedTasksList = new ArrayTaskList();

    public static final File savedTasksFile = new File("data/tasks.txt");

    private TasksService service = new TasksService(savedTasksList);

    @Override
    public void start(Stage primaryStage) throws Exception {

        LOGGER.info("saved data reading");
        if (savedTasksFile.length() != 0) {
            TaskIO.readBinary(savedTasksList, savedTasksFile);
        }
        try {
            LOGGER.info("application start");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();
            Controller ctrl= loader.getController();
            service = new TasksService(savedTasksList);

            ctrl.setService(service);
            primaryStage.setTitle("Task Manager");
            primaryStage.setScene(new Scene(root, defaultwidth, defaultHeight));
            primaryStage.setMinWidth(defaultwidth);
            primaryStage.setMinHeight(defaultHeight);
            primaryStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
            LOGGER.error("error reading main.fxml");
        }
        primaryStage.setOnCloseRequest(we -> {
                System.exit(0);
            });
        new Notificator(FXCollections.observableArrayList(service.getObservableList())).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
