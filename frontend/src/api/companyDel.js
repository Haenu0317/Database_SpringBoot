import request from "@/api/request";

export const Delcompany = (params) => {
    return request({
        url:'/api/auth/company/del',
        method:'POST',
        data:params
    })
}