import request from "@/api/request";

export const Findallbuyerdata = (params) => {
    return request({
        url:'/buyer/Findall',
        data:params
    })
}