package sparqlExec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** EXAMPLE DATA:
"select ?varid ?date "
		+ "WHERE {  "
		+ "?joinpart  a <http://www.w3.org/ns/dcat#Dataset>. "
		+ "?joinpart <http://www.w3.org/ns/dcat#distribution> ?varid. "
		+ "?varid <http://purl.org/dc/terms/issued> ?date. "
		//+ "bind(replace(str(?date), \"Z\", \"\", \"i\") as ?date2) . "
		//		+ "bind(replace(str(?date2), \"..*\", \"\", \"i\") as ?date) . "
		+ "}";

 "SELECT (COUNT(*) as ?triples_in_graph) "
		 + "FROM <http://odp-op-demo.arhs-developments.com/resource/jrc-names/> "
		 + "WHERE { "
		 + "?s ?p ?o . "
		 + "}";
 */
public class SparqlExecutorApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen.fxml"));
		primaryStage.setTitle("Sparql Executor");
		primaryStage.setScene(new Scene(root, 500, 275));
		primaryStage.show();

        //queryFile(context);
        //querySparqlEndpoint(context);
    }

    private static void queryFile() {
    	 String sparqlQuery =
 				"select ?varid ?date "
 				+ "WHERE {  "
 					+ "?joinpart  a <http://www.w3.org/ns/dcat#Dataset>. "
 					+ "?joinpart <http://www.w3.org/ns/dcat#distribution> ?varid. "
 					+ "?varid <http://purl.org/dc/terms/issued> ?date. "
 					//+ "bind(replace(str(?date), \"Z\", \"\", \"i\") as ?date2) . "
 					//		+ "bind(replace(str(?date2), \"..*\", \"\", \"i\") as ?date) . "
 				+ "}";

//    	 Executor exec = context.getBean(Executor.class);
//    	 exec.execute(new File("src/main/resources/JRC_Petten_4700000-odp.rdf"), sparqlQuery);
    }

    private static void querySparqlEndpoint() {
    	String sparqlQuery =
        		"SELECT (COUNT(*) as ?triples_in_graph) "
        			+ "FROM <http://odp-op-demo.arhs-developments.com/resource/jrc-names/> "
					+ "WHERE { "
						+ "?s ?p ?o . "
    				+ "}";
    	// 44475545

        //Executor exec = context.getBean(Executor.class);

        // TODO: Handle better
        try {
			//exec.execute(new URL("http://op-odp1.aris-lux.lan/sparqlep"), sparqlQuery);
		} catch (/*MalformedURL*/Exception e) {
			e.printStackTrace();
		}
    }

}
