import request from "@/api/request";

export const insstoredata = (params) => {
    return request({
        url:'/api/auth/store/add',
        method:'POST',
        data:params
    })
}