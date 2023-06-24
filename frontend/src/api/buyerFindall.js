import request from "@/api/request";

export const Findallbuyerdata = (params) => {
    return request({
        url:'/api/auth/outstore/findAll',
        methods:'GET',
        data:params
    })
}