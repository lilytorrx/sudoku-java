import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;
import ui.custom.screen.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {
    public static void main(String [] args) {
        var gameConfig = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }
}
