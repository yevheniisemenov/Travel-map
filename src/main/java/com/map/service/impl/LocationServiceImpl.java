package com.map.service.impl;

import com.map.repository.LocationRepository;
import com.map.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yevhenii Semenov
 */
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
}
