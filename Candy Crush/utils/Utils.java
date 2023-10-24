package utils;

public class Utils {
    public static int labelWidth = 60;
    public static int labelHeight = 60;
    public static int labelSpacing = 5;
    public static int dimension = 10;

    public static int panelWidth() {
        return dimension * labelWidth + (dimension + 1) * labelSpacing;
    }
    public static int panelHeight() {
        return dimension * labelHeight + (dimension + 1) * labelSpacing;
    }
    public static int labelPositionX(int x) {
        return x * (labelSpacing + labelWidth) + labelSpacing;
    }
    public static int labelPositionY(int y) {
        return y * (labelSpacing + labelHeight) + labelSpacing;
    }
}
