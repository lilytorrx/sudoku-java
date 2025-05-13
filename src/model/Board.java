package model;

import java.util.List;
import java.util.Collections;

public class Board {
    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStateEnum getStatus() {
        if(spaces.stream().flatMap(list -> list.stream()).noneMatch(s -> s.isFixed() && s.getActual() != null) ) {
            return GameStateEnum.NOT_STARTED;
        }
        return spaces.stream().flatMap(list -> list.stream()).anyMatch(s -> s.getActual() == null) ? GameStateEnum.INCOMPLETE : GameStateEnum.COMPLETE;
    }

    public boolean hasErros() {
        if(getStatus() == GameStateEnum.NOT_STARTED) {
            return false;
        }
        return spaces.stream().flatMap(list -> list.stream()).anyMatch(s -> s.getActual() != null && !s.getActual().equals(s.getExpected()));
    }

}
