import request from "@/api/request";

export const findonesellerdata = (params) => {
    return request({
        url:'/seller/Findone',
        method:'POST',
        data:params
    })
}