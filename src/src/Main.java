import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FileIO io = new FileIO();
        ArrayList<Varer> alleVarer = io.laesVarer("src/src/varer.csv");
        ArrayList<Varer> tilbudsVarer = io.laesVarer("src/src/tilbud.csv");

        Kasseapperat kasse = new Kasseapperat(alleVarer, tilbudsVarer);
        kasse.scan("5707196133561");
        kasse.scan("5050083123924");
        kasse.scan("5707196133561"); // samme vare igen, med en anden imellem
        kasse.printKassebon();



    }
}
