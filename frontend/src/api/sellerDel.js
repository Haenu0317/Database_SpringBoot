import request from "@/api/request";

export const Delseller = (params) => {
    return request({
        url:'/seller/Del',
        method:'POST',
        data:params
    })
}