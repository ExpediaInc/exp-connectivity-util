package com.expedia.eps.contentsystem.tcs.domain;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List of contents that are supported by TCS
 */
public enum Section {
    ACTIVITY,
    AFFINITY,
    DESTINATION,
    HOTEL,
    NEIGHBORHOOD,
    POI,
    SIMILAR_DESTINATION,
    TIP,
    ARTICLE;

    public static List<String> toList(EnumSet<Section> types) {
        return types.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

}
