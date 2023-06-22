import request from "@/api/request";

export const delonebuyerdata = (params) => {
    return request({
        url:'/buyer/Del',
        method:'POST',
        data:params
    })
}