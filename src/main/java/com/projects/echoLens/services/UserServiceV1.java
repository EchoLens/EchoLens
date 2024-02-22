package com.projects.echoLens.services;


import com.projects.echoLens.dtos.RegisterRequestV1DTO;
import com.projects.echoLens.dtos.ResponseV1DTO;
import com.projects.echoLens.entity.EchoUserInfo;
import com.projects.echoLens.repository.EchoLensUserInfoReposity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
public class UserServiceV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceV1.class);
    @Autowired
    public EchoLensUserInfoReposity echoLensUserInfoReposity;
    public ResponseV1DTO userRegister(RegisterRequestV1DTO registerRequest) throws Exception {
        // hash password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ResponseV1DTO responseV1DTO = new ResponseV1DTO();
        try {
            String hashedPassword = encoder.encode(registerRequest.getPassword());

            EchoUserInfo echoUserInfoEntity = new EchoUserInfo();
            echoUserInfoEntity.setUsername(registerRequest.getUsername());
            echoUserInfoEntity.setPassword(registerRequest.getPassword());
            echoUserInfoEntity.setUseremail(registerRequest.getUseremail());
            echoLensUserInfoReposity.insert(echoUserInfoEntity,registerRequest.getUseremail());
        }
        catch (Exception e) {
            LOGGER.error("Exception occured in calling iConsent service POST", e);
        }

        return responseV1DTO;
    }
}
