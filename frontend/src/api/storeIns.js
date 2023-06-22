import request from "@/api/request";

export const insstoredata = (params) => {
    return request({
        url:'/store/Ins',
        method:'POST',
        data:params
    })
}