package com.dlnu.service;

import com.dlnu.entity.user.InStore;

import java.util.List;

public interface InStoreService {
    int createInStore(String intime, String carid, double inton, int inpieces);

    int deleteInStore(String carid);

    int updateInStore(String intime, String carid, double inton, int inpieces);

    InStore findInStore(String carid);

    List<InStore> findAllInStore();
}
