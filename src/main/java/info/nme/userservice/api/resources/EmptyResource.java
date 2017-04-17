package info.nme.userservice.api.resources;

import info.nme.userservice.api._internal.AbstractResource;
import info.nme.userservice.api.model.EmptyApiData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;

/**
 * Resource class which does not manage any real resource but which maintain a list of links to other resources.
 *
 * @author Roland Krüger
 * @see EmptyApiData
 */
public class EmptyResource extends AbstractResource<EmptyApiData> {

    public EmptyResource(Link self) {
        super(self);
    }

    @Override
    protected ParameterizedTypeReference<EmptyApiData> getParameterizedTypeReference() {
        return new ParameterizedTypeReference<EmptyApiData>() {
        };
    }

    @Override
    protected Class<EmptyApiData> getResourceType() {
        return EmptyApiData.class;
    }
}
