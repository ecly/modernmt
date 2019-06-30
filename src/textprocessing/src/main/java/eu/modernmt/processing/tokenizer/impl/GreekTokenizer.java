package eu.modernmt.processing.tokenizer.impl;

import eu.modernmt.lang.Language2;
import eu.modernmt.processing.tokenizer.BaseTokenizer;
import eu.modernmt.processing.tokenizer.abbr.AbbreviationAnnotator;
import eu.modernmt.processing.tokenizer.jflex.annotators.CommonTermsTokenAnnotator;

import java.io.Reader;

public class GreekTokenizer extends BaseTokenizer {

    public GreekTokenizer() {
        super.annotators.add(AbbreviationAnnotator.getInstance(Language2.GREEK));
        super.annotators.add(new CommonTermsTokenAnnotator((Reader) null));
    }
}
