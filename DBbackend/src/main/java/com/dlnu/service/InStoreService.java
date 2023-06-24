package com.dlnu.service;

import com.dlnu.entity.user.InStore;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InStoreService {
    int CreateInStore(String inid, String intime, String incarid, double inton, int incasenum, String instoreid);

    int deleteInStore(String inid);

    int updateInStore(String inid, String intime, String incarid, double inton, int incasenum, String instoreid);

    InStore findInStore(String inid);

    List<InStore> findAllInStore();
}
