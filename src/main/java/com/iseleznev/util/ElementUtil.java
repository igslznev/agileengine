package com.iseleznev.util;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class ElementUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementUtil.class);

    public static String getAbsolutePath(Element element) {
        StringBuilder absolutePath = new StringBuilder();
        Elements parents = element.parents();
        absolutePath.append(parents.last().tagName());
        for (int i = parents.size() - 2; i >= 0; i--) {
            Element parent = parents.get(i);
            absolutePath.append(" > ");
            if(!parent.siblingElements().is(parent.tagName())) {
                absolutePath.append(parent.tagName());
                continue;
            }
            absolutePath.append(parent.tagName())
                    .append("[")
                    .append(parent.siblingIndex())
                    .append("]");
        }
        return absolutePath.toString();
    }


    public static String getClassSelector(Element element) {
        StringBuilder selector = new StringBuilder(element.tagName());
        String classes = StringUtil.join(element.classNames(), ".");
        if (classes.length() > 0) {
            selector.append('.').append(classes);
        }
        LOGGER.info("Class selector for element {} : {}", element.cssSelector(), selector);
        return selector.toString();
    }

    public static String getClassSelectorWithParent(Element element) {
        return getParentSelector(element) + getClassSelector(element);
    }

    public static String getAttributeSelector(Element element) {
        StringBuilder selector = new StringBuilder(element.tagName());
        String attributes = "[" + StringUtil.join(element.attributes().asList()
                .stream().skip(1).map(Attribute::getKey).collect(Collectors.toList()), "][") + "]";
        if (attributes.length() > 0) {
            selector.append(attributes);
        }
        LOGGER.info("Attribute selector for element {} : {}", element.cssSelector(), selector);
        return selector.toString();
    }

    public static String getAttributeSelectorWithParent(Element element) {
        return getParentSelector(element) + getAttributeSelector(element);
    }

    private static String getParentSelector(Element element) {
        String selector = element.parent().cssSelector() + " > ";
        LOGGER.info("Parent selector for element {} : {}", element.cssSelector(), selector);
        return selector;
    }
}
