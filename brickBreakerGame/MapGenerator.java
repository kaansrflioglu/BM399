import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int[][] map;
    public int bricksWidth;
    public int bricksHeight;
    public MapGenerator(int row , int col){
        map = new int[row][col];
        for (int[] map1 : map) {
            for (int j = 0; j < map[0].length; j++) {
                map1[j] = 1;
            }
        }
        bricksWidth = 85;
        bricksHeight = 25;
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    //g.setColor( randomColor.getRandomColor());
                    g.setColor(Color.magenta);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                }
            }
        }
    }

/*    public static class randomColor {
        public static Color getRandomColor() {
            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
            return new Color(red, green, blue);
        }
    }*/

    public void setBricksValue(int value,int row,int col)
    {
        map[row][col] = value;
    }
}
