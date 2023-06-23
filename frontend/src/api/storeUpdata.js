import request from "@/api/request";

export const updatastore = (params) => {
    return request({
        url:'/api/auth/store/update',
        method:'POST',
        data:params
    })
}