export default {

    addRequest(state, payload) {
        console.log('request/addRequest');
        console.log('state', state)
        console.log('payload', payload);
        state.requests.push(payload);
    },

    setRequests(state, payload) {
        console.log("🚀 -----------------------------------------------------------------")
        console.log("🚀 : file: mutations.js : line 11 : setRequests : payload", payload)
        console.log("🚀 -----------------------------------------------------------------")
        console.log("🚀 -------------------------------------------------------------")
        console.log("🚀 : file: mutations.js : line 11 : setRequests : state", state)
        console.log("🚀 -------------------------------------------------------------")

        state.requests = payload;
    }
};