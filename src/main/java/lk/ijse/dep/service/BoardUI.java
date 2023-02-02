package lk.ijse.dep.service;

public interface BoardUI {
    public void update(int col, boolean isHuman);
    public void update(Winner winner);

    void notifyWinner(Winner winner);
}
