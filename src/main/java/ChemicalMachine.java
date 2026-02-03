
import java.util.ArrayList;
import java.util.List;

public class ChemicalMachine {
    public static List<String> applyHeat(List<String> ingredients) {
        ArrayList<String> ret = new ArrayList<>();
        if (ingredients == null || ingredients.size() < 2) {
            ret.add("UNKNOWN");
            return ret;
        }

        if (ingredients.get(0).equals("GREEN") && ingredients.get(1).equals("YELLOW")) {
            ret.add("BROWN");
            ret.add("YELLOW");
            return ret;
        }

        if (ingredients.get(0).equals("BROWN") && ingredients.get(1).equals("YELLOW")) {
            ret.add("MAGENTA");
            return ret;
        }

        ret.add("UNKNOWN");
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(applyHeat(applyHeat(new ArrayList<String>(List.of("GREEN", "YELLOW"))))); // should print [MAGENTA]
        System.out.println(applyHeat(new ArrayList<String>(List.of("GREEN", "BROWN")))); // should print [MAGENTA]
        System.out.println(applyHeat(new ArrayList<String>(List.of("GREEN", "")))); // should print [MAGENTA]
        System.out.println(applyHeat(new ArrayList<String>(List.of("GREEN", "","")))); // should print [MAGENTA]
        System.out.println(applyHeat(new ArrayList<String>(List.of("GREEN", "UNKNOWN")))); // should print [MAGENTA]

        System.out.println(applyHeat(new ArrayList<String>(List.of("BROWN", "YELLOW")))); // should print [MAGENTA]
        System.out.println(applyHeat(new ArrayList<String>(List.of("YELLOW", "BROWN")))); // should print [UNKNOWN]
        System.out.println(applyHeat(new ArrayList<String>(List.of("YELLOW", "BROWN")))); // should print [UNKNOWN]

    }
}
