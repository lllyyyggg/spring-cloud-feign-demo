package com.lanyage.eureka.consumerhello.service;

import com.lanyage.eureka.commonapi.service.UserApiService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("SERVICE-HELLO")
public interface UserService extends UserApiService {
}
