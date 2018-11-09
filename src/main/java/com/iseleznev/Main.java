package com.iseleznev;

import com.iseleznev.exception.ElementNotFoundException;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.iseleznev.util.ElementExtractor.*;
import static com.iseleznev.util.ElementUtil.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    
    private static final String ORIGINAL_ID = "make-everything-ok-button";

    public static void main(String[] args) {
        final String originalFilePath = args[0];
        final String targetFilePath = args[1];
        final String originalElementId = args.length == 3 ? args[2] : ORIGINAL_ID;

        Optional<Element> buttonOpt = findElementById(new File(originalFilePath), originalElementId);
        Optional<String> stringifiedAttributesOpt = buttonOpt.map(button ->
                button.attributes().asList().stream()
                        .map(attr -> attr.getKey() + " = " + attr.getValue())
                        .collect(Collectors.joining(", "))
        );
        stringifiedAttributesOpt.ifPresent(attrs -> LOGGER.info("Original element attrs: [{}]", attrs));

        Element original = buttonOpt.orElseThrow(ElementNotFoundException::new);
        LOGGER.info("Absolute path for target: {}", getAbsolutePath(findTargetElement(new File(targetFilePath), original)));

    }
    
}
