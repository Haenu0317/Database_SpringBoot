import request from "@/api/request";

export const inssellerdata = (params) => {
    return request({
        url:'/api/auth/instore/add',
        method:'POST',
        data:params
    })
}