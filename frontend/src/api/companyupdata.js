import request from "@/api/request";

export const Updatacompany = (params) => {
    return request({
        url:'/company/Updata',
        method:'POST',
        data:params
    })
}