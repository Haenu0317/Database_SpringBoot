import request from "@/api/request";

export const Findallstoredata = (params) => {
    return request({
        url:'/store/Findall',
        data:params
    })
}