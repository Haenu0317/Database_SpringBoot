import request from "@/api/request";

export const Findallsellerdata = (params) => {
    return request({
        url:'/api/auth/instore/findall',
        methods:'GET',
        data:params
    })
}