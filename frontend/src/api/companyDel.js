import request from "@/api/request";

export const Delcompany = (params) => {
    return request({
        url:'/company/Del',
        method:'POST',
        data:params
    })
}