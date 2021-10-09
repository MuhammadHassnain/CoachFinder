export default {


    setLoginUser(state, payload) {

    console.log("🚀 -----------------------------------------------------------------")
    console.log("🚀 : file: mutations.js : line 5 : setLoginUser : payload", payload)
    console.log("🚀 -----------------------------------------------------------------")

        
        state.loggedInUser = {
            token: payload.Authorization,
            userId: payload.userId,
            tokenExpiration: payload.tokenExpiration
        }
            
    },

    setUserInfo(state, payload) {
        console.log("🚀 -----------------------------------------------------------------")
        console.log("🚀 : file: mutations.js : line 20 : setUserInfo : payload", payload)
        console.log("🚀 -----------------------------------------------------------------")
        state.UserInfo = payload;
    }

};