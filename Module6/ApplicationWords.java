package applicationwords;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationWords extends Application {
    TextArea ta;
    TextField txtinputFile;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Load Words");

        ta = new TextArea();
        ta.setPrefHeight(400);
        txtinputFile = new TextField("input.txt");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t) {
                System.out.print("loading...");
                String filename = txtinputFile.getText();
                
                loadWords(filename);
            }
            
        });
        GridPane root = new GridPane();
        
        root.add(txtinputFile, 0, 1);
        root.add(btn,0,2);
        root.add(ta,0,3,8,7);
        
        Scene scene = new Scene(root, 400, 550);
        
        primaryStage.setTitle("Words Occurences GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public  void loadWords(String filename)
	{
		WordOccurences wo = new WordOccurences(filename);
		Map<String, Integer> map = wo.map;
		//sort them
		Map<String,Integer>  sortedMap =  map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
				collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		String txt = "";
		int count =0;
		for(String s:sortedMap.keySet())
		{
			count++;
			txt+= s+": "+map.get(s)+"\n";
			if(count == 20)//only 20 sorted values
			{
				break;
			}
		}
		System.out.println(txt);
                ta.setText(txt);
	}

    public static void main(String[] args) {
        launch(args);
    }
    
}
