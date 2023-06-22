import request from "@/api/request";

export const inscompanydata = (params) => {
    return request({
        url:'/company/Ins',
        method:'POST',
        data:params
    })
}