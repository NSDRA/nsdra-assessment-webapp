package io.github.ammar257ammar.fair.nsdra.semantic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.common.text.StringUtil;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import io.github.ammar257ammar.fair.nsdra.dto.MaturityIndicatorAssessmentDto;

public class FairAssessor {

  public static List<MaturityIndicatorAssessmentDto> assessSubmission(
      Repository repo, List<MaturityIndicatorAssessmentDto> miSubmittedList) {

    if (repo == null) {
      return null;
    }

    List<MaturityIndicatorAssessmentDto> miReportedList = new ArrayList<MaturityIndicatorAssessmentDto>();

    RepositoryConnection connection = repo.getConnection();

    try {

      String query = "PREFIX schema: <https://schema.org/>\r\n"
          + "PREFIX bs: <https://bioschemas.org/>\r\n"
          + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n"
          + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n"
          + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + "\r\n"
          + "SELECT ?name WHERE {\r\n" + " \r\n"
          + "\t?entity a schema:Dataset ."
          + "\t?entity schema:variableMeasured ?mVar ."
          + "\t?mVar a schema:PropertyValue ." + "\t?mVar schema:name ?name ."
          + "\t\r\n" + "}";

      TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL,
          query);

      TupleQueryResult tupleQueryResult = tupleQuery.evaluate();

      List<String> measuredVariables = new ArrayList<String>();

      while (tupleQueryResult.hasNext()) {

        BindingSet bindingSet = tupleQueryResult.next();

        String name = StringUtil.trimDoubleQuotes(
            bindingSet.getBinding("name").getValue().toString());

        String normalizedName = normalize(name);

        measuredVariables.add(normalizedName);
      }

      tupleQueryResult.close();

      for (MaturityIndicatorAssessmentDto mi : miSubmittedList) {

        String normalizedSubmittedVariable = normalize(mi.getVariable());

        if (measuredVariables.contains(normalizedSubmittedVariable)) {
          mi.setStatus("PASS");
        } else {
          mi.setStatus("FAIL");
          mi.setComment("Variable is not measured/reported");
        }
        miReportedList.add(mi);

      }

      return miReportedList;

    } catch (Exception ex) {

      ex.printStackTrace();
      return null;

    } finally {
      connection.close();
    }
  }

  public static String normalize(String name) {

    return name.trim().toLowerCase().replaceAll(" ", "").replaceAll("-", "")
        .replaceAll("_", "");

  }
}
