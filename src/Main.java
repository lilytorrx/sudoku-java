import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Board;
import model.Space;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int boardLimit = 9;

    public static void main(String[] args) throws Exception {
        final var positions = Stream.of(args).collect(Collectors.
        toMap(key -> key.split(";")[0], 
        value -> value.split(";")[1]));
    
        var option = -1;
        while(true) {
            System.out.println("Selecione uma das opções: ");
            System.out.println("1. Iniciar um novo jogo");
            System.out.println("2. Colocar um novo número");
            System.out.println("3. Remover um número");
            System.out.println("4. Visualizar jogo atual");
            System.out.println("5. Verificar status do jogo");
            System.out.println("6. Limpar jogo");
            System.out.println("7. Finalizar jogo");
            System.out.println("8. Exit");
            
            option = scanner.nextInt();
    
            switch (option) {
                case 1:
                    startGame(positions);
                    break;
                case 2:
                    inputNumber();
                    break;
                case 3: 
                    removeNumber();
                    break;
                case 4:
                    showCurrentGame();
                    break;
                case 5:
                    checkGameStatus();
                    break;
                case 6:
                    clearGame();
                    break;
                case 7:
                    finishGame();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Selecione uma opção válida.");
                    break;
                }
            }
        }

    private static void finishGame() {

    }

    private static void clearGame() {

    }

    private static void checkGameStatus() {

    }

    private static void showCurrentGame() {

    }

    private static void removeNumber() {

    }

    private static void inputNumber() {
        
    }

    private static void startGame(final Map<String, String> positions) {
        if(board != null) {
            System.out.println("O jogo já foi iniciado!");
            return;
        }
        List<List<Space>> spaces = new ArrayList<>();
        for(int i = 0; i < boardLimit; i++) {
            spaces.add(new ArrayList<>());
            for(int j = 0; j < boardLimit; j++) {
                var positionsConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionsConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionsConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }
}


// 0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false
