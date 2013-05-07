package org.lacassandra.smooshyfaces;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StringCursor extends Cursor<String> {
    public static final String DISCRIMINATOR_VALUE = "SC";

    public StringCursor() {
        super(StringUtils.EMPTY, StringUtils.EMPTY);
        this.discriminator = DISCRIMINATOR_VALUE;
    }

    public StringCursor(final String current, final String next) {
        super(current, next);
        this.discriminator = DISCRIMINATOR_VALUE;
    }
}
