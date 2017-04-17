package info.nme.userservice.repository;

import info.nme.userservice.api._internal.RestApiConstants;
import info.nme.userservice.model.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
    Authority findByAuthority(@Param(RestApiConstants.AUTHORITY_PARAM) String authority);

    @RestResource(exported = false)
    @Override Iterable<Authority> findAll();
}
