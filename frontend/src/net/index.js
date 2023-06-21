import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error('发生错误')
const defaultFailure = (message) =>ElMessage.warning(message)
function post(url,data,success,failure=defaultFailure,error=defaultError){
    axios.post(url,data,{
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        //是否携带cookie
        withCredentials: true
        }).then(({data})=>{
            if (data.success){
                success(data.message,data.status)
            }else {
                failure(data.message,data.status)
            }
    }).catch(error)
}

function get(url,success,failure=defaultFailure,error=defaultError){
    axios.get(url,{
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        //是否携带cookie
        withCredentials: true
    }).then(({data})=>{
        if (data.success){
            success(data.message,data.status)
        }else {
            failure(data.message,data.status)
        }
    }).catch(error)

}

export {get,post}