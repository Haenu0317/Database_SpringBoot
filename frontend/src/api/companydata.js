import request from "./request";

export const getcompany = (params) => {
    return request({
        url:'/api/auth/company/find',
        method:'get',
        data:params
    })
}

