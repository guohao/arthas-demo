/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taobao.arthas.demo.web;

import com.taobao.arthas.demo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Integer id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Find user by id: {}", id);
        }
        if (id == null) {
            throw new IllegalArgumentException("Id must not be  null");
        }
        if (id < 1) {
            // return new User(id, "name:" + id);
           throw new IllegalArgumentException("id < 1");
        }
        return new User(id, "name:" + id);
    }

    @GetMapping("/user/{id}/slow")
    public User findUserById2(@PathVariable Integer id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new User(id, "name:" + id);
    }

}