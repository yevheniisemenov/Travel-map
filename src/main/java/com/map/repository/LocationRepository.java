package com.map.repository;

import com.map.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YevheniiSemenov
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
