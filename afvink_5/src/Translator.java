import java.util.ArrayList;
import java.util.List;

public class Translator {

    static final String[] ONE = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K"
            , "M", "F", "P", "S", "T", "W", "Y", "V"};
    static final String[] THREE = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLN", "GLU", "GLY"
            ,"HIS", "ILE", "LEU", "LYS", "MET", "PHE", "PRO", "SER"
            ,"THR", "TRP", "TYR", "VAL"
    };

    private static boolean validateLength(String stringToValidate) {
        if ((stringToValidate.length() % 3) == 0 && (stringToValidate.length() != 1)) return true;
        else return false;
    }

    static String[] splitEqually(String text, int size) {
        // code stolen from https://stackoverflow.com/questions/3760152/split-string-to-equal-length-substrings-in-java
        // modified to return a string array. idk if that's right to do this way, but
        // I was told not to think about that too much and just implement whatever works
        List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret.toArray(new String[0]);
    }

    public static String translateThree2One(String stringToTranslate) throws WrongLengthAA {
        String[] charSetsToTranslate;

        stringToTranslate = stringToTranslate.replaceAll("\\W+", "");

        if (validateLength(stringToTranslate)) {
            charSetsToTranslate = splitEqually(stringToTranslate, 3);
            //idk why this is possible
            String translatedThree2One = "";
            String translatedSequence = "";
            for (int i = 0; i < charSetsToTranslate.length; i++) {

                try {
                    translatedThree2One = three2One( charSetsToTranslate[i]);
                } catch (NotAnAA notAnAA) {
                    System.out.println("Not a valid AA!");
                }

                translatedSequence += translatedThree2One;

            }
            return translatedSequence;

        } else {
            throw new WrongLengthAA("This string isn't divisible by 3!!!!!!!!!!!!!!!!!!1111");

        }

    }

    public static String translateOne2Three(String stringToTranslate) {

        char[] charsToTranslate = stringToTranslate.toCharArray();

        //idk why this is possible
        String translatedOne2Three = "";
        String translatedSequence = "";
        for (int i = 0; i < charsToTranslate.length; i++) {
            try {
                translatedOne2Three = one2Three(String.valueOf(charsToTranslate[i]));
            } catch (NotAnAA notAnAA) {
                System.out.println("Not a valid AA!");
            }
            if (translatedSequence.length() < 2) {
                translatedSequence += translatedOne2Three;
            } else
                translatedSequence += "-" + translatedOne2Three;
        }
        return translatedSequence;
    }


    public static String one2Three(String symbol) throws NotAnAA {
        String threeCode = "";
        for (int i = 0; i < ONE.length; i++) {
            if (ONE[i].equals(symbol)) {
                threeCode = THREE[i];
            }

        }
        if (threeCode.equals("")) {
            throw new NotAnAA("Dit is een niet bestaand aminozuur: " + symbol);
        }
        return threeCode;
    }

    public static String three2One(String symbol) throws NotAnAA {
        String oneCode = "";

        // looks up the character of the string
        for (int i = 0; i < THREE.length; i++) {
            if (THREE[i].equals(symbol)) {
                oneCode= ONE[i];
            }

        }
        if (oneCode.equals("")) {
            throw new NotAnAA("Dit is een niet bestaand aminozuur: " + symbol);
        }
        return oneCode;
    }

}

class NotAnAA extends Exception {

    public NotAnAA() {
        super();
    }

    public NotAnAA(String err) {
        super(err);
    }
}

class WrongLengthAA extends Exception {

    public WrongLengthAA () {
        super();
    }

    public WrongLengthAA (String err) {
        super(err);
    }
}