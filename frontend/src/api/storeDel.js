import request from "@/api/request";

export const Delstore = (params) => {
    return request({
        url:'/store/Del',
        method:'POST',
        data:params
    })
}