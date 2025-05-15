package model;

public enum GameStateEnum {
    NOT_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    private String label;

    GameStateEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
