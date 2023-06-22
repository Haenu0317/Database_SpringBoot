import request from "@/api/request";

export const Findallfreecompanydata = (params) => {
    return request({
        url:'/free/company',
        method:'POST',
        data:params
    })
}