package sparqlExec;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;


public class Executor {

	public Executor() {

	}

	public void execute(File file, String sparqlQuery) {

		Model model = ModelFactory.createDefaultModel();

		try (InputStream in = new FileInputStream(file)) {
			model.read(in,null); // null base URI, since model URIs are absolute
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Query query = QueryFactory.create(sparqlQuery);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = (ResultSet) qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();

		/*
		Query qry = QueryFactory.create(sparql);
	    QueryExecution qe = QueryExecutionFactory.create(qry, getModel());
	    ResultSet rs = qe.execSelect();

	    while(rs.hasNext())
	    {
	        QuerySolution sol = rs.nextSolution();
	        RDFNode str = sol.get("str");
	        RDFNode thing = sol.get("thing");

	        ...
	    }

	    qe.close();
	    */

	}

	public void execute(URL url, String sparqlQuery) {

		Query query = QueryFactory.create(sparqlQuery);
		QueryExecution qe = QueryExecutionFactory.sparqlService(url + "", query);

		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);

		qe.close() ;

	}

}
