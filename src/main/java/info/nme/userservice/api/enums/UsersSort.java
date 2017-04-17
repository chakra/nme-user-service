package info.nme.userservice.api.enums;

import info.nme.userservice.api.model.UserApiData;

/**
 * Enum to specify sort-by semantics for the user resource.
 *
 * @author Roland Krüger
 */
public enum UsersSort {
    USERNAME("username"), EMAIL("email"), FULLNAME("fullname");

    private String property;

    /**
     * @param property name of the {@link UserApiData} property to sort after
     */
    UsersSort(String property) {
        this.property = property;
    }

    /**
     * Resolves enum constant to domain type property.
     */
    public String getProperty() {
        return property;
    }
}
