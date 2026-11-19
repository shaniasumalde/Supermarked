import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Kasseapperat {
        private HashMap<String, Varer> alleVarer = new HashMap<>();
        private HashMap<String, Varer> tilbudsVarer = new HashMap<>();
        private ArrayList<Varer> kurv = new ArrayList<>();

        public Kasseapperat(ArrayList<Varer> alleListe, ArrayList<Varer> tilbudsListe) {
            for (Varer v : alleListe) {
                alleVarer.put(v.getId(), v);
            }
            for (Varer v : tilbudsListe) {
                tilbudsVarer.put(v.getId(), v);
            }
        }

        public void scan(String id) {
            Varer v = alleVarer.get(id);
            if (v == null) {
                System.out.println("Ukendt vare: " + id);
                return;
            }
            kurv.add(v);
        }

        // pris for én vare: tilbudspris hvis den er på tilbud, ellers normalpris
        public double getPris(Varer v) {
            Varer tilbud = tilbudsVarer.get(v.getId());
            if (tilbud != null) {
                return tilbud.getPrice();
            }
            return v.getPrice();
        }

        public double beregnTotal() {
            double total = 0;
            for (Varer v : kurv) {
                total += getPris(v);
            }
            return total;
        }

        public double beregnMoms() {
            return beregnTotal() * 0.2; // 25 % moms er 20 % af totalbeløbet
        }

    private String kr(double beloeb) {
        return String.format("%.2f", beloeb).replace('.', ',') + " kr.";
    }

        public void printKassebon() {
            // 1. Tæl ens varer (LinkedHashMap husker rækkefølgen fra første scanning)
            LinkedHashMap<String, Integer> antal = new LinkedHashMap<>();
            for (Varer v : kurv) {
                if (antal.containsKey(v.getId())) {
                    antal.put(v.getId(), antal.get(v.getId()) + 1);
                } else {
                    antal.put(v.getId(), 1);
                }
            }

            // 2. Print linjerne
            System.out.println("========== KASSEBON ==========");
            for (String id : antal.keySet()) {
                Varer vare = alleVarer.get(id);
                int stk = antal.get(id);

                System.out.println(vare.getName());
                System.out.println("  " + stk + " x " + kr(vare.getPrice())
                        + " = " + kr(stk * vare.getPrice()));

                // Rabat på separat linje som negativt beløb
                double rabatPrStk = vare.getPrice() - getPris(vare);
                if (rabatPrStk > 0) {
                    System.out.println("  Tilbud: -" + kr(stk * rabatPrStk));
                }
            }

            // 3. Total og moms
            System.out.println("------------------------------");
            System.out.println("TOTAL: " + kr(beregnTotal()));
            System.out.println("Heraf moms (25 %): " + kr(beregnMoms()));
            System.out.println("==============================");
        }
    }




