export default {


    async login(context, payload) {
             var headers = new Headers();
        headers.append("Content-Type", "application/json");
        const data = {
            // id:'c'+ (context.getters.coaches.length + 1).toString(),
            username: payload.username,
            password: payload.password
        }
        var requestOption = {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data)
        };


        const response = await fetch("http://localhost:9090/login", requestOption);
        
        if (response.ok) {
            const data = await response.json();
            localStorage.setItem("token", data.payload.Authorization);
            localStorage.setItem("userId", data.payload.userId);
            localStorage.setItem("tokenExpiration", data.payload.tokenExpiration);
            context.commit('setLoginUser', data.payload);
        } else if (response.status === 401) {
            
            const error = new Error("Failed to Login, Check Username or Password");
            throw error;
        } else {
            const responseData = await response.json();
            const error = new Error(responseData.error || "Failed to Authenticate");
            throw error;
        }
    },

    tryLogin(context) {

        
        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('userId');
        const expire = localStorage.getItem('tokenExpiration');

        console.log("🚀 --------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 39 : tryLogin : tryLogin()", context)
        console.log("🚀 --------------------------------------------------------------")


        if (token && userId && expire) {
            const payload = { 'Authorization': token, 'userId': userId, 'tokenExpiration': expire }
            context.commit('setLoginUser', payload);
            context.dispatch('getAuthDetail');
        }
    },

    async signup(context, payload) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        const data = {
            // id:'c'+ (context.getters.coaches.length + 1).toString(),
            email: payload.email,
            password: payload.password
        }
        var requestOption = {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data)
        };


        const response = await fetch("http://localhost:9090/signup", requestOption);

        const responseData = await response.json();

        if (!response.ok) {
            const error = new Error(responseData.errors.message || "Failed to Authenticate");
            throw error;
        }
    },

    async getAuthDetail(context) {
        console.log("🚀 -----------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 63 : getAuthDetail : context", context)
        console.log("🚀 -----------------------------------------------------------------")

        var myHeaders = new Headers();
        myHeaders.append("Authorization", "Bearer " + context.getters.token);

        var requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
        };

        const response = await fetch("http://localhost:9090/api/coaches/profile", requestOptions)

        if (response.status === 403) {
            context.commit('setUserInfo',
                {
                    id: null,
                    email: null,
                    firstName: null,
                    lastName: null,
                    description: null,
                    hourlyRate: null,
                    areas: null
                })
        } else if (response.status === 200) {
            const respData = await response.json();
            context.commit('setUserInfo',respData)
        } else {
            throw new Error('Failed To Fetch User Info');
        }

    },

    logout(context) {
        console.log("🚀 ----------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 99 : logout : context", context)
        console.log("🚀 ----------------------------------------------------------")
        
        context.commit('setUserInfo', {
            id: null,
            email: null,
            firstName: null,
            lastName: null,
            description: null,
            hourlyRate: null,
            areas: null
        }
        );
        context.commit('setLoginUser', {
            token: null,
            tokenExpiration: null,
            userId: null
        });

        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        localStorage.removeItem('tokenExpiration');

    }
};