import request from "@/api/request";

export const insbuyerdata = (params) => {
    return request({
        url:'/api/auth/outstore/add',
        method:'POST',
        data:params
    })
}