package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.*;

public class GraphicHashController{

    private Map<Integer, List<Map.Entry<String, String>>> buckets;
    @FXML
    private VBox mainVbox;

    @FXML
        public void initialize() {
        mainVbox.setSpacing(10);
        mainVbox.setAlignment(Pos.CENTER);

            HashMap<String, String> contacts = new HashMap<>();
            contacts.put("Buzz", "1234-5678");
            contacts.put("Juan", "4567-8910");
            contacts.put("Pedro", "7891-0111");
            contacts.put("Jorge", "1213-1415");

            buckets = new HashMap<>();
            int bucketCount = 10;

            for (String key : contacts.keySet()) {
                int hash = Math.abs(key.hashCode()) % bucketCount;
                buckets.putIfAbsent(hash, new ArrayList<>());
                buckets.get(hash).add(new AbstractMap.SimpleEntry<>(key, contacts.get(key)));
            }

            renderTable(bucketCount);
        }

        private void renderTable(int bucketCount) {
            mainVbox.getChildren().clear();

            for (int i = 0; i < bucketCount; i++) {
                HBox row = new HBox(10);
                Label indexLabel = new Label("[" + i + "]");
                indexLabel.setMinWidth(40);
                row.getChildren().add(indexLabel);

                VBox bucketBox = new VBox(5);
                if (buckets.containsKey(i)) {
                    for (Map.Entry<String, String> entry : buckets.get(i)) {
                        Label label = new Label(entry.getKey() + " → " + entry.getValue());
                        label.setStyle("-fx-background-color: lightblue; -fx-padding: 3;");
                        bucketBox.getChildren().add(label);
                    }
                } else {
                    bucketBox.getChildren().add(new Label("⌀"));
                }

                row.getChildren().add(bucketBox);
                mainVbox.getChildren().add(row);
            }
        }
    }