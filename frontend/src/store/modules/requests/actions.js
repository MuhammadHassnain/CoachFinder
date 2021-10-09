export default {

    async contactCoach(context, payload) {
        console.log('action: request/contactCoach');
        console.log(context);
        console.log(payload);

        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        var raw = JSON.stringify(
            {
                "coachId": payload.coachId,
                "email": payload.email,
                "message":payload.message
            }
        );
        var requestOptions = {
            method: 'POST',
            headers: headers,
            body: raw,
            redirect: 'follow'
        };
        
        const response = await fetch("http://localhost:9090/api/requests", requestOptions)

        
        if (!response.ok) {
            const error = new Error( "Can't Request now. Try Again Later");
            throw error;
        }
    },

    async loadRequest(context) {
        console.log("🚀 ---------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 14 : loadRequest : context", context)
        console.log("🚀 ---------------------------------------------------------------")
        
        var headers = new Headers();
        headers.append('authorization', "Bearer " + context.rootGetters.token);

        var reqOptions = {
            method: 'GET',
            headers: headers
        };

        const response = await fetch('http://localhost:9090/api/coaches/requests',reqOptions);
        
        const responseData = await response.json();
        console.log(responseData);
        if (!response.ok) {
            const error = new Error(responseData.message || "Error Fetching Request. Try Again Later");
            throw error;
        }
        const requests = [];
        for (const idx in responseData) {
            const request = {
                id: responseData[idx].id,
                email: responseData[idx].email,
                message: responseData[idx].message
            }
            requests.push(request);
        }
        console.log("🚀 -----------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 35 : loadRequest : requests", requests)
        console.log("🚀 -----------------------------------------------------------------")

        context.commit('setRequests', requests);


    }
};