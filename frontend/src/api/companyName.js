import request from "./request";

export const getcompanyName = (params) => {
    return request({
        url:'/api/auth/company/getcompany',
        method:'POST',
        data:params
    })
}
