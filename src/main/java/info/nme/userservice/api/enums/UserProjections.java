package info.nme.userservice.api.enums;

import info.nme.userservice.api.resources.UserResource;

/**
 * Enum that defines the available projections for the User resource.
 *
 * @author Roland Krüger
 * @see UserResource#useProjection(UserProjections)
 */
public enum UserProjections {

    /**
     * Projection that sends the complete data set for a user, including the user's list of authorities and the
     * hashed and salted password.
     */
    FULL_DATA(Values.USER_FULL_DATA),

    /**
     * Projection that sends an excerpt of a user's data set: user name, registration date, last login time, and
     * email address. Can be used to save bandwidth.
     */
    EXCERPT(Values.USER_EXCERPT_DATA);

    private String name;

    UserProjections(String name) {
        this.name = name;
    }

    /**
     * Returns the projection's name as defined by {@link UserProjections.Values}
     */
    public String getName() {
        return name;
    }

    /**
     * Projection names as used by the service.
     */
    public static class Values {
        public static final String USER_FULL_DATA = "full-data";
        public static final String USER_EXCERPT_DATA = "excerpt";
    }

}
