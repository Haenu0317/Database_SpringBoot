import request from "@/api/request";

export const Updatacompany = (params) => {
    return request({
        url:'/api/auth/company/update',
        method:'POST',
        data:params
    })
}