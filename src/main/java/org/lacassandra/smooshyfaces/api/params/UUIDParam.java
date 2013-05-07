package org.lacassandra.smooshyfaces.api.params;


import java.util.UUID;

public class UUIDParam extends Param<UUID> {
    protected UUIDParam() {

    }

    protected UUIDParam(final UUID uuid) {
        super(uuid);
    }

    public boolean isDefault() {
        return false;
    }

    public static UUIDParam valueOf(final String stringValue) {
        try {
            return new UUIDParam(UUID.fromString(stringValue));
        }
        catch (Exception e) {
            return DEFAULT_VALUE;
        }
    }

    public final static UUIDParam DEFAULT_VALUE = new UUIDParam() {
        @Override
        public boolean isDefault() {
            return true;
        }
    };
}
