package persistencia;

import model.Estacio;
import model.Tren;
import model.TrenMercaderies;
import model.TrenPassatgers;
import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import principal.GestorLiniesTrensException;
import model.Linia;
import model.Zona;

/**
 *
 * @author fta
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Zona zona;

    public Document getDoc() {
        return doc;
    }

    public Zona getZona() {
        return zona;
    }

    public void desarZona(String nomFitxer, Zona zona) throws GestorLiniesTrensException {
        construeixModel(zona);
        desarModel(nomFitxer);
    }

    public void carregarZona(String nomFitxer) throws GestorLiniesTrensException {
        carregarFitxer(nomFitxer);
        llegirFitxerZona();
    }

    /*Paràmetres: Zona a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte zona passat per paràmetre 
     *   per construir un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "zona". Aquesta arrel, l'heu d'afegir 
     *   a doc. Un cop fet això, heu de recórrer l'ArrayList components de zona
     *   i per a cada component, afegir un fill a doc. Cada fill tindrà com atributs
     *   els atributs de l'objecte (codi, nom, matrícula, etc.)
     *
     * - Si es tracta d'un tren de mercaderies, a l'atribut de mercaderies perilloses de
     *   l'element se li ha d'assignar el valor "Si", si el valor de l'atribut de l'objecte
     *   corresponent a mercaderies perilloses és true, i "No" en cas contrari.
     *   
     * - Si es tracta d'una línia, a més, heu d'afegir fills addicionals amb les estacions
     *   i els trens amb els seus nombres de vagons. Els nombres de vagons s’han d’afegir com 
     *   a atributs dels trens als quals estan associats. En el cas dels trens de mercaderies,
     *   heu de fer el mateix que el que s'especifica en el punt anterior.
     *
     * - Si heu de gestionar alguna excepció relacionada amb la construcció del model,
     *   heu de llençar una excepció de tipus GestorLiniesTrensException amb codi 
     *   "GestorXML.model".
     *
     *Retorn: cap
     */
    public void construeixModel(Zona zona) throws GestorLiniesTrensException {
        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Element fill, net, renet;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestorLiniesTrensException("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("zona");
        arrel.setAttribute("codi", String.valueOf(zona.getCodi()));
        arrel.setAttribute("responsable", zona.getResponsable());
        doc.appendChild(arrel);

        for (int i = 0; i < zona.getComponents().size(); i++) {

            if (zona.getComponents().get(i) instanceof Estacio) {

                fill = doc.createElement("estacio");

                fill.setAttribute("codi", ((Estacio) zona.getComponents().get(i)).getCodi());
                fill.setAttribute("nom", ((Estacio) zona.getComponents().get(i)).getNom());
                fill.setAttribute("poblacio", ((Estacio) zona.getComponents().get(i)).getPoblacio());
                fill.setAttribute("adreca", ((Estacio) zona.getComponents().get(i)).getAdreca());

                arrel.appendChild(fill);

            } else if (zona.getComponents().get(i) instanceof TrenPassatgers) {

                fill = doc.createElement("trenPassatgers");

                fill.setAttribute("matricula", ((TrenPassatgers) zona.getComponents().get(i)).getMatricula());
                fill.setAttribute("anyAdquisicio", String.valueOf(((TrenPassatgers) zona.getComponents().get(i)).getAnyAdquisicio()));
                fill.setAttribute("tipus", ((TrenPassatgers) zona.getComponents().get(i)).getTipus());

                arrel.appendChild(fill);

            } else if (zona.getComponents().get(i) instanceof TrenMercaderies) {

                fill = doc.createElement("trenMercaderies");

                fill.setAttribute("matricula", ((TrenMercaderies) zona.getComponents().get(i)).getMatricula());
                fill.setAttribute("anyAdquisicio", String.valueOf(((TrenMercaderies) zona.getComponents().get(i)).getAnyAdquisicio()));
                fill.setAttribute("tipus", ((TrenMercaderies) zona.getComponents().get(i)).getTipus());

                if (((TrenMercaderies) zona.getComponents().get(i)).isMercaderiesPerilloses()) {
                    fill.setAttribute("mercaderiesPerilloses", "Si");
                } else {
                    fill.setAttribute("mercaderiesPerilloses", "No");
                }

                fill.setAttribute("locomotores", String.valueOf(((TrenMercaderies) zona.getComponents().get(i)).getLocomotores()));

                arrel.appendChild(fill);

            } else if (zona.getComponents().get(i) instanceof Linia) {

                fill = doc.createElement("linia");

                fill.setAttribute("nomId", ((Linia) zona.getComponents().get(i)).getNomId());

                for (int j = 0; j < ((Linia) zona.getComponents().get(i)).getEstacions().size(); j++) {

                    net = doc.createElement("estacio");

                    net.setAttribute("codi", ((Estacio) ((Linia) zona.getComponents().get(i)).getEstacions().get(j)).getCodi());
                    net.setAttribute("nom", ((Estacio) ((Linia) zona.getComponents().get(i)).getEstacions().get(j)).getNom());
                    net.setAttribute("poblacio", ((Estacio) ((Linia) zona.getComponents().get(i)).getEstacions().get(j)).getPoblacio());
                    net.setAttribute("adreca", ((Estacio) ((Linia) zona.getComponents().get(i)).getEstacions().get(j)).getAdreca());

                    fill.appendChild(net);

                }

                Iterator tren = ((Linia) zona.getComponents().get(i)).getTrens().keySet().iterator();

                while (tren.hasNext()) {

                    Object trenActual = tren.next();

                    if (trenActual instanceof TrenPassatgers) {

                        net = doc.createElement("trenPassatgers");

                    } else { //És TrenMercaderies

                        net = doc.createElement("trenMercaderies");

                        if (((TrenMercaderies) trenActual).isMercaderiesPerilloses()) {
                            net.setAttribute("mercaderiesPerilloses", "Si");
                        } else {
                            net.setAttribute("mercaderiesPerilloses", "No");
                        }

                        net.setAttribute("locomotores", String.valueOf(((TrenMercaderies) trenActual).getLocomotores()));
                    }

                    net.setAttribute("matricula", ((Tren) trenActual).getMatricula());
                    net.setAttribute("anyAdquisicio", String.valueOf(((Tren) trenActual).getAnyAdquisicio()));
                    net.setAttribute("tipus", ((Tren) trenActual).getTipus());
                    

                    net.setAttribute("vagons", String.valueOf(((Linia) zona.getComponents().get(i)).getTrens().get(trenActual)));

                    fill.appendChild(net);
                }

                arrel.appendChild(fill);
            }
        }
    }

    public void desarModel(String nomFitxer) throws GestorLiniesTrensException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new GestorLiniesTrensException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorLiniesTrensException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new GestorLiniesTrensException("GestorXML.carrega");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, 
     *   per assignar valors als atributs de zona i la resta d'objectes que formen 
     *   els components de zona.
     *    
     * - Primer heu de crear l'objecte zona a partir de l'arrel del document XML
     *   per després recórrer el doc i per cada fill, afegir un objecte a l'atribut 
     *   components de zona mitjançant el mètode escaient de la classe Zona. Recordeu
     *   que com l'arrel conté els atributs codi i responsable de la zona, al fer 
     *   Element arrel = doc.getDocumentElement(); ja podeu crear l'objecte de tipus Zona.
     *
     * - Si l'element del document XML que s'ha llegit és una línia, recordeu que a 
     *   més d'afegir-lo a components, també haureu d'assignar-li les estacions i els
     *   trens amb els seus nombres de vagons.
     *
     *Retorn: cap
     */
    private void llegirFitxerZona() throws GestorLiniesTrensException {
        String component;

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte zona
        zona = new Zona(Integer.parseInt(arrel.getAttribute("codi")), arrel.getAttribute("responsable"));

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int i = 0; i < llistaFills.getLength(); i++) {

            Node fill = llistaFills.item(i);

            if (fill.getNodeType() == Node.ELEMENT_NODE) {

                component = fill.getNodeName();

                if (component.equals("estacio")) {

                    String codi = ((Element) fill).getAttribute("codi");
                    String nom = ((Element) fill).getAttribute("nom");
                    String poblacio = ((Element) fill).getAttribute("poblacio");
                    String adreca = ((Element) fill).getAttribute("adreca");

                    zona.addEstacio(new Estacio(codi, nom, poblacio, adreca));

                } else if (component.equals("trenPassatgers")) {

                    String matricula = ((Element) fill).getAttribute("matricula");
                    int any = Integer.parseInt(((Element) fill).getAttribute("anyAdquisicio"));
                    String tipus = ((Element) fill).getAttribute("nom");                    

                    zona.addTrenPassatgers(new TrenPassatgers(matricula, any, tipus));

                } else if (component.equals("trenMercaderies")) {

                    String matricula = ((Element) fill).getAttribute("matricula");
                    int any = Integer.parseInt(((Element) fill).getAttribute("anyAdquisicio"));
                    String tipus = ((Element) fill).getAttribute("nom");
                    
                    boolean mercaderies=false;
                    
                    if(((Element) fill).getAttribute("mercaderiesPerilloses").equals("Si")){
                        mercaderies=true;
                    }
                    
                    int locomotores = Integer.parseInt(((Element) fill).getAttribute("locomotores"));

                    zona.addTrenMercaderies(new TrenMercaderies(matricula, any, tipus, mercaderies, locomotores));

                } else if (component.equals("linia")) {

                    String nomId = ((Element) fill).getAttribute("nomId");
                    
                    Linia novaLinia = new Linia(nomId);

                    zona.addLinia(novaLinia);

                    NodeList nets = fill.getChildNodes();

                    for (int j = 0; j < nets.getLength(); j++) {

                        Node net = nets.item(j);

                        if (net != null && net.getNodeType() == Node.ELEMENT_NODE) {

                            int tipus = 0;

                            component = net.getNodeName();

                            switch (component) {
                                case "estacio":
                                    novaLinia.addEstacio((Estacio) zona.getComponents().get(zona.selectComponent(1, ((Element) net).getAttribute("codi"))));
                                    break;
                                case "trenPassatgers":
                                    novaLinia.addTren((TrenPassatgers) zona.getComponents().get(zona.selectComponent(2, ((Element) net).getAttribute("matricula"))), Integer.parseInt(((Element) net).getAttribute("vagons")));
                                    break;
                                default: //trenMercaderies
                                    novaLinia.addTren((TrenMercaderies) zona.getComponents().get(zona.selectComponent(3, ((Element) net).getAttribute("matricula"))), Integer.parseInt(((Element) net).getAttribute("vagons")));
                                    break;
                            }

                        }
                    }
                }
            }
        }
    }
}
