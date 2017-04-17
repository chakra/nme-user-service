package info.nme.userservice.api.resources;

import info.nme.userservice.api._internal.AbstractResource;
import info.nme.userservice.api.model.EmptyApiData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

/**
 * @author Roland Krüger
 */
public class ConfirmRegistrationResource extends AbstractResource<EmptyApiData> {

    ConfirmRegistrationResource(Link self) {
        super(self);
    }

    public final ResponseEntity confirmRegistration() {
        return restTemplate.postForEntity(self.getHref(), null, ResponseEntity.class);
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
