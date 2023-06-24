import request from "@/api/request";

export const delonebuyerdata = (params) => {
    return request({
        url:'/api/auth/outstore/del',
        method:'POST',
        data:params
    })
}