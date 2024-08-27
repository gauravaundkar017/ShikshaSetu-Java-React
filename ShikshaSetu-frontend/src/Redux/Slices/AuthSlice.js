import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {jwtDecode} from "jwt-decode"; // CommonJS module syntax for importing
import { toast } from "react-hot-toast";

import axiosInstance from "../../Helpers/axiosInstance";

const initialState = {
    isLoggedIn: JSON.parse(localStorage.getItem('isLoggedIn')) || false,
    role: localStorage.getItem('role') || "",
    data: (() => {
        try {
            return JSON.parse(localStorage.getItem('data')) || {};
        } catch {
            return {};
        }
    })(),
    token: localStorage.getItem('token') || ""
};

export const createAccount = createAsyncThunk("/auth/signup", async (data) => {
    try {
        const res = axiosInstance.post("user/register", data);
        toast.promise(res, {
            loading: "Wait! creating your account",
            success: (data) => {
                return data?.data?.message;
            },
            error: "Failed to create account"
        });
        return (await res).data;
    } catch (error) {
        toast.error(error?.response?.data?.message);
    }
});

export const login = createAsyncThunk("/auth/login", async (data) => {
    try {
        const res = axiosInstance.post("user/login", data);
        toast.promise(res, {
            loading: "Wait! authentication in progress...",
            success: (data) => {
                return data?.data?.message;
            },
            error: "Failed to log in"
        });
        return (await res).data;
    } catch (error) {
        toast.error(error?.response?.data?.message);
    }
});

export const logout = createAsyncThunk("/auth/logout", async () => {
    try {
        const res = axiosInstance.get("user/logout");
        toast.promise(res, {
            loading: "Wait! logout in progress...",
            success: (data) => {
                return data?.data?.message;
            },
            error: "Failed to log out"
        });
        return (await res).data;
    } catch (error) {
        toast.error(error?.response?.data?.message);
    }
});

export const updateProfile = createAsyncThunk("/user/update/profile", async (data) => {
    try {
        const res = axiosInstance.put(`user/update/${data[0]}`, data[1]);
        toast.promise(res, {
            loading: "Wait! profile update in progress...",
            success: (data) => {
                return data?.data?.message;
            },
            error: "Failed to update profile"
        });
        return (await res).data;
    } catch (error) {
        toast.error(error?.response?.data?.message);
    }
});

export const getUserData = createAsyncThunk("/user/details", async () => {
    try {
        const res = axiosInstance.get("user/me");
        return (await res).data;
    } catch (error) {
        toast.error(error.message);
    }
});

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(login.fulfilled, (state, action) => {
                console.log('Login action payload:', action.payload); // Log payload for debugging
                
                if (action.payload?.jwt) {
                    // Decode the JWT token to extract user information
                    const decodedToken = jwtDecode(action.payload.jwt);
                    console.log('Decoded Token:', decodedToken); // Log decoded token for debugging

                    // Store the JWT token and decoded user data in localStorage
                    localStorage.setItem("token", action.payload.jwt);
                    state.token = action.payload.jwt;

                    localStorage.setItem("data", JSON.stringify(decodedToken));
                    localStorage.setItem("isLoggedIn", true);
                    localStorage.setItem("role", decodedToken.role);

                    // Update the Redux state with the decoded user data
                    state.isLoggedIn = true;
                    state.data = decodedToken;
                    state.role = decodedToken.role;
                }
            })
            .addCase(logout.fulfilled, (state) => {
                // Clear localStorage and reset state on logout
                localStorage.clear();
                state.data = {};
                state.isLoggedIn = false;
                state.role = "";
                state.token = "";
            })
            .addCase(getUserData.fulfilled, (state, action) => {
                if (!action?.payload?.user) return;

                // Store user data in localStorage and update Redux state
                localStorage.setItem("data", JSON.stringify(action?.payload?.user));
                localStorage.setItem("isLoggedIn", true);
                localStorage.setItem("role", action?.payload?.user?.role);

                state.isLoggedIn = true;
                state.data = action?.payload?.user;
                state.role = action?.payload?.user?.role;
            });
    }
});

// export const {} = authSlice.actions;
export default authSlice.reducer;



// import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
// import { toast } from "react-hot-toast";

// import axiosInstance from "../../Helpers/axiosInstance"
// const initialState = {
//     isLoggedIn: JSON.parse(localStorage.getItem('isLoggedIn')) || false,
//     role: localStorage.getItem('role') || "",
//     data: (() => {
//         try {
//             return JSON.parse(localStorage.getItem('data')) || {};
//         } catch {
//             return {};
//         }
//     })(),
//     token: localStorage.getItem('token') || ""
//     // isLoggedIn: localStorage.getItem('isLoggedIn') || false,
//     // role: localStorage.getItem('role') || "",
//     // data: localStorage.getItem('data') != undefined ? JSON.parse(localStorage.getItem('data')) : {}
// };

// export const createAccount = createAsyncThunk("/auth/signup", async (data) => {
//     try {
//         const res = axiosInstance.post("user/register", data);
//         toast.promise(res, {
//             loading: "Wait! creating your account",
//             success: (data) => {
//                 return data?.data?.message;
//             },
//             error: "Failed to create account"
//         });
//         return (await res).data;
//     } catch(error) {
//         toast.error(error?.response?.data?.message);
        

//     }
// })

// export const login = createAsyncThunk("/auth/login", async (data) => {
//     try {
//         const res = axiosInstance.post("user/login", data);
//         toast.promise(res, {
//             loading: "Wait! authentication in progress...",
//             success: (data) => {
//                 return data?.data?.message;
//             },
//             error: "Failed to log in"
//         });
//         return (await res).data;
//     } catch(error) {
//         toast.error(error?.response?.data?.message);
//     }
// });

// export const logout = createAsyncThunk("/auth/logout", async () => {
//     try {
//         const res = axiosInstance.get("user/logout");
//         toast.promise(res, {
//             loading: "Wait! logout in progress...",
//             success: (data) => {
//                 return data?.data?.message;
//             },
//             error: "Failed to log out"
//         });
//         return (await res).data;
//     } catch(error) {
//         toast.error(error?.response?.data?.message);
//     }
// });

// export const updateProfile = createAsyncThunk("/user/update/profile", async (data) => {
//     try {
//         const res = axiosInstance.put(`user/update/${data[0]}`, data[1]);
//         toast.promise(res, {
//             loading: "Wait! profile update in progress...",
//             success: (data) => {
//                 return data?.data?.message;
//             },
//             error: "Failed to update profile"
//         });
//         return (await res).data;
//     } catch(error) {
//         toast.error(error?.response?.data?.message);
//     }
// })

// export const getUserData = createAsyncThunk("/user/details", async () => {
//     try {
//         const res = axiosInstance.get("user/me");
//         return (await res).data;
//     } catch(error) {
//         toast.error(error.message);
//     }
// })


// const authSlice = createSlice({
//     name: 'auth',
//     initialState,
//     reducers: {},
//     extraReducers: (builder) => {
//         builder
//         .addCase(login.fulfilled, (state, action) => {
//             console.log('Login action payload:', action.payload); // Log payload for debugging
//             if (action.payload?.jwt) {
//                 localStorage.setItem("token", action.payload.jwt);
//                 state.token = action.payload.jwt;
//             }
//             // localStorage.setItem("token", action?.payload?.jwt);  // Store JWT token
//             // state.token = action?.payload?.user?.jwt
//             if (action.payload?.user) {
//                 localStorage.setItem("data", JSON.stringify(action?.payload?.user));
//                 localStorage.setItem("isLoggedIn", true);
//                 localStorage.setItem("role", action?.payload?.user?.role);
//                 state.isLoggedIn = true;
//                 state.data = action?.payload?.user;
//                 state.role = action?.payload?.user?.role
//             }

//         })
//         .addCase(logout.fulfilled, (state) => {
//             localStorage.clear();
//             state.data = {};
//             state.isLoggedIn = false;
//             state.role = "";
//         })
//         .addCase(getUserData.fulfilled, (state, action) => {
//             if(!action?.payload?.user) return;
//             localStorage.setItem("data", JSON.stringify(action?.payload?.user));
//             localStorage.setItem("isLoggedIn", true);
//             localStorage.setItem("role", action?.payload?.user?.role);
//             state.isLoggedIn = true;
//             state.data = action?.payload?.user;
//             state.role = action?.payload?.user?.role
//         });
//     }
// });

// // export const {} = authSlice.actions;
// export default authSlice.reducer;