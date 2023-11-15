package io.github.ralfspoeth.xml;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public record Element(Name name, List<Element> childNodes, Collection<Attr> attributes) {

    public Map<Name, String> attributeMap() {
        return attributes.stream().collect(toMap(Attr::name, Attr::value));
    }

}
