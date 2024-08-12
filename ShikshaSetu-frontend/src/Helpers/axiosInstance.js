import axios from "axios";

// const BASE_URL = "http://localhost:7000/api/v1";
const BASE_URL = "https://localhost:8443";

const axiosInstance = axios.create();


axiosInstance.defaults.baseURL = BASE_URL;
// axiosInstance.defaults.withCredentials = true;


// const axiosInstance = axios.create({
//     baseURL: 'http://localhost:7000/api/v1',
//     headers: {
//         'Content-Type': 'application/json'
//     }
// });

// axiosInstance.interceptors.request.use((config) => {
//     const token = localStorage.getItem('token');
//     if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//     }
//     return config;
// }, (error) => {
//     return Promise.reject(error);
// });






export default axiosInstance;