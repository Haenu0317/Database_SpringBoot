import request from "@/api/request";

export const findonesellerdata = (params) => {
    return request({
        url:'/api/auth/instore/findone',
        method:'POST',
        data:params
    })
}