package com.dlnu;

import com.dlnu.entity.user.InStore;
import com.dlnu.entity.user.OutStore;
import com.dlnu.mapper.InStoreMapper;
import com.dlnu.util.Util;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DatabaseSpringBootApplicationTests {
    @Resource
    InStoreMapper mapper;


    @Test
    void contextLoads1() throws ParseException {
        List<InStore> allInStore = mapper.findAllInStore();
        String intime = allInStore.get(0).getIntime();
        String intime1 = allInStore.get(1).getIntime();
        int intervalDays = Util.getIntervalDays(intime, intime1);
        System.out.println(intervalDays);

    }

    @Test
    void contextLoads() {

    }


}
