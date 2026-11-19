import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public ArrayList<Varer> laesVarer(String sti) {
        ArrayList<Varer> varer = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(sti));
            scanner.nextLine(); // spring headeren over

            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();
                String[] felter = linje.split(";");

                String id = felter[0];
                String name = felter[1];
                String priceDate = felter[2];
                double price = Double.parseDouble(felter[3]);
                boolean isBudgetBrand = Boolean.parseBoolean(felter[4]);
                double quantity = Double.parseDouble(felter[5]);
                String unit = felter[6];
                boolean isWeighted = Boolean.parseBoolean(felter[7]);
                boolean isBio = Boolean.parseBoolean(felter[8]);
                boolean isAvailable = Boolean.parseBoolean(felter[9]);

                Varer vare = new Varer(id, name, priceDate, price, isBudgetBrand,
                        quantity, unit, isWeighted, isBio, isAvailable);
                varer.add(vare);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Filen blev ikke fundet: " + sti);
        }

        return varer;
    }
}