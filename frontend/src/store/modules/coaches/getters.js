export default {

    coaches(state) {
        console.log('Coaches')
        console.log(state.coaches);
        return state.coaches
    },

    hasCoaches(state) {
        return state.coaches && state.coaches.length > 0;
    }

    ,
    isCurrentUser(_, getters, _2, rootGetters) {
       
        console.log('here')
        console.log(_, getters, _2, rootGetters);
        const userId = rootGetters.userId;
        console.log(userId);
        console.log('here')

        return getters.coaches.some(coach => coach.id === userId)

    },

    isCoach(_, _1, _2, rootGetters) {
        console.log("🚀 -------------------------------------------------------------------")
        console.log("🚀 : file: getters.js : line 27 : isCoach : rootGetters", rootGetters)
        console.log("🚀 -------------------------------------------------------------------")
        return rootGetters.UserInfo.id !== null;
    },

    
    shouldUpdate(state) {
        const lastFetch = state.lastFetch;
        if (!lastFetch) {
            return true;
        }
        const curr = new Date().getTime();
        return (curr-lastFetch)/1000 > 60;
    }

};