import request from "@/api/request";

export const findonestoredata = (params) => {
    return request({
        url:'/store/Findone',
        method:'POST',
        data:params
    })
}