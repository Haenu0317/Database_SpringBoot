import request from "@/api/request";

export const findonebuyerdata = (params) => {
    return request({
        url:'/api/auth/outstore/findone',
        method:'POST',
        data:params
    })
}