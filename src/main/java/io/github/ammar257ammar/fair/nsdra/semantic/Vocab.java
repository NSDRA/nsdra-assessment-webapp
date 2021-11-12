package io.github.ammar257ammar.fair.nsdra.semantic;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

/**
 * Vocabulary constants for the 'https://bioschemas.org/' namespace.
 */
public class Vocab {

	public static final String NAMESPACE = "https://schema.org/";

	public static final String PREFIX = "schema";

	public static final IRI DATASET = getIRI("Dataset");

	private static IRI getIRI(String localName) {
		return SimpleValueFactory.getInstance().createIRI(NAMESPACE, localName);
	}
}