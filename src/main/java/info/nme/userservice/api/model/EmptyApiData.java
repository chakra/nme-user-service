package info.nme.userservice.api.model;

import info.nme.userservice.api.resources.EmptyResource;
import info.nme.userservice.api._internal.model.AbstractBaseApiData;
import org.springframework.hateoas.Link;

/**
 * Empty API data class with no data fields. Used as dummy data type by {@link EmptyResource}s.
 *
 * @author Roland Krüger
 * @see EmptyResource
 */
public class EmptyApiData extends AbstractBaseApiData<EmptyResource> {
    @Override
    protected EmptyResource createNewResource(Link self) {
        return new EmptyResource(self);
    }
}
