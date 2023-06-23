import request from "@/api/request";

export const Findallstoredata = (params) => {
    return request({
        url:'/api/auth/store/findall',
        methods:'GET',
        data:params
    })
}