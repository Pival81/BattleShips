package org.openjfx.Utils;
import static java.lang.System.out;

public class Utils {

    public static void print2dArray(boolean[][] array){
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                out.print("[");
                out.print(array[i][j] ? "x" : "-");
                out.print("]");
            }
            out.println();
        }
        out.println();
    }
}
