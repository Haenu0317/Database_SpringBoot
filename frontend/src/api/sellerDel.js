import request from "@/api/request";

export const Delseller = (params) => {
    return request({
        url:'/api/auth/instore/del',
        method:'POST',
        data:params
    })
}