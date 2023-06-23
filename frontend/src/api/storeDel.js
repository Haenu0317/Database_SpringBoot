import request from "@/api/request";

export const Delstore = (params) => {
    return request({
        url:'/api/auth/store/del',
        method:'DELETE',
        data:params
    })
}