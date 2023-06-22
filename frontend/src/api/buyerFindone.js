import request from "@/api/request";

export const findonebuyerdata = (params) => {
    return request({
        url:'/buyer/Findone',
        method:'POST',
        data:params
    })
}