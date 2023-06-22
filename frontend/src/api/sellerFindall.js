import request from "@/api/request";

export const Findallsellerdata = (params) => {
    return request({
        url:'/seller/Findall',
        data:params
    })
}