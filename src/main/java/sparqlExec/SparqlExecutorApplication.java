package sparqlExec;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SparqlExecutorApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SparqlExecutorApplication.class, args);

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
}
