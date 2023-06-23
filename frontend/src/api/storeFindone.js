import request from "@/api/request";

export const findonestoredata = (params) => {
    return request({
        url:'/api/auth/store/findone',
        method:'POST',
        data:params
    })
}