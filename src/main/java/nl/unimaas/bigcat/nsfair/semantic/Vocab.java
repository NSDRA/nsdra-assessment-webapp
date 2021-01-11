package nl.unimaas.bigcat.nsfair.semantic;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

/**
 * Vocabulary constants for the 'https://bioschemas.org/' namespace. It's a good idea to always create a vocabulary class
 * such as this one when you program with RDF4J. It makes it far easier to reuse certain resources and properties in
 * various places in your code.
 */
public class Vocab {

	public static final String NAMESPACE = "https://schema.org/";

	public static final String PREFIX = "schema";

	public static final IRI DATASET = getIRI("Dataset");

	private static IRI getIRI(String localName) {
		return SimpleValueFactory.getInstance().createIRI(NAMESPACE, localName);
	}

}