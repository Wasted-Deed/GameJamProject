package wasteed.project.gamejamproject.map;

public interface Territory {
    boolean belongs(int x, int y);

    void join(int x, int y);

    void lose(int x, int y);

    // размер текущей территории
    int getSize();
}
