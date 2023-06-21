package com.dlnu.service;

public interface InStoreService {
    int createInStore(String intime, String carid, double inton, int inpieces);
    int deleteInStore(String carid);
}
