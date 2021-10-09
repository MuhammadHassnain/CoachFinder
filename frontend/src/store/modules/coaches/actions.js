export default {

    async registerCoach(context, data) {
        console.log(context);
        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("Authorization", "Bearer "+context.rootGetters.token);
        const coach = {
            // id:'c'+ (context.getters.coaches.length + 1).toString(),
            firstName: data.firstName,
            lastName: data.lastName,
            hourlyRate: data.rate,
            description: data.description,
            areas: data.areas
        }
        var requestOption = {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(coach)
        };

        const response = await fetch('http://localhost:9090/api/coaches/register', requestOption);

        if (!response.ok) {
            throw new Error("Failed to Register");
        }

        const responseData = await response.data;

        
        console.log(responseData);
        context.commit('registerCoach', {...responseData});
    },


    async loadCoaches(context,payload) {
        
        console.log("🚀 ---------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 37 : loadCoaches : context", context)
        console.log("🚀 ---------------------------------------------------------------")


        
        if (!payload.forceRefresh && !context.getters.shouldUpdate) { return;}
        const response = await fetch('http://localhost:9090/api/coaches');

        const responseData = await response.json();

        if (!response.ok) {
            const error = new Error(responseData.message || "Failed To Fetch")
            throw error;
        }
        const coaches = []
        for (const idx in responseData) {
            console.log(idx);
            const coach = {
                id: responseData[idx].id,
                firstName: responseData[idx].firstName,
                lastName: responseData[idx].lastName,
                hourlyRate: responseData[idx].hourlyRate,
                description: responseData[idx].description,
                areas: responseData[idx].areas
            }
            coaches.push(coach);
        }


        console.log("🚀 ---------------------------------------------------------------")
        console.log("🚀 : file: actions.js : line 72 : loadCoaches : coaches", coaches)
        console.log("🚀 ---------------------------------------------------------------")

        
        context.commit('setCoaches', coaches);
        context.commit('setFetchTimeStamp');

    }
};