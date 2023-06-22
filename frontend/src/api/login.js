import request from "./request";

export const login = (username,password,remember) => {
    return request({
        url:'api/auth/login',
        method:'POST',
        data:{
            username: username,
            password: password,
            remember: remember
        }
    })
}
