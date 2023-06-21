package com.dlnu.controller;

import com.dlnu.common.R;
import com.dlnu.service.InStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instore")
public class InStoreController {
    @Resource
    InStoreService inStoreService;

    @PostMapping("/add")
    public R<String> add(@RequestParam("intime") String intime,
                         @RequestParam("carid") String carid,
                         @RequestParam("inton") double inton,
                         @RequestParam("inpieces") int inpieces) {
        int inStore = inStoreService.createInStore(intime, carid, inton, inpieces);
        if (inStore == 1) {
            return R.success("入库成功");
        } else {
            return R.error(400, "入库失败");
        }
    }

    @PostMapping("/delete")
    public R<String> delete(@RequestParam("carid") String carid) {
        int inStore = inStoreService.deleteInStore(carid);
        if (inStore == 1) {
            return R.success("删除成功");
        } else {
            return R.error(400, "删除失败");
        }
    }
}
