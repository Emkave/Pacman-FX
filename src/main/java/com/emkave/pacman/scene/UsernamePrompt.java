package com.emkave.pacman.scene;


import com.emkave.pacman.Application;
import com.emkave.pacman.handler.SceneHandler;
import com.emkave.pacman.ui.UIImageBasedButton;
import com.emkave.pacman.ui.UILabel;
import com.emkave.pacman.ui.UITextBasedButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class UsernamePrompt {
    private static UITextBasedButton arrowDown1 = new UITextBasedButton("▼");
    private static UITextBasedButton arrowDown2 = new UITextBasedButton("▼");
    private static UITextBasedButton arrowDown3 = new UITextBasedButton("▼");
    private static UITextBasedButton arrowUp1 = new UITextBasedButton("▲");
    private static UITextBasedButton arrowUp2 = new UITextBasedButton("▲");
    private static UITextBasedButton arrowUp3 = new UITextBasedButton("▲");
    private static UITextBasedButton confirm = new UITextBasedButton(Application.localeResourceBundle.getString("confirm"));
    private static UILabel userNameCh1 = new UILabel(Character.toString((char)((byte)(UsernamePrompt.counter1 + 65))), 30);
    private static UILabel userNameCh2 = new UILabel(Character.toString((char)((byte)(UsernamePrompt.counter2 + 65))), 30);
    private static UILabel userNameCh3 = new UILabel(Character.toString((char)((byte)(UsernamePrompt.counter3 + 65))), 30);
    private static UILabel promptLabel = new UILabel(Application.localeResourceBundle.getString("username_prompt"), 30);
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static int counter3 = 0;


    public static StackPane load() {
        StackPane uiLayer = new StackPane();

        UsernamePrompt.arrowDown1.setOnAction(_ -> {
            UsernamePrompt.counter1++;
            UsernamePrompt.userNameCh1.setText(Character.toString((char)((UsernamePrompt.counter1 + 65))));
        });
        UsernamePrompt.arrowDown1.setTranslateX(-50);
        UsernamePrompt.arrowDown1.setTranslateY(50);
        UsernamePrompt.arrowDown1.removePacmanEffect();
        UsernamePrompt.arrowDown1.setPrefSize(30, 50);


        UsernamePrompt.arrowDown2.setOnAction(_ -> {
            UsernamePrompt.counter2++;
            UsernamePrompt.userNameCh2.setText(Character.toString((char)((UsernamePrompt.counter2 + 65))));
        });
        UsernamePrompt.arrowDown2.setTranslateY(50);
        UsernamePrompt.arrowDown2.removePacmanEffect();
        UsernamePrompt.arrowDown2.setPrefSize(30, 50);


        UsernamePrompt.arrowDown3.setOnAction(_ -> {
            UsernamePrompt.counter3++;
            UsernamePrompt.userNameCh3.setText(Character.toString((char)((UsernamePrompt.counter3 + 65))));
        });
        UsernamePrompt.arrowDown3.setTranslateX(50);
        UsernamePrompt.arrowDown3.setTranslateY(50);
        UsernamePrompt.arrowDown3.removePacmanEffect();
        UsernamePrompt.arrowDown3.setPrefSize(30, 50);


        UsernamePrompt.arrowUp1.setOnAction(_ -> {
            UsernamePrompt.counter1--;
            UsernamePrompt.userNameCh1.setText(Character.toString((char)((UsernamePrompt.counter1 + 65))));
        });
        UsernamePrompt.arrowUp1.setTranslateX(-50);
        UsernamePrompt.arrowUp1.setTranslateY(-50);
        UsernamePrompt.arrowUp1.removePacmanEffect();
        UsernamePrompt.arrowUp1.setPrefSize(30, 50);


        UsernamePrompt.arrowUp2.setOnAction(_ -> {
            UsernamePrompt.counter2--;
            UsernamePrompt.userNameCh2.setText(Character.toString((char)((UsernamePrompt.counter2 + 65))));
        });
        UsernamePrompt.arrowUp2.setTranslateY(-50);
        UsernamePrompt.arrowUp2.removePacmanEffect();
        UsernamePrompt.arrowUp2.setPrefSize(30, 50);


        UsernamePrompt.arrowUp3.setOnAction(_ -> {
            UsernamePrompt.counter3--;
            UsernamePrompt.userNameCh3.setText(Character.toString((char)((UsernamePrompt.counter3 + 65))));
        });
        UsernamePrompt.arrowUp3.setTranslateX(50);
        UsernamePrompt.arrowUp3.setTranslateY(-50);
        UsernamePrompt.arrowUp3.removePacmanEffect();
        UsernamePrompt.arrowUp3.setPrefSize(30, 50);


        UsernamePrompt.confirm.setOnAction(_ -> {
            SceneHandler.loadMainMenu();
        });
        UsernamePrompt.confirm.setTranslateY(200);

        UsernamePrompt.promptLabel.setTranslateY(-270);
        UsernamePrompt.promptLabel.setFill(Color.WHITE);

        UsernamePrompt.userNameCh1.setFill(Color.VIOLET);
        UsernamePrompt.userNameCh1.setTranslateX(-50);

        UsernamePrompt.userNameCh2.setFill(Color.VIOLET);

        UsernamePrompt.userNameCh3.setFill(Color.VIOLET);
        UsernamePrompt.userNameCh3.setTranslateX(50);

        uiLayer.getChildren().addAll(promptLabel, arrowUp1, arrowUp2, arrowUp3, arrowDown1, arrowDown2, arrowDown3, userNameCh1, userNameCh2, userNameCh3, confirm);

        return uiLayer;
    }
}
