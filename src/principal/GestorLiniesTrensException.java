package principal;

/**
 *
 * @author fta
 */
public class GestorLiniesTrensException extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public GestorLiniesTrensException(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                missatge = "El valor introduït no és correcte";
                break;
            case "3":
                missatge = "L'estació ja és de la línia";
                break;
            case "4":
                missatge = "Aquest tren ja pertany a la línia";
                break;
            case "5":
                missatge = "L'estació ja existeix";
                break;
            case "6":
                missatge = "El tren de passatgers ja existeix";
                break;
            case "7":
                missatge = "El tren de mercaderies ja existeix";
                break;
            case "8":
                missatge = "La línia ja existeix";
                break;
            case "9":
                missatge = "Ja no hi caben més components";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar la botiga";
                break;
            case "GestorXML.desar":
            case "GestorSerial.desar":
            case "GestorJDBC.desar":
                missatge = "No s'ha pogut desar la botiga a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
            case "GestorSerial.carrega":
            case "GestorJDBC.carrega":
                missatge = "No s'ha pogut carregar la botiga a causa d'error d'entrada/sortida";
                break;
            case "GestorJDBC.noExisteix":
            case "GestorDB4O.noExisteix":
                this.missatge = "El fitxer no existeix";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }

    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }
}
