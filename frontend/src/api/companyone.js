import request from "@/api/request";

export const getonecompany = (params) => {
    return request({
        url:'/api/auth/company/getcompany',
        method:'POST',
        data:params
    })
}