package ui.custom.screen;

import service.BoardService;
import ui.custom.button.CheckGameStatusButton;
import ui.custom.button.FinishGameButton;
import ui.custom.button.ResetButton;
import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600,600);
    private final BoardService boardService;

    private JButton checkGameStatusButton;
    private JButton resetButton;
    private JButton finishGameButton;

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
        finishGameButton = new FinishGameButton(e -> {
            String message = null;
            if(boardService.gameIsFinished()) {
                message = "Parabéns! O jogo está completo!";
                JOptionPane.showMessageDialog(null, message);
                finishGameButton.setEnabled(false);
            } else {
                message = "Seu jogo possui algumas inconsistências, ajuste e tente novamente!";
                JOptionPane.showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatus(JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e-> {
            var hasErros = BoardService.hasErrors();
            var gameStatus = BoardService.getStatus();
            var message = switch(gameStatus) {
                case NON_STARTED -> "O jogo não foi iniciado!";
                case INCOMPLETE -> "O jogo está incompleto!";
                case COMPLETE -> "O jogo está completo!";
            };
            message += hasErros ? " e contém erros!" : " e não contém erros.";
            JOptionPane.showMessageDialog(null, message);
        });

        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetButton = new ResetButton(e->{
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
