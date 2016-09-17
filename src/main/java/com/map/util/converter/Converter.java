package com.map.util.converter;

/**
 * @author Andrew Pasika
 */
public interface Converter<S, D> {

    D convert(S source);
}
