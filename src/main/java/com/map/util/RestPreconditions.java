package com.map.util;

import com.map.exception.ResourceNotFoundException;

/**
 * @author Yevhenii Semenov
 */
public class RestPreconditions {

    private RestPreconditions() {
        throw new RuntimeException("Utils class should not have instances");
    }

    public static <T> T checkFound(final T resource) {
        if(resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }
}
