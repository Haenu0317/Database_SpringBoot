import request from "./request";

export const getcompany = (params) => {
    return request({
        url:'/company/find',
        data:params
    })
}

