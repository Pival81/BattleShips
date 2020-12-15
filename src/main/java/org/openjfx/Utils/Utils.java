package org.openjfx.Utils;
import com.google.gson.Gson;

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

    public static String toJson(boolean[][] array){
        return new Gson().toJson(array);
    }

    public static boolean[][] fromJson(String str){
        return new Gson().fromJson(str, new boolean[][]{}.getClass());
    }
}
