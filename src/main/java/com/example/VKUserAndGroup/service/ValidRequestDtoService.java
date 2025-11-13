package com.example.VKUserAndGroup.service;

import com.example.VKUserAndGroup.dto.RequestDto;
import com.example.VKUserAndGroup.exception.EmptyVkGroupIdException;
import com.example.VKUserAndGroup.exception.EmptyVkUserIdException;
import org.springframework.stereotype.Service;

@Service
public class ValidRequestDtoService {

    public void valid(RequestDto dto){
        validUserId(dto.getUser_id());
        validGroupId(dto.getGroup_id());
    }

    public void validUserId(String userId){
        if (userId == null || userId.isEmpty()) throw new EmptyVkUserIdException("User ID is empty");
    }

    public void validGroupId(String groupId){
        if (groupId == null || groupId.isEmpty()) throw new EmptyVkGroupIdException("Group ID is empty");
    }
}
