import request from "@/api/request";

export const insbuyerdata = (params) => {
    return request({
        url:'/buyer/Ins',
        method:'POST',
        data:params
    })
}