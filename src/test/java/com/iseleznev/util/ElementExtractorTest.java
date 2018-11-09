package com.iseleznev.util;

import com.iseleznev.exception.ElementNotFoundException;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static com.iseleznev.util.ElementExtractor.findElementById;
import static com.iseleznev.util.ElementExtractor.findTargetElement;
import static org.assertj.core.api.Assertions.assertThat;

class ElementExtractorTest {

    private static final String ORIGINAL_FILE_PATH = "samples/sample-0-origin.html";

    private static final String ORIGINAL_ELEMENT_ID = "make-everything-ok-button";

    @Test
    void findElementByIdTest() {
        String originalElementCssSelector = "#" + ORIGINAL_ELEMENT_ID;
        Optional<Element> originalButtonOpt = findElementById(new File(ORIGINAL_FILE_PATH), ORIGINAL_ELEMENT_ID);
        String elementCssSelector = originalButtonOpt.map(Element::cssSelector).orElse("");
        assertThat(originalElementCssSelector).isEqualToIgnoringCase(elementCssSelector);
    }

    @Test
    void findTargetElementTest() {
        String outputPath = "samples/sample-1-evil-gemini.html";
        Optional<Element> originalButtonOpt = findElementById(new File(ORIGINAL_FILE_PATH), ORIGINAL_ELEMENT_ID);
        Element original = originalButtonOpt.orElseThrow(ElementNotFoundException::new);
        Element target = findTargetElement(new File(outputPath), original);
        assertThat(target).isNotNull();
    }
}