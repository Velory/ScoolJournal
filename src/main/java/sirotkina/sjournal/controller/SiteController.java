package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

/**
 * Created by Марина on 02.07.2017.
 */
public class SiteController {

    @FXML
    private WebView webView;

    private String link;

    public void initialize(){
        link = "https://www.google.com.ua";
        webView.getEngine().load(link);
    }

    public void setLink(String link) {
        this.link = link;
    }
}
