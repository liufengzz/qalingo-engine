/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.7.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2013
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.service;

import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

public interface GeolocService {

    String geolocAndGetCountryIsoCode(String customerRemoteAddr) throws Exception;
    
    Country geolocAndGetCountry(String customerRemoteAddr) throws Exception;
    
    String geolocAndGetCityName(String customerRemoteAddr) throws Exception;
    
    City geolocAndGetCity(String customerRemoteAddr) throws Exception;
    
}