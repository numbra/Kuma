package com.foursixteen.kuma;

import com.foursixteen.kuma.util.IOUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Kuma extends Application {

    public static final Logger log = Logger.getLogger(Kuma.class.getName());

    public Properties settings;
    public List<Server> servers;
    private FileInputStream fis;
    private Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(String.format(Reference.NAME + "-" + Reference.VERSION));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/foursixteen/kuma/layout/Main.fxml"));
        AnchorPane root = loader.load();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    @Override
    public void init() {

        try {
            settings = new Properties();
            fis = new FileInputStream("kuma.properties");
            settings.load(fis);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

        servers = new ArrayList<>();
        File serversPath = new File(settings.getProperty("serversPath", "servers"));
        serversPath.mkdirs();
        for (File f : Objects.requireNonNull(serversPath.listFiles())) {
            servers.add(new Server(UUID.fromString(f.getName())));
        }
        log.info(servers.size() + " servers loaded.");

    }

    public void downloadBuildTools() throws IOException, InterruptedException {
        String url = settings.getProperty("buildToolsUrl", Reference.BUILD_TOOLS_URL);
        File dir = new File(settings.getProperty("buildToolsDirectory", Reference.BUILD_TOOLS_DIRECTORY));
        dir.mkdirs();

        IOUtil.exec(dir, "curl", "-o", "BuildTools.jar", url);
    }

    public void update(String version) throws IOException, InterruptedException {
        File dir = new File(settings.getProperty("buildToolsDirectory", Reference.BUILD_TOOLS_DIRECTORY));
        if (!dir.exists())
            downloadBuildTools();

        File buildToolsJar = new File(dir, "BuildTools.jar");
        IOUtil.exec(dir, "java", "-jar", "BuildTools.jar", "--rev", version);
    }
}
