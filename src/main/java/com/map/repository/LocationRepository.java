package com.map.repository;

import com.map.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YevheniiSemenov
 */
public interface LocationRepository extends JpaRepository<Place, Long> {
}
