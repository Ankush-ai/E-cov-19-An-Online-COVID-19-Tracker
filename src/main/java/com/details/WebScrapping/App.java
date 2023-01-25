package com.details.WebScrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class App extends JFrame {

    private JLabel countryLabel;
    private JLabel confirmedLabel;
    private JLabel deathsLabel;
    private JLabel recoveredLabel;
    private JTextField countryField;

    public App() {
        setTitle("COVID-19 Data");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countryLabel = new JLabel("Country:");
        countryField = new JTextField(10);
        confirmedLabel = new JLabel("Confirmed: N/A");
        deathsLabel = new JLabel("Deaths: N/A");
        recoveredLabel = new JLabel("Recovered: N/A");

        JButton fetchButton = new JButton("Fetch Data");
        fetchButton.addActionListener(e -> fetchData());

        add(countryLabel);
        add(countryField);
        add(fetchButton);
        add(confirmedLabel);
        add(deathsLabel);
        add(recoveredLabel);

        setVisible(true);
    }

    private void fetchData() {
        String country = countryField.getText();
        try {
            Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/" + country + "/").get();
            Elements confirmedData = doc.select("div.maincounter-number > span");
            String confirmed = confirmedData.get(0).text();
            String deaths = confirmedData.get(1).text();
            String recovered = confirmedData.get(2).text();

            confirmedLabel.setText("Confirmed: " + confirmed);
            deathsLabel.setText("Deaths: " + deaths);
            recoveredLabel.setText("Recovered: " + recovered);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}


