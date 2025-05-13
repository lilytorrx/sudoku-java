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

    public boolean hasErrors() {
        if(getStatus() == GameStateEnum.NOT_STARTED) {
            return false;
        }
        return spaces.stream().flatMap(list -> list.stream()).anyMatch(s -> s.getActual() != null && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);
        if(space.isFixed()) {
            return false;
        }
        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        var space = spaces.get(col).get(row);
        if(space.isFixed()) {
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset() {
        spaces.forEach(col -> col.forEach(Space::clearSpace));
    }

    public boolean isFinished() {
        return !hasErrors() && getStatus() == GameStateEnum.COMPLETE;
    }

}
