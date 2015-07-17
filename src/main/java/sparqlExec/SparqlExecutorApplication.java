package sparqlExec;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SparqlExecutorApplication {

    public static void main(String[] args) {
    	/*
    	 * Currently the Spring boot appliance seems a bit exaggerated; but maybe we'll need it some time
    	 */

        ApplicationContext context = SpringApplication.run(SparqlExecutorApplication.class, args);

        queryFile(context);
        querySparqlEndpoint(context);
    }

    private static void queryFile(ApplicationContext context) {
    	 String sparqlQuery =
 				"select ?varid ?date "
 				+ "WHERE {  "
 					+ "?joinpart  a <http://www.w3.org/ns/dcat#Dataset>. "
 					+ "?joinpart <http://www.w3.org/ns/dcat#distribution> ?varid. "
 					+ "?varid <http://purl.org/dc/terms/issued> ?date. "
 					//+ "bind(replace(str(?date), \"Z\", \"\", \"i\") as ?date2) . "
 					//		+ "bind(replace(str(?date2), \"..*\", \"\", \"i\") as ?date) . "
 				+ "}";

    	 Executor exec = context.getBean(Executor.class);
    	 exec.execute(new File("src/main/resources/JRC_Petten_4700000-odp.rdf"), sparqlQuery);
    }

    private static void querySparqlEndpoint(ApplicationContext context) {
    	String sparqlQuery =
        		"SELECT (COUNT(*) as ?count) "
        			+ "FROM <http://odp-op-demo.arhs-developments.com/resource/jrc-names/> "
					+ "WHERE { "
						+ "?s ?p ?o . "
    				+ "}";

        Executor exec = context.getBean(Executor.class);

        // TODO: Handle better
        try {
			exec.execute(new URL("http://op-odp1.aris-lux.lan/sparqlep"), sparqlQuery);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }

}
