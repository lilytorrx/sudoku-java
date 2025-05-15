package ui.custom.screen;

import service.BoardService;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600,600);
    private final BoardService boardService;

    private JButton FinishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(final Map<String,String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        addResetButton(mainPanel);
        addCheckGameStatus(mainPanel);
        addFinishGame(mainPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGame(JPanel mainPanel) {
        mainPanel.add(FinishGameButton);
    }

    private void addCheckGameStatus(JPanel mainPanel) {
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        JButton resetButton = new ResetButton(e->{
            var dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja reiniciar o jogo? Você vai perder TODO o seu progesso!",
                    "Limpar o jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if(dialogResult == 0) {
                boardService.reset();
            }
        });
        mainPanel.add(resetButton);
    }
}
