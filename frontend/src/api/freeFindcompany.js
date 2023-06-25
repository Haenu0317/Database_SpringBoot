import request from "@/api/request";

export const Findallfreecompanydata = (params) => {
    return request({
        url:'/api/auth/company/free',
        method:'POST',
        data:params
    })
}