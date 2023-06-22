import request from "./request";

export const getcompanyName = (params) => {
    return request({
        url:'/company/name',
        method:'POST',
        data:params
    })
}
