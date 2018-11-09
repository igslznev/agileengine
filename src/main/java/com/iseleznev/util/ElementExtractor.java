package com.iseleznev.util;

import com.iseleznev.exception.ElementNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.iseleznev.util.ElementUtil.*;

public class ElementExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementExtractor.class);


    private static final String CHARSET_NAME = "utf8";

    public static Optional<Element> findElementById(File htmlFile, String targetElementId) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.of(doc.getElementById(targetElementId));

        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
            return Optional.empty();
        }
    }

    public static Element findTargetElement(File htmlFile, Element element) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            LOGGER.info("Searching element by css selector {}", element.cssSelector());
            Elements result =  doc.select(element.cssSelector());
            if(!result.isEmpty()) {
                return result.first();
            }

            result = doc.select(getClassSelectorWithParent(element));
            if(!result.isEmpty()) {
                return result.first();
            }

            result = doc.select(getClassSelector(element));
            if(!result.isEmpty()) {
                return result.first();
            }

            result = doc.select(getAttributeSelectorWithParent(element));
            if(!result.isEmpty()) {
                return result.first();
            }

            result = doc.select(getAttributeSelector(element));
            if(!result.isEmpty()) {
                return result.first();
            }

            LOGGER.error("Target element has not been found!");
            throw new ElementNotFoundException();

        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
            return new Elements().first();
        }
    }
}
