import request from "@/api/request";

export const inscompanydata = (params) => {
    return request({
        url:'/api/auth/company/add',
        method:'POST',
        data:params
    })
}