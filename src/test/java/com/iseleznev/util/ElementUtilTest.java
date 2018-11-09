package com.iseleznev.util;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static com.iseleznev.util.ElementExtractor.findElementById;
import static org.assertj.core.api.Assertions.assertThat;

public class ElementUtilTest {

    private static final String ORIGINAL_FILE_PATH = "samples/sample-0-origin.html";

    private static final String ORIGINAL_ELEMENT_ID = "make-everything-ok-button";

    @Test
    void getAbsolutePath() {
        String originalAbsolutePath = "html > body > div > div[5] > div[9] > div[1] > div > div[5]";
        Optional<Element> originalButtonOpt = findElementById(new File(ORIGINAL_FILE_PATH), ORIGINAL_ELEMENT_ID);
        String originalAbsolutePathFromElementUtil = originalButtonOpt.map(ElementUtil::getAbsolutePath).orElse("");
        assertThat(originalAbsolutePath).isEqualToIgnoringCase(originalAbsolutePathFromElementUtil);
    }

    @Test
    void getClassSelector() {
        String originalClassSelector = "a.btn.btn-success";
        Optional<Element> originalButtonOpt = findElementById(new File(ORIGINAL_FILE_PATH), ORIGINAL_ELEMENT_ID);
        String originalClassSelectorFromElementUtil = originalButtonOpt.map(ElementUtil::getClassSelector).orElse("");
        assertThat(originalClassSelector).isEqualToIgnoringCase(originalClassSelectorFromElementUtil);
    }

    @Test
    void getAttributeSelector() {
        String originalAttributeSelector = "a[class][href][title][rel][onclick]";
        Optional<Element> originalButtonOpt = findElementById(new File(ORIGINAL_FILE_PATH), ORIGINAL_ELEMENT_ID);
        String originalAttributeSelectorFromElementUtil = originalButtonOpt.map(ElementUtil::getAttributeSelector).orElse("");
        assertThat(originalAttributeSelector).isEqualToIgnoringCase(originalAttributeSelectorFromElementUtil);
    }

}
