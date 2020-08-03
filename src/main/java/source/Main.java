package source;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import source.Manage;
import source.outputNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private TextField input;
    private outputNode o;
    private TextArea output;
    private List vulns;
    private List nodes;
    private TableView table;
    private TableColumn vulnID;
    private TableColumn vulnURL;
    private TableColumn vulnType;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group group=new Group();
        Scene scene = new Scene(group,800,525);

        input = new TextField();
        input.setLayoutX(120);
        input.setLayoutY(40);
        input.setPrefWidth(300);
        input.setPrefHeight(30);

        Button cwbutton = new Button("crawler");
        cwbutton.setLayoutX(500);
        cwbutton.setLayoutY(40);
        crawlButtonEvent cbe = new crawlButtonEvent();
        cwbutton.setOnAction(cbe);

        Button sbutton = new Button("scanner");
        sbutton.setLayoutX(600);
        sbutton.setLayoutY(40);
        scanButtonEvent sbe = new scanButtonEvent();
        sbutton.setOnAction(sbe);

        Button svbutton = new Button("save");
        svbutton.setLayoutX(700);
        svbutton.setLayoutY(40);
        svbutton.setPrefSize(65,25);
        saveButtonEvent svbe = new saveButtonEvent();
        svbutton.setOnAction(svbe);

        Label label = new Label("Target");
        label.setLayoutX(40);
        label.setLayoutY(40);

        //addLinks();

        table = new TableView();
        table.setEditable(false);
        table.setLayoutX(40);
        table.setLayoutY(100);
        table.setMaxHeight(250);


        vulnID = new TableColumn("#");
        vulnID.setPrefWidth(100);
        vulnID.setStyle("-fx-alignment: CENTER;");

        vulnURL = new TableColumn("URL");
        vulnURL.setPrefWidth(475);

        vulnType = new TableColumn("Type");
        vulnType.setPrefWidth(150);
        vulnType.setStyle("-fx-alignment: CENTER;");;

        table.getColumns().addAll(vulnID, vulnURL, vulnType);

        Label label2 = new Label("Console");
        label2.setLayoutX(40);
        label2.setLayoutY(360);

        output = new TextArea();
        output.setEditable(false);
        output.setLayoutX(40);
        output.setLayoutY(390);
        output.setPrefSize(730,100);


        group.getChildren().addAll(cwbutton,sbutton,svbutton,label,label2,input,output,table);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Web Scanner");
        primaryStage.show();


    }

    public void addLinks(List links){
        for(int i = 0;i < links.size(); i ++){
            o = new outputNode();
            o.setId(i+1);
            o.setUrl((String)links.get(i));
            vulnID.setCellValueFactory(new PropertyValueFactory<outputNode,String>("id"));
            vulnURL.setCellValueFactory(new PropertyValueFactory<outputNode,String>("url"));
            table.getItems().add(o);
        }
    }


    public void addVulns(List nodes){
        int count = 0;
        vulns = new ArrayList();
        for(int i = 0;i < nodes.size(); i ++){
            outputNode o = (outputNode)nodes.get(i);
            output.appendText("Scanning "+o.getVulName()+"\r\n");
            if(o.getStatus() == 1){
                vulns.add(o);
                count += 1;
                o.setId(count);
                vulnID.setCellValueFactory(new PropertyValueFactory<outputNode,String>("id"));
                vulnType.setCellValueFactory(new PropertyValueFactory<outputNode,String>("vulType"));
                vulnURL.setCellValueFactory(new PropertyValueFactory<outputNode,String>("url"));
                table.getItems().add(o);
            }
        }
        output.appendText("Scanning "+Integer.toString(nodes.size())+" pocs Find "+Integer.toString(count)+" vulns");
    }

    public void saveOutput(List vulns){
        Output o = new Output(vulns);
        o.run("output.html");
        System.out.println("save success!");
    }

    private class crawlButtonEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            Manage m = new Manage();
            List l = m.crawl(input.getText());
            addLinks(l);
        }
    }

    private class scanButtonEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0){
            try {
                Manage m = new Manage();
                nodes = m.scan(input.getText());
                addVulns(nodes);
            }catch (Exception e){

            }
        }
    }

    private class saveButtonEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0){
            try {
                saveOutput(vulns);
            }catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}