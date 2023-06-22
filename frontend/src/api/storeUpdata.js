import request from "@/api/request";

export const updatastore = (params) => {
    return request({
        url:'/store/Updata ',
        method:'POST',
        data:params
    })
}