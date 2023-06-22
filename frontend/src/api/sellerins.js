import request from "@/api/request";

export const inssellerdata = (params) => {
    return request({
        url:'/seller/Ins',
        method:'POST',
        data:params
    })
}