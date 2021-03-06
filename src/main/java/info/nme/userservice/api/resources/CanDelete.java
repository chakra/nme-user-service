package info.nme.userservice.api.resources;

import info.nme.userservice.api._internal.AbstractPagedResource;
import info.nme.userservice.api._internal.model.AbstractBaseApiData;
import org.springframework.web.client.RestClientException;

/**
 * Interface to indicate that a paged resource ({@link AbstractPagedResource})
 * supports deleting existing entities via DELETE.
 *
 * @param <T> the entity type which must be a subclass of {@link AbstractBaseApiData}
 * @author Roland Krüger
 */
public interface CanDelete<T extends AbstractBaseApiData> {
    /**
     * Deletes an existing entity.
     *
     * @param entity entity to be deleted
     * @throws RestClientException when an error has occurred while communicating with the service
     */
    void delete(T entity) throws RestClientException;
}
