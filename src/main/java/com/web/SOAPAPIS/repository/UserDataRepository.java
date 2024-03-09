package com.web.SOAPAPIS.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDataRepository {
    private  final EntityManager em;

    public Map<String,String> getAllUsers(){
        Map<String,String> users = new HashMap<>();
        Query query = em.createNativeQuery("select username , password  from user_data");
        List<Object[]> result = query.getResultList();
        for(Object[] obj : result){
            users.put(obj[0].toString(),obj[1].toString());
        }
        return users;


    }
}
