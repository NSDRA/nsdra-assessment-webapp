package nl.unimaas.bigcat.nsfair.semantic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.rdf4j.common.text.StringUtil;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import nl.unimaas.bigcat.nsfair.domain.MaturityIndicator;

public class FairAssessor {
	
	public static MaturityIndicator assess_MI_R1_3_NT_PCHEM(Repository repo){
		
		List<String> properties = new ArrayList<String>();
		properties.add("Size");
		properties.add("Specific surface area");
		properties.add("Zeta potential");
		properties.add("Shape");
		
		List<String> reportedProperties = new ArrayList<String>();
		
        int count = 0;
        
        MaturityIndicator mi = new MaturityIndicator();
        
        mi.setId("MI_R1_3_NT_PCHEM");
        mi.setUrl("https://github.com/ammar257ammar/NanoMaturityIndicators/blob/main/Gen2_MI_R1.3_NT_PCHEM.md");
        mi.setTitle("Four Physio-Chemical properties are reported");
        
        String comment = "Missing: ";

        if(repo == null){
        	mi.setStatus("ERROR");
        	return mi;
        }
        
		RepositoryConnection connection = repo.getConnection();
		
		try {
            
			String query = "PREFIX schema: <https://schema.org/>\r\n" + 
					"PREFIX bs: <https://bioschemas.org/>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"\r\n" + 
					"SELECT ?name WHERE {\r\n" + 
					" \r\n" + 
					"	?entity a schema:Dataset ." +
					"	?entity schema:variableMeasured ?pchem ." +
					"	?pchem a schema:PropertyValue ." +
					"	?pchem schema:name ?name ." +
					"	\r\n" + 
					"}";
			            
            TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);

            TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
                        
            while (tupleQueryResult.hasNext()) {

                BindingSet bindingSet = tupleQueryResult.next();
                
                String name = StringUtil.trimDoubleQuotes(bindingSet.getBinding("name").getValue().toString()).trim();
                                
                if(properties.contains(name)){
                	count++;
                	reportedProperties.add(name);
                }
            }

            tupleQueryResult.close();

		}catch(Exception ex){
			
			ex.printStackTrace();
			mi.setStatus("ERROR");
        	return mi;
			
        } finally {
            connection.close();
        }
		
		properties.removeAll(reportedProperties);	
		comment += StringUtils.join(properties,",");
		
        if(count == 4){
        	mi.setStatus("PASS");
    		mi.setComment("");
        	return mi;
        }else{
        	mi.setStatus("FAIL");
    		mi.setComment(comment);
        	return mi;
        }	
	}
	
	public static MaturityIndicator assess_MI_R1_3_NT_UNIT(Repository repo){
		
        int count = 0;
        int countTotal = 0;
        
        MaturityIndicator mi = new MaturityIndicator();
        
        mi.setId("MI_R1_3_NT_UNIT");
        mi.setUrl("https://github.com/ammar257ammar/NanoMaturityIndicators/blob/main/Gen2_MI_R1.3_NT_UNIT.md");
        mi.setTitle("Units for all measured variables are reported");
        
        if(repo == null){
        	mi.setStatus("ERROR");
        	return mi;
        }
        
		RepositoryConnection connection = repo.getConnection();
		
		try {
            
			String query = "PREFIX schema: <https://schema.org/>\r\n" + 
					"PREFIX bs: <https://bioschemas.org/>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"\r\n" + 
					"SELECT ?var ?unit WHERE {\r\n" + 
					" \r\n" + 
					"	?entity a schema:Dataset ." +
					"	?entity schema:variableMeasured ?var ." +
					"	?var a schema:PropertyValue ." +
					"	OPTIONAL{ ?var schema:unitText ?unit .}" +
					"	\r\n" + 
					"}";
			
            TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query);

            TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
                        
            while (tupleQueryResult.hasNext()) {

                BindingSet bindingSet = tupleQueryResult.next();
                
                countTotal++;

                String unit = "";

                if(bindingSet.getBinding("unit") != null){
                	unit = StringUtil.trimDoubleQuotes(bindingSet.getBinding("unit").getValue().toString()).trim();
                }
                                
                if(!unit.equals("")){
                	count++;
                }
            }

            tupleQueryResult.close();

		}catch(Exception ex){
			
			ex.printStackTrace();
			mi.setStatus("ERROR");
        	return mi;
			
        } finally {
            connection.close();
        }

        mi.setComment(count + " out of " + countTotal + " units reported");
        
        if((count == countTotal) && countTotal != 0){
        	mi.setStatus("PASS");
        }else{
        	mi.setStatus("FAIL");
        }
        
        return mi;
	}
}
