package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.

Their main functions are:
CrudRepository mainly provides CRUD functions.
PagingAndSortingRepository provides methods to do pagination and sorting records.
JpaRepository provides some JPA-related methods such as flushing the persistence context and deleting records in a batch.
Because of the inheritance mentioned above,  JpaRepository will have all the functions of CrudRepository and PagingAndSortingRepository.
So if you don't need the repository to have the functions provided by JpaRepository and PagingAndSortingRepository , use CrudRepository.
 */

@RepositoryRestResource(path = "locations") //todo 第二个微服务沿用过来
public interface LocationRepository extends JpaRepository<Location, Long> {


    Page<Location> findByRunnerMovementType(Location.RunnerMovementType movementType, Pageable pageable);

    Page<Location> findByUnitInfoRunningId(String runningId, Pageable pageable);
}
