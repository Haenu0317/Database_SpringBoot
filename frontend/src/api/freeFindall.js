import request from "@/api/request";

export const Findallfreedata = (params) => {
    return request({
        url:'/buyer/free',
        data:params
    })
}