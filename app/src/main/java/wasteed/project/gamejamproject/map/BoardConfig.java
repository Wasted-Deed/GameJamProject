package wasteed.project.gamejamproject.map;

public class BoardConfig {
    private final int height;
    private final int width;

    public BoardConfig(int height, int width) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
